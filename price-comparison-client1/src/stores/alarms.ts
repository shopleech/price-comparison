import { defineStore } from 'pinia'
import { IAlarm } from '@/domain/IAlarm'

export const useAlarmsStore = defineStore({
    id: 'alarms',
    state: () => ({
        alarm: [] as IAlarm,
        alarms: [] as IAlarm[],
    }),
    getters: {
        alarmCount: (state) => state.alarms.length,
    },
    actions: {
        add (alarm: IAlarm) {
            this.alarms.push(alarm)
        }
    },
})
