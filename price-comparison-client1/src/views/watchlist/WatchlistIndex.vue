<template>
    <Header v-if="isAuthenticated" title="" back="home"/>

    <h4>Jälgimisnimekiri</h4>
    <div v-for="item of getWatchlist()" :key="item.id" class="border p-2 mb-4 row">
        <div class="col-2">
            <img src="https://placehold.co/50x50/EEE/31343C?font=playfair-display&text=Product" alt="product"/>
        </div>
        <div class="col-6">
            <RouterLink :to="{ name: 'product-details', params: { id: item.productId } }" class="text-dark">
                {{ item.product.name }}
            </RouterLink><br/>
            <small>{{ item.product.barcode }}</small>
        </div>
        <div class="col-2">
            <RouterLink :to="{ name: 'product-details', params: { id: item.productId } }" class="text-dark">
                {{ item.product.minPrice }}
            </RouterLink>
        </div>
        <div class="col-2">
            <button @click="addAlarm(item.productId)">
                <i class="bi bi-bell"></i>
            </button>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { WatchlistService } from '@/bll/service/WatchlistService'
import { useWatchlistStore } from '@/stores/watchlist'
import Logger from '@/util/logger'
import { IWatchlist } from '@/dal/domain/IWatchlist'
import Header from '@/components/Header.vue'
import { IdentityService } from '@/bll/service/IdentityService'
import router from '@/router'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
        Header
    },
    props: {},
    emits: [],
})
export default class WatchlistIndex extends Vue {
    private logger = new Logger(WatchlistIndex.name)
    private identityService = new IdentityService()

    watchlistStore = useWatchlistStore()
    watchlistService = new WatchlistService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        const items = await this.watchlistService.getAll()
        this.watchlistStore.$state.watchlists = items.data as IWatchlist[]
    }

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }

    getWatchlist (): IWatchlist[] {
        return this.watchlistStore.$state.watchlists
    }

    addAlarm (productId: number) {
        this.logger.info('addAlarm')
        router.push('/alarm/create/' + productId)
    }
}

</script>
