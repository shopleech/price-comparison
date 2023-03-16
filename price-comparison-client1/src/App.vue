<template>
    <header class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <RouterLink :to="{ name: 'home' }">
                    <img src="https://via.placeholder.com/200x50.png?text=H%20O%20M%20E" alt="Price Comparison"/>
                </RouterLink>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <router-link class="nav-link" to="/">Category</router-link>
                        </li>
                        <li class="nav-item" v-if="isAuthenticated">
                            <router-link class="nav-link" to="/merchandises">Merchandise</router-link>
                        </li>
                        <li class="nav-item" v-if="isAuthenticated">
                            <router-link class="nav-link" to="/wishes">Wishlist</router-link>
                        </li>
                        <li class="nav-item" v-if="isAuthenticated">
                            <router-link class="nav-link" to="/alarms">Alarms</router-link>
                        </li>
                        <li class="nav-item" v-if="isAuthenticated">
                            <router-link class="nav-link" to="/ratings">Ratings</router-link>
                        </li>
                        <li class="nav-item" v-if="!isAuthenticated">
                            <router-link class="nav-link" to="/identity/register">Register</router-link>
                        </li>
                        <li class="nav-item" v-if="!isAuthenticated">
                            <router-link class="nav-link" to="/identity/login">Login</router-link>
                        </li>
                        <li class="nav-item" v-if="isAuthenticated">
                            <a class="nav-link fa-mercury" @click="refreshTokenClicked()">Token refresh</a>
                        </li>
                        <li class="nav-item" v-if="isAuthenticated">
                            <a class="nav-link" @click="logoutClicked()">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container">
        <main>
            <div v-if="!isAuthenticated" class="p-4 bg-info">
                In order to see this page correctly you'll have to log in or register please!
            </div>
            <div class="p-4">
                <router-view/>
            </div>
        </main>
    </div>
    <footer class="small">
        <hr/>
        <div class="small p-2">
            <router-link to="/api/about">About</router-link>
        </div>
    </footer>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import router from '@/router/index'
import { useIdentityStore } from '@/stores/identity'

@Options({
    // don't forget to register imported components
    components: {
    },
})
export default class App extends Vue {
    private identityStore = useIdentityStore()

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    logoutClicked (): void {
        this.identityStore.clearJwt()
        router.push('/')
    }

    refreshTokenClicked (): void {
        this.identityStore.refreshUser().then(async value => {
            if (value) {
                console.log('token refresh')
            }
        })
        router.push('/')
    }
}
</script>

<style>
@import './assets/base.css';
</style>
