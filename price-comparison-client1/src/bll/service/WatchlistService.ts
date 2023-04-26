import type { IWatchlist } from '@/dal/domain/IWatchlist'
import httpCLient from '@/util/http-client'
import { BaseService } from './BaseService'
import { AxiosError } from 'axios'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class WatchlistService extends BaseService<IWatchlist> {
    constructor () {
        super('v1/watchlist')
    }

    async addProductToWishlist () {
        this.logger.info('add')

        const entity: IWatchlist = {}

        let response
        try {
            response = await httpCLient.post(`/${this._path}`, entity,
                { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
            )
        } catch (e) {
            const res = {
                status: (e as AxiosError).response?.status,
                errorMsg: (e as AxiosError).response?.data.error,
            }

            return res
        }

        return { status: response.status }
    }
}
