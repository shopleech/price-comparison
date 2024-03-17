<script setup>
import { googleSdkLoaded } from "vue3-google-login"
const login = () => {
  googleSdkLoaded((google) => {
    google.accounts.oauth2.initCodeClient({
      client_id: '431621825092-e2pah2n24bubb2qk5asti0p5dnc66t7l.apps.googleusercontent.com',
      scope: 'email profile openid',
      callback: (response) => {
        console.log("Handle the response", response)
      }
    }).requestCode()
  })
}
</script>

<template>
  <button @click="login">Login Using Google</button>
</template>