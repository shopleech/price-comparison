<template>
    <h2>List of accounts</h2>
    <p>
        <RouterLink :to="{ name: 'accounts-create' }">Create new</RouterLink>
    </p>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Customer Id</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item of accountsStore.accounts" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.customerId }}</td>
            <td>
                <RouterLink :to="{ name: 'accounts-details', params: { id: item.id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'accounts-edit', params: { id: item.id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'accounts-delete', params: { id: item.id } }">Delete</RouterLink>
            </td>
        </tr>
        </tbody>
    </table>
</template>

<script lang="ts">
import { AccountService } from '@/services/AccountService'
import { useAccountsStore } from '@/stores/accounts'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class AccountIndex extends Vue {
    accountsStore = useAccountsStore()
    accountService = new AccountService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.accountsStore.$state.accounts =
            await this.accountService.getAll()
    }
}

</script>
