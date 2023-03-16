import type { IPrice } from '@/domain/IPrice'
import { BaseService } from './BaseService'

export class PriceService extends BaseService<IPrice> {
    constructor() {
        super("v1/prices");
    }
}
