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
        <RouterLink :to="{ name: 'ratings' }">Back to Ratings</RouterLink>
    </div>
</template>

<script lang="ts">
import { RatingService } from '@/services/RatingService'
import { useRatingsStore } from '@/stores/ratings'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class RatingDetails extends Vue {
    id!: string;
    ratingsStore = useRatingsStore()
    ratingService = new RatingService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.ratingsStore.$state.rating =
            await this.ratingService.get(this.id);
    }
}

</script>
