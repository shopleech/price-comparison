/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export interface ICategory {
    id?: number;
    parentCategoryId?: number;
    categoryTypeCode?: string;
    name?: string;
}
