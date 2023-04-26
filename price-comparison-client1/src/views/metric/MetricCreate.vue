<template>
    <h4>Create wish</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
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
import { CustomerService } from '@/bll/service/CustomerService'
import { MetricService } from '@/bll/service/MetricService'
import { useMetricStore } from '@/stores/metric'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class MetricCreate extends Vue {
    private logger = new Logger(MetricCreate.name)
    wishesStore = useMetricStore()
    wishService = new MetricService()
    customerService = new CustomerService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const res = await this.wishService.add(
            {}
        )

        if (res.status == null || res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            // this.wishesStore.$state.wishes =
            //     await this.wishService.getAll()

            this.$router.push('/wishes')
        }
    }
}
</script>
