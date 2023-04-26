<template>
    <h2>Edit alarm {{ id }}</h2>
    <div class="row">

    </div>

    <div>
        <RouterLink :to="{ name: 'alarms' }">Back to Alarms</RouterLink>
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
export default class AlarmEdit extends Vue {
    private logger = new Logger(AlarmEdit.name)
    id!: string
}
</script>
