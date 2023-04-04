<template>
    <div class="row" v-if="isAuthenticated">
        <div class="col-12 p-3">
            <h3>Price comparison app</h3>
            <hr/>
            <div v-if="errorMsg != null" className="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div>
                <div className="form-group">
                    <label className="control-label" htmlFor="email">Shop name</label>
                    <input v-model="shopName" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="firstname">Latitude</label>
                    <input v-model="latitude" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="lastname">Longitude</label>
                    <input v-model="longitude" className="form-control" type="text"/>
                </div>
                <div class="row fixed-bottom p-3">
                    <div class="col-6 col-md-2">
                        <a @click="logoutClicked()" class="btn btn-secondary btn-lg w-100">Logout</a>
                    </div>
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
import { useIdentityStore } from '@/stores/identity'
import { ProductService } from '@/services/ProductService'
import { useProductStore } from '@/stores/product'
import { IShop } from '@/domain/IShop'
import Logger from '@/logger'

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
export default class ShopAdd extends Vue {
    private logger = new Logger(ShopAdd.name)
    private identityStore = useIdentityStore()
    private productStore = useProductStore()
    private productService = new ProductService()
    private shopName = ''
    private latitude = ''
    private longitude = ''

    shop: IShop | null = null

    async addStoreClicked (): Promise<void> {
        /*
        this.shop =
            await this.productService.addStore({
                shopName: this.shopName,
                latitude: this.latitude,
                longitude: this.longitude
            } as IShop)
        */
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
    }
}
</script>
