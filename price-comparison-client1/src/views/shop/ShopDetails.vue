<template>
    <h2>Details rating {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'ratings-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'ratings-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'ratings-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'ratings' }">Back to Shops</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { useShopStore } from '@/stores/shop'
import { ShopService } from '@/bll/service/ShopService'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 31.03.2023
 */
@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class ShopDetails extends Vue {
    private logger = new Logger(ShopDetails.name)
    id!: string
    shopStore = useShopStore()
    shopService = new ShopService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // const item = await this.shopService.get(this.id)
        // this.shopStore.$state.shop = item.data as IShop
    }
}

</script>
