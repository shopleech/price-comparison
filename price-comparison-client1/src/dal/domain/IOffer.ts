/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export interface IOffer {
    id?: number;
    accountId?: number;
    productId?: number;
    shopId?: number;
    barcode?: string;
    name?: string;
    description?: string;
    url?: string;
}
