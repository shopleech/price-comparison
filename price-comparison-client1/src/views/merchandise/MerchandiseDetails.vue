<template>
    <h2>Details merchandise {{ id }}</h2>
    <div class="row">
        <div class="col-md-12">
            <div>
                <RouterLink :to="{ name: 'merchandises-details', params: { id: id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'merchandises-edit', params: { id: id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'merchandises-delete', params: { id: id } }">Delete</RouterLink>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'merchandises' }">Back to Merchandises</RouterLink>
    </div>
</template>

<script lang="ts">
import { MerchandiseService } from '@/services/MerchandiseService'
import { useMerchandisesStore } from '@/stores/merchandises'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {
        id: String
    },
    emits: [],
})
export default class MerchandiseDetails extends Vue {
    id!: string;
    merchandisesStore = useMerchandisesStore()
    merchandiseService = new MerchandiseService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.merchandisesStore.$state.merchandise =
            await this.merchandiseService.get(this.id);
    }
}

</script>
