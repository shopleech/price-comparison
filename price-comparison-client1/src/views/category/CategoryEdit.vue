<template>
    <h2>Edit category {{ id }}</h2>
    <div class="row">
        <div class="col-2">
            <RouterLink :to="{ name: 'categories' }">Back to Categories</RouterLink>
        </div>
        <div class="col-10">
            <div class="row">
                <div class="col-md-12">

                    <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                        <ul>
                            <li>{{ errorMsg }}</li>
                        </ul>
                    </div>

                    <div>
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
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { useCategoriesStore } from '@/stores/categories'
import { CategoryService } from '@/services/CategoryService'

@Options({
    components: {},
    props: {
        id: String,
        name: String
    },
    emits: [],
})
export default class CategoryCreate extends Vue {
    id!: string;
    categoryName!: string
    errorMsg: string | null = null
    categoriesStore = useCategoriesStore()
    categoryService = new CategoryService()

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        const x = await this.categoryService.get(this.id)
        x.name = this.categoryName

        const res = await this.categoryService.update(this.id, x)

        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.categoriesStore.$state.category =
                await this.categoryService.get(this.id);
            this.$router.push(`/categories/details/${this.id}`)
        }
    }

    async mounted (): Promise<void> {
        console.log('mounted')
        const x = await this.categoryService.get(this.id)
        this.categoryName = x.name ?? ""
        this.categoriesStore.$state.category = x;
    }
}
</script>
