import { BaseRepository } from '@/dal/repository/BaseRepository'
import { IUser } from '@/dal/domain/IUser'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import { ILoginInfo } from '@/dal/domain/ILoginInfo'
import { IJwtResponse } from '@/dal/domain/IJwtResponse'
import { IUserRepository } from '@/dal/repository/model/IUserRepository'
import httpCLient from '@/util/http-client'
import { AxiosError } from 'axios'
import { IRegisterInfo } from '@/dal/domain/IRegisterInfo'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export class UserRepository extends BaseRepository<IUser> implements IUserRepository {
    constructor () {
        super('v1/auth')
    }

    async login (entity: ILoginInfo): Promise<IServiceResult<IJwtResponse>> {
        this.logger.info('login')

        let response
        try {
            response = await httpCLient.post(`/${this._path}`, entity, {})
        } catch (e) {
            return {
                status: (e as AxiosError).response?.status,
                errorMsg: (e as AxiosError).response?.data.error,
            }
        }

        return {
            status: response.status,
            data: response.data.details
        }
    }

    async register (entity: IRegisterInfo): Promise<IServiceResult<IJwtResponse>> {
        this.logger.info('register')

        let response
        try {
            response = await httpCLient.post(`/${this._path}`, entity, {})
        } catch (e) {
            return {
                status: (e as AxiosError).response?.status,
                errorMsg: (e as AxiosError).response?.data.error,
            }
        }

        return {
            status: response.status,
            data: response.data.details
        }
    }
}
