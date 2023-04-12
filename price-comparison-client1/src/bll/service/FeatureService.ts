import type { IFeature } from '@/dal/domain/IFeature'
import { BaseService } from './BaseService'

export class FeatureService extends BaseService<IFeature> {
    constructor() {
        super("v1/feature");
    }
}
