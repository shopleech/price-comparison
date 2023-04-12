import { IJwtResponse } from '@/dal/domain/IJwtResponse'

export interface IResponseMessage {
    data?: IJwtResponse;
    errorMsg?: string;
}
