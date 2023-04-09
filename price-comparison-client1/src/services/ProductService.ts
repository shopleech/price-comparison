import type { IProduct } from '@/domain/IProduct'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { ISearchItem } from '@/domain/ISearchItem'
import { IShop } from '@/domain/IShop'
import { useProductStore } from '@/stores/product'
import { IServiceResult } from '@/services/IServiceResult'
import { AxiosError } from 'axios'

/**
 * @author Ahto Jalak
 * @since 16.03.2023
 */
export class ProductService extends BaseService<IProduct> {
    productStore = useProductStore()

    constructor () {
        super('v1/product')
    }

    async getAllByKeyword (searchEntity: ISearchItem): Promise<IServiceResult<IProduct[]>> {
        this.logger.info('getAll')
        let response
        try {
            response = await httpCLient.post(`${this._path}/search`, searchEntity, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            const res = {
                status: (e as AxiosError).response?.status,
                errorMsg: (e as AxiosError).response?.data.error,
            }
            return res
        }

        // const res = response.data.details as TEntity[]
        // return res
        return {
            status: response.status,
            data: response.data.details
        }
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

    search (keyword: string) {
        this.productStore.$state.keyword = keyword
    }

    getKeyword () {
        this.logger.info(this.productStore.$state.keyword)
        return this.productStore.$state.keyword
    }

    setKeyword (barcode: string) {
        this.productStore.$state.keyword = barcode
    }

    size () {
        return this.productStore.getCount()
    }
}
