<template>
    <div>Hwee {{ hello }} {{ name }}</div>
</template>

<script>
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        name: String, // string interface
    }
})
export default class Greeting extends Vue {
    hello = "Greetings, " + this.name;
}
</script>

<style scoped>

</style>
