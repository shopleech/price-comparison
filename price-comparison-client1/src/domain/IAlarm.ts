export interface IAlarm {
    id?: string;
    accountId?: string;
    productId?: string;
    alarmTypeCode?: string;
    minValue?: number;
    maxValue?: number;
    name?: string;
}
