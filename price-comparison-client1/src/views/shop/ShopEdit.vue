<template>
    <h2>Edit rating {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'ratings' }">Back to Shops</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'

/**
 * @author Ahto Jalak
 * @since 31.03.2023
 */
@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class ShopEdit extends Vue {
    private logger = new Logger(ShopEdit.name)
    id!: string
}
</script>
