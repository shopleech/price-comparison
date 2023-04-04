/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export interface IReview {
    id?: number;
    customerId?: number;
    productId?: number;
    reviewTypeCode?: string;
    score?: number;
    description?: string;
}
