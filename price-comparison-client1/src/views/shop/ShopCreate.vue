<template>
    <RouterLink :to="{ name: 'home' }">
        <img src="https://via.placeholder.com/40x40.png?text=back" alt="back"/>
    </RouterLink>

    <h4>Create shop</h4>
    <div class="row">
        <div class="col-md-12">

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>
            <div>
                <div class="form-group">
                    <label className="control-label" htmlFor="firstname">Name</label>
                    <input v-model="name" className="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <label className="control-label" htmlFor="firstname">Address</label>
                    <input v-model="address" className="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <label className="control-label" htmlFor="firstname">Url</label>
                    <input v-model="url" className="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <label className="control-label" htmlFor="firstname">Latitude</label>
                    <input v-model="latitude" className="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <label className="control-label" htmlFor="firstname">Longitude</label>
                    <input v-model="longitude" className="form-control" type="text"/>
                </div>
                <div class="form-group">
                    <input @click="submitClicked()" type="submit" value="Create" class="btn btn-primary"/>
                </div>
            </div>
        </div>
    </div>

    <div>
        <RouterLink :to="{ name: 'home' }">Back to Shops</RouterLink>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { ShopService } from '@/services/ShopService'
import Logger from '@/logger'
import router from '@/router'
import { IShop } from '@/domain/IShop'

/**
 * @author Ahto Jalak
 * @since 31.03.2023
 */
@Options({
    components: {},
    props: {
        id: String,
    },
    emits: [],
})
export default class ShopCreate extends Vue {
    private logger = new Logger(ShopCreate.name)
    private shopService = new ShopService()
    id = 0
    name = ''
    address = ''
    url = ''
    latitude = '59.436962'
    longitude = '24.753574'
    shop: IShop | null = null;
    shops: IShop[] | null = null;
    errorMsg: string | null = null;

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')

        const shopInfo: IShop = {
            name: this.name,
            address: this.address,
            url: this.url,
            latitude: this.latitude,
            longitude: this.longitude
        }

        const res = await this.shopService.add(shopInfo)

        if (res.status == null || res.status >= 300) {
            this.errorMsg = res.status + ' ' + res.errorMsg
        } else {
            await this.shopService.pullAllShops()
            await router.push('/')
        }
    }

    async mounted () {
        this.logger.info('mounted')
        this.shops = await this.shopService.pullAllShops()
    }
}
</script>
