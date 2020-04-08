create database 996xmr;

use 996xmr;

CREATE TABLE IF NOT EXISTS `menu`(
   `id` BIGINT UNSIGNED AUTO_INCREMENT,
   `menu_name` VARCHAR(100) NOT NULL,
   `menu_title` VARCHAR(40) NOT NULL,
   `url` VARCHAR(255),
   `target` VARCHAR(32),
   `sort` INT,
   PRIMARY KEY ( `id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from menu;
