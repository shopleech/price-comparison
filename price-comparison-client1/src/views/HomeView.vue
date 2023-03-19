<template>
    <div class="row" v-if="isAuthenticated">
        <div class="col-12 p-3">
            <h3>Price comparison app</h3>
            <hr/>
            <div class="row">
                <div class="col-12">

                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <form>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Product barcode"
                                   aria-label="Product title or barcode" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button">Search</button>
                            </div>
                        </div>
                    </form>
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
                    {{ num_of_products }}
                </div>
            </div>
            <div class="row">
                <div class="col-8 col-md-2">
                    Number of price updates
                </div>
                <div class="col-4 col-md-2">
                    {{ num_of_price_updates }}
                </div>
            </div>
            <div class="row">
                <div class="col-8 col-md-2">
                    Number of users
                </div>
                <div class="col-4 col-md-2">
                    {{ num_of_users }}
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
import router from '@/router'

@Options({
    components: {},
    data () {
        return {
            num_of_products: 0,
            num_of_price_updates: 0,
            num_of_users: 0,
        }
    },
    methods: {},
    props: {}
})
export default class AboutView extends Vue {
    private identityStore = useIdentityStore()

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    logoutClicked (): void {
        this.identityStore.clearJwt()
        router.push('/')
    }
}
</script>
