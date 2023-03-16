import type { IAccount } from '@/domain/IAccount'
import { defineStore } from 'pinia'

export const useAccountsStore = defineStore({
    id: "accounts",
    state: () => ({
        account: [
        ] as IAccount,
        accounts: [
        ] as IAccount[],
    }),
    getters: {
        accountCount: (state) => state.accounts.length,
    },
    actions: {
        add(account: IAccount) {
            this.accounts.push(account);
        }
    },
});
