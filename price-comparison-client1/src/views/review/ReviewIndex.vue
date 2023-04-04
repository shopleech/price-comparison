<template>
    <div class="row">
        <div class="col-lg-3 border p-2">
            <p>What now?</p>
        </div>
        <div class="col-lg-9">
            <h3>Your product reviews</h3>
            <div v-if="isAuthenticated">
                <div v-for="item of ratings" :key="item.id" class="row p-2">
                    <div class="col-3">
                        <a href="#" @click="clickToProduct(item.merchandiseId)">
                            <img src="https://via.placeholder.com/200x50.png?text=product" alt="product"/>
                        </a>
                    </div>
                    <div class="col-6">
                        <h3>{{ item.productName }}</h3>
                        <p>
                            {{ item.description }}
                        </p>
                        <p>Score: {{ item.score }}</p>
                    </div>
                    <div class="col-3">
                        <a href="#" @click="clickToReviewDelete(item.id)">
                            Delete
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { OfferService } from '@/services/OfferService'
import { Options, Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import { IReview } from '@/domain/IReview'
import router from '@/router'
import { ReviewService } from '@/services/ReviewService'
import { useReviewStore } from '@/stores/review'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class ReviewIndex extends Vue {
    private logger = new Logger(ReviewIndex.name)
    private ratingsStore = useReviewStore()
    private ratingService = new ReviewService()
    private merchandiseService = new OfferService()
    private identityStore = useIdentityStore()
    public ratings: IReview[] = []

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    get isAdmin (): boolean {
        return this.identityStore.isAdmin()
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.ratings =
            await this.ratingService.getAll()
    }

    clickToProduct (merchandiseId: string): void {
        this.merchandiseService.get(merchandiseId)
            .then(y => {
                router.push('/review/details/' + y.productId)
            })
    }

    clickToReviewDelete (ratingId: string): void {
        this.ratingService.delete(ratingId)
            .then(() => {
                router.push('/review')
            })
    }
}

</script>
