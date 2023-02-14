package com.poly.datn.controller.router;

public class Router {
    public static class USER_API {
        // BASE API
        public static final String BASE = "/api/user";

        // API FOR PRODUCT
        public static final String PRODUCT = "/product";

        // OPTIONS
        public static final String FIND_BY = "/{id}";
        public static final String FILTER = "/filter";
    }

    public static class ADMIN_API {
        public static final String BASE = "/api/admin";

    }
}
