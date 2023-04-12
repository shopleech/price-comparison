<template>
    <h2>Details merchandise {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'merchandises-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'merchandises-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'merchandises-delete', params: { id: id } }">Delete</RouterLink>
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
export default class OfferDetails extends Vue {
    private logger = new Logger(OfferDetails.name)
    id!: string
    merchandisesStore = useOfferStore()
    merchandiseService = new OfferService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.merchandisesStore.$state.offer =
        //     await this.merchandiseService.get(this.id)
    }
}

</script>
