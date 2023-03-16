<template>
    <h4>Create wish</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'wishes' }">Back to Wishes</RouterLink>
    </div>
</template>

<script lang="ts">
import { WishService } from '@/services/WishService'
import { useWishesStore } from '@/stores/wishes'
import { Options, Vue } from 'vue-class-component'
import { CustomerService } from '@/services/CustomerService'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class WishCreate extends Vue {
    wishesStore = useWishesStore()
    wishService = new WishService()
    customerService = new CustomerService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        const res = await this.wishService.add(
            {
                accountId: '123',
                productId: '123'
            }
        )

        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            // this.wishesStore.$state.wishes =
            //     await this.wishService.getAll()

            this.$router.push('/wishes')
        }
    }
}
</script>
