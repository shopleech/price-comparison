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
        <RouterLink :to="{ name: 'wishes' }">Back to Rolees</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { RoleService } from '@/services/RoleService'
import { useRoleStore } from '@/stores/role'
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
export default class RoleDetails extends Vue {
    id!: string

    private logger = new Logger(RoleDetails.name)

    wishesStore = useRoleStore()
    wishService = new RoleService()

    async mounted (): Promise<void> {
        this.logger.info('mounted')
        this.wishesStore.$state.role =
            await this.wishService.get(this.id)
    }
}

</script>
