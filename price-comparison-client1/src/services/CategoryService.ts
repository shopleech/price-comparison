import type { ICategory } from '@/domain/ICategory'
import { BaseService } from './BaseService'
import { IDropdownItem } from '@/domain/IDropdownItem'
import httpCLient from '@/http-client'

export class CategoryService extends BaseService<ICategory> {
    constructor () {
        super('v1/categories')
    }

    async getAllForDropdown (): Promise<IDropdownItem[]> {
        const items = []
        for (const allElement of await this.getAll()) {
            items.push({
                text: allElement.name,
                key: allElement.id
            })
        }
        return items
    }

    async getAllByCategoryId (categoryId: string | null): Promise<ICategory[]> {
        console.log('getAll')
        let customHeaders
        let response

        if (categoryId == null) {
            customHeaders = { Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token }
        } else {
            customHeaders = {
                CategoryId: categoryId,
                Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
            }
        }

        try {
            response = await httpCLient.get(`${this._path}`, { headers: customHeaders })
        } catch (e) {
            // TODO
            throw new TypeError('error with get all by category id')
        }
        console.log(response)

        const res = response.data as ICategory[]
        return res
    }
}
