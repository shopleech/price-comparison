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
                    <input @click="loginClicked()" type="submit" value="Login" class="btn btn-primary"/>
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
    private identityStore = useIdentityStore()
    private loginWasOk: boolean | null = null
    private errorMsg: string | null = null
    private email = ''
    private password = ''

    loginClicked (): void {
        console.log('submitClicked')
        const loginInfo: ILoginInfo = {
            email: this.email,
            password: this.password
        }
        this.identityStore.authenticateUser(loginInfo)
            .then((response: IResponseMessage) => {
                console.log(response)
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
        console.log('beforeCreate')
    }

    created (): void {
        console.log('created')
    }

    beforeMount (): void {
        console.log('beforeMount')
    }

    mounted (): void {
        console.log('mounted')
    }

    beforeUpdate (): void {
        console.log('beforeUpdate')
    }

    updated (): void {
        console.log('updated')
    }

    beforeDestroy (): void {
        console.log('beforeDestroy')
    }

    destroyed (): void {
        console.log('destroyed')
    }
}
</script>
