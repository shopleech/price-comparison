<template>
    <Header v-if="isAuthenticated" title="" back="home"/>

    <div class="row">
        <div class="col-4">
            <button @click="clickAddReview(getProduct().id)" class="small"
                    :style="{backgroundColor: reviewIsActive(getProduct().id) ? 'green' : 'white'}">
                <i class="bi bi-star"></i> lisa hinnang
            </button>
        </div>
        <div class="col-4">
            <button @click="clickAddBookmark(getProduct().id)" class="small"
                    :style="{backgroundColor: bookmarkIsActive(getProduct().id) ? 'green' : 'white'}">
                <i class="bi bi-bookmark-plus"></i> lisa nimekirja
            </button>
        </div>
        <div class="col-4">
            <button @click="clickAddOffer(getProduct().id)" class="small">
                <i class="bi bi-database-add"></i> lisa pakkumine
            </button>
        </div>
    </div>
    <div class="row">
        <div class="col-3">Pilt</div>
        <div class="col-9">
            <img src="https://placehold.co/50x50/EEE/31343C?font=playfair-display&text=Product" alt="product"/>
        </div>
    </div>
    <div class="row">
        <div class="col-3">Nimi</div>
        <div class="col-9">
            {{ getProduct().name }}
        </div>
    </div>
    <div class="row">
        <div class="col-3">Triipkood</div>
        <div class="col-9">{{ getProduct().barcode }}</div>
    </div>
    <div class="row">
        <div class="col-3">Kirjeldus</div>
        <div class="col-9">{{ getProduct().description }}</div>
    </div>

    <h4>Pakkumised</h4>
    <div v-for="item of getOffersDetails()" :key="item.id" class="border" @click="setItemDetails(item.id)">
        <div class="row">
            <div class="col-2">
                <img :src="getShopImageByType(item.shop.url)" alt="" width="32" height="32"/>
            </div>
            <div class="col-7">
                <div>{{ item.name }}</div>
                <div>{{
                        distanceUtil.round(
                            distanceUtil.calculateDistance(
                                this.identityStore.getCoords().latitude ?? 59.436962,
                                this.identityStore.getCoords().longitude ?? 24.753574,
                                item.latitude, item.longitude))
                    }}km
                </div>
            </div>
            <div class="col-md-2 p-2">
                €{{ item.offers.sort((a, b) => a.price.amount - b.price.amount)[0].price.amount }}
            </div>
        </div>
        <div class="bg-light" v-if="showItemDetails(item.id)">
            <div class="row" v-for="subItem of item.offers" :key="subItem.id">
                <div class="col-8">
                    <div>Toote nimi: {{ subItem.name }}</div>
                    <div>Lisatud: unknown</div>
                </div>
                <div class="col-4">
                    €{{ subItem.price.amount }}
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
import { IdentityService } from '@/bll/service/IdentityService'
import Header from '@/components/Header.vue'
import { WatchlistService } from '@/bll/service/WatchlistService'
import router from '@/router'
import { IWatchlist } from '@/dal/domain/IWatchlist'
import { IOfferResults } from '@/dal/domain/IOfferResults'
import { IOffer } from '@/dal/domain/IOffer'
import { useWatchlistStore } from '@/stores/watchlist'
import { IReview } from '@/dal/domain/IReview'
import { useReviewStore } from '@/stores/review'

/**
 * @author Ahto Jalak
 * @since 15.04.2023
 */
@Options({
    components: {
        Header,
    },
    props: {
        id: Number,
    },
    emits: [],
})
export default class ProductDetails extends Vue {
    id!: number

    private logger = new Logger(ProductDetails.name)
    private productService = new ProductService()
    private offerService = new ProductService()
    private shopService = new ShopService()
    private watchlistService = new WatchlistService()
    private shopStore = useShopStore()
    private identityStore = useIdentityStore()
    private productStore = useProductStore()
    private watchlistStore = useWatchlistStore()
    private reviewStore = useReviewStore()
    private identityService = new IdentityService()
    distanceUtil = new DistanceUtil()

    productId = 0
    errorMsg: string | null = null
    private itemDetails: any = []

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
                            'K'
                        )
                    }

                    uniqueNodes.sort(function (a, b) {
                        return (a.distance ?? 0) - (b.distance ?? 0)
                    })

                    this.shopStore.$state.shops = uniqueNodes
                }
            }
        })
    }

    getShopList (): IShop[] {
        return this.shopStore.$state.shops
    }

    getProduct () {
        return this.productStore.$state.product
    }

    getOffers () {
        return this.productStore.$state.offers
    }

    getOffersDetails () {
        const offers = [] as IOfferResults[]
        const shops = []

        for (let i = 0; i < this.getOffers().length; i++) {
            const item = this.getOffers()[i] as IOffer
            if (item.shopId && shops.indexOf(item.shopId) === -1) {
                shops.push(item.shopId)
                const shop = item.shop as IShop
                offers.push({
                    id: shop.id,
                    name: shop.name,
                    latitude: shop.latitude,
                    longitude: shop.longitude,
                    offers: this.getOffers().filter(o => {
                        return o.shopId === shop.id
                    })
                } as IOfferResults)
            }
        }
        this.logger.info(offers.length.toString())

        return offers
    }

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }

    setItemDetails (id: number) {
        const x = this.itemDetails.indexOf(id)
        if (x === -1) {
            this.itemDetails.push(id)
            return true
        } else {
            this.itemDetails.splice(this.itemDetails.indexOf(id))
            return false
        }
    }

    showItemDetails (id: number) {
        return this.itemDetails.indexOf(id) !== -1
    }

    clickAddReview (id: number) {
        router.push('/review/create/' + id)
    }

    clickAddBookmark (id: number) {
        const entity = {
            productId: id,
        } as IWatchlist
        this.watchlistService.add(entity).then((item) => {
            if (item.errorMsg !== undefined) {
                this.errorMsg = item.errorMsg
            } else {
                if (item.data) {
                    this.watchlistStore.add(item.data)
                }
            }
        })
    }

    clickAddOffer (id: number) {
        this.logger.info(id.toString())
        router.push('/offer/create/' + id)
    }

    bookmarkIsActive (id: number) {
        this.logger.info('bookmarkIsActive')
        for (let i = 0; i < this.watchlistStore.watchlistCount; i++) {
            const x = this.watchlistStore.$state.watchlists[i] as IWatchlist
            if (id === x.productId) {
                return true
            }
        }

        return false
    }

    reviewIsActive (id: number) {
        this.logger.info('reviewIsActive')
        for (let i = 0; i < this.reviewStore.reviewCount; i++) {
            const x = this.reviewStore.$state.reviews[i] as IReview
            if (id === x.productId) {
                return true
            }
        }

        return false
    }

    getCategoryImageByType (id: string) {
        return `https://price-comparison-images.s3.eu-west-1.amazonaws.com/category/${id}.png`
    }

    getShopImageByType (id: string) {
        return `https://price-comparison-images.s3.eu-west-1.amazonaws.com/shop/${id}`
    }

    getProductImageByBarcode (id: string) {
        return `https://price-comparison-images.s3.eu-west-1.amazonaws.com/product/${id}.png`
    }
}
</script>
