import type { IAlarm } from '@/domain/IAlarm'
import { BaseService } from './BaseService'

export class AlarmService extends BaseService<IAlarm> {
    constructor() {
        super("v1/alarms");
    }
}
