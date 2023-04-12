import { IProduct } from '@/dal/domain/IProduct'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import { ProductRepository } from '@/dal/repository/ProductRepository'
import { IProductService } from '@/bll/service/model/IProductService'

/**
 * @author Ahto Jalak
 * @since 16.03.2023
 */
export class ProductService implements IProductService {
    private productRepository = new ProductRepository()

    async findByName (searchName: string): Promise<IServiceResult<IProduct[]>> {
        return this.productRepository.findByName({
            name: searchName
        })
    }

    async add (productInfo: IProduct): Promise<IServiceResult<IProduct>> {
        return this.productRepository.add(productInfo)
    }

    async getAll (): Promise<IServiceResult<IProduct[]>> {
        return this.productRepository.getAll()
    }
}
