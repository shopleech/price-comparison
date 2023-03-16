<template>
    <h2>List of wishes</h2>
    <p>
        <RouterLink :to="{ name: 'wishes-create' }">Create new</RouterLink>
    </p>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Customer Id</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item of wishes" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.customerId }}</td>
            <td>
                <RouterLink :to="{ name: 'wishes-details', params: { id: item.id } }">Details</RouterLink>
                |
                <RouterLink :to="{ name: 'wishes-edit', params: { id: item.id } }">Edit</RouterLink>
                |
                <RouterLink :to="{ name: 'wishes-delete', params: { id: item.id } }">Delete</RouterLink>
            </td>
        </tr>
        </tbody>
    </table>
</template>

<script lang="ts">
import { WishService } from '@/services/WishService'
import { useWishesStore } from '@/stores/wishes'
import { Options, Vue } from 'vue-class-component'

@Options({
    components: {},
    props: {},
    emits: [],
})
export default class WishIndex extends Vue {
    wishesStore = useWishesStore()
    wishService = new WishService()

    async mounted (): Promise<void> {
        console.log('mounted')
        this.wishesStore.$state.wishes =
            await this.wishService.getAll()
    }
}

</script>
