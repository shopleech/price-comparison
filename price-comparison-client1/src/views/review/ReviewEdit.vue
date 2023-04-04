<template>
    <h2>Edit rating {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'ratings' }">Back to Reviews</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class ReviewEdit extends Vue {
    private logger = new Logger(ReviewEdit.name)
    id!: string
}
</script>
