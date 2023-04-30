<template>
    <h4>Delete account {{ id }}</h4>
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
        <RouterLink :to="{ name: 'accounts' }">Back to Accounts</RouterLink>
    </div>
</template>

<script lang="ts">
import { AccountService } from '@/bll/service/AccountService'
import { useAccountStore } from '@/stores/account'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        id: Number,
    },
    emits: [],
})
export default class AccountDelete extends Vue {
    private logger = new Logger(AccountDelete.name)
    id!: number
    accountsStore = useAccountStore()
    accountService = new AccountService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        await this.accountService.delete(this.id)

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.accountsStore.$state.accounts =
                await this.accountService.getAll()

            this.$router.push('/accounts')
        }
         */
    }
}
</script>
