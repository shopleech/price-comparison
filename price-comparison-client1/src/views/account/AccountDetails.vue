<template>
    <h2>Details account {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'accounts-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'accounts-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'accounts-delete', params: { id: id } }">Delete</RouterLink>
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
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class AccountDetails extends Vue {
    private logger = new Logger(AccountDetails.name)
    id!: string
    accountsStore = useAccountStore()
    accountService = new AccountService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.accountsStore.$state.account =
        //     await this.accountService.get(this.id)
    }
}

</script>
