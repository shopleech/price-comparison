import type { IProduct } from '@/domain/IProduct'
import { defineStore } from 'pinia'

export const useProductsStore = defineStore({
    id: "products",
    state: () => ({
        product: [
        ] as IProduct,
        products: [
        ] as IProduct[],
        rating_text: "No Rating Selected",
        currentRating: "No Rating",
        currentSelectedRating: "No Current Rating",
        boundRating: 3,
    }),
    getters: {
        productCount: (state) => state.products.length,
    },
    actions: {
        add(product: IProduct) {
            this.products.push(product);
        },
        setRating: function(rating: string) {
            this.rating_text = "You have Selected: " + rating + " stars";
        },
        showCurrentRating: function(rating: any) {
            this.currentRating = (rating === 0) ? this.currentSelectedRating : "Click to select " + rating + " stars"
        },
        setCurrentSelectedRating: function(rating: any) {
            this.currentSelectedRating = "You have Selected: " + rating + " stars";
        }
    },
});
