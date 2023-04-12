import type { IAlarm } from '@/dal/domain/IAlarm'
import { BaseService } from './BaseService'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class AlarmService extends BaseService<IAlarm> {
    constructor () {
        super('v1/alarm')
    }
}
