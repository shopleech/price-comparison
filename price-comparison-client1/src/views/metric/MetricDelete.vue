<template>
    <h4>Delete wish {{ id }}</h4>
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
        <RouterLink :to="{ name: 'wishes' }">Back to Metrices</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { MetricService } from '@/bll/service/MetricService'
import { useMetricStore } from '@/stores/metric'
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
export default class MetricDelete extends Vue {
    private logger = new Logger(MetricDelete.name)
    id!: string
    wishesStore = useMetricStore()
    wishService = new MetricService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        // const res = await this.wishService.delete(this.id);

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.wishesStore.$state.wishes =
                await this.wishService.getAll()

            this.$router.push('/wishes')
        }
         */
    }
}
</script>
