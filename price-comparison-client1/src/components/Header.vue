<template>
    <header>
        <RouterLink :to="{ name: 'home' }">
            <img src="https://placehold.co/200x60/EEE/31343C?font=playfair-display&text=Price%20Comparison" alt=""/>
        </RouterLink>
    </header>
</template>

<script>
import { Vue } from 'vue-class-component'

export default class Header extends Vue {
}
</script>

<style scoped>

</style>
