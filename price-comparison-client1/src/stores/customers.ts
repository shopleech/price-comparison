import type { ICustomer } from '@/domain/ICustomer'
import { defineStore } from 'pinia'

export const useCustomersStore = defineStore({
    id: "customers",
    state: () => ({
        customer: [
        ] as ICustomer,
        customers: [
        ] as ICustomer[],
    }),
    getters: {
        customerCount: (state) => state.customers.length,
    },
    actions: {
        add(customer: ICustomer) {
            this.customers.push(customer);
        }
    },
});
