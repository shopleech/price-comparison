import { IPublicStats } from '@/dal/domain/IPublicStats'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import { StatsRepository } from '@/dal/repository/StatsRepository'
import { IStatsService } from '@/bll/service/model/IStatsService'

/**
 * @author Ahto Jalak
 * @since 19.03.2023
 */
export class StatsService implements IStatsService {
    private statsRepository = new StatsRepository()

    async getPublicStats (): Promise<IServiceResult<IPublicStats>> {
        return this.statsRepository.getPublicStats()
    }
}
