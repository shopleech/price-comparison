<template>
    <h4>Delete rating {{ id }}</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Delete" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'ratings' }">Back to Reviews</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import router from '@/router'
import { useReviewStore } from '@/stores/review'
import { ReviewService } from '@/bll/service/ReviewService'
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
export default class ReviewDelete extends Vue {
    private logger = new Logger(ReviewDelete.name)
    id!: string
    ratingsStore = useReviewStore()
    ratingService = new ReviewService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        await this.ratingService
            .delete(this.id)
            .then(() => {
                router.push('/ratings')
            })

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.ratingsStore.$state.ratings =
                await this.ratingService.getAll()

            this.$router.push('/ratings')
        }
         */
    }
}
</script>
