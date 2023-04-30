<template>
    <Header v-if="isAuthenticated" title="" back="home"/>

    <h4>Create Import</h4>
    <div class="row">
        <div class="col-md-12">
            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Store</label>
                <select class="col-sm-2" v-model="storeId">
                    <option v-for="option in getShopList()" v-bind:key="option.id" :value="option.id">
                        {{ option.name }}
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Import as plain text</label>
                <textarea v-model="listDataString" class="form-control" style="min-width: 100%"></textarea>
            </div>
            <div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Import" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { ProductService } from '@/bll/service/ProductService'
import { Options, Vue } from 'vue-class-component'
import Logger from '@/util/logger'
import router from '@/router'
import { IProductImport } from '@/dal/domain/IProductImport'
import { IShop } from '@/dal/domain/IShop'
import { useShopStore } from '@/stores/shop'
import { ShopService } from '@/bll/service/ShopService'
import DistanceUtil from '@/util/distance-util'
import { useIdentityStore } from '@/stores/identity'
import { IdentityService } from '@/bll/service/IdentityService'
import Header from '@/components/Header.vue'

/**
 * @author Ahto Jalak
 * @since 15.04.2023
 */
@Options({
    components: {
        Header,
    },
    props: {
        barcode: String,
    },
    emits: [],
})
export default class ProductImport extends Vue {
    private logger = new Logger(ProductImport.name)
    private productService = new ProductService()
    private shopService = new ShopService()
    private shopStore = useShopStore()
    private identityStore = useIdentityStore()
    private identityService = new IdentityService()

    listDataString = 'barcode;name;description;url;price'
    storeId = 0
    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const obj: IProductImport = {
            storeId: this.storeId,
            productImportItems: this.convertToJson(this.listDataString),
        }
        this.logger.info(obj as string)

        await this.productService.import(obj).then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    router.push('/')
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

    convertToJson (input: string) {
        const lines = input.split(/\n/)
        const header = lines[0].split(/\\t|\n|;|\t/)
        const output = lines.slice(1).map(line => {
            const fields = line.split(/\\t|\n|;|\t/)
            return Object.fromEntries(header.map((h, i) => [h, fields[i]]))
        })

        return output
    }

    get isAuthenticated (): boolean {
        return this.identityService.isAuthenticated()
    }
}
</script>
