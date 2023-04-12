<template>
    <h4>Delete category {{ id }}</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Delete" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'categories' }">Back to Categories</RouterLink>
    </div>
</template>

<script lang="ts">
import { CategoryService } from '@/bll/service/CategoryService'
import { useCategoryStore } from '@/stores/category'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class CategoryDelete extends Vue {
    private logger = new Logger(CategoryDelete.name)
    id!: string
    categoriesStore = useCategoryStore()
    categoryService = new CategoryService()

    errorMsg: string | null = null

    submitClicked (): void {
        this.logger.info('submitClicked')

        // this.categoryService.delete(this.id).then(() => {
        //     router.push('/')
        // })

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.categoriesStore.$state.categories =
                await this.categoryService.getAll()

            this.$router.push('/categories')
        }
         */
    }
}
</script>
