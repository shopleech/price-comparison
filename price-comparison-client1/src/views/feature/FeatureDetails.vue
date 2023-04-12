<template>
    <h2>Details wish {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'wishes-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'wishes-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'wishes-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'wishes' }">Back to Featurees</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { useFeatureStore } from '@/stores/feature'
import { FeatureService } from '@/bll/service/FeatureService'
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
export default class FeatureDetails extends Vue {
    private logger = new Logger(FeatureDetails.name)
    id!: string
    wishesStore = useFeatureStore()
    wishService = new FeatureService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.wishesStore.$state.feature =
        //     await this.wishService.get(this.id)
    }
}

</script>
