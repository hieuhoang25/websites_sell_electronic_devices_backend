package com.poly.datn.controller.router;

public class Router {
    public static class API {
        // BASE API
        public static final String BASE = "/api/un";

        // API FOR PRODUCT
        public static final String PRODUCT = "/product";
        public static final String PRODUCT_DETAIL = "/product-detail";
        public static final String PRODUCT_COLOR = "/product-color";
        //BRAND
        public static final String BRAND = "/brand";
        // OPTIONS
        public static final String FIND_BY_ID = "/{id}";
        public static final String FILTER = "/filter";

        //CATEGORY
        public static final String CATEGORY = "/category";
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
        public static final String VERIFICATION_OTP = "/verification-otp/{username}/{code}";
        public static final String RESEND_OTP = "/resend-otp/{username}";
        public static final String ORDER_STATUS = "/order-status";

        //STORAGE
        public static final String STORAGE = "/storage";
        public static final String PRODUCT_STORAGE = "/product-storage";
        public static final String REFRESH_TOKEN = "/refresh-token";
        public static final String LOGOUT = "/logout";
        public static final String TOKEN_LOGIN_GOOGLE = "/token-login-google";
        public static final String PRODUCT_TOP_SALES = "/product-top-sales";
        public static final String PRODUCT_ARRIVAL = "/product-arrival";
        public static final String PRODUCT_BIG_DISCOUNT = "/product-big-discount";
        public static final String LOGIN_FACEBOOK = "/login-facebook";


        public static final String PAY_METHOD = "/paymethod";

        public static final String USER_PROMO = "/promo";
        }

    public static class USER_API {
        // BASE API
        public static final String BASE = "/api/user";
        public static final String ORDER = "/order";
        public static final String ORDER_TRACKING = "/order-tracking";
        public static final String INFO = "/info";
        public static final String WISHLIST = "/wishlists";
        public static final String IS_RATING = "/is-rating";

        public static final String CHANGE_PASSWORD = "/change-password";

        public static final String RATING = "/rating";

        public static final String ADDRESS = "/address";
        public static final String CANCEL_ORDER = "/cancelling-order";

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
        public static final String NOTIFICATION = "/notification";
        public static final String ORDER_DETAIL = "/order-detail";

        public static final String BRAND = "/brand";

        public static final String STATISTIC = "/statistic";
        public static final String COLOR = "/color";
        public static final String STORAGE = "/storage";
        public static final String ACCOUNT = "/account";
        public static final String ORDER_STATUS_CHANGE = "/status-change";
    }

    public static final String FILE_API = "/api/files";

    public static class CART_API {
        public static final String CART_DETAIL_TAG_NAME = "Cart Detail Controller";
        public static final String CART = "/cart";

        public static final String USER_CART = "/{userId}";

        public static final String CART_ID_VAR = "/{cartId}";

        public static final String CART_ITEMS = "/items";

        public static final String CART_DETAIL = "/item";
    }

    public static class CHECK_OUT_API {
        public static final String CHECKOUT = "/checkout";
        public static final String AUTH_VAR = "/{userId}";
        public static final String CHECKED_OUT_RERIRECT_API = "/order";
    }

    public static class FLASH_DEAL{

        public static final String FLASH_DEAL = "/deal";
        public static final String PROMO_USER = "/promo-user";
        public static final String PROMO_CURRENT_USER = "/promo-code";

        public static final String CURRENT = "/current";

        public static final String TODAY = "/today";
        public static final String SPECIFIC_DATE = "/${date}";
        public static final String SPECIFIC_TIME = "/${time}";
        public static final String SPECIFIC_DATETIME = "/${datetime}";
    }
}
