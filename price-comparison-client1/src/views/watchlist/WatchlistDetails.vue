<template>
    <h2>Details watchlist {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'watchlistes-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'watchlistes-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'watchlistes-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'watchlistes' }">Back to Watchlistes</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { WatchlistService } from '@/bll/service/WatchlistService'
import { useWatchlistStore } from '@/stores/watchlist'
import Logger from '@/util/logger'
import { IWatchlist } from '@/dal/domain/IWatchlist'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        id: Number,
    },
    emits: [],
})
export default class WatchlistDetails extends Vue {
    private logger = new Logger(WatchlistDetails.name)

    id!: number
    watchlistStore = useWatchlistStore()
    watchlistService = new WatchlistService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        const item = await this.watchlistService.get(this.id)
        this.watchlistStore.$state.watchlist = item.data as IWatchlist
    }
}

</script>
