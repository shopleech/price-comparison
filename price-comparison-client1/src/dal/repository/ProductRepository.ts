import { BaseRepository } from '@/dal/repository/BaseRepository'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import { IProductRepository } from '@/dal/repository/model/IProductRepository'
import httpCLient from '@/http-client'
import { AxiosError } from 'axios'
import { IProduct } from '@/dal/domain/IProduct'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export class ProductRepository extends BaseRepository<IProduct> implements IProductRepository {
    constructor () {
        super('v1/product')
    }

    async findByName (entity: IProduct): Promise<IServiceResult<IProduct[]>> {
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
