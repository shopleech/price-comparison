<template>
    <header>
        <div class="row">
            <div class="col-6">
                <RouterLink :to="{ name: 'home' }">
                    <img src="https://placehold.co/200x60/EEE/31343C?font=playfair-display&text=Price%20Comparison" alt=""/>
                </RouterLink>
            </div>
            <div class="col-6">
                My location: {{ getCoords().latitude }} {{ getCoords().longitude }}
                <button @click="clickGeolocation"><i class="bi bi-geo-alt"></i></button>
            </div>
        </div>
    </header>
</template>

<script>
import { Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export default class Header extends Vue {
    identityStore = useIdentityStore()
    logger = new Logger(Header.name)

    clickGeolocation () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                position => {
                    this.logger.info(`Location: ${position.coords.latitude} ${position.coords.longitude}`)
                    this.identityStore.$state.currentLocation = {
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude
                    }
                },
                error => {
                    this.logger.error(error.message)
                })
        } else {
            this.logger.warn('your browser does not support geolocation')
        }
    }

    getCoords () {
        return this.identityStore.$state.currentLocation
    }
}
</script>

<style scoped>

</style>
