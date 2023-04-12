import type { IMetric } from '@/dal/domain/IMetric'
import { BaseService } from './BaseService'

export class MetricService extends BaseService<IMetric> {
    constructor() {
        super("v1/metrics");
    }
}
