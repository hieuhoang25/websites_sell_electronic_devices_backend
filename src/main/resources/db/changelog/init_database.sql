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
    id              int          NOT NULL AUTO_INCREMENT,
    expiration_date datetime     NULL DEFAULT NULL,
    created_date    datetime     NULL DEFAULT NULL,
    name            varchar(255) NULL DEFAULT NULL,
    updated_date    datetime     NULL DEFAULT NULL,
    maximum_price   double       NULL DEFAULT NULL,
    activate        bit(1)       NULL DEFAULT NULL,
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

INSERT INTO `category`
VALUES (1, 'Điện thoại', NULL, '2023-02-02 21:23:18', NULL);
INSERT INTO `category`
VALUES (2, 'Laptop', NULL, '2023-02-02 21:23:31', NULL);
INSERT INTO `category`
VALUES (3, 'Phụ kiện', NULL, '2023-02-02 21:23:48', NULL);
INSERT INTO `category`
VALUES (4, 'Đồng hồ', NULL, '2023-02-02 21:24:33', NULL);
INSERT INTO `category`
VALUES (5, 'Tablet', NULL, '2023-02-02 21:24:47', NULL);
INSERT INTO `category`
VALUES (6, 'PC', NULL, '2023-02-02 21:25:02', NULL);
INSERT INTO `category`
VALUES (7, 'Phụ kiện di động', 3, '2023-02-14 21:26:52', NULL);
INSERT INTO `category`
VALUES (8, 'Phụ kiện laptop', 3, '2023-02-14 21:30:22', NULL);
INSERT INTO `category`
VALUES (9, 'Sạc dự phòng', 7, '2023-02-14 21:30:48', NULL);
INSERT INTO `category`
VALUES (10, 'Ốp lưng điện thoại', 7, '2023-02-14 21:31:09', NULL);
INSERT INTO `category`
VALUES (11, 'Giá đỡ laptop', 8, '2023-02-14 21:31:29', NULL);
INSERT INTO `category`
VALUES (12, 'Chuột, bàn phím', 8, '2023-02-14 21:31:58', NULL);


INSERT INTO `brand`
VALUES (1, 'SamSung', NULL, '2023-02-02 21:18:46', NULL);
INSERT INTO `brand`
VALUES (2, 'Apple', NULL, '2023-02-02 21:19:23', NULL);
INSERT INTO `brand`
VALUES (3, 'Sony', NULL, '2023-02-02 21:19:44', NULL);
INSERT INTO `brand`
VALUES (4, 'Xiaomi', NULL, '2023-02-02 21:20:24', NULL);
INSERT INTO `brand`
VALUES (5, 'Huawei', NULL, '2023-02-02 21:20:53', NULL);
INSERT INTO `brand`
VALUES (6, 'Vivo', NULL, '2023-02-02 21:21:05', NULL);
INSERT INTO `brand`
VALUES (7, 'Nokia', NULL, '2023-02-02 21:21:33', NULL);
INSERT INTO `brand`
VALUES (8, 'Google', NULL, '2023-02-02 21:21:43', NULL);

INSERT INTO `payment_method`
VALUES (1, 'VISA_CARD');
INSERT INTO `payment_method`
VALUES (2, 'MOMO');
INSERT INTO `payment_method`
VALUES (3, 'CASH');


INSERT INTO `user`
VALUES (1, 'synhatphu2@gmail.com', 'Nhật Phú', '038565758', '2023-02-09 21:12:29', '2023-02-09 21:12:42');


INSERT INTO `account`
VALUES (1, 'NhatPhu', '12345', '2023-02-04 22:22:12', NULL, b'1', 1, NULL);


INSERT INTO `role`
VALUES (1, 'USER');
INSERT INTO `role`
VALUES (2, 'ADMIN');
INSERT INTO `role`
VALUES (3, 'SUPER_ADMIN');


INSERT INTO `authority`
VALUES (1, 3, 1);



INSERT INTO `product_variant_type`
VALUES (1, 'Iphone');
INSERT INTO `product_variant_type`
VALUES (2, 'Macbook');

INSERT INTO `product_type`
VALUES (1, 'Laptop', b'1', 2);
INSERT INTO `product_type`
VALUES (2, 'SmartPhone', b'1', 1);


INSERT INTO `product_attribute`
VALUES (3, 'Dung lượng lưu trữ');
INSERT INTO `product_attribute`
VALUES (2, 'Màu sắc');
INSERT INTO `product_attribute`
VALUES (1, 'Ram');


INSERT INTO `product_attribute_value`
VALUES (1, 'Trắng', 2, 1);
INSERT INTO `product_attribute_value`
VALUES (2, '64GB', 3, 1);
INSERT INTO `product_attribute_value`
VALUES (3, '6GB', 1, 1);
INSERT INTO `product_attribute_value`
VALUES (4, 'Đen', 2, 1);
INSERT INTO `product_attribute_value`
VALUES (5, '128GB', 3, 1);
INSERT INTO `product_attribute_value`
VALUES (7, '256GB', 3, 1);

INSERT INTO `product`
VALUES (1, 'Iphone 12',
        'Trong những tháng cuối năm 2020, Apple đã chính thức giới thiệu đến người dùng cũng như iFan thế hệ iPhone 12 series mới với hàng loạt tính năng bứt phá, thiết kế được lột xác hoàn toàn, hiệu năng đầy mạnh mẽ và một trong số đó chính là iPhone 12 64GB.',
        '2023-02-02 21:26:56', NULL, 1, b'1', 2, NULL, 1, NULL);

INSERT INTO `product_variant`
VALUES (1, 'IP12W', 100, 20000000, b'1', 1, NULL, 1, 'Iphone 12 màu trắng');
INSERT INTO `product_variant`
VALUES (2, 'IP12B', 100, 22000000, b'1', 1, NULL, 1, 'Iphone 12 màu đen');

INSERT INTO `product_variant_option`
VALUES (1, 'Color', 1, 1, b'1');
INSERT INTO `product_variant_option`
VALUES (2, 'Dung lượng lưu trữ', 2, 1, b'1');
INSERT INTO `product_variant_option`
VALUES (3, 'Ram', 3, 1, b'1');
INSERT INTO `product_variant_option`
VALUES (4, 'Color', 4, 2, b'1');
INSERT INTO `product_variant_option`
VALUES (5, 'Dung lượng lưu trữ', 5, 2, b'1');
INSERT INTO `product_variant_option`
VALUES (6, 'Ram', 3, 2, b'1');








