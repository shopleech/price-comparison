import type { IJwtResponse } from '@/domain/IJwtResponse'
import httpCLient from '@/http-client'
import { useIdentityStore } from '@/stores/identity'
import type { AxiosError } from 'axios'
import type { IServiceResult } from './IServiceResult'
import { ILoginInfo, IRegisterInfo } from '@/domain/ILoginInfo'

export class IdentityService {
    private identityStore = useIdentityStore()

    async login (loginInfo: ILoginInfo): Promise<IServiceResult<IJwtResponse>> {
        try {
            const response = await httpCLient.post('/v1/auth/login', loginInfo)
            return {
                status: response.status,
                data: response.data as IJwtResponse
            }
        } catch (e) {
            if ((e as AxiosError).response) {
                return {
                    status: (e as AxiosError).response!.status,
                    errorMsg: (e as AxiosError).response!.statusText,
                }
            } else {
                console.log((e as AxiosError).message);
                return {
                    status: 400,
                    errorMsg: 'unknown: ' + (e as AxiosError).message,
                }
            }
        }
    }

    async refreshIdentity (): Promise<IServiceResult<IJwtResponse>> {
        try {
            const response = await httpCLient.post('/v1/auth/refresh', {
                token: this.identityStore.$state.jwt?.token,
                refreshToken: this.identityStore.$state.jwt?.refreshToken
            })
            return {
                status: response.status,
                data: response.data as IJwtResponse,
            }
        } catch (e) {
            const err = e as AxiosError
            return {
                status: err.response!.status,
                errorMsg: err.response!.statusText,
            }
        }
    }

    async register (registerInfo: IRegisterInfo): Promise<IServiceResult<IJwtResponse>> {
        try {
            const response = await httpCLient.post('/v1/auth/register', registerInfo)
            return {
                status: response.status,
                data: response.data as IJwtResponse,
            }
        } catch (e) {
            const err = e as AxiosError
            return {
                status: err.response!.status,
                errorMsg: err.response!.statusText,
            }
        }
    }
}
