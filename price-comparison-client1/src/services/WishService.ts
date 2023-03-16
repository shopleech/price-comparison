import type { IWish } from '@/domain/IWish'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { AxiosError } from 'axios'

export class WishService extends BaseService<IWish> {
    constructor() {
        super("v1/Wishes");
    }

    async addProductToWishlist (productId: string) {
        console.log('add')

        const entity: IWish = {
            productId: productId,
        }

        let response
        try {
            response = await httpCLient.post(`/${this._path}`, entity,
                { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
            )
        } catch (e) {
            const res = {
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }
            console.log(res)

            return res
        }

        return { status: response.status }
    }
}
