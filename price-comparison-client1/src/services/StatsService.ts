import type { IProduct } from '@/domain/IProduct'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { IPublicStats } from '@/domain/IPublicStats'

/**
 * @author Ahto Jalak
 * @since 19.03.2023
 */
export class StatsService extends BaseService<IProduct> {
    constructor () {
        super('v1/stats')
    }

    async getPublicStats () {
        let response
        try {
            response = await httpCLient.get(`${this._path}/public`, {
                headers: {}
            })
        } catch (e) {
            throw new TypeError('failed getting stats')
        }

        if (response.data.details) {
            return response.data.details as IPublicStats
        }

        return {} as IPublicStats
    }
}
