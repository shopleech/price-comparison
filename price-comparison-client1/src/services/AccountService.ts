import type { IAccount } from '@/domain/IAccount'
import { BaseService } from './BaseService'

export class AccountService extends BaseService<IAccount> {
    constructor() {
        super("v1/accounts");
    }
}
