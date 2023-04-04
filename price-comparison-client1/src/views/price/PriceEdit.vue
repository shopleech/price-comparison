<template>
    <h2>Edit wish {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'wishes' }">Back to Pricees</RouterLink>
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
export default class PriceEdit extends Vue {
    private logger = new Logger(PriceEdit.name)
    id!: string
}
</script>
