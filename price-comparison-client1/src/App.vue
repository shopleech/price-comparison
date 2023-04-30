<template>
    <div class="container-fluid h-100 row">
        <main class="h-100 col-xl-4 col-lg-5 col-md-6 col-sm-7 bg-white">
            <router-view/>
        </main>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import router from '@/router/index'
import { useIdentityStore } from '@/stores/identity'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
    },
})
export default class App extends Vue {
    private logger = new Logger(App.name)
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
                this.logger.info('token refresh')
            }
        })
        router.push('/')
    }
}
</script>

<style>
@import './assets/base.css';

html, body {
    height: 100%;
}

#app {
    background-color: whitesmoke;
    height: 100%;
}
.main-border {
    border: 1px solid silver;
}
</style>
