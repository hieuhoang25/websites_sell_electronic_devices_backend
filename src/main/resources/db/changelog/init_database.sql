-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 17, 2023 at 04:46 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electronic_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
                           `unique_id` int(11) NOT NULL,
                           `username` varchar(255) DEFAULT NULL unique,
                           `password` varchar(255) DEFAULT NULL,
                           `create_date` datetime DEFAULT NULL,
                           `update_date` datetime DEFAULT NULL,
                           `active` bit(1) DEFAULT NULL,
                           `last_access` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`unique_id`, `username`, `password`, `create_date`, `update_date`, `active`, `last_access`) VALUES
    (1, 'NhatPhu', '12345', '2023-02-04 22:22:12', NULL, b'1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
                           `id` int(11) NOT NULL,
                           `wards` varchar(255) DEFAULT NULL,
                           `district` varchar(255) DEFAULT NULL,
                           `address_line` varchar(255) DEFAULT NULL,
                           `province` varchar(255) DEFAULT NULL,
                           `postal_id` varchar(255) DEFAULT NULL,
                           `is_default` bit(1) DEFAULT NULL,
                           `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `authority`
--

CREATE TABLE `authority` (
                             `unique_id` int(11) NOT NULL,
                             `role_id` int(11) DEFAULT NULL,
                             `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `authority`
--

INSERT INTO `authority` (`unique_id`, `role_id`, `account_id`) VALUES
    (1, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
                         `id` int(11) NOT NULL,
                         `brand_name` varchar(255) DEFAULT NULL,
                         `image` varchar(255) DEFAULT NULL,
                         `created_date` datetime DEFAULT NULL,
                         `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `brand_name`, `image`, `created_date`, `updated_date`) VALUES
                                                                                      (1, 'SamSung', NULL, '2023-02-02 21:18:46', NULL),
                                                                                      (2, 'Apple', NULL, '2023-02-02 21:19:23', NULL),
                                                                                      (3, 'Sony', NULL, '2023-02-02 21:19:44', NULL),
                                                                                      (4, 'Xiaomi', NULL, '2023-02-02 21:20:24', NULL),
                                                                                      (5, 'Huawei', NULL, '2023-02-02 21:20:53', NULL),
                                                                                      (6, 'Vivo', NULL, '2023-02-02 21:21:05', NULL),
                                                                                      (7, 'Nokia', NULL, '2023-02-02 21:21:33', NULL),
                                                                                      (8, 'Google', NULL, '2023-02-02 21:21:43', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
                        `id` int(11) NOT NULL,
                        `user_id` int(11) DEFAULT NULL,
                        `create_date` datetime DEFAULT NULL,
                        `price_sum` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart_detail`
--

CREATE TABLE `cart_detail` (
                               `id` int(11) NOT NULL,
                               `cart_id` int(11) DEFAULT NULL,
                               `quantity` int(11) DEFAULT NULL,
                               `create_date` datetime DEFAULT NULL,
                               `product_variant_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
                            `id` int(11) NOT NULL,
                            `category_name` varchar(100) DEFAULT NULL,
                            `parent_id` int(11) DEFAULT NULL,
                            `create_date` datetime DEFAULT NULL,
                            `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`, `parent_id`, `create_date`, `update_date`) VALUES
                                                                                              (1, 'Điện thoại', NULL, '2023-02-02 21:23:18', NULL),
                                                                                              (2, 'Laptop', NULL, '2023-02-02 21:23:31', NULL),
                                                                                              (3, 'Phụ kiện', NULL, '2023-02-02 21:23:48', NULL),
                                                                                              (4, 'Đồng hồ', NULL, '2023-02-02 21:24:33', NULL),
                                                                                              (5, 'Tablet', NULL, '2023-02-02 21:24:47', NULL),
                                                                                              (6, 'PC', NULL, '2023-02-02 21:25:02', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
                         `id` int(11) NOT NULL,
                         `color_name` varchar(100) DEFAULT NULL,
                         `color_code` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`id`, `color_name`, `color_code`) VALUES
                                                           (1, 'Màu đen', '#000'),
                                                           (2, 'Trắng', '#fff');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
                          `id` int(11) NOT NULL,
                          `user_id` int(11) DEFAULT NULL,
                          `created_date` datetime DEFAULT NULL,
                          `is_pay` bit(1) DEFAULT NULL,
                          `payment_id` int(11) DEFAULT NULL,
                          `status` int(11) DEFAULT NULL,
                          `is_cancelled` bit(1) DEFAULT NULL,
                          `promotion_id` int(11) DEFAULT NULL,
                          `district` varchar(50) DEFAULT NULL,
                          `address_line` varchar(50) DEFAULT NULL,
                          `province` varchar(50) DEFAULT NULL,
                          `postal_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
                                `id` int(11) NOT NULL,
                                `order_id` int(11) DEFAULT NULL,
                                `product_variant_id` int(11) DEFAULT NULL,
                                `create_date` datetime DEFAULT NULL,
                                `price_sum` double DEFAULT NULL,
                                `promotion_value` double DEFAULT NULL,
                                `quatity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
                                `id` int(11) NOT NULL,
                                `name` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment_method`
--

CREATE TABLE `payment_method` (
                                  `id` int(11) NOT NULL,
                                  `method` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` (`id`, `method`) VALUES
                                                  (1, 'VISA_CARD'),
                                                  (2, 'MOMO'),
                                                  (3, 'CASH');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
                           `id` int(11) NOT NULL,
                           `product_name` varchar(255) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `create_date` datetime DEFAULT NULL,
                           `update_date` datetime DEFAULT NULL,
                           `category_id` int(11) DEFAULT NULL,
                           `is_delete` bit(1) DEFAULT NULL,
                           `brand_id` int(11) DEFAULT NULL,
                           `promotion_id` int(11) DEFAULT NULL,
                           `type` int(11) DEFAULT NULL,
                           `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `product_name`, `description`, `create_date`, `update_date`, `category_id`, `is_delete`, `brand_id`, `promotion_id`, `type`, `image`) VALUES
    (1, 'Iphone 12', 'Trong những tháng cuối năm 2020, Apple đã chính thức giới thiệu đến người dùng cũng như iFan thế hệ iPhone 12 series mới với hàng loạt tính năng bứt phá, thiết kế được lột xác hoàn toàn, hiệu năng đầy mạnh mẽ và một trong số đó chính là iPhone 12 64GB.', '2023-02-02 21:26:56', NULL, 1, b'1', 2, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_attribute`
--

CREATE TABLE `product_attribute` (
                                     `id` int(11) NOT NULL,
                                     `attribute_name` varchar(100) DEFAULT NULL,
                                     `attribute_value` int(11) DEFAULT NULL,
                                     `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_variant`
--

CREATE TABLE `product_variant` (
                                   `id` int(11) NOT NULL,
                                   `sku_name` varchar(255) DEFAULT NULL,
                                   `quantity` int(11) DEFAULT NULL,
                                   `price` double DEFAULT NULL,
                                   `status` bit(1) DEFAULT NULL,
                                   `product_id` int(11) DEFAULT NULL,
                                   `image` varchar(255) DEFAULT NULL,
                                   `display_name` varchar(255) DEFAULT NULL,
                                   `color_id` int(11) DEFAULT NULL,
                                   `storage_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_variant`
--

INSERT INTO `product_variant` (`id`, `sku_name`, `quantity`, `price`, `status`, `product_id`, `image`, `display_name`, `color_id`, `storage_id`) VALUES
                                                                                                                                                     (2, 'IP12B', 100, 22000000, b'1', 1, NULL, 'IPhone 12 128GB', 1, 2),
                                                                                                                                                     (3, 'IP12W', 100, 20000000, b'1', 1, NULL, 'IPhone 12 64GB', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `promotion_product`
--

CREATE TABLE `promotion_product` (
                                     `id` int(11) NOT NULL,
                                     `expiration_date` datetime DEFAULT NULL,
                                     `created_date` datetime DEFAULT NULL,
                                     `name` varchar(255) DEFAULT NULL,
                                     `updated_date` datetime DEFAULT NULL,
                                     `maximum_price` double DEFAULT NULL,
                                     `activate` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion_type`
--

CREATE TABLE `promotion_type` (
                                  `id` int(11) NOT NULL,
                                  `name_promotion_type` varchar(255) DEFAULT NULL,
                                  `condition_minimum` int(11) DEFAULT NULL,
                                  `amount` double DEFAULT NULL,
                                  `is_limited` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion_user`
--

CREATE TABLE `promotion_user` (
                                  `id` int(11) NOT NULL,
                                  `name_promotion_user` varchar(255) DEFAULT NULL,
                                  `discount_value` int(11) DEFAULT NULL,
                                  `is_used` int(11) DEFAULT NULL,
                                  `create_date` datetime DEFAULT NULL,
                                  `update_date` datetime DEFAULT NULL,
                                  `expiration_date` datetime DEFAULT NULL,
                                  `promotion_code` varchar(255) DEFAULT NULL,
                                  `promotion_type` int(11) DEFAULT NULL,
                                  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
                          `id` int(11) NOT NULL,
                          `point` int(11) DEFAULT NULL,
                          `created_date` datetime DEFAULT NULL,
                          `user_id` int(11) DEFAULT NULL,
                          `order_detail_id` int(11) DEFAULT NULL,
                          `content` varchar(255) DEFAULT NULL,
                          `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
                        `unique_id` int(11) NOT NULL,
                        `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`unique_id`, `role_name`) VALUES
                                                  (1, 'USER'),
                                                  (2, 'ADMIN'),
                                                  (3, 'SUPER_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `storage`
--

CREATE TABLE `storage` (
                           `id` int(11) NOT NULL,
                           `storage_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `storage`
--

INSERT INTO `storage` (`id`, `storage_name`) VALUES
                                                 (1, '64GB'),
                                                 (2, '128GB');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
                        `id` int(11) NOT NULL,
                        `email` varchar(255) DEFAULT NULL unique,
                        `full_name` varchar(255) DEFAULT NULL,
                        `phone` varchar(13) DEFAULT NULL,
                        `create_date` datetime DEFAULT NULL,
                        `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `full_name`, `phone`, `create_date`, `update_date`) VALUES
    (1, 'synhatphu2@gmail.com', 'Nhật Phú', '038565758', '2023-02-09 21:12:29', '2023-02-09 21:12:42');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
                            `id` int(11) NOT NULL,
                            `product_id` int(11) DEFAULT NULL,
                            `user_id` int(11) DEFAULT NULL,
                            `update_date` datetime DEFAULT NULL,
                            `create_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
    ADD PRIMARY KEY (`unique_id`),
  ADD KEY `fk_account_user_1` (`unique_id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_address_1` (`user_id`);

--
-- Indexes for table `authority`
--
ALTER TABLE `authority`
    ADD PRIMARY KEY (`unique_id`),
  ADD KEY `fk_authority_account_1` (`account_id`),
  ADD KEY `fk_authority_role_1` (`role_id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_cart_1` (`user_id`);

--
-- Indexes for table `cart_detail`
--
ALTER TABLE `cart_detail`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cart_detail_product_variant_1` (`product_variant_id`),
  ADD KEY `fk_cart_detail_cart_1` (`cart_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category_category_1` (`parent_id`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_orders_promotion_user_1` (`promotion_id`),
  ADD KEY `fk_orders_order_status_1` (`status`),
  ADD KEY `fk_orders_payment_method_1` (`payment_id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_detail_orders_1` (`order_id`),
  ADD KEY `fk_order_detail_product_variant_1` (`product_variant_id`);

--
-- Indexes for table `order_status`
--
ALTER TABLE `order_status`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment_method`
--
ALTER TABLE `payment_method`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_category_1` (`category_id`),
  ADD KEY `fk_product_brand_1` (`brand_id`),
  ADD KEY `fk_promotion_product_product_1` (`promotion_id`);

--
-- Indexes for table `product_attribute`
--
ALTER TABLE `product_attribute`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_attribute_product` (`product_id`);

--
-- Indexes for table `product_variant`
--
ALTER TABLE `product_variant`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_variant_product_1` (`product_id`),
  ADD KEY `fk_color_product` (`color_id`),
  ADD KEY `fk_storage_product` (`storage_id`);

--
-- Indexes for table `promotion_product`
--
ALTER TABLE `promotion_product`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `promotion_type`
--
ALTER TABLE `promotion_type`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `promotion_user`
--
ALTER TABLE `promotion_user`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_promotion_user_user_1` (`user_id`),
  ADD KEY `fk_promotion_user_promotion_type_1` (`promotion_type`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rating_product_1` (`product_id`),
  ADD KEY `fk_rating_order_detail_1` (`order_detail_id`),
  ADD KEY `fk_rating_user_1` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`unique_id`);

--
-- Indexes for table `storage`
--
ALTER TABLE `storage`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_wishlist_product_1` (`product_id`),
  ADD KEY `fk_wishlist_user_1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
    MODIFY `unique_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `authority`
--
ALTER TABLE `authority`
    MODIFY `unique_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cart_detail`
--
ALTER TABLE `cart_detail`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `color`
--
ALTER TABLE `color`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_detail`
--
ALTER TABLE `order_detail`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_status`
--
ALTER TABLE `order_status`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment_method`
--
ALTER TABLE `payment_method`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product_attribute`
--
ALTER TABLE `product_attribute`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_variant`
--
ALTER TABLE `product_variant`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `promotion_product`
--
ALTER TABLE `promotion_product`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promotion_type`
--
ALTER TABLE `promotion_type`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promotion_user`
--
ALTER TABLE `promotion_user`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
    MODIFY `unique_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `storage`
--
ALTER TABLE `storage`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
    ADD CONSTRAINT `fk_account_user_1` FOREIGN KEY (`unique_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `address`
--
ALTER TABLE `address`
    ADD CONSTRAINT `fk_user_address_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `authority`
--
ALTER TABLE `authority`
    ADD CONSTRAINT `fk_authority_account_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`unique_id`),
  ADD CONSTRAINT `fk_authority_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`unique_id`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
    ADD CONSTRAINT `fk_user_cart_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart_detail`
--
ALTER TABLE `cart_detail`
    ADD CONSTRAINT `fk_cart_detail_cart_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `fk_cart_detail_product_variant_1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
    ADD CONSTRAINT `fk_category_category_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
    ADD CONSTRAINT `fk_orders_order_status_1` FOREIGN KEY (`status`) REFERENCES `order_status` (`id`),
  ADD CONSTRAINT `fk_orders_payment_method_1` FOREIGN KEY (`payment_id`) REFERENCES `payment_method` (`id`),
  ADD CONSTRAINT `fk_orders_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_orders_promotion_user_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_user` (`id`);

--
-- Constraints for table `order_detail`
--
ALTER TABLE `order_detail`
    ADD CONSTRAINT `fk_order_detail_orders_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `fk_order_detail_product_variant_1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
    ADD CONSTRAINT `fk_product_brand_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `fk_product_category_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `fk_promotion_product_product_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion_product` (`id`);

--
-- Constraints for table `product_attribute`
--
ALTER TABLE `product_attribute`
    ADD CONSTRAINT `fk_product_attribute_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_variant`
--
ALTER TABLE `product_variant`
    ADD CONSTRAINT `fk_color_product` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  ADD CONSTRAINT `fk_product_variant_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `fk_storage_product` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`);

--
-- Constraints for table `promotion_user`
--
ALTER TABLE `promotion_user`
    ADD CONSTRAINT `fk_promotion_user_promotion_type_1` FOREIGN KEY (`promotion_type`) REFERENCES `promotion_type` (`id`),
  ADD CONSTRAINT `fk_promotion_user_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
    ADD CONSTRAINT `fk_rating_order_detail_1` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`id`),
  ADD CONSTRAINT `fk_rating_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `fk_rating_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
    ADD CONSTRAINT `fk_wishlist_product_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `fk_wishlist_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `wishlist` ADD CONSTRAINT `uq_user_prod` UNIQUE KEY(`product_id`,`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


