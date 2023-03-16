<template>
    <h2>Edit merchandise {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'merchandises' }">Back to Merchandises</RouterLink>
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
export default class MerchandiseEdit extends Vue {
    id!: string
}
</script>
