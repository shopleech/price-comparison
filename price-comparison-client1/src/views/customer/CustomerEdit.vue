<template>
    <h2>Edit customer {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'customers' }">Back to Customers</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class CustomerEdit extends Vue {
    id!: string
}
</script>
