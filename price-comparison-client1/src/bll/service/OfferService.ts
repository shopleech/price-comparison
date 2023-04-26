import type { IOffer } from '@/dal/domain/IOffer'
import { BaseService } from './BaseService'
import HttpClient from '@/util/http-client'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class OfferService extends BaseService<IOffer> {
    constructor () {
        super('v1/offer')
    }

    async getAllByProductId (): Promise<IOffer[]> {
        this.logger.info('getAll')
        let response
        try {
            response = await HttpClient.get(`${this._path}`, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            // TODO
            throw new TypeError('error with get all by category id')
        }

        const res = response.data as IOffer[]
        return res
    }

    async upload (content: any[]) {
        await HttpClient.post(`${this._path}`,
            content
        ).then(function () {
            return true
        }).catch(function () {
            return true
        })
    }
}
