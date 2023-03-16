import type { IWish } from '@/domain/IWish'
import { defineStore } from 'pinia'

export const useWishesStore = defineStore({
    id: "wishes",
    state: () => ({
        wish: [
        ] as IWish,
        wishes: [
        ] as IWish[],
    }),
    getters: {
        wishCount: (state) => state.wishes.length,
    },
    actions: {
        add(wish: IWish) {
            this.wishes.push(wish);
        }
    },
});
