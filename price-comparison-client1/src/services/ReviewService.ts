import { BaseService } from './BaseService'
import { IReview } from '@/domain/IReview'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class ReviewService extends BaseService<IReview> {
    constructor () {
        super('v1/review')
    }
}
