/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export interface IProduct {
    id?: number;
    categoryId?: number;
    productTypeCode?: string;
    barcode?: string;
    barcodeTypeCode?: string;
    name?: string;
    description?: string;
}
