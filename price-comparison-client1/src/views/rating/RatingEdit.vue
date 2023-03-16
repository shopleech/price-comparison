<template>
    <h2>Edit rating {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'ratings' }">Back to Ratings</RouterLink>
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
export default class RatingEdit extends Vue {
    id!: string
}
</script>
