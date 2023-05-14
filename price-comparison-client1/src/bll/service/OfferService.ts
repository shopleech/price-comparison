import type { IOffer } from '@/dal/domain/IOffer'
import { BaseService } from './BaseService'
import HttpClient from '@/util/http-client'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export class OfferService extends BaseService<IOffer> {
    constructor () {
        super('v1/offer')
    }

    async getAllByProductId (): Promise<IOffer[]> {
        this.logger.info('getAll')
        let response
        try {
            response = await HttpClient.get(`${this._path}`, {
                headers: {
                    Authorization: 'Bearer ' + this.identityStore.$state.jwt?.token
                }
            })
        } catch (e) {
            // TODO
            throw new TypeError('error with get all by category id')
        }

        const res = response.data as IOffer[]
        return res
    }

    upload(barcode: string, file : any, onUploadProgress : any) {
        const formData = new FormData();

        formData.append("multipartfile", file);
        formData.append("barcode", barcode);

        return HttpClient.post("/v1/offer/upload", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            },
            onUploadProgress
        });
    }
}
