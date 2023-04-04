import type { IAccount } from '@/domain/IAccount'
import { defineStore } from 'pinia'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export const useAccountStore = defineStore({
    id: 'accounts',
    state: () => ({
        account: [] as IAccount,
        accounts: [] as IAccount[],
    }),
    getters: {
        accountCount: (state) => state.accounts.length,
    },
    actions: {
        add (account: IAccount) {
            this.accounts.push(account)
        }
    },
})
