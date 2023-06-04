<template>
    <Header v-if="isAuthenticated" title="" back="home"/>

    <h4>Hinnateavituse lisamine</h4>
    <div class="row">
        <div class="col-md-12">
            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div class="row">
                <div class="col-4">Toote nimi</div>
                <div class="col-8">{{ getProduct().name }}</div>
            </div>
            <div class="row">
                <div class="col-4">Triipkood</div>
                <div class="col-8">{{ getProduct().barcode }}</div>
            </div>
            <div class="row">
                <div class="col-4">Hetke hind</div>
                <div class="col-8">{{ getProduct().minPrice }}</div>
            </div>
            <div class="form-group">
                <label class="col-4 control-label">Hinna muutuse samm</label>
                <input type="text" class="col-8" v-model="minStep" style="width: 100px;"/>
            </div>
            <div class="form-group">
                <label class="col-4 control-label">Hinna alumine limiit</label>
                <input type="text" class="col-8" v-model="minValue" style="width: 100px;"/>
            </div>
            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Lisa hinnateavitus" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { AlarmService } from '@/bll/service/AlarmService'
import { useAlarmStore } from '@/stores/alarm'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'
import Header from '@/components/Header.vue'
import { IdentityService } from '@/bll/service/IdentityService'
import router from '@/router'
import { IAlarm } from '@/dal/domain/IAlarm'
import { ProductService } from '@/bll/service/ProductService'
import { useProductStore } from '@/stores/product'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {
        Header,
    },
    props: {
        id: Number,
    },
    emits: [],
})
export default class AlarmCreate extends Vue {
    private logger = new Logger(AlarmCreate.name)
    id!: number
    alarmStore = useAlarmStore()
    productStore = useProductStore()
    alarmService = new AlarmService()
    productService = new ProductService()
    private identityService = new IdentityService()

    minValue: number | undefined = undefined
    minStep: number | undefined = undefined
    errorMsg: string | null = null

    mounted () {
        this.productService.getById(this.id).then((item) => {
            if (item.errorMsg !== undefined) {
                this.productStore.$state.product = {}
            } else {
                if (item.data) {
                    this.productStore.$state.product = item.data
                }
            }
        })
    }

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const alarmInfo: IAlarm = {
            productId: this.id,
            minValue: this.minValue,
        }
        this.alarmService.add(alarmInfo).then((item) => {
            if (item.errorMsg !== undefined) {
                this.errorMsg = item.errorMsg
            } else {
                if (item.data) {
                    this.alarmStore.add(item.data)
                }
                router.push('/watchlist')
            }
        })
    }

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }

    getProduct () {
        return this.productStore.$state.product
    }
}
</script>
