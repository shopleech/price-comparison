<template>
    <h1>Login</h1>

    <div className="row">
        <div className="col-md-12">

            <div v-if="errorMsg != null" className="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div className="form-group">
                    <label className="control-label" htmlFor="firstname">email</label>
                    <input v-model="email" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="lastname">password</label>
                    <input v-model="password" className="form-control" type="password"/>
                </div>
                <div className="form-group">
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

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class Login extends Vue {
    private identityStore = useIdentityStore()
    private loginWasOk: boolean | null = null

    email = ''
    password = ''
    errorMsg: string | null = null

    loginClicked (): void {
        console.log('submitClicked')
        const loginInfo: ILoginInfo = {
            email: this.email,
            password: this.password
        }
        this.identityStore.authenticateUser(loginInfo)
            .then((isLoggedIn: boolean) => {
                this.loginWasOk = isLoggedIn
                console.log('after auth user got: ' + this.loginWasOk)
                router.push('/')
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
