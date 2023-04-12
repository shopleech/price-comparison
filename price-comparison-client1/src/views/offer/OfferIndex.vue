<template>
    <div class="row">
        <div class="col-2 border p-4">
            <p>
                <RouterLink :to="{ name: 'merchandises-create' }">Upload CSV file</RouterLink>
            </p>
            <p>
                What now?
            </p>
            <p>
                Merchant steps:
            </p>
            <ul class="list-group-numbered px-0">
                <li>Signup and login</li>
                <li>Add your product</li>
                <li>Compete offers</li>
            </ul>
            <p>
                Consumer steps:
            </p>
            <ul class="list-group-numbered px-0">
                <li>Signup and login</li>
                <li>Search and filter</li>
                <li>Compare offers</li>
            </ul>
        </div>
        <div class="col-10 p-4">
            <h2>Your stock</h2>
            <div v-for="item of limitArray(5)" :key="item.id" class="border p-2 mb-4">
                <h3>{{ item.name }}</h3>
                <div>{{ item.barcode }}</div>
                <small v-if="isAdmin">
                    <RouterLink :to="{ name: 'merchandises-details', params: { id: item.id } }">Details</RouterLink>
                    |
                    <RouterLink :to="{ name: 'merchandises-edit', params: { id: item.id } }">Edit</RouterLink>
                    |
                    <RouterLink :to="{ name: 'merchandises-delete', params: { id: item.id } }">Delete</RouterLink>
                </small>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { OfferService } from '@/bll/service/OfferService'
import { Options, Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import { useOfferStore } from '@/stores/offer'
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
export default class OfferIndex extends Vue {
    private logger = new Logger(OfferIndex.name)
    merchandisesStore = useOfferStore()
    merchandiseService = new OfferService()
    private identityStore = useIdentityStore()

    get isAuthenticated (): boolean {
        return this.identityStore.getJwt() !== null
    }

    get isAdmin (): boolean {
        return this.identityStore.isAdmin()
    }

    limitArray () {
        return this.merchandisesStore.$state.offers
    }

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        // this.merchandisesStore.$state.offers =
        //     await this.merchandiseService.getAll()
    }
}

</script>
