import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '../views/identity/Login.vue'
import CustomerIndex from '@/views/customer/CustomerIndex.vue'
import CustomerCreate from '@/views/customer/CustomerCreate.vue'
import CustomerDelete from '@/views/customer/CustomerDelete.vue'
import CustomerEdit from '@/views/customer/CustomerEdit.vue'
import CustomerDetails from '@/views/customer/CustomerDetails.vue'
import CategoryIndex from '@/views/category/CategoryIndex.vue'
import CategoryCreate from '@/views/category/CategoryCreate.vue'
import CategoryDelete from '@/views/category/CategoryDelete.vue'
import CategoryEdit from '@/views/category/CategoryEdit.vue'
import CategoryDetails from '@/views/category/CategoryDetails.vue'
import MerchandiseIndex from '@/views/merchandise/MerchandiseIndex.vue'
import MerchandiseCreate from '@/views/merchandise/MerchandiseCreate.vue'
import MerchandiseDelete from '@/views/merchandise/MerchandiseDelete.vue'
import MerchandiseEdit from '@/views/merchandise/MerchandiseEdit.vue'
import MerchandiseDetails from '@/views/merchandise/MerchandiseDetails.vue'
import ProductIndex from '@/views/product/ProductIndex.vue'
import ProductEdit from '@/views/product/ProductEdit.vue'
import ProductCreate from '@/views/product/ProductCreate.vue'
import ProductDelete from '@/views/product/ProductDelete.vue'
import ProductDetails from '@/views/product/ProductDetails.vue'
import WishIndex from '@/views/wish/WishIndex.vue'
import WishEdit from '@/views/wish/WishEdit.vue'
import WishCreate from '@/views/wish/WishCreate.vue'
import WishDelete from '@/views/wish/WishDelete.vue'
import WishDetails from '@/views/wish/WishDetails.vue'
import Register from '@/views/identity/Register.vue'
import RatingCreate from '@/views/rating/RatingCreate.vue'
import RatingDelete from '@/views/rating/RatingDelete.vue'
import RatingDetails from '@/views/rating/RatingDetails.vue'
import RatingIndex from '@/views/rating/RatingIndex.vue'
import RatingEdit from '@/views/rating/RatingEdit.vue'
import AlarmCreate from '@/views/alarm/AlarmCreate.vue'
import AlarmIndex from '@/views/alarm/AlarmIndex.vue'
import AlarmEdit from '@/views/alarm/AlarmEdit.vue'
import AlarmDelete from '@/views/alarm/AlarmDelete.vue'
import AlarmDetails from '@/views/alarm/AlarmDetails.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: import('../views/HomeView.vue'),
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '/identity/login',
        name: 'identity-login',
        component: Login,
    },
    {
        path: '/identity/register',
        name: 'identity-register',
        component: Register,
    },
    {
        path: '/categories',
        name: 'categories',
        component: CategoryIndex
    },
    {
        path: '/categories/create',
        name: 'categories-create',
        component: CategoryCreate
    },
    {
        path: '/categories/edit/:id',
        name: 'categories-edit',
        component: CategoryEdit,
        props: true
    },
    {
        path: '/categories/delete/:id',
        name: 'categories-delete',
        component: CategoryDelete,
        props: true
    },
    {
        path: '/categories/details/:id',
        name: 'categories-details',
        component: CategoryDetails,
        props: true
    },
    {
        path: '/customers',
        name: 'customers',
        component: CustomerIndex
    },
    {
        path: '/customers/create',
        name: 'customers-create',
        component: CustomerCreate
    },
    {
        path: '/customers/edit/:id',
        name: 'customers-edit',
        component: CustomerEdit,
        props: true
    },
    {
        path: '/customers/delete/:id',
        name: 'customers-delete',
        component: CustomerDelete,
        props: true
    },
    {
        path: '/customers/details/:id',
        name: 'customers-details',
        component: CustomerDetails,
        props: true
    },
    {
        path: '/merchandises',
        name: 'merchandises',
        component: MerchandiseIndex
    },
    {
        path: '/merchandises/create',
        name: 'merchandises-create',
        component: MerchandiseCreate
    },
    {
        path: '/merchandises/edit/:id',
        name: 'merchandises-edit',
        component: MerchandiseEdit,
        props: true
    },
    {
        path: '/merchandises/delete/:id',
        name: 'merchandises-delete',
        component: MerchandiseDelete,
        props: true
    },
    {
        path: '/merchandises/details/:id',
        name: 'merchandises-details',
        component: MerchandiseDetails,
        props: true
    },
    {
        path: '/products',
        name: 'products',
        component: ProductIndex
    },
    {
        path: '/products/:id',
        name: 'products-category',
        component: ProductIndex
    },
    {
        path: '/products/create',
        name: 'products-create',
        component: ProductCreate
    },
    {
        path: '/products/edit/:id',
        name: 'products-edit',
        component: ProductEdit,
        props: true
    },
    {
        path: '/products/delete/:id',
        name: 'products-delete',
        component: ProductDelete,
        props: true
    },
    {
        path: '/products/details/:id',
        name: 'products-details',
        component: ProductDetails,
        props: true
    },
    {
        path: '/wishes',
        name: 'wishes',
        component: WishIndex
    },
    {
        path: '/wishes/create',
        name: 'wishes-create',
        component: WishCreate
    },
    {
        path: '/wishes/edit/:id',
        name: 'wishes-edit',
        component: WishEdit,
        props: true
    },
    {
        path: '/wishes/delete/:id',
        name: 'wishes-delete',
        component: WishDelete,
        props: true
    },
    {
        path: '/wishes/details/:id',
        name: 'wishes-details',
        component: WishDetails,
        props: true
    },
    {
        path: '/ratings',
        name: 'ratings',
        component: RatingIndex
    },
    {
        path: '/ratings/create/:id',
        name: 'ratings-create',
        component: RatingCreate,
        props: true
    },
    {
        path: '/ratings/edit/:id',
        name: 'ratings-edit',
        component: RatingEdit,
        props: true
    },
    {
        path: '/ratings/delete/:id',
        name: 'ratings-delete',
        component: RatingDelete,
        props: true
    },
    {
        path: '/ratings/details/:id',
        name: 'ratings-details',
        component: RatingDetails,
        props: true
    },
    {
        path: '/alarms',
        name: 'alarms',
        component: AlarmIndex
    },
    {
        path: '/alarms/create',
        name: 'alarms-create',
        component: AlarmCreate
    },
    {
        path: '/alarms/edit/:id',
        name: 'alarms-edit',
        component: AlarmEdit,
        props: true
    },
    {
        path: '/alarms/delete/:id',
        name: 'alarms-delete',
        component: AlarmDelete,
        props: true
    },
    {
        path: '/alarms/details/:id',
        name: 'alarms-details',
        component: AlarmDetails,
        props: true
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
