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
        <RouterLink :to="{ name: 'wishes' }">Back to Useres</RouterLink>
    </div>
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
    props: {
        id: String
    },
    emits: [],
})
export default class UserDetails extends Vue {
    private logger = new Logger(UserDetails.name)
    id!: string
    userStore = useUserStore()
    userService = new UserService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.userStore.$state.user =
            await this.userService.get(this.id)
    }
}

</script>
