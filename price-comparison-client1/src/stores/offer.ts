import type { IOffer } from '@/dal/domain/IOffer'
import { defineStore } from 'pinia'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export const useOfferStore = defineStore({
    id: 'offers',
    state: () => ({
        offer: [] as IOffer,
        offers: [] as IOffer[],
    }),
    getters: {
        offerCount: (state) => state.offers.length,
    },
    actions: {
        add (offer: IOffer) {
            this.offers.push(offer)
        },
    },
})
