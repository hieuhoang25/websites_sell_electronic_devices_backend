package com.poly.datn.controller.router;

public class Router {
    public static class USER_API {
        public static final String BASE = "/api/user";
        public static final String FIND_BY = "/{id}";
        public static final String PRODUCT_FIND_ID = "/product/{id}";
    }

    public static class ADMIN_API {
        public static final String BASE = "/api/admin";

    }
}
