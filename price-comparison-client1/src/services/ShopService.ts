import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { AxiosError } from 'axios'
import { IShop } from '@/domain/IShop'
import { useShopStore } from '@/stores/shop'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class ShopService extends BaseService<IShop> {
    shopStore = useShopStore()

    constructor () {
        super('v1/shop')
    }

    async pullAllShops () {
        return this.shopStore.$state.shops
    }
}
