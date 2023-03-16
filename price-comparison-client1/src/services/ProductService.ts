import type { IProduct } from '@/domain/IProduct'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { IFormProductPage } from '@/domain/forms/IFormProductPage'

export class ProductService extends BaseService<IProduct> {
    constructor () {
        super('v1/products')
    }

    async getAllByCategoryId (categoryId: string): Promise<IProduct[]> {
        console.log('getAll')
        let response
        try {
            response = await httpCLient.get(`${this._path}`, {
                headers: {
                    CategoryId: categoryId,
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            // TODO
            throw new TypeError('error with get all by category id')
        }
        console.log(response)

        const res = response.data as IProduct[]
        return res
    }

    async getAllByCategoryIdAndFilters (categoryId: string, form: IFormProductPage) : Promise<IProduct[]> {
        console.log('getAll')
        let response
        try {
            response = await httpCLient.get(`${this._path}`, {
                headers: {
                    MinPrice: form.filterByMinPrice,
                    MaxPrice: form.filterByMaxPrice,
                    CategoryId: categoryId,
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            // TODO
            throw new TypeError('error with get all by category id')
        }
        console.log(response)

        const res = response.data as IProduct[]
        return res
    }
}
