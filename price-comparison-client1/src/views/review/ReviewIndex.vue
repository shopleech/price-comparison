<template>
    <Header v-if="isAuthenticated" title="" back="home"/>

    <h4>Hinnangud</h4>
    <div v-for="item of getReviews()" :key="item.id" class="border p-2 mb-4 row">
        <div class="col-2">
            <img src="https://placehold.co/50x50/EEE/31343C?font=playfair-display&text=Product" alt="product"/>
        </div>
        <div class="col-6">
            <RouterLink :to="{ name: 'product-details', params: { id: item.productId } }" class="text-dark">
                {{ item.id }}
            </RouterLink><br/>
            <small>{{ item.productId }}</small>
        </div>
        <div class="col-2">
            <RouterLink :to="{ name: 'product-details', params: { id: item.productId } }" class="text-dark">
                {{ item.productId }}
            </RouterLink>
        </div>
        <div class="col-2">
        </div>
    </div>
</template>

<script lang="ts">
import { OfferService } from '@/bll/service/OfferService'
import { Options, Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import { IReview } from '@/dal/domain/IReview'
import router from '@/router'
import { ReviewService } from '@/bll/service/ReviewService'
import { useReviewStore } from '@/stores/review'
import Logger from '@/util/logger'
import { IdentityService } from '@/bll/service/IdentityService'
import Header from '@/components/Header.vue'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
        Header,
    },
    props: {},
    emits: [],
})
export default class ReviewIndex extends Vue {
    private logger = new Logger(ReviewIndex.name)
    private reviewStore = useReviewStore()
    private reviewService = new ReviewService()
    private merchandiseService = new OfferService()
    private identityStore = useIdentityStore()
    public ratings: IReview[] = []
    private identityService = new IdentityService()

    get isAdmin (): boolean {
        return this.identityStore.isAdmin()
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        const items = await this.reviewService.getAll()
        this.reviewStore.$state.reviews = items.data as IReview[]
    }

    clickToProduct (merchandiseId: string): void {
        this.logger.info(merchandiseId)
        // this.merchandiseService.get(merchandiseId)
        //     .then(y => {
        //         router.push('/review/details/' + y.productId)
        //     })
    }

    clickToReviewDelete (ratingId: string): void {
        this.reviewService.delete(ratingId)
            .then(() => {
                router.push('/review')
            })
    }

    getReviews(): IReview[] {
        return this.reviewStore.$state.reviews
    }

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }
}

</script>
