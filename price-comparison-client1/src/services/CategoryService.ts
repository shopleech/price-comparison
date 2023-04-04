import type { ICategory } from '@/domain/ICategory'
import { BaseService } from './BaseService'
import { IDropdownItem } from '@/domain/forms/IDropdownItem'
import httpCLient from '@/http-client'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class CategoryService extends BaseService<ICategory> {
    constructor () {
        super('v1/category')
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
        this.logger.info('getAll')
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

        const res = response.data as ICategory[]

        return res
    }
}
