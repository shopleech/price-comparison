<template>
    <div>
        <h2> Cheat Sheet Samples </h2>
        <div> {{result}} </div>
        <form v-on:submit.prevent="submitForm">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" placeholder="Your name" v-model="form.name">
            </div>
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" placeholder="name@example.com"
                       v-model="form.email">
            </div>
            <div class="form-group">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="terms" value="yes" v-model="form.terms">
                    <label class="form-check-label" for="terms">Agree to Terms & Conditions</label>
                </div>
            </div>
            <div class="form-group">
                <button class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import HttpClient from '@/http-client'
import CameraScanner from '../components/CameraScanner.vue'

@Options({
    components: {
    },
    data () {
        return {
            result: null
        }
    },
    methods: {
    },
    props: {
    }
})
export default class AboutView extends Vue {
    form = {
        name: '',
        email: '',
        gender: '',
        refer: '',
        profession: [],
        message: '',
        satisfaction: '5',
        interested: [],
        terms: false
    }

    async submitForm () {
        await HttpClient.post('/v1/about', this.form)
            .then((res) => {
                // Perform Success Action
                console.log(res)
            })
            .catch((error) => {
                // error.response.status Check status code
                console.log(error)
            }).finally(() => {
                // Perform action in always
                console.log("submit finished")
            });
    }
}
</script>
