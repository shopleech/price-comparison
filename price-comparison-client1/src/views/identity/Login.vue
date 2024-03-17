<template>
    <RouterLink :to="{ name: 'home' }">
        <i class="bi bi-backspace"></i>
    </RouterLink>
    <script src="https://accounts.google.com/gsi/client" async></script>

    <h2>Logi sisse</h2>
    <GoogleAuthButton/>
    <div className="row">
        <div className="col-sm">
            <div v-if="errorMsg != null" className="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div class="form-group">
                <label className="control-label" htmlFor="firstname">e-mail</label>
                <input v-model="email" className="form-control" type="text"/>
            </div>
            <div className="form-group">
                <label className="control-label" htmlFor="lastname">parool</label>
                <input v-model="password" className="form-control" type="password"/>
            </div>
            <div class="row">
                <div class="col-6">
                    <button @click="loginClicked" class="btn btn-primary w-100">
                        Logi sisse
                    </button>
                </div>
            </div>
        </div>
    </div>

</template>

<script lang="ts">
import { useIdentityStore } from '@/stores/identity'
import { Options, Vue } from 'vue-class-component'
import { ILoginInfo } from '@/dal/domain/ILoginInfo'
import router from '@/router'
import { IResponseMessage } from '@/dal/domain/IResponseMessage'
import Logger from '@/util/logger'
import axios from 'axios'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
        GoogleAuthButton
    },
    data () {
        return {
            asd: 'asd',
        }
    },
    computed: {
        gapi() {
            return window.gapi;
        }
    },
    methods: {
        GoogleAuthSuccess(user : any) {
            var auth = user.getAuthResonse();
            axios.post("http://localhost:5056/auth/token/"+ auth.id_token).then(() => (console.log('success'))).catch(err => console.log(err));
        },
        GoogleAuthFail(err: any) {
            console.log(err);
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
        this.gapi.load("auth2", () => {
           var auth2 = this.gapi.auth2.init({client_id: "431621825092-e2pah2n24bubb2qk5asti0p5dnc66t7l.apps.googleusercontent.com"});
           auth2.attachClickHandler(document.getElementById('google-auth-button'), {}, this.GoogleAuthSuccess, this.GoogleAuthFail);
        });
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
