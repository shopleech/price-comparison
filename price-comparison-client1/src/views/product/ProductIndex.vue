<template>
    <h2>List of products</h2>
    <p>
        <RouterLink :to="{ name: 'products-create' }">Create new</RouterLink>
    </p>
    <div v-for="item of productsStore.products" :key="item.id">
        <h3>
            <RouterLink :to="{ name: 'products-details', params: { id: item.id } }">{{ item.name }}</RouterLink>
        </h3>
        <div v-if="isAuthenticated">
            <a href="#">Add rating</a> | <a href="#">Add alarm</a>
        </div>
        <div>
            {{ item.description }}
        </div>
        <div v-if="isAuthenticated && isAdmin">
            <RouterLink :to="{ name: 'products-edit', params: { id: item.id } }">Edit</RouterLink>
            |
            <RouterLink :to="{ name: 'products-delete', params: { id: item.id } }">Delete</RouterLink>
        </div>
    </div>
</template>

<script lang="ts">
import { ProductService } from '@/services/ProductService'
import { useProductStore } from '@/stores/product'
import { Options, Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
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
export default class ProductIndex extends Vue {
    private logger = new Logger(ProductIndex.name)
    categoryId = 'asd'
    productsStore = useProductStore()
    productService = new ProductService()
    private identityStore = useIdentityStore()

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    get isAdmin (): boolean {
        return this.identityStore.isAdmin()
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.productsStore.$state.products =
        //    await this.productService.getAll(this.categoryId)
    }
}

</script>
