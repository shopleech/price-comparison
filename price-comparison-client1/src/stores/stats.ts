import { defineStore } from 'pinia'
import { IPublicStats } from '@/dal/domain/IPublicStats'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export const useStatsStore = defineStore({
    id: 'stats',
    state: () => ({
        publicStats: {} as IPublicStats
    }),
    getters: {
    },
    actions: {
    },
})
