<template>
    <div class="row">
        <div class="col-lg-10">
            <h2>Main category items</h2>
            <div v-for="item of categories" :key="item.id" class="border p-2 mb-4 row">
                <div class="col-3">
                    <img src="https://via.placeholder.com/200x50.png?text=category" alt="category"/>
                </div>
                <div class="col-6">
                    <h3>
                        <RouterLink :to="{ name: 'categories-details', params: { id: item.id } }" class="text-dark">
                            {{ item.name }}
                        </RouterLink>
                    </h3>
                </div>
                <div v-if="identityStore.isAdmin()" class="col-3 small">
                    <RouterLink :to="{ name: 'categories-details', params: { id: item.id } }">Details</RouterLink>
                    |
                    <RouterLink :to="{ name: 'categories-edit', params: { id: item.id } }">Edit</RouterLink>
                    |
                    <RouterLink :to="{ name: 'categories-delete', params: { id: item.id } }">Delete</RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { CategoryService } from '@/bll/service/CategoryService'
import { useCategoryStore } from '@/stores/category'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class CategoryIndex extends Vue {
    private logger = new Logger(CategoryIndex.name)
    categoryStore = useCategoryStore()
    categoryService = new CategoryService()
    errorMsg: string | null = null

    /*
    limitArray (length = 3) {
        if (length === -1) {
            return this.categoriesStore.$state.categories
        }
        if (length > this.categoriesStore.$state.categories.length) {
            return this.categoriesStore.$state.categories
        }

        return this.categoriesStore.$state.categories.slice(0, length)
    }
    */

    async mounted (): Promise<void> {
        this.categoryService.getAllByCategoryId(null).then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    this.categoryStore.$state.categories = items.data
                }
            }
        })
    }
}

</script>
