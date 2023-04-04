<template>
    <div>Hwee {{ hello }} {{ name }}</div>
    <ul>
        <li class="nav-item" v-if="!isAuthenticated">
            <router-link class="nav-link" to="/identity/register">Register</router-link>
        </li>
        <li class="nav-item" v-if="!isAuthenticated">
            <router-link class="nav-link" to="/identity/login">Login</router-link>
        </li>
        <li class="nav-item" v-if="isAuthenticated">
            <a class="nav-link" @click="logoutClicked()">Logout</a>
        </li>
        <li class="nav-item" v-if="isAuthenticated">
            <a class="nav-link" @click="refreshTokenClicked()">Refresh token</a>
        </li>
    </ul>
</template>

<script>
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        name: String, // string interface
    }
})
export default class Identity extends Vue {
    hello = 'Greetings, ' + this.name
}
</script>

<style scoped>

</style>
