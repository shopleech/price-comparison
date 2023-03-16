import httpCLient from '@/http-client'
import { useIdentityStore } from '@/stores/identity'
import type { AxiosError } from 'axios'
import type { IServiceResult } from './IServiceResult'

export class BaseService<TEntity> {
    protected identityStore = useIdentityStore()
    protected _path: string

    constructor (path: string) {
        this._path = path
    }

    async getAll (): Promise<TEntity[]> {
        console.log('getAll')
        let response
        try {
            response = await httpCLient.get(`/${this._path}`, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            throw new TypeError("Something happened with get all")
        }
        console.log(response)

        const res = response.data as TEntity[]
        return res
    }

    async get (id: string): Promise<TEntity> {
        console.log('get')
        let response
        try {
            response = await httpCLient.get(
                `/${this._path}/${id}`,
                { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
            )
        } catch (e) {
            throw new TypeError("Something happened with get")
        }
        console.log(response)
        const res = response.data as TEntity
        return res
    }

    async add (entity: TEntity): Promise<IServiceResult<void>> {
        console.log('add')

        let response
        try {
            response = await httpCLient.post(`/${this._path}`, entity,
                { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
            )
        } catch (e) {
            const res = {
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }
            console.log(res)

            // try token refresh
            if (res.status === 401 && this.identityStore.jwt) {
                await this.identityStore.refreshUser().then(async value => {
                    if (value) {
                        console.log('token refresh')
                    }
                })
                // retry
                const retry = await this.extracted(entity)
                return retry;
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
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }
            console.log(res)
            return res
        }
        return { status: response.status }
    }

    async update (id: string, entity: TEntity): Promise<IServiceResult<void>> {
        console.log('add')

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
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }
            console.log(res)
            return res
        }

        return { status: response.status }
    }

    async delete (id: string): Promise<TEntity> {
        console.log('delete')
        const response = await httpCLient.delete(
            `/${this._path}/${id}`,
            { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
        )
        console.log(response)
        const res = response.data as TEntity
        return res
    }
}
