<template>
    <h4>Delete watchlist {{ id }}</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Delete" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'watchlist' }">Back to Watchlistes</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { useWatchlistStore } from '@/stores/watchlist'
import { WatchlistService } from '@/bll/service/WatchlistService'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class WatchlistDelete extends Vue {
    private logger = new Logger(WatchlistDelete.name)
    id!: string
    watchlistStore = useWatchlistStore()
    watchlistService = new WatchlistService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        // const res = await this.watchlistService.delete(this.id);

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.watchlistStore.$state.watchlists =
                await this.watchlistService.getAll()

            this.$router.push('/watchlist')
        }
         */
    }
}
</script>
