package com.poly.datn.controller.router;

public class Router {
    public static class API {
        // BASE API
        public static final String BASE = "/api";

        // API FOR PRODUCT
        public static final String PRODUCT = "/product";

        // OPTIONS
        public static final String FIND_BY_ID = "/{id}";
        public static final String FILTER = "/filter";

        //CATEGORY
        public static final String CATEGORY = "/category";
    }

    public static class USER_API{
        // BASE API
        public static final String BASE = "/api/user";
        public static final String ORDER = "/order";
        public static final String TRACKING = "-tracking";
    }

    public static class ADMIN_API {
        public static final String BASE = "/api/admin";

        public static final String CATEGORY = "/category";
        public static final String ORDER_STATUS = "/order-status";

        public static final String PRODUCT = "/product";

        public static final String PRODUCT_VARIANT = "/product-variant";

        public static final String PRODUCT_ATTRIBUTE = "/product-attribute";

        public static final String PROMOTION_TYPE = "/promotion-type";

        public static final String PROMOTION_USER = "/promotion-user";

        public static final String PROMOTION_PRODUCT = "/promotion-product";

        public static final String USER = "/user";
        public static final String ORDER = "/order";

        public static final String ORDER_DETAIL = "/order-detail";

        public static final String BRAND = "/brand";
    }

    public static final String FILE_API = "/api/files";

    public static class CART_API {
        public static final String BASE = "/api/cart";

        public static final String USER_CART = "/{userId}";
    }
}
