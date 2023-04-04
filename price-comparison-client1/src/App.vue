<template>
    <div class="container-fluid h-100" id="yellow">
        <main class="h-100">
            <router-view/>
        </main>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import router from '@/router/index'
import { useIdentityStore } from '@/stores/identity'
import Logger from '@/logger'

@Options({
    // don't forget to register imported components
    components: {},
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

</style>
