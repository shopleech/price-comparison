import { IMetric } from '@/dal/domain/IMetric'
import { defineStore } from 'pinia'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export const useMetricStore = defineStore({
    id: 'metrics',
    state: () => ({
        metric: {} as IMetric,
        metrics: [] as IMetric[],
    }),
    getters: {
        metricCount: (state) => state.metrics.length,
    },
    actions: {
        add (metric: IMetric) {
            this.metrics.push(metric)
        },
    },
})
