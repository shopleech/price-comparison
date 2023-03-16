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
        <RouterLink :to="{ name: 'ratings' }">Back to Ratings</RouterLink>
    </div>
</template>

<script lang="ts">
import { RatingService } from '@/services/RatingService'
import { useRatingsStore } from '@/stores/ratings'
import { Options, Vue } from 'vue-class-component'
import router from '@/router'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class RatingDelete extends Vue {
    id!: string
    ratingsStore = useRatingsStore()
    ratingService = new RatingService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        await this.ratingService
            .delete(this.id)
            .then(entity => {
                console.log(entity.id)
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
