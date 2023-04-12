import { IShop } from '@/dal/domain/IShop'
import { ShopRepository } from '@/dal/repository/ShopRepository'
import { IShopService } from '@/bll/service/model/IShopService'
import { IServiceResult } from '@/bll/service/model/IServiceResult'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class ShopService implements IShopService {
    private shopRepository = new ShopRepository()

    async findByName (searchName: string): Promise<IServiceResult<IShop[]>> {
        return this.shopRepository.findByName({
            name: searchName
        })
    }

    async add (shopInfo: IShop): Promise<IServiceResult<IShop>> {
        return this.shopRepository.add(shopInfo)
    }

    async getAll (): Promise<IServiceResult<IShop[]>> {
        return this.shopRepository.getAll()
    }
}
