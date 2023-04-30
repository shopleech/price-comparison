<template>
    <header>
        <RouterLink :to="{ name: back }" v-if="back != null && back !== ''">
            <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
        </RouterLink>
        <h1>{{ title }}</h1>
        <div class="row">
            <div class="col-9 small">
                <div v-if="getCoords().latitude">
                    Minu koordinaadid: {{ getCoords().latitude }} {{ getCoords().longitude }}
                </div>
            </div>
            <div class="col-3 text-right">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="bi bi-menu-down"></i>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#" @click="clickGeolocation">
                            <i class="bi bi-broadcast"></i> Minu koordinaadid</a>
                        <a class="dropdown-item" href="#" @click="clickWatchlist">Minu jälgimisnimekiri</a>
                        <a class="dropdown-item" href="#" @click="clickReviews">Minu hinnangud</a>
                        <a class="dropdown-item" href="#" @click="clickNewOffer">Uute pakkumiste lisamine</a>
                        <a class="dropdown-item" href="#" @click="clickNewShop">Uue kaupluse lisamine</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="input-group mb-3">
                    <input v-model="keyword" type="text" class="form-control" placeholder="Toote nimi või triipkood"
                           aria-label="Toote nimi või triipkood">
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
                    <barcode-scanner :qrbox="100" :fps="10" @result="onScan"/>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
import { Options, Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import Logger from '@/util/logger'
import router from '@/router'
import BarcodeScanner from '@/components/BarcodeScanner.vue'
import { useProductStore } from '@/stores/product'
import { ProductService } from '@/bll/service/ProductService'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
        BarcodeScanner,
    },
    props: {
        title: String,
        back: String,
    },
    methods: {
        onScan (decodedText, decodedResult) {
            this.logger.info('onscannn')
            this.keyword = decodedText
            this.searchProduct()
            this.logger.info(decodedResult)
            this.showScanner = false
        }
    },
})
export default class Header extends Vue {
    identityStore = useIdentityStore()
    productStore = useProductStore()
    logger = new Logger(Header.name)
    showScanner = false
    productService = new ProductService()
    keyword = ''

    mounted () {
        this.keyword = this.getKeyword()
    }

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

    clickNewOffer () {
        router.push('/product/import')
    }

    clickNewShop () {
        router.push('/shop/create')
    }

    clickReviews () {
        router.push('/review')
    }

    clickWatchlist () {
        router.push('/watchlist')
    }

    isAuthenticated () {
        this.logger.info('is?')
        return this.identityStore.$state.jwt !== null
    }

    searchProduct () {
        this.logger.info('searchProduct')

        this.productStore.$state.keyword = this.keyword

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

        router.push({
            name: 'home'
        })
    }

    getKeyword () {
        return this.productStore.$state.keyword
    }
}
</script>

<style scoped>

</style>
