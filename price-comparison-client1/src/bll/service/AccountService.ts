import type { IAccount } from '@/dal/domain/IAccount'
import { BaseService } from './BaseService'

export class AccountService extends BaseService<IAccount> {
    constructor() {
        super("v1/account");
    }
}
