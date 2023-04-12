<template>
    <div class="row" v-if="isAuthenticated">
        <div class="col-12 p-3">
            <div class="row">
                <div class="col-6">
                    <RouterLink :to="{ name: 'shop-create' }" class="text-dark">
                        Add store
                    </RouterLink>
                </div>
                <div class="col-6">
                    <RouterLink :to="{ name: 'product-create' }" class="text-dark">
                        Add product
                    </RouterLink>
                </div>
            </div>
            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="input-group mb-3">
                        <input v-model="keyword" type="text" class="form-control" placeholder="Product barcode"
                               aria-label="Product title or barcode" @input="searchProduct">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" @click="searchProduct" type="button">
                                Search
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="keyword.trim().length >= 3">
                Number of products found: {{ getProductList().length }}
                <div v-for="item of getProductList()" :key="item.id" class="border p-2 mb-4 row">
                    <div class="col-6">
                        <img src="https://via.placeholder.com/50x50.png?text=product" alt="product"/>
                    </div>
                    <div class="col-6">
                        <h3>
                            <RouterLink :to="{ name: 'product-details', params: { id: item.id } }" class="text-dark">
                                {{ item.name }}
                            </RouterLink>
                        </h3>
                    </div>
                </div>
            </div>
            <div v-if="keyword.trim().length < 3">
                <div v-for="item of getCategoryList()" :key="item.id" class="border p-2 mb-4 row">
                    <div class="col-6">
                        <img src="https://via.placeholder.com/50x50.png?text=category" alt="category"/>
                    </div>
                    <div class="col-6">
                        <h3>
                            <RouterLink :to="{ name: 'category-details', params: { id: item.id } }" class="text-dark">
                                {{ item.name }}
                            </RouterLink>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="row fixed-bottom p-3">
                <div class="col-6">
                    <a @click="logoutClicked()" class="btn btn-secondary btn-lg w-100">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row" v-if="!isAuthenticated">
        <div class="col-12 p-3">
            <div class="row">
                <div class="col-6">
                    Number of products
                </div>
                <div class="col-6">
                    {{ getStats().numOfProducts }}
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    Number of price updates
                </div>
                <div class="col-6">
                    {{ getStats().numOfPriceUpdates }}
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    Number of users
                </div>
                <div class="col-6">
                    {{ getStats().numOfUsers }}
                </div>
            </div>
            <div class="row">
                <div class="col-12"></div>
            </div>
            <div class="row fixed-bottom p-3 col-xl-4 col-lg-5 col-md-6 col-sm-7">
                <div class="col-6">
                    <RouterLink :to="{ name: 'identity-login'}" class="btn btn-secondary btn-lg w-100">
                        Login
                    </RouterLink>
                </div>
                <div class="col-6">
                    <RouterLink :to="{ name: 'identity-register'}" class="btn btn-primary btn-lg w-100">
                        Get started
                    </RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import router from '@/router'
import { ProductService } from '@/bll/service/ProductService'
import { IProduct } from '@/dal/domain/IProduct'
import { StatsService } from '@/bll/service/StatsService'
import { IdentityService } from '@/bll/service/IdentityService'
import Logger from '@/logger'
import { useProductStore } from '@/stores/product'
import { useStatsStore } from '@/stores/stats'
import { IPublicStats } from '@/dal/domain/IPublicStats'
import { useCategoryStore } from '@/stores/category'
import { CategoryService } from '@/bll/service/CategoryService'
import { ICategory } from '@/dal/domain/ICategory'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    data () {
        return {}
    },
    methods: {},
    props: {}
})
export default class HomeView extends Vue {
    private logger = new Logger(HomeView.name)
    private identityService = new IdentityService()
    private productService = new ProductService()
    private statsService = new StatsService()
    private categoryService = new CategoryService()
    private productStore = useProductStore()
    private statsStore = useStatsStore()
    private categoryStore = useCategoryStore()
    keyword = ''
    errorMsg: string | null = null

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }

    async logoutClicked (): Promise<void> {
        this.logger.info('trying to logout')
        await this.identityService.logout()
        await router.push('/')
    }

    searchProduct () {
        this.logger.info('searchShop')

        if (this.keyword.trim().length < 3) {
            this.productStore.$reset()
            return
        }

        this.productService.findByName(this.keyword).then((items) => {
            this.logger.info('found something')
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    this.productStore.$state.products = items.data
                }
            }
        })
    }

    mounted (): void {
        this.logger.info('mounted')

        this.statsService.getPublicStats().then((item) => {
            this.logger.info('found something')
            if (item.errorMsg !== undefined) {
                this.errorMsg = item.errorMsg
            } else {
                if (item.data) {
                    this.statsStore.$state.publicStats = item.data
                }
            }
        })

        this.categoryService.getAllByCategoryId(null).then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    this.categoryStore.$state.categories = items.data
                }
            }
        })
    }

    getProductList (): IProduct[] {
        return this.productStore.$state.products
    }

    getStats (): IPublicStats {
        return this.statsStore.$state.publicStats
    }

    getCategoryList (): ICategory[] {
        return this.categoryStore.$state.categories
    }
}
</script>
