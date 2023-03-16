import type { IJwtResponse } from '@/domain/IJwtResponse'
import httpCLient from '@/http-client'
import { useIdentityStore } from '@/stores/identity'
import type { AxiosError } from 'axios'
import type { IServiceResult } from './IServiceResult'
import { ILoginInfo, IRegisterInfo } from '@/domain/ILoginInfo'

export class IdentityService {
    identityStore = useIdentityStore()

    async login (loginInfo: ILoginInfo): Promise<IServiceResult<IJwtResponse>> {
        try {
            const response = await httpCLient.post('/v1/auth/login', loginInfo)
            return {
                status: response.status,
                data: response.data as IJwtResponse
            }
        } catch (e) {
            const response = {
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }

            console.log(response)

            console.log((e as AxiosError).response)

            return response
        }
    }

    async refreshIdentity (): Promise<IServiceResult<IJwtResponse>> {
        try {
            console.log(this.identityStore.$state.jwt)

            const response = await httpCLient.post('/v1/auth/refresh',
                {
                    token: this.identityStore.$state.jwt?.token,
                    refreshToken: this.identityStore.$state.jwt?.refreshToken
                }
            )
            return {
                status: response.status,
                data: response.data as IJwtResponse
            }
        } catch (e) {
            const response = {
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }

            console.log(response)

            console.log((e as AxiosError).response)

            return response
        }
    }

    async register (registerInfo: IRegisterInfo): Promise<IServiceResult<IJwtResponse>> {
        try {
            const response = await httpCLient.post('/v1/auth/register', registerInfo)
            return {
                status: response.status,
                data: response.data as IJwtResponse
            }
        } catch (e) {
            const response = {
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data,
            }

            console.log(response)

            console.log((e as AxiosError).response)

            return response
        }
    }
}
