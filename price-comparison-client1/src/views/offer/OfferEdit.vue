<template>
    <h2>Edit merchandise {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'merchandises' }">Back to Offers</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'

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
export default class OfferEdit extends Vue {
    private logger = new Logger(OfferEdit.name)
    id!: string
}
</script>
