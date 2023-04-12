<template>
    <h4>Delete merchandise {{ id }}</h4>
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
        <RouterLink :to="{ name: 'merchandises' }">Back to Offers</RouterLink>
    </div>
</template>

<script lang="ts">
import { OfferService } from '@/bll/service/OfferService'
import { Options, Vue } from 'vue-class-component'
import { useOfferStore } from '@/stores/offer'
import Logger from '@/logger'

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
export default class OfferDelete extends Vue {
    private logger = new Logger(OfferDelete.name)
    id!: string
    merchandisesStore = useOfferStore()
    merchandiseService = new OfferService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        await this.merchandiseService.delete(this.id)

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.merchandisesStore.$state.merchandises =
                await this.merchandiseService.getAll()

            this.$router.push('/merchandises')
        }
         */
    }
}
</script>
