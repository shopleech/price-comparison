<template>
    <Header v-if="isAuthenticated" title="" back="home"/>

    <h4>Uue pakkumise lisamine</h4>
    <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
        <ul>
            <li>{{ errorMsg }}</li>
        </ul>
    </div>
    <div class="form-group">
        <label class="col-4 control-label">Shop</label>
        <select class="col-8" v-model="shopId">
            <option v-for="option in getShopList()" v-bind:key="option.id" :value="option.id">
                {{ option.name }} ({{ distanceUtil.round(option.distance) }}km)
            </option>
        </select>
    </div>
    <div class="form-group">
        <label class="col-4 control-label">Price</label>
        <input type="text" class="col-8" v-model="price"/>
    </div>
    <div>
        <div class="form-group">
            <input @click="submitClicked()" type="submit" value="Add offer" class="btn btn-primary"/>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import HttpClient from '@/util/http-client'
import Papa from 'papaparse'
import { OfferService } from '@/bll/service/OfferService'
import { useOfferStore } from '@/stores/offer'
import Logger from '@/util/logger'
import { IdentityService } from '@/bll/service/IdentityService'
import Header from '@/components/Header.vue'
import router from '@/router'
import { IOffer } from '@/dal/domain/IOffer'
import { IPrice } from '@/dal/domain/IPrice'
import DistanceUtil from '@/util/distance-util'
import { IShop } from '@/dal/domain/IShop'
import { ShopService } from '@/bll/service/ShopService'
import { useShopStore } from '@/stores/shop'
import { useIdentityStore } from '@/stores/identity'

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
export default class OfferCreate extends Vue {
    private logger = new Logger(OfferCreate.name)
    offerStore = useOfferStore()
    offerService = new OfferService()
    private identityStore = useIdentityStore()
    private identityService = new IdentityService()
    private shopService = new ShopService()
    private shopStore = useShopStore()
    distanceUtil = new DistanceUtil()

    errorMsg: string | null = null
    uploadFile = ''
    content = []
    parsed = false
    shopId: number | undefined = undefined
    price: number | undefined = undefined
    id!: number

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const obj: IOffer = {
            productId: this.id,
            shopId: this.shopId,
            price: {
                amount: this.price
            } as IPrice
        }
        this.logger.info(obj as string)

        await this.offerService.add(obj).then((item) => {
            if (item.errorMsg !== undefined) {
                this.errorMsg = item.errorMsg
            } else {
                if (item.data) {
                    router.push('/product/details/' + this.id)
                }
            }
        })
    }

    mounted (): void {
        this.logger.info('mounted')
        const distanceUtil = new DistanceUtil()

        this.shopService.getAll().then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                const uniqueNodes = items.data
                if (uniqueNodes) {
                    for (let i = 0; i < uniqueNodes.length; i++) {
                        uniqueNodes[i].distance = distanceUtil.calculateDistance(
                            this.identityStore.getCoords().latitude ?? 59.436962,
                            this.identityStore.getCoords().longitude ?? 24.753574,
                            uniqueNodes[i].latitude ?? 59.436962,
                            uniqueNodes[i].longitude ?? 24.753574,
                            "K"
                        );
                    }

                    uniqueNodes.sort(function(a, b) {
                        return (a.distance ?? 0) - (b.distance ?? 0);
                    });

                    this.shopStore.$state.shops = uniqueNodes
                }
            }
        })
    }

    getShopList (): IShop[] {
        return this.shopStore.$state.shops
    }

    parseFile () {
        Papa.parse(this.uploadFile, {
            header: true,
            skipEmptyLines: true,
            complete: (results: any) => {
                this.content = results
                this.parsed = true
            }
        })
    }

    async submitFile () {
        const formData = new FormData()

        formData.append('file', this.uploadFile)

        await HttpClient.post('/preview-file',
            formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }
        )
    }

    async submitUpdates () {
        this.offerService.upload(this.content)
    }

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }
}
</script>
