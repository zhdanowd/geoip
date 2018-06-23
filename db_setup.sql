CREATE DATABASE geoip;

USE geoip;

CREATE TABLE `ip2location_db5`(
	`ip_from` INT(20) UNSIGNED,
	`ip_to` INT(20) UNSIGNED,
	`country_code` CHAR(2),
	`country_name` VARCHAR(64),
	`region_name` VARCHAR(128),
	`city_name` VARCHAR(128),
	`latitude` DOUBLE,
	`longitude` DOUBLE,
	INDEX `idx_ip_from` (`ip_from`),
	INDEX `idx_ip_to` (`ip_to`),
	INDEX `idx_ip_from_to` (`ip_from`, `ip_to`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOAD DATA LOCAL
	INFILE 'IP2LOCATION-LITE-DB5.CSV'
INTO TABLE
	`ip2location_db5`
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 0 LINES;


ALTER TABLE `geoip`.`ip2location_db5` 
ADD COLUMN `id` INT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);
