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
import { CategoryService } from '@/services/CategoryService'
import { useCategoriesStore } from '@/stores/categories'
import { Options, Vue } from 'vue-class-component'
import router from '@/router'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class CategoryDelete extends Vue {
    id!: string;
    categoriesStore = useCategoriesStore()
    categoryService = new CategoryService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        await this.categoryService.delete(this.id).then(item => {
            console.log(item)
            router.push('/')
        });

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
