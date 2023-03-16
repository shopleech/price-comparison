<template>
    <h4>Delete merchandise {{ id }}</h4>
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
export default class MerchandiseDelete extends Vue {
    id!: string;
    merchandisesStore = useMerchandisesStore()
    merchandiseService = new MerchandiseService()

    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        console.log('submitClicked')

        const res = await this.merchandiseService.delete(this.id);

        /*
        if (res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            this.merchandisesStore.$state.merchandises =
                await this.merchandiseService.getAll()

            this.$router.push('/merchandises')
        }
         */
    }
}
</script>
