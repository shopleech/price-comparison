<template>
    <h2>Details rating {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'ratings-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'ratings-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'ratings-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'ratings' }">Back to Reviews</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { ReviewService } from '@/bll/service/ReviewService'
import { useReviewStore } from '@/stores/review'
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
export default class ReviewDetails extends Vue {
    private logger = new Logger(ReviewDetails.name)
    id!: string
    reviewStore = useReviewStore()
    ratingService = new ReviewService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.reviewStore.$state.review =
        //     await this.ratingService.get(this.id)
    }
}

</script>
