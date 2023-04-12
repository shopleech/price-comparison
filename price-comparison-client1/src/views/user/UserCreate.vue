<template>
    <h4>Create wish</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'wishes' }">Back to Useres</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { CustomerService } from '@/bll/service/CustomerService'
import { UserService } from '@/bll/service/UserService'
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
export default class UserCreate extends Vue {
    private logger = new Logger(UserCreate.name)
    userStore = useUserStore()
    userService = new UserService()
    customerService = new CustomerService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const res = await this.userService.add(
            {}
        )

        if (res.status == null || res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            // this.wishesStore.$state.wishes =
            //     await this.wishService.getAll()

            this.$router.push('/wishes')
        }
    }
}
</script>
