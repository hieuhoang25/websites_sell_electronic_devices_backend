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


INSERT INTO `product`
VALUES (1, 'Iphone 12',
        'Trong những tháng cuối năm 2020, Apple đã chính thức giới thiệu đến người dùng cũng như iFan thế hệ iPhone 12 series mới với hàng loạt tính năng bứt phá, thiết kế được lột xác hoàn toàn, hiệu năng đầy mạnh mẽ và một trong số đó chính là iPhone 12 64GB.',
        '2023-02-02 21:26:56', NULL, 1, b'1', 2, NULL, 1, NULL);


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