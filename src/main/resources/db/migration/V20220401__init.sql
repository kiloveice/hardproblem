/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : what_to_eat

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 07/04/2022 09:17:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                         `price` decimal(10, 2) NULL DEFAULT NULL,
                         `score` int(11) NULL DEFAULT NULL,
                         `status` tinyint(1) NULL DEFAULT 1,
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for food_tag
-- ----------------------------
DROP TABLE IF EXISTS `food_tag`;
CREATE TABLE `food_tag`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `food_id` int(11) NULL DEFAULT NULL,
                             `tag_id` int(11) NULL DEFAULT NULL,
                             `status` tinyint(1) NULL DEFAULT 1,
                             `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`) USING BTREE,
                             INDEX `food_tag_ft`(`food_id`) USING BTREE,
                             INDEX `food_tag_tf`(`tag_id`) USING BTREE,
                             CONSTRAINT `food_tag_ft` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
                             CONSTRAINT `food_tag_tf` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                         `status` tinyint(1) NULL DEFAULT 1,
                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu_food
-- ----------------------------
DROP TABLE IF EXISTS `menu_food`;
CREATE TABLE `menu_food`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `menu_id` int(11) NULL DEFAULT NULL,
                              `food_id` int(11) NULL DEFAULT NULL,
                              `status` tinyint(1) NULL DEFAULT 1,
                              `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `menu_food_mf`(`menu_id`) USING BTREE,
                              INDEX `menu_food_fm`(`food_id`) USING BTREE,
                              CONSTRAINT `menu_food_fm` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
                              CONSTRAINT `menu_food_mf` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `status` tinyint(1) NULL DEFAULT 1,
                        `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
