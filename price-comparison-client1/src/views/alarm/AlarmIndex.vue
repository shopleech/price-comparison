<template>
    <h2>List of alarms</h2>
    <p>
        <RouterLink :to="{ name: 'alarms-create' }">Create new</RouterLink>
    </p>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Alarm Id</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item of alarmsStore.alarms" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.alarmId }}</td>
            <td>
                <RouterLink :to="{ name: 'alarms-details', params: { id: item.id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'alarms-edit', params: { id: item.id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'alarms-delete', params: { id: item.id } }">Delete</RouterLink>
            </td>
        </tr>
        </tbody>
    </table>
</template>

<script lang="ts">
import { AlarmService } from '@/services/AlarmService'
import { useAlarmsStore } from '@/stores/alarms'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class AlarmIndex extends Vue {
    alarmsStore = useAlarmsStore()
    alarmService = new AlarmService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.alarmsStore.$state.alarms =
            await this.alarmService.getAll()
    }
}

</script>
