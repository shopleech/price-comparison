import type { IFeature } from '@/domain/IFeature'
import { BaseService } from './BaseService'

export class FeatureService extends BaseService<IFeature> {
    constructor() {
        super("v1/features");
    }
}
