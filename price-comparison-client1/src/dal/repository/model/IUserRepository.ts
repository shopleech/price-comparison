import { ILoginInfo } from '@/dal/domain/ILoginInfo'
import { IServiceResult } from '@/bll/service/model/IServiceResult'
import { IJwtResponse } from '@/dal/domain/IJwtResponse'
import { IRegisterInfo } from '@/dal/domain/IRegisterInfo'

/**
 * @author Ahto Jalak
 * @since 12.04.2023
 */
export interface IUserRepository {
    login (entity: ILoginInfo): Promise<IServiceResult<IJwtResponse>>

    register (entity: IRegisterInfo): Promise<IServiceResult<IJwtResponse>>
}
