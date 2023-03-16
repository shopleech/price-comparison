import { defineStore } from 'pinia'
import { IRating } from '@/domain/IRating'

export const useRatingsStore = defineStore({
    id: 'ratings',
    state: () => ({
        rating: [] as IRating,
        ratings: [] as IRating[],
    }),
    getters: {
        ratingCount: (state) => state.ratings.length,
    },
    actions: {
        add (rating: IRating) {
            this.ratings.push(rating)
        }
    },
})
