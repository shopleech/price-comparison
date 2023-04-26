import { BaseRepository } from '@/dal/repository/BaseRepository'
import { IOfferRepository } from '@/dal/repository/model/IOfferRepository'
import httpCLient from '@/util/http-client'
import { AxiosError } from 'axios'
import { IOffer } from '@/dal/domain/IOffer'
import { IServiceResult } from '@/bll/service/model/IServiceResult'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export class OfferRepository extends BaseRepository<IOffer> implements IOfferRepository {
    constructor () {
        super('v1/offer')
    }

    async getOfferListById (productId: number): Promise<IServiceResult<IOffer[]>> {
        this.logger.info('findByName')

        let response
        try {
            response = await httpCLient.get(`/${this._path}/product/${productId}`, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token,
                }
            })
        } catch (e) {
            return {
                status: (e as AxiosError).response?.status,
                errorMsg: (e as AxiosError).response?.data.message,
            }
        }

        return {
            status: response.status,
            data: response.data.details,
        }
    }
}
