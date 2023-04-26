<template>
    <h4>Delete rating {{ id }}</h4>
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
        <RouterLink :to="{ name: 'ratings' }">Back to Shops</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { ShopService } from '@/bll/service/ShopService'
import { useShopStore } from '@/stores/shop'
import Logger from '@/util/logger'

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
export default class ShopDelete extends Vue {
    private logger = new Logger(ShopDelete.name)
    id!: string
    shopStore = useShopStore()
    ratingService = new ShopService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        // await this.ratingService
        //     .delete(this.id)
        //     .then(() => {
        //         router.push('/shop')
        //     })

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.shopStore.$state.shops =
                await this.ratingService.getAll()

            this.$router.push('/shop')
        }
         */
    }
}
</script>
