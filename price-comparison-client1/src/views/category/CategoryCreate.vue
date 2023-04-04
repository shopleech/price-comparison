<template>
    <h4>Create category</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <select v-model="categoryParentCategoryId">
                    <option v-for="asd in categories"
                            v-bind:key="asd.id" v-bind:value="asd.id">
                        {{ asd.name }}
                    </option>
                </select>
                <div class="form-group">
                    <label>Category name</label>
                    <input type="text" v-model="categoryName"/>
                </div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'categories' }">Back to Categories</RouterLink>
    </div>
</template>

<script lang="ts">
import { CategoryService } from '@/services/CategoryService'
import { useCategoryStore } from '@/stores/category'
import { Options, Vue } from 'vue-class-component'
import { ICategory } from '@/domain/ICategory'
import router from '@/router'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class CategoryCreate extends Vue {
    private logger = new Logger(CategoryCreate.name)
    categoriesStore = useCategoryStore()
    categoryService = new CategoryService()

    categories: ICategory[] = []
    categoryParentCategoryId: string | null = null
    categoryName: string | null = null
    errorMsg: string | null = null

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.categories.push({
            id: '',
            name: '--- no parent category ---'
        })
        this.categories = await this.categoryService.getAll()
    }

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')
        const category = {
            parentCategoryId: this.categoryParentCategoryId,
            name: this.categoryName,
        }
        await this.categoryService.add(category as ICategory).then(res => {
            if (res.status == null || res.status >= 300) {
                this.errorMsg = res.status + ' ' + res.errorMsg
            } else {
                // this.categoriesStore.$state.categories =
                //     await this.categoryService.getAll()
                router.push('/categories')
            }
        })
    }
}
</script>
