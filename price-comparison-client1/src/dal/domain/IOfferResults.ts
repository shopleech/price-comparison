import { IShop } from '@/dal/domain/IShop'
import { IOffer } from '@/dal/domain/IOffer'

/**
 * @author Ahto Jalak
 * @since 30.04.2023
 */
export interface IOfferResults {
    id?: number
    name?: string
    shop?: IShop
    latitude?: number
    longitude?: number
    minPrice?: number
    offers?: IOffer[]
}
