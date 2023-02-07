
CREATE TABLE account
(
    unique_id   int          NOT NULL AUTO_INCREMENT,
    username    varchar(255) NULL DEFAULT NULL,
    password    varchar(255) NULL DEFAULT NULL,
    create_date datetime     NULL DEFAULT NULL,
    update_date datetime     NULL DEFAULT NULL,
    active      bit(1)       NULL DEFAULT NULL,
    user_id     int          NULL DEFAULT NULL,
    last_access datetime     NULL DEFAULT NULL,
    PRIMARY KEY (unique_id)
);
CREATE TABLE address
(
    id           int          NOT NULL AUTO_INCREMENT,
    wards        varchar(255) NULL DEFAULT NULL,
    district     varchar(255) NULL DEFAULT NULL,
    address_line varchar(255) NULL DEFAULT NULL,
    province     varchar(255) NULL DEFAULT NULL,
    postal_id    varchar(255) NULL DEFAULT NULL,
    is_default   bit(1)       NULL DEFAULT NULL,
    user_id      int          NULL DEFAULT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE authority
(
    unique_id  int NOT NULL AUTO_INCREMENT,
    role_id    int NULL DEFAULT NULL,
    account_id int NULL DEFAULT NULL,
    PRIMARY KEY (unique_id)
);
CREATE TABLE brand
(
    id           int          NOT NULL AUTO_INCREMENT,
    brand_name   varchar(255) NULL DEFAULT NULL,
    image        varchar(255) NULL DEFAULT NULL,
    created_date datetime     NULL DEFAULT NULL,
    updated_date datetime     NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cart
(
    id          int      NOT NULL AUTO_INCREMENT,
    user_id     int      NULL DEFAULT NULL,
    create_date datetime NULL DEFAULT NULL,
    price_sum   float    NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cart_detail
(
    id                 int      NOT NULL AUTO_INCREMENT,
    cart_id            int      NULL DEFAULT NULL,
    quantity           int      NULL DEFAULT NULL,
    create_date        datetime NULL DEFAULT NULL,
    product_variant_id int      NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id            int          NOT NULL AUTO_INCREMENT,
    category_name varchar(100) NULL DEFAULT NULL,
    parent_id     int          NULL DEFAULT NULL,
    create_date   datetime     NULL DEFAULT NULL,
    update_date   datetime     NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_detail
(
    id                 int      NOT NULL AUTO_INCREMENT,
    order_id           int      NULL DEFAULT NULL,
    product_variant_id int      NULL DEFAULT NULL,
    create_date        datetime NULL DEFAULT NULL,
    price_sum          int      NULL DEFAULT NULL,
    promotion_value    double   NULL DEFAULT NULL,
    quatity            int      NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_status
(
    id   int         NOT NULL AUTO_INCREMENT,
    name varchar(55) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id           int         NOT NULL AUTO_INCREMENT,
    user_id      int         NULL DEFAULT NULL,
    created_date datetime    NULL DEFAULT NULL,
    is_pay       bit(1)      NULL DEFAULT NULL,
    payment_id   int         NULL DEFAULT NULL,
    status       int         NULL DEFAULT NULL,
    is_cancelled bit(1)      NULL DEFAULT NULL,
    promotion_id int         NULL DEFAULT NULL,
    district     varchar(50) NULL DEFAULT NULL,
    address_line varchar(50) NULL DEFAULT NULL,
    province     varchar(50) NULL DEFAULT NULL,
    postal_id    varchar(50) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE payment_method
(
    id     int          NOT NULL AUTO_INCREMENT,
    method varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id           int          NOT NULL AUTO_INCREMENT,
    product_name varchar(255) NULL DEFAULT NULL,
    description  varchar(255) NULL DEFAULT NULL,
    create_date  datetime     NULL DEFAULT NULL,
    update_date  datetime     NULL DEFAULT NULL,
    category_id  int          NULL DEFAULT NULL,
    is_delete    bit(1)       NULL DEFAULT NULL,
    brand_id     int          NULL DEFAULT NULL,
    promotion_id int          NULL DEFAULT NULL,
    type         int          NULL DEFAULT NULL,
    image        varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_attribute
(
    id             int         NOT NULL AUTO_INCREMENT,
    name_attribute varchar(50) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX name_attribute (name_attribute ASC)
);

CREATE TABLE product_attribute_value
(
    id              int          NOT NULL AUTO_INCREMENT,
    attribute_value varchar(100) NULL DEFAULT NULL,
    attribute_id    int          NULL DEFAULT NULL,
    variant_type_id int          NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_type
(
    id              int          NOT NULL AUTO_INCREMENT,
    name            varchar(255) NULL DEFAULT NULL,
    multi_variation bit(1)       NULL DEFAULT NULL,
    variant_type_id int          NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_variant
(
    id           int          NOT NULL AUTO_INCREMENT,
    sku_name     varchar(255) NULL DEFAULT NULL,
    quantity     int          NULL DEFAULT NULL,
    price        double       NULL DEFAULT NULL,
    status       bit(1)       NULL DEFAULT NULL,
    product_id   int          NULL DEFAULT NULL,
    image        varchar(255) NULL DEFAULT NULL,
    variant_type int          NULL DEFAULT NULL,
    display_name varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_variant_option
(
    id                 int          NOT NULL AUTO_INCREMENT,
    title              varchar(100) NULL DEFAULT NULL,
    attribute_value_id int          NULL DEFAULT NULL,
    variant_id         int          NULL DEFAULT NULL,
    active             bit(1)       NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_variant_type
(
    id        int         NOT NULL AUTO_INCREMENT,
    type_name varchar(50) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX type_name (type_name ASC)
);

CREATE TABLE promotion_type
(
    id                  int          NOT NULL AUTO_INCREMENT,
    name_promotion_type varchar(255) NULL DEFAULT NULL,
    condition_minimum   int          NULL DEFAULT NULL,
    amount              double       NULL DEFAULT NULL,
    is_limited          bit(1)       NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE promotion_user
(
    id                  int          NOT NULL AUTO_INCREMENT,
    name_promotion_user varchar(255) NULL DEFAULT NULL,
    discount_value      int          NULL DEFAULT NULL,
    is_used             int          NULL DEFAULT NULL,
    create_date         datetime     NULL DEFAULT NULL,
    update_date         datetime     NULL DEFAULT NULL,
    expiration_date     datetime     NULL DEFAULT NULL,
    promotion_code      varchar(255) NULL DEFAULT NULL,
    promotion_type      int          NULL DEFAULT NULL,
    user_id             int          NULL DEFAULT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE promotion_product
(
    id             int          NOT NULL AUTO_INCREMENT,
    expiation_date datetime     NULL DEFAULT NULL,
    created_date   datetime     NULL DEFAULT NULL,
    name           varchar(255) NULL DEFAULT NULL,
    updated_date   datetime     NULL DEFAULT NULL,
    maximum_price  double       NULL DEFAULT NULL,
    activate       bit(1)       NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE rating
(
    id              int          NOT NULL AUTO_INCREMENT,
    point           int          NULL DEFAULT NULL,
    created_date    datetime     NULL DEFAULT NULL,
    user_id         int          NULL DEFAULT NULL,
    order_detail_id int          NULL DEFAULT NULL,
    content         varchar(255) NULL DEFAULT NULL,
    product_id      int          NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    unique_id int          NOT NULL AUTO_INCREMENT,
    role_name varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (unique_id)
);

CREATE TABLE user
(
    id          int          NOT NULL AUTO_INCREMENT,
    email       varchar(255) NULL DEFAULT NULL,
    full_name   varchar(255) NULL DEFAULT NULL,
    phone       varchar(13)  NULL DEFAULT NULL,
    create_date datetime     NULL DEFAULT NULL,
    update_date datetime     NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE wishlist
(
    id          int      NOT NULL AUTO_INCREMENT,
    product_id  int      NULL DEFAULT NULL,
    user_id     int      NULL DEFAULT NULL,
    update_date datetime NULL DEFAULT NULL,
    create_date datetime NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT fk_account_user_1 FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE authority
    ADD CONSTRAINT fk_authority_account_1 FOREIGN KEY (account_id) REFERENCES account (unique_id);
ALTER TABLE authority
    ADD CONSTRAINT fk_authority_role_1 FOREIGN KEY (role_id) REFERENCES role (unique_id);
ALTER TABLE cart_detail
    ADD CONSTRAINT fk_cart_detail_product_variant_1 FOREIGN KEY (product_variant_id) REFERENCES product_variant (id);
ALTER TABLE cart_detail
    ADD CONSTRAINT fk_cart_detail_cart_1 FOREIGN KEY (cart_id) REFERENCES cart (id);
ALTER TABLE category
    ADD CONSTRAINT fk_category_category_1 FOREIGN KEY (parent_id) REFERENCES category (id);
ALTER TABLE order_detail
    ADD CONSTRAINT fk_order_detail_orders_1 FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE order_detail
    ADD CONSTRAINT fk_order_detail_product_variant_1 FOREIGN KEY (product_variant_id) REFERENCES product_variant (id);
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_promotion_user_1 FOREIGN KEY (promotion_id) REFERENCES promotion_user (id);
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_order_status_1 FOREIGN KEY (status) REFERENCES order_status (id);
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_payment_method_1 FOREIGN KEY (payment_id) REFERENCES payment_method (id);
ALTER TABLE rating
    ADD CONSTRAINT fk_rating_product_1 FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_category_1 FOREIGN KEY (category_id) REFERENCES category (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_brand_1 FOREIGN KEY (brand_id) REFERENCES brand (id);
ALTER TABLE product
    ADD CONSTRAINT fk_product_product_type_1 FOREIGN KEY (type) REFERENCES product_type (id);
ALTER TABLE product_attribute_value
    ADD CONSTRAINT fk_product_attribute_value_product_variant_type_1 FOREIGN KEY (variant_type_id) REFERENCES product_variant_type (id);
ALTER TABLE product_attribute_value
    ADD CONSTRAINT fk_product_attribute_value_product_attribute_1 FOREIGN KEY (attribute_id) REFERENCES product_attribute (id);
ALTER TABLE product_variant
    ADD CONSTRAINT fk_product_variant_product_1 FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE product_variant
    ADD CONSTRAINT fk_product_variant_product_variant_type_1 FOREIGN KEY (variant_type) REFERENCES product_variant_type (id);
ALTER TABLE product_variant_option
    ADD CONSTRAINT fk_product_variant_option_product_attribute_value_1 FOREIGN KEY (attribute_value_id) REFERENCES product_attribute_value (id);
ALTER TABLE product_variant_option
    ADD CONSTRAINT fk_product_variant_option_product_variant_1 FOREIGN KEY (variant_id) REFERENCES product_variant (id);
ALTER TABLE product_type
    ADD CONSTRAINT fk_product_variant_type_product_type_1 FOREIGN KEY (variant_type_id) REFERENCES product_variant_type (id);
ALTER TABLE promotion_user
    ADD CONSTRAINT fk_promotion_user_user_1 FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE promotion_user
    ADD CONSTRAINT fk_promotion_user_promotion_type_1 FOREIGN KEY (promotion_type) REFERENCES promotion_type (id);
ALTER TABLE product
    ADD CONSTRAINT fk_promotion_product_product_1 FOREIGN KEY (promotion_id) REFERENCES promotion_product (id);
ALTER TABLE rating
    ADD CONSTRAINT fk_rating_order_detail_1 FOREIGN KEY (order_detail_id) REFERENCES order_detail (id);
ALTER TABLE rating
    ADD CONSTRAINT fk_rating_user_1 FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE cart
    ADD CONSTRAINT fk_user_cart_1 FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE address
    ADD CONSTRAINT fk_user_address_1 FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_product_1 FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE wishlist
    ADD CONSTRAINT fk_wishlist_user_1 FOREIGN KEY (user_id) REFERENCES user (id);









