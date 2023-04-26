<template>
    <h2>Details wish {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'wishes-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'wishes-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'wishes-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'wishes' }">Back to Metrices</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { MetricService } from '@/bll/service/MetricService'
import { useMetricStore } from '@/stores/metric'
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
export default class MetricDetails extends Vue {
    private logger = new Logger(MetricDetails.name)
    id!: string
    wishesStore = useMetricStore()
    wishService = new MetricService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.wishesStore.$state.metric =
        //     await this.wishService.get(this.id)
    }
}

</script>
