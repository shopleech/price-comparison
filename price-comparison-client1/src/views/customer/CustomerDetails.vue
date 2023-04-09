<template>
    <h2>Details customer {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'customers-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'customers-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'customers-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'customers' }">Back to Customers</RouterLink>
    </div>
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
    props: {
        id: String
    },
    emits: [],
})
export default class CustomerDetails extends Vue {
    private logger = new Logger(CustomerDetails.name)
    id!: string
    customersStore = useCustomerStore()
    customerService = new CustomerService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.customersStore.$state.customer =
        //     await this.customerService.get(this.id)
    }
}

</script>
