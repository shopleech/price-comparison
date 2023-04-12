import { BaseRepository } from '@/dal/repository/BaseRepository'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import httpCLient from '@/http-client'
import { AxiosError } from 'axios'
import { IPublicStats } from '@/dal/domain/IPublicStats'
import { IStatsRepository } from '@/dal/repository/model/IStatsRepository'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export class StatsRepository extends BaseRepository<IPublicStats> implements IStatsRepository {
    constructor () {
        super('v1/stats')
    }

    async getPublicStats (): Promise<IServiceResult<IPublicStats>> {
        let response
        try {
            response = await httpCLient.get(`/${this._path}/public`, {
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
