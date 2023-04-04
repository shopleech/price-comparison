/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export interface IAlarm {
    id?: string;
    accountId?: string;
    productId?: string;
    alarmTypeCode?: string;
    minValue?: number;
    maxValue?: number;
    name?: string;
}
