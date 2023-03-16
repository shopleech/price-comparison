import type { ICustomer } from '@/domain/ICustomer'
import { BaseService } from './BaseService'

export class CustomerService extends BaseService<ICustomer> {
    constructor() {
        super("v1/customers");
    }
}
