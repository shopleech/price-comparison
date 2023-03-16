<template>
    <h4>Create account</h4>
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
        <RouterLink :to="{ name: 'accounts' }">Back to Accounts</RouterLink>
    </div>
</template>

<script lang="ts">
import { AccountService } from '@/services/AccountService'
import { useAccountsStore } from '@/stores/accounts'
import { Options, Vue } from 'vue-class-component'
import { CustomerService } from '@/services/CustomerService'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class AccountCreate extends Vue {
    accountsStore = useAccountsStore()
    accountService = new AccountService()
    customerService = new CustomerService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        const res = await this.accountService.add(
            {
                customerId: '123'
            }
        )

        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            // this.accountsStore.$state.accounts =
            //     await this.accountService.getAll()

            this.$router.push('/accounts')
        }
    }
}
</script>
