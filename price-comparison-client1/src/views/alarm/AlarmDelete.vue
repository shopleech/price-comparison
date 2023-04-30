<template>
    <h4>Delete alarm {{ id }}</h4>
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
        <RouterLink :to="{ name: 'alarms' }">Back to Alarms</RouterLink>
    </div>
</template>

<script lang="ts">
import { AlarmService } from '@/bll/service/AlarmService'
import { useAlarmStore } from '@/stores/alarm'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'

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
export default class AlarmDelete extends Vue {
    private logger = new Logger(AlarmDelete.name)
    id!: number
    alarmsStore = useAlarmStore()
    alarmService = new AlarmService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        await this.alarmService.delete(this.id)

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.alarmsStore.$state.alarms =
                await this.alarmService.getAll()

            this.$router.push('/alarms')
        }
         */
    }
}
</script>
