import { defineStore } from 'pinia'
import { IJwtResponse } from '@/domain/IJwtResponse'
import { ILoginInfo, IRegisterInfo } from '@/domain/ILoginInfo'
import { IdentityService } from '@/services/IdentityService'
import { IResponseMessage } from '@/domain/IResponseMessage'

export const useIdentityStore = defineStore({
    id: 'identity',
    state: () => ({
        jwt: null as IJwtResponse | null
    }),
    getters: {},
    actions: {
        getJwt (): IJwtResponse | null {
            return this.jwt
        },
        setJwt (jwtStr: IJwtResponse | null) {
            this.jwt = jwtStr
        },
        isUser (): boolean {
            if (this.jwt !== null) {
                return this.jwt.roles.split(',').indexOf('user') > -1
            }
            return false
        },
        isAdmin (): boolean {
            if (this.jwt !== null) {
                return this.jwt.roles.split(',').indexOf('admin') > -1
            }
            return false
        },
        async authenticateUser (loginInfo: ILoginInfo): Promise<IResponseMessage> {
            const identityService = new IdentityService()
            const serviceResult = await identityService.login(loginInfo)
            if (serviceResult.status === 200) {
                this.setJwt(serviceResult.data!)
                return {
                    data: serviceResult.data,
                }
            }
            return {
                errorMsg: serviceResult.errorMsg,
            }
        },
        async registerUser (registerDTO: IRegisterInfo): Promise<IResponseMessage> {
            const identityService = new IdentityService()
            const jwt = await identityService.register(registerDTO)
            if (jwt.status === 200) {
                this.setJwt(jwt.data!)
                return {
                    data: jwt.data
                }
            }
            return {
                errorMsg: jwt.errorMsg
            }
        },
        async refreshUser (): Promise<boolean> {
            const identityService = new IdentityService()
            const res = await identityService.refreshIdentity()
            if (res !== null) {
                this.setJwt(res.data!)
                return true
            }
            return false
        },
        clearJwt (): void {
            this.setJwt(null)
        },
    },
})
