import type { IProduct } from '@/domain/IProduct'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { ISearchItem } from '@/domain/ISearchItem'
import { IShop } from '@/domain/IShop'

/**
 * @author Ahto Jalak
 * @since 16.03.2023
 */
export class ProductService extends BaseService<IProduct> {
    constructor () {
        super('v1/product')
    }

    async getAllByKeyword (searchEntity: ISearchItem) {
        let response
        try {
            response = await httpCLient.post(`${this._path}/search`, searchEntity, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            throw new TypeError('failed getting all by keyword')
        }

        if (response.data.details) {
            return response.data.details as IProduct[]
        }

        return [] as IProduct[]
    }

    async addStore (shopEntity: IShop) {
        let response
        try {
            response = await httpCLient.post(`${this._path}/add-shop`, shopEntity, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            throw new TypeError('failed getting all by keyword')
        }

        if (response.data.details) {
            return response.data.details as IProduct[]
        }

        return {} as IShop
    }
}
