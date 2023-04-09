<template>
    <div class="row">
        <div class="col-2 p-4">
            <div v-if="isAdmin">
                For Admin:
                <RouterLink :to="{ name: 'products-details', params: { id: id } }">Product details</RouterLink>
                |
                <RouterLink :to="{ name: 'products-edit', params: { id: id } }">Edit product</RouterLink>
                |
                <RouterLink :to="{ name: 'products-delete', params: { id: id } }">Delete product</RouterLink>
            </div>
        </div>
        <div class="col-10 p-4">
            <div class="row">
                <div class="col-md-4">
                    <img src="https://via.placeholder.com/350x200.png?text=product" class="img-fluid" alt=""/>
                </div>
                <div class="col-md-8">
                    <a href="#" @click="clickToAddProductToWishlist()">Add product to wishlist</a>
                    <div>
                        <h3>{{ product.name }}</h3>
                        <p>
                            Description: {{ product.description }}
                        </p>
                    </div>
                </div>
            </div>

            <div class="p-4">
                <h4>Merch</h4>
                <div v-for="item of merchandises" :key="item.id" class="row border">
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
import { Options, Vue } from 'vue-class-component'
import vue3StarRatings from 'vue3-star-ratings'
import { ProductService } from '@/services/ProductService'
import { OfferService } from '@/services/OfferService'
import { useProductStore } from '@/stores/product'
import { useIdentityStore } from '@/stores/identity'
import { IProduct } from '@/domain/IProduct'
import { IOffer } from '@/domain/IOffer'
import { ReviewService } from '@/services/ReviewService'
import { WatchlistService } from '@/services/WatchlistService'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
        vue3StarRatings
    },
    props: {
        id: String,
        name: String,
        description: String,
        rating: Number,
    },
    emits: [],
})
export default class ProductDetails extends Vue {
    id = ''

    private logger = new Logger(ProductDetails.name)

    private productsStore = useProductStore()
    private productService = new ProductService()
    private merchandiseService = new OfferService()
    private ratingService = new ReviewService()
    private wishService = new WatchlistService()
    private identityStore = useIdentityStore()

    merchandises: IOffer[] = []
    product: IProduct = {
        barcode: '',
        name: '',
        description: ''
    }

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    get isAdmin (): boolean {
        return this.identityStore.isAdmin()
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.product =
        //     await this.productService.get(this.id)
        this.merchandises =
            await this.merchandiseService.getAllByProductId()
    }

    async clickToAddProductToWishlist (): Promise<void> {
        await this.wishService.addProductToWishlist()
    }

    async clickToAddRating (): Promise<void> {
        /*
        const res = await this.ratingService.addMerchandiseRating(id)
        this.logger.info(res)
        */
    }
}

</script>
