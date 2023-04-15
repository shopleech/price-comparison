<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h2>Create Import</h2>
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
                <label class="col-sm-2 control-label">Import as plain text (barcode;title;price):</label>
                <textarea v-model="listDataString" rows="20" cols="80"></textarea>
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
import Logger from '@/logger'
import router from '@/router'
import { IProductImport } from '@/dal/domain/IProductImport'
import { IShop } from '@/dal/domain/IShop'
import { useShopStore } from '@/stores/shop'
import { ShopService } from '@/bll/service/ShopService'

/**
 * @author Ahto Jalak
 * @since 15.04.2023
 */
@Options({
    components: {},
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

    listDataString = ''
    storeId = 0
    errorMsg: string | null = null

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const obj: IProductImport = {
            storeId: this.storeId,
            productImportItems: JSON.parse(this.listDataString),
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

        this.shopService.getAll().then((items) => {
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    this.shopStore.$state.shops = items.data
                }
            }
        })
    }

    getShopList (): IShop[] {
        return this.shopStore.$state.shops
    }
}
</script>
