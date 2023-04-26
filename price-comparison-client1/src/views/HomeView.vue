<template>
    <div class="row" v-if="isAuthenticated">
        <div class="col-12 p-3">
            <div class="row">
                <div class="col-3">
                    <RouterLink :to="{ name: 'shop-create' }" class="text-dark">
                        Add store
                    </RouterLink>
                </div>
                <div class="col-3">
                    <RouterLink :to="{ name: 'product-import' }" class="text-dark">
                        Import products
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
                                <i class="bi bi-search"></i>
                            </button>
                            <button class="btn btn-outline-secondary" @click="showScanner=!showScanner" type="button">
                                <i class="bi bi-qr-code-scan"></i>
                            </button>
                        </div>
                    </div>
                    <div v-if="showScanner">
                        <barcode-scanner :qrbox="100" :fps="10" style="width: 200px;" @result="onScan"/>
                    </div>
                </div>
            </div>
            <div v-if="keyword.trim().length >= 3">
                Number of products found: {{ getProductList().length }}
                <div v-for="item of getProductList()" :key="item.id" class="border p-2 mb-4 row">
                    <div class="col-3">
                        <img src="https://placehold.co/60x60/EEE/31343C?font=playfair-display&text=Product" alt="product"/>
                    </div>
                    <div class="col-7">
                        <RouterLink :to="{ name: 'product-details', params: { id: item.id } }" class="text-dark">
                            {{ item.name }}
                        </RouterLink><br/>
                        <small>{{ item.barcode }}</small>
                    </div>
                    <div class="col-2">
                        <RouterLink :to="{ name: 'product-details', params: { id: item.id } }" class="text-dark">
                            {{ item.minPrice }}
                        </RouterLink>
                    </div>
                </div>
                <div v-if="getProductList().length === 0">
                    Product not found?
                    <RouterLink :to="{ name: 'product-create', params: { barcode: keyword } }" class="text-dark">
                        Add this product
                    </RouterLink>
                </div>

            </div>
            <div v-if="keyword.trim().length < 3">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <button @click="currentCategoryId=0" class="border-0">
                                Main category
                            </button>
                        </li>
                        <li class="breadcrumb-item" v-for="breadcrumb of getBreadcrumbs(currentCategoryId)"
                            :key="breadcrumb.id">
                            <button @click="currentCategoryId=breadcrumb.id" class="border-0">
                                {{ breadcrumb.name }}
                            </button>
                        </li>
                    </ol>
                </nav>
                <div v-for="item of getFilteredCategoryList(currentCategoryId)" :key="item.id"
                     class="border p-2 mb-4 row">
                    <div class="col-3">
                        <img src="https://placehold.co/60x60/EEE/31343C?font=playfair-display&text=Category" alt="category"/>
                    </div>
                    <div class="col-9">
                        <button @click="clickCategory(item.id)" class="border-0">
                            {{ item.name }}
                        </button>
                    </div>
                </div>
            </div>
            <div class="row fixed-bottom p-3">
                <div class="row fixed-bottom p-3 col-xl-4 col-lg-5 col-md-6 col-sm-7">
                    <div class="col-6">
                        <button @click="logoutClicked()" class="btn btn-secondary btn-lg w-100">
                            Logout
                        </button>
                    </div>
                    <div class="col-6">

                    </div>
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
import Logger from '@/util/logger'
import { useProductStore } from '@/stores/product'
import { useStatsStore } from '@/stores/stats'
import { IPublicStats } from '@/dal/domain/IPublicStats'
import { useCategoryStore } from '@/stores/category'
import { CategoryService } from '@/bll/service/CategoryService'
import { ICategory } from '@/dal/domain/ICategory'
import { IBreadcrumb } from '@/dal/domain/IBreadcrumb'
import { useIdentityStore } from '@/stores/identity'
import BarcodeScanner from '@/components/BarcodeScanner.vue'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: { BarcodeScanner },
    data () {
        return {}
    },
    methods: {
        onScan (decodedText: string, decodedResult: object) {
            this.logger.info('onscannn')
            this.keyword = decodedText
            this.searchProduct()
        }
    },
    props: {}
})
export default class HomeView extends Vue {
    private logger = new Logger(HomeView.name)
    private identityService = new IdentityService()
    private productService = new ProductService()
    private statsService = new StatsService()
    private categoryService = new CategoryService()
    private identityStore = useIdentityStore()
    private productStore = useProductStore()
    private statsStore = useStatsStore()
    private categoryStore = useCategoryStore()
    currentCategoryId = 0
    keyword = ''
    showScanner = false
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

        this.categoryService.getAll().then((items) => {
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

    getFilteredCategoryList (parentId: number) {
        return this.getCategoryList()
            .filter(element => typeof element !== 'undefined' && element.parentCategoryId === parentId)
    }

    getBreadcrumbs (categoryId: number): ICategory[] {
        const breadcrumbs = [] as IBreadcrumb[]
        if (!categoryId) {
            return breadcrumbs
        }

        let item = this.getCategoryList()
            .filter(element => typeof element !== 'undefined' && element.id === categoryId).shift()
        if (item) {
            breadcrumbs.push(item)
        }

        let parentId = item?.parentCategoryId ?? 0
        while (parentId > 0) {
            item = this.getCategoryList()
                .filter(element => typeof element !== 'undefined' && element.id === parentId).shift()
            if (item) {
                breadcrumbs.push(item)
            }

            parentId = item?.parentCategoryId ?? 0
        }

        return breadcrumbs.reverse()
    }

    clickCategory (categoryId: number) {
        this.currentCategoryId = categoryId
    }
}
</script>
