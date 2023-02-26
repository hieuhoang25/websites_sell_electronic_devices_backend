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
    }

    public static class ADMIN_API {
        public static final String BASE = "/api/admin";

        public static final String CATEGORY = "/category";
        public static final String ORDER_STATUS = "/order-status";

        public static final String PRODUCT = "/product";

        public static final String PRODUCT_VARIANT = "/product_variant";

        public static final String PRODUCT_ATTRIBUTE = "/product_attribute";

        public static final String USER = "/user";
        public static final String ORDER = "/order";


    }

    public static class PRODUCT_API {
        public static final String BASE = "/api/product";

    }
}
