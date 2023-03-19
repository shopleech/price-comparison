import { IJwtResponse } from '@/domain/IJwtResponse'

export interface IResponseMessage {
    data?: IJwtResponse;
    errorMsg?: string;
}
