<template>
    <h2>Edit product {{ id }}</h2>
    <div class="row">

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
export default class ProductEdit extends Vue {
    private logger = new Logger(ProductEdit.name)
    id!: string
}
</script>
