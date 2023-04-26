<template>
    <h2>Details alarm {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'alarms-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'alarms-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'alarms-delete', params: { id: id } }">Delete</RouterLink>
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
        id: String
    },
    emits: [],
})
export default class AlarmDetails extends Vue {
    private logger = new Logger(AlarmDetails.name)
    id!: string
    alarmsStore = useAlarmStore()
    alarmService = new AlarmService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.alarmsStore.$state.alarm =
        //     await this.alarmService.get(this.id)
    }
}

</script>
