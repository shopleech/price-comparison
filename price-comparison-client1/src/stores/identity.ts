import { defineStore } from 'pinia'
import { IJwtResponse } from '@/domain/IJwtResponse'
import { ILoginInfo, IRegisterInfo } from '@/domain/ILoginInfo'
import { IdentityService } from '@/services/IdentityService'

export const useIdentityStore = defineStore({
    id: 'identity',
    state: () => ({
        jwt: null as IJwtResponse | null
    }),
    getters: {
    },
    actions: {
        getJwt (): IJwtResponse | null {
            return this.jwt
        },
        setJwt (jwtStr: IJwtResponse | null) {
            console.log("asd")
            console.log(jwtStr)
            this.jwt = jwtStr
        },
        isUser (): boolean {
            if (this.jwt !== null) {
                return this.jwt.roles.split(",").indexOf("user") > -1
            }
            return false
        },
        isAdmin (): boolean {
            if (this.jwt !== null) {
                return this.jwt.roles.split(",").indexOf("admin") > -1
            }
            return false
        },
        async authenticateUser (loginInfo: ILoginInfo): Promise<boolean> {
            const identityService = new IdentityService()
            const jwt = await identityService.login(loginInfo)
            console.log(jwt)
            console.log("asdasd")
            if (jwt.status === 200) {
                this.setJwt(jwt.data!)
            }
            return jwt !== null
        },
        async registerUser (registerDTO: IRegisterInfo): Promise<boolean> {
            const identityService = new IdentityService()
            const jwt = await identityService.register(registerDTO)
            if (jwt.status === 200) {
                this.setJwt(jwt.data!)
            }
            return jwt !== null
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
