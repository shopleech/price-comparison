import { IProduct } from '@/dal/domain/IProduct'
import { IServiceResult } from '@/bll/service/model/IServiceResult'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export interface IProductService {
    findByName (searchName: string): Promise<IServiceResult<IProduct[]>>

    add (productInfo: IProduct): Promise<IServiceResult<IProduct>>

    getAll (): Promise<IServiceResult<IProduct[]>>
}
