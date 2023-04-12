import { BaseRepository } from '@/dal/repository/BaseRepository'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import httpCLient from '@/http-client'
import { AxiosError } from 'axios'
import { ICategory } from '@/dal/domain/ICategory'
import { ICategoryRepository } from '@/dal/repository/model/ICategoryRepository'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export class CategoryRepository extends BaseRepository<ICategory> implements ICategoryRepository {
    constructor () {
        super('v1/category')
    }

    async findByName (entity: ICategory): Promise<IServiceResult<ICategory[]>> {
        this.logger.info('findByName')

        let response
        try {
            response = await httpCLient.post(`/${this._path}/search`, entity, {
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
