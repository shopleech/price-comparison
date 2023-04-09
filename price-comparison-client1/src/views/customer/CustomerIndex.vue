<template>
    <h2>List of customers</h2>
    <p>
        <RouterLink :to="{ name: 'customers-create' }">Create new</RouterLink>
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
        <tr v-for="item of customersStore.customers" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.customerId }}</td>
            <td>
                <RouterLink :to="{ name: 'customers-details', params: { id: item.id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'customers-edit', params: { id: item.id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'customers-delete', params: { id: item.id } }">Delete</RouterLink>
            </td>
        </tr>
        </tbody>
    </table>
</template>

<script lang="ts">
import { CustomerService } from '@/services/CustomerService'
import { useCustomerStore } from '@/stores/customer'
import { Options, Vue } from 'vue-class-component'
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
export default class CustomerIndex extends Vue {
    private logger = new Logger(CustomerIndex.name)
    customersStore = useCustomerStore()
    customerService = new CustomerService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.customersStore.$state.customers =
        //     await this.customerService.getAll()
    }
}

</script>
