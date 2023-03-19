import type { IProduct } from '@/domain/IProduct'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { ISearchItem } from '@/domain/ISearchItem'

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
}
