import type { IMerchandise } from '@/domain/IMerchandise'
import { defineStore } from 'pinia'

export const useMerchandisesStore = defineStore({
    id: "merchandises",
    state: () => ({
        merchandise: [
        ] as IMerchandise,
        merchandises: [
        ] as IMerchandise[],
    }),
    getters: {
        merchandiseCount: (state) => state.merchandises.length,
    },
    actions: {
        add(merchandise: IMerchandise) {
            this.merchandises.push(merchandise);
        },
    },
});
