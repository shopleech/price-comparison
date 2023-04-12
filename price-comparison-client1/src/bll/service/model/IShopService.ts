import { IShop } from '@/dal/domain/IShop'
import { IServiceResult } from '@/bll/service/model/IServiceResult'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export interface IShopService {
    findByName (searchName: string): Promise<IServiceResult<IShop[]>>

    add (shopInfo: IShop): Promise<IServiceResult<IShop>>

    getAll (): Promise<IServiceResult<IShop[]>>
}
