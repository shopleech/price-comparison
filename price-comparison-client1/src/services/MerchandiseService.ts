import type { IMerchandise } from '@/domain/IMerchandise'
import { BaseService } from './BaseService'
import HttpClient from '@/http-client'

export class MerchandiseService extends BaseService<IMerchandise> {
    constructor () {
        super('v1/merchandises')
    }

    async getAllByProductId (productId: string): Promise<IMerchandise[]> {
        console.log('getAll')
        let response
        try {
            response = await HttpClient.get(`${this._path}`, {
                headers: {
                    ProductId: productId,
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            // TODO
            throw new TypeError('error with get all by category id')
        }
        console.log(response)

        const res = response.data as IMerchandise[]
        return res
    }

    async upload (content: any[]) {
        await HttpClient.post(`${this._path}`,
            content
        ).then(function () {
            console.log('SUCCESS!!')
        }).catch(function () {
            console.log('FAILURE!!')
        })
    }
}
