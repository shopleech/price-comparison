<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h2>Products</h2>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div class="form-group">
                <label>Category</label>
                <select v-model="category">
                    <option v-for="option in categoryOptions" v-bind:key="option.id">
                        {{ option.name }}
                    </option>
                </select>
            </div>

            <div class="form-group">
                <label>Barcode</label>
                <input type="text" v-model="barcode"/>
            </div>

            <div class="form-group">
                <label>Product name</label>
                <input type="text" v-model="name"/>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'products' }">Back to Products</RouterLink>
    </div>
</template>

<script lang="ts">
import { CategoryService } from '@/services/CategoryService'
import { CustomerService } from '@/services/CustomerService'
import { ProductService } from '@/services/ProductService'
import { useProductStore } from '@/stores/product'
import { Options, Vue } from 'vue-class-component'
import { IProduct } from '@/domain/IProduct'
import { ICategory } from '@/domain/ICategory'
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
export default class ProductCreate extends Vue {
    private logger = new Logger(ProductCreate.name)
    productsStore = useProductStore()
    productService = new ProductService()
    customerService = new CustomerService()
    categoryService = new CategoryService()

    name = ''
    barcode = ''
    category = ''
    errorMsg: string | null = null
    private categoryOptions: ICategory[] | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const obj: IProduct = {}

        const res = await this.productService.add(obj)

        if (res.status == null || res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            // this.productsStore.$state.products =
            //     await this.productService.getAll()

            this.$router.push('/products')
        }
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.categoryOptions = await this.categoryService.getAll()
    }
}
</script>
