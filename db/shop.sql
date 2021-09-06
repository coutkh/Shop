DROP DATABASE IF EXISTS `shop`;

CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8 ;
USE `shop` ;

CREATE TABLE IF NOT EXISTS `shop`.`role` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` ENUM('guest', 'user', 'admin') NOT NULL)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shop`.`users` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `login` VARCHAR(16) NOT NULL UNIQUE,
    `email` VARCHAR(255) NULL,
    `password` VARCHAR(32) NOT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `role_id` INT /*NOT NULL*/,
    CONSTRAINT `fk_users_role_id` FOREIGN KEY (`role_id`)
    REFERENCES `shop`.`role` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `shop`.`category` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL UNIQUE)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shop`.`product`(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `count` DECIMAL NOT NULL,
    `price` DECIMAL NOT NULL,
    `color` VARCHAR(255),
    `category_id` INT,
    `create_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `last_update` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_product_category_id` FOREIGN KEY (`category_id`)
    REFERENCES `shop`.`category` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `shop`.`status` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` ENUM('open', 'in_the_process','closed', 'paid') NOT NULL)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shop`.`receipt`(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `total` DECIMAL,
    `status_id` INT ,
    `users_id` INT ,
    `create_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_receipt_status_id`	FOREIGN KEY (`status_id`)
    REFERENCES `shop`.`status` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_receipt_user_id`	FOREIGN KEY (`users_id`)
    REFERENCES `shop`.`users` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS `shop`.`receipt_has_product`(
    `receipt_id` INT,
    `product_id` INT,
    `count` INT UNSIGNED NOT NULL DEFAULT 1,
    `price` DECIMAL NOT NULL DEFAULT 0,
    CONSTRAINT `fk_receipt_has_product_product_id` FOREIGN KEY (`product_id`)
    REFERENCES `shop`.`product` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    CONSTRAINT `fk_receipt_has_product_receipt_id` FOREIGN KEY (`receipt_id`)
    REFERENCES `shop`.`receipt` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


INSERT INTO role (id, name) VALUES (DEFAULT, 'guest');
INSERT INTO role (id, name) VALUES (DEFAULT, 'user');
INSERT INTO role (id, name) VALUES (DEFAULT, 'admin');

SET @temp = 'guest';
INSERT INTO users (id, login, password, role_id) VALUES (DEFAULT, @temp, @temp, (SELECT id FROM role WHERE name = @temp));
SET @temp = 'user';
INSERT INTO users (id, login, password, role_id) VALUES (DEFAULT, @temp, @temp, (SELECT id FROM role WHERE name = @temp));
SET @temp = 'admin';
INSERT INTO users (id, login, password, role_id) VALUES (DEFAULT, @temp, @temp, (SELECT id FROM role WHERE name = @temp));

INSERT INTO category (id, name) VALUES (DEFAULT, 'PC') ;

INSERT INTO product (id, name, count, price, category_id) VALUES (DEFAULT, 'Acer A14', 2, 15000.00, (SELECT id FROM category WHERE name = 'PC'));

INSERT INTO status (id, name) VALUES (DEFAULT , 'open');
INSERT INTO status (id, name) VALUES (DEFAULT , 'in_the_process');
INSERT INTO status (id, name) VALUES (DEFAULT , 'closed');
INSERT INTO status (id, name) VALUES (DEFAULT , 'paid');
