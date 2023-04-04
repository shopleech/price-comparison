<template>
    <div class="row" v-if="isAuthenticated">
        <div class="col-12 p-3">
            <h3>Price comparison app</h3>
            <hr/>
            <div class="row">
                <div class="col-6">
                    <RouterLink :to="{ name: 'shop-create' }" class="text-dark">
                        Add store
                    </RouterLink>
                </div>
                <div>
                    Import prices in bulk
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="input-group mb-3">
                        <input v-model="keyword" type="text" class="form-control" placeholder="Product barcode"
                               aria-label="Product title or barcode" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" @click="searchProductClicked" type="button">
                                Search
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div v-for="item of products" :key="item.id" class="border p-2 mb-4 row">
                <div class="col-3">
                    <img src="https://via.placeholder.com/50x50.png?text=product" alt="product"/>
                </div>
                <div class="col-6">
                    <h3>
                        <RouterLink :to="{ name: 'products-details', params: { id: item.id } }" class="text-dark">
                            {{ item.name }}
                        </RouterLink>
                    </h3>
                </div>
            </div>
            <div class="row fixed-bottom p-3">
                <div class="col-6 col-md-2">
                    <a @click="logoutClicked()" class="btn btn-secondary btn-lg w-100">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row" v-if="!isAuthenticated">
        <div class="col-12 p-3">
            <h3>Price comparison app</h3>
            <hr/>
            <div class="row">
                <div class="col-8 hidden-md-down col-md-2">
                    Number of products
                </div>
                <div class="col-4 col-md-2">
                    {{ numOfProducts }}
                </div>
            </div>
            <div class="row">
                <div class="col-8 col-md-2">
                    Number of price updates
                </div>
                <div class="col-4 col-md-2">
                    {{ numOfPriceUpdates }}
                </div>
            </div>
            <div class="row">
                <div class="col-8 col-md-2">
                    Number of users
                </div>
                <div class="col-4 col-md-2">
                    {{ numOfUsers }}
                </div>
            </div>
            <div class="row">
                <div class="col-12"></div>
            </div>
            <div class="row fixed-bottom p-3">
                <div class="col-6 col-md-2">
                    <RouterLink :to="{ name: 'identity-login'}" class="btn btn-secondary btn-lg w-100">
                        Login
                    </RouterLink>
                </div>
                <div class="col-6 col-md-2">
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
import { ProductService } from '@/services/ProductService'
import { IProduct } from '@/domain/IProduct'
import { ISearchItem } from '@/domain/ISearchItem'
import { IPublicStats } from '@/domain/IPublicStats'
import { StatsService } from '@/services/StatsService'
import { IdentityService } from '@/services/IdentityService'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    data () {
        return {
            keyword: '',
        }
    },
    methods: {},
    props: {}
})
export default class HomeView extends Vue {
    private logger = new Logger(HomeView.name)

    private identityService = new IdentityService()

    private productService = new ProductService()

    private statsService = new StatsService()

    private keyword = ''
    private numOfProducts: number | undefined = 0
    private numOfPriceUpdates: number | undefined = 0
    private numOfUsers: number | undefined = 0

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }

    async logoutClicked (): Promise<void> {
        this.logger.info('trying to logout')
        await this.identityService.logout()
        await router.push('/')
    }

    products: IProduct[] | null = null
    publicStats: IPublicStats | null = null

    async searchProductClicked (): Promise<void> {
        this.products =
            await this.productService.getAllByKeyword({ keyword: this.keyword } as ISearchItem)
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')

        this.publicStats = await this.statsService.getPublicStats()

        this.numOfProducts = this.publicStats?.numOfProducts
        this.numOfPriceUpdates = this.publicStats?.numOfPriceUpdates
        this.numOfUsers = this.publicStats?.numOfUsers
    }
}
</script>
