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
                    <input v-model="name" className="form-control" type="text" @input="searchShop"/>
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
            <div>
                Shops with similar name found: {{ getShopList().length }}
                <ul>
                    <li v-for="item of getShopList()" :key="item.id">
                        {{ item.name }}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { ShopService } from '@/bll/service/ShopService'
import Logger from '@/logger'
import router from '@/router'
import { IShop } from '@/dal/domain/IShop'
import { useShopStore } from '@/stores/shop'

/**
 * @author Ahto Jalak
 * @since 31.03.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class ShopCreate extends Vue {
    private logger = new Logger(ShopCreate.name)
    private shopStore = useShopStore()
    private shopService = new ShopService()
    name = ''
    address = ''
    url = ''
    latitude = '59.436962'
    longitude = '24.753574'
    errorMsg: string | null = null

    submitClicked (): void {
        this.logger.info('submitClicked')

        const shopInfo: IShop = {
            name: this.name,
            address: this.address,
            url: this.url,
            latitude: this.latitude,
            longitude: this.longitude
        }
        this.shopService.add(shopInfo).then((item) => {
            if (item.errorMsg !== undefined) {
                this.errorMsg = item.errorMsg
            } else {
                if (item.data) {
                    this.shopStore.$state.shop = item.data
                }
                router.push('/')
            }
        })
    }

    searchShop () {
        this.logger.info('searchShop')

        if (this.name.trim().length < 3) {
            this.shopStore.$reset()
            return
        }

        this.shopService.findByName(this.name).then((items) => {
            this.logger.info('found something')
            if (items.errorMsg !== undefined) {
                this.errorMsg = items.errorMsg
            } else {
                if (items.data) {
                    this.shopStore.$state.shops = items.data
                }
            }
        })
    }

    getShopList () {
        return this.shopStore.$state.shops
    }
}
</script>
