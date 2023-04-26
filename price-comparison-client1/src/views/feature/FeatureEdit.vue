<template>
    <h2>Edit wish {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'wishes' }">Back to Featurees</RouterLink>
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
export default class FeatureEdit extends Vue {
    private logger = new Logger(FeatureEdit.name)
    id!: string
}
</script>
