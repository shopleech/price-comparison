<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h2>View Product details</h2>
    <div class="row">
        <div class="col-md-12">

        </div>
        <div class="col-10 p-4">
            <div class="row">
                <div class="col-md-4">
                    <img src="https://via.placeholder.com/350x200.png?text=product" class="img-fluid" alt=""/>
                </div>
                <div class="col-md-8">
                    <a href="#" @click="clickToAddProductToWishlist()">Add product to wishlist</a>
                    <div>
                        <h3>{{ getProduct().name }}</h3>
                        <p>
                            Description: {{ getProduct().description }}
                        </p>
                    </div>
                </div>
            </div>

            <div class="p-4">
                <h4>Merch</h4>
                <div v-for="item of getOffers()" :key="item.id" class="row border">
                    <div class="col-md-2 p-2">
                        <img src="https://via.placeholder.com/100x50.png?text=company" class="img-fluid" alt=""/>
                    </div>
                    <div class="col-md-3 p-2">
                        {{ item.name }}
                    </div>
                    <div class="col-md-5">
                        <a href="#" @click="clickToAddRating(item.id)">Add rating</a>
                    </div>
                    <div class="col-md-2 p-2">
                        €{{ item.lastPrice }}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { ProductService } from '@/bll/service/ProductService'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'
import { IShop } from '@/dal/domain/IShop'
import { useShopStore } from '@/stores/shop'
import { ShopService } from '@/bll/service/ShopService'
import { useIdentityStore } from '@/stores/identity'
import { useProductStore } from '@/stores/product'
import DistanceUtil from '@/util/distance-util'

/**
 * @author Ahto Jalak
 * @since 15.04.2023
 */
@Options({
    components: {},
    props: {
        id: Number,
    },
    emits: [],
})
export default class ProductImport extends Vue {
    id!: number

    private logger = new Logger(ProductImport.name)
    private productService = new ProductService()
    private offerService = new ProductService()
    private shopService = new ShopService()
    private shopStore = useShopStore()
    private identityStore = useIdentityStore()
    private productStore = useProductStore()

    productId = 0
    errorMsg: string | null = null

    submitClicked (): void {
        this.logger.info('submitClicked')
    }

    mounted (): void {
        this.logger.info('mounted')
        const distanceUtil = new DistanceUtil()

        this.productService.getById(this.id).then((item) => {
            if (item.errorMsg !== undefined) {
                this.errorMsg = item.errorMsg
            } else {
                if (item.data) {
                    this.productStore.$state.product = item.data
                }
            }
        })

        this.productService.getOfferListById(this.id).then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    this.productStore.$state.offers = items.data
                }
            }
        })

        this.shopService.getAll().then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                const uniqueNodes = items.data
                if (uniqueNodes) {
                    for (let i = 0; i < uniqueNodes.length; i++) {
                        uniqueNodes[i].distance = distanceUtil.calculateDistance(
                            this.identityStore.getCoords().latitude ?? 59.436962,
                            this.identityStore.getCoords().longitude ?? 24.753574,
                            uniqueNodes[i].latitude ?? 59.436962,
                            uniqueNodes[i].longitude ?? 24.753574,
                            "K"
                        );
                    }

                    uniqueNodes.sort(function(a, b) {
                        return (a.distance ?? 0) - (b.distance ?? 0);
                    });

                    this.shopStore.$state.shops = uniqueNodes
                }
            }
        })
    }

    getShopList (): IShop[] {
        return this.shopStore.$state.shops
    }

    getProduct() {
        return this.productStore.$state.product;
    }

    getOffers() {
        return this.productStore.$state.offers;
    }
}
</script>
