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
        <RouterLink :to="{ name: 'wishes' }">Back to Wishes</RouterLink>
    </div>
</template>

<script lang="ts">
import { WishService } from '@/services/WishService'
import { useWishesStore } from '@/stores/wishes'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class WishDetails extends Vue {
    id!: string;
    wishesStore = useWishesStore()
    wishService = new WishService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.wishesStore.$state.wish =
            await this.wishService.get(this.id);
    }
}

</script>
