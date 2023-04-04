<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h2>Login</h2>
    <div className="row">
        <div className="col-sm">
            <div v-if="errorMsg != null" className="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <label className="control-label" htmlFor="firstname">email</label>
                    <input v-model="email" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="lastname">password</label>
                    <input v-model="password" className="form-control" type="password"/>
                </div>
                <div class="form-group">
                    <button @click="loginClicked" class="btn btn-primary">
                        Login
                    </button>
                </div>
                <div>
                    Forgot your password?
                </div>
            </div>
        </div>
    </div>

</template>

<script lang="ts">
import { useIdentityStore } from '@/stores/identity'
import { Options, Vue } from 'vue-class-component'
import { ILoginInfo } from '@/domain/ILoginInfo'
import router from '@/router'
import { IResponseMessage } from '@/domain/IResponseMessage'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    data () {
        return {
            asd: 'asd',
        }
    },
    props: {},
    emits: [],
})
export default class Login extends Vue {
    private logger = new Logger(Login.name)
    private identityStore = useIdentityStore()
    private loginWasOk: boolean | null = null
    private errorMsg: string | null = null
    private email = ''
    private password = ''

    loginClicked (): void {
        this.logger.info('submitClicked')
        const loginInfo: ILoginInfo = {
            email: this.email,
            password: this.password
        }
        this.identityStore.authenticateUser(loginInfo)
            .then((response: IResponseMessage) => {
                this.loginWasOk = response.errorMsg == null

                if (this.loginWasOk) {
                    router.push('/')
                } else {
                    if (response.errorMsg != null) {
                        this.errorMsg = response.errorMsg
                    }
                }
            })
    }

    // ============ Lifecycle methods ==========
    beforeCreate (): void {
        this.logger.info('beforeCreate')
    }

    created (): void {
        this.logger.info('created')
    }

    beforeMount (): void {
        this.logger.info('beforeMount')
    }

    mounted (): void {
        this.logger.info('mounted')
    }

    beforeUpdate (): void {
        this.logger.info('beforeUpdate')
    }

    updated (): void {
        this.logger.info('updated')
    }

    beforeDestroy (): void {
        this.logger.info('beforeDestroy')
    }

    destroyed (): void {
        this.logger.info('destroyed')
    }
}
</script>
