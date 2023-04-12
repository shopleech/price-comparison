import { IProduct } from '@/dal/domain/IProduct'
import { IServiceResult } from '@/bll/service/model/IServiceResult'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export interface IProductRepository {
    findByName (entity: IProduct): Promise<IServiceResult<IProduct[]>>
}
