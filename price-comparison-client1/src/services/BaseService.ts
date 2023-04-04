import httpCLient from '@/http-client'
import { useIdentityStore } from '@/stores/identity'
import type { AxiosError } from 'axios'
import type { IServiceResult } from './IServiceResult'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class BaseService<TEntity> {
    protected logger = new Logger(BaseService.name)

    protected identityStore = useIdentityStore()

    protected _path: string

    constructor (path: string) {
        this._path = path
    }

    async getAll (): Promise<TEntity[]> {
        this.logger.info('getAll')
        let response
        try {
            response = await httpCLient.get(`/${this._path}`, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            throw new TypeError('Something happened with get all')
        }

        const res = response.data as TEntity[]
        return res
    }

    async get (id: string): Promise<TEntity> {
        this.logger.info('get')
        let response
        try {
            response = await httpCLient.get(
                `/${this._path}/${id}`,
                { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
            )
        } catch (e) {
            throw new TypeError('Something happened with get')
        }
        const res = response.data as TEntity
        return res
    }

    async add (entity: TEntity): Promise<IServiceResult<void>> {
        this.logger.info('add')

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

            // try token refresh
            if (res.status === 401 && this.identityStore.jwt) {
                await this.identityStore.refreshUser().then(async value => {
                    if (value) {
                        this.logger.info('token refresh')
                    }
                })
                // retry
                const retry = await this.extracted(entity)
                return retry
            }

            return res
        }

        return { status: response.status }
    }

    private async extracted (entity: TEntity) {
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

    async update (id: string, entity: TEntity): Promise<IServiceResult<void>> {
        this.logger.info('add')

        let response
        try {
            response = await httpCLient.put(`/${this._path}/${id}`, entity,
                {
                    headers: {
                        Authorization: 'bearer ' + this.identityStore.$state.jwt?.token
                    }
                }
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

    async delete (id: string): Promise<TEntity> {
        this.logger.info('delete')
        const response = await httpCLient.delete(
            `/${this._path}/${id}`,
            { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
        )
        const res = response.data as TEntity
        return res
    }
}
