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
import { useCustomersStore } from '@/stores/customers'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class CustomerDetails extends Vue {
    id!: string;
    customersStore = useCustomersStore()
    customerService = new CustomerService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.customersStore.$state.customer =
            await this.customerService.get(this.id);
    }
}

</script>
