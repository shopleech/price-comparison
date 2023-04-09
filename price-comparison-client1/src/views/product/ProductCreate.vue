<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h2>Create products</h2>
    <div class="row">
        <div class="col-md-12">
            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Barcode</label>
                <input type="text" class="col-sm-2" v-model="barcode"/>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Name</label>
                <input type="text" class="col-sm-2" v-model="name"/>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Description</label>
                <input type="text" class="col-sm-2" v-model="description"/>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Category</label>
                <select class="col-sm-2" v-model="categoryId">
                    <option v-for="option in categoryOptions" v-bind:key="option.id" :value="option.id">
                        {{ option.name }}
                    </option>
                </select>
            </div>
            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { CategoryService } from '@/services/CategoryService'
import { ProductService } from '@/services/ProductService'
import { Options, Vue } from 'vue-class-component'
import { IProduct } from '@/domain/IProduct'
import { ICategory } from '@/domain/ICategory'
import Logger from '@/logger'
import router from '@/router'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        barcode: String,
    },
    emits: [],
})
export default class ProductCreate extends Vue {
    barcode = ''

    name = ''
    description = ''
    categoryId = 0
    errorMsg: string | null = null

    private logger = new Logger(ProductCreate.name)
    private productService = new ProductService()
    private categoryService = new CategoryService()
    private categoryOptions: ICategory[] | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const obj: IProduct = {
            categoryId: this.categoryId,
            barcode: this.barcode,
            name: this.name,
            description: this.description
        }
        this.logger.info(obj as string)

        const res = await this.productService.add(obj)

        if (res.status == null || res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            await router.push('/')
        }
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.categoryOptions = await this.categoryService.getAllCategories()
    }
}
</script>
