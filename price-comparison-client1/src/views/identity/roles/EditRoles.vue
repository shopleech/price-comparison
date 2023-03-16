<template>
<div class="row">
    <div class="col-2"></div>
    <div class="col-10">
        <form>
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">User</label>
                <select class="custom-select" id="exampleInputPassword1">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">admin</label>
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck2">
                <label class="form-check-label" for="exampleCheck2">user</label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
</template>

<script>
import { Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import router from '@/router'

export default class EditRoles extends Vue {
    private identityStore = useIdentityStore()
    private wasOk: boolean | null = null

    email = ''
    password = ''
    errorMsg: string | null = null

    submitClicked (): void {
        console.log('submitClicked')
        const info = {
            email: this.email,
            password: this.password
        }
        this.identityStore.setUserRole(info)
            .then((isLoggedIn: boolean) => {
                this.wasOk = isLoggedIn
                console.log('after auth user got: ' + this.wasOk)
            })
        router.push('/')
    }
}
</script>

<style scoped>

</style>
