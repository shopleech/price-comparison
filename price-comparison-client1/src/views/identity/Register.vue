<template>
    <h1>Register</h1>

    <div className="row">
        <div className="col-md-12">

            <div v-if="errorMsg != null" className="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div className="form-group">
                    <label className="control-label" htmlFor="Email">email</label>
                    <input v-model="email" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="Password">password</label>
                    <input v-model="password" className="form-control" type="password"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="PasswordRepeat">password repeat</label>
                    <input v-model="passwordRepeat" className="form-control" type="password"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="firstname">First name</label>
                    <input v-model="firstname" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <label className="control-label" htmlFor="lastname">Last name</label>
                    <input v-model="lastname" className="form-control" type="text"/>
                </div>
                <div className="form-group">
                    <div class="form-check form-switch">
                        <input v-model="consent" class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                        <label class="form-check-label" for="flexSwitchCheckDefault">I agree with terms and
                            conditions</label>
                    </div>
                </div>
                <div className="form-group">
                    <input @click="registerClicked()" type="submit" value="Register" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

</template>

<script lang="ts">
import { useIdentityStore } from '@/stores/identity'
import { Options, Vue } from 'vue-class-component'
import { IRegisterInfo } from '@/domain/ILoginInfo'
import router from '@/router'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class Register extends Vue {
    private identityStore = useIdentityStore()
    private loginWasOk: boolean | null = null

    email = ''
    password = ''
    passwordRepeat = ''
    firstname = ''
    lastname = ''
    consent = ''
    errorMsg: string | null = null

    async registerClicked (): Promise<void> {
        console.log('submitClicked')
        const registerInfo: IRegisterInfo = {
            email: this.email,
            password: this.password,
            firstname: this.firstname,
            lastname: this.lastname,
        }
        this.identityStore.registerUser(registerInfo)
            .then((isLoggedIn: boolean) => {
                this.loginWasOk = isLoggedIn
                console.log('after auth user got: ' + this.loginWasOk)
                router.push('/')
            })
    }
}
</script>
