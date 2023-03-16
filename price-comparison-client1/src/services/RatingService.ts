import type { IRating } from '@/domain/IRating'
import httpCLient from '@/http-client'
import { BaseService } from './BaseService'
import { AxiosError } from 'axios'

export class RatingService extends BaseService<IRating> {
    constructor() {
        super("v1/ratings");
    }

    async addMerchandiseRating (merchandiseId: string) {
        console.log('add')

        const entity: IRating = {
            merchandiseId: merchandiseId,
            score: 5
        }

        let response
        try {
            response = await httpCLient.post(`/${this._path}`, entity,
                { headers: { Authorization: 'bearer ' + this.identityStore.$state.jwt?.token } }
            )
        } catch (e) {
            const res = {
                status: (e as AxiosError).response!.status,
                // errorMsg: (e as AxiosError).response!.data.error,
            }
            console.log(res)

            return res
        }

        return { status: response.status }
    }
}
