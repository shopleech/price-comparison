import { IServiceResult } from '@/bll/service/model/IServiceResult'
import { IPublicStats } from '@/dal/domain/IPublicStats'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export interface IStatsService {
    getPublicStats (): Promise<IServiceResult<IPublicStats>>
}
