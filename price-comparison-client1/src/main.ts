import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

import 'jquery/src/jquery'
import 'popper.js/dist/popper.js'
import "bootstrap/dist/js/bootstrap.js"

createApp(App)
    .use(createPinia())
    .use(router)
    .mount('#app')
