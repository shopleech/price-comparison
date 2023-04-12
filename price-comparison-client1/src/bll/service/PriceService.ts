import type { IPrice } from '@/dal/domain/IPrice'
import { BaseService } from './BaseService'

export class PriceService extends BaseService<IPrice> {
    constructor() {
        super("v1/price");
    }
}
