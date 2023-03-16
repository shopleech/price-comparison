import type { ICategory } from '@/domain/ICategory'
import { defineStore } from 'pinia'

export const useCategoriesStore = defineStore({
    id: 'categories',
    state: () => ({
        category: [] as ICategory,
        categories: [] as ICategory[],
    }),
    getters: {
        categoryCount: (state) => state.categories.length,
    },
    actions: {
        add (category: ICategory) {
            this.categories.push(category)
        }
    },
})
