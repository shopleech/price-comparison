<template>
    <h4>Delete product {{ id }}</h4>
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
        <RouterLink :to="{ name: 'products' }">Back to Products</RouterLink>
    </div>
</template>

<script lang="ts">
import { ProductService } from '@/services/ProductService'
import { useProductsStore } from '@/stores/products'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class ProductDelete extends Vue {
    id!: string;
    productsStore = useProductsStore()
    productService = new ProductService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        const res = await this.productService.delete(this.id);

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.productsStore.$state.products =
                await this.productService.getAll()

            this.$router.push('/products')
        }
         */
    }
}
</script>
