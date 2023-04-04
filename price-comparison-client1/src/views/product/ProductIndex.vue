<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h2>Products</h2>
    <div class="row">
        <div class="col-12 p-3">
            <h3>Price comparison app</h3>
            <hr/>
            <div class="row">
                <div class="col-6">
                    <RouterLink :to="{ name: 'shop-create' }" class="text-dark">
                        Add store
                    </RouterLink>
                </div>
                <div class="col-6">
                    <RouterLink :to="{ name: 'product-create' }" class="text-dark">
                        Add product
                    </RouterLink>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="input-group mb-3">
                        <input v-model="keyword" type="text" class="form-control" placeholder="Product barcode"
                               aria-label="Product title or barcode" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" @click="searchProductClicked" type="button">
                                Search
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div v-for="item of products" :key="item.id" class="border p-2 mb-4 row">
                <div class="col-3">
                    <img src="https://via.placeholder.com/50x50.png?text=product" alt="product"/>
                </div>
                <div class="col-6">
                    <h3>
                        <RouterLink :to="{ name: 'products-details', params: { id: item.id } }" class="text-dark">
                            {{ item.name }}
                        </RouterLink>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { ProductService } from '@/services/ProductService'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/logger'
import { IdentityService } from '@/services/IdentityService'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class ProductIndex extends Vue {
    keyword!: string

    private logger = new Logger(ProductIndex.name)
    private productService = new ProductService()
    private identityService = new IdentityService()

    async searchProductClicked (): Promise<void> {
        this.logger.info('click')
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
    }
}

</script>
