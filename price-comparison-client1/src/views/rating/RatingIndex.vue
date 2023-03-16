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
                        <a href="#" @click="clickToRatingDelete(item.id)">
                            Delete
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { RatingService } from '@/services/RatingService'
import { MerchandiseService } from '@/services/MerchandiseService'
import { useRatingsStore } from '@/stores/ratings'
import { Options, Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import { IRating } from '@/domain/IRating'
import router from '@/router'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class RatingIndex extends Vue {
    private ratingsStore = useRatingsStore()
    private ratingService = new RatingService()
    private merchandiseService = new MerchandiseService()
    private identityStore = useIdentityStore()
    public ratings: IRating[] = []

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    get isAdmin (): boolean {
        return this.identityStore.isAdmin()
    }

    async mounted (): Promise<void> {
        console.log('mounted')
        this.ratings =
            await this.ratingService.getAll()
    }

    clickToProduct (merchandiseId: string): void {
        this.merchandiseService.get(merchandiseId)
            .then(y => {
                console.log(y)
                router.push('/products/details/' + y.productId)
            })
    }

    clickToRatingDelete(ratingId: string) : void {
        this.ratingService.delete(ratingId)
            .then(y => {
                console.log(y)
                router.push('/ratings')
            })
    }
}

</script>
