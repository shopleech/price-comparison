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
import { Options, Vue } from 'vue-class-component'
import { UserService } from '@/services/UserService'
import { useUserStore } from '@/stores/user'
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
export default class UserIndex extends Vue {
    private logger = new Logger(UserIndex.name)
    userStore = useUserStore()
    userService = new UserService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.userStore.$state.users =
            await this.userService.getAll()
    }
}

</script>
