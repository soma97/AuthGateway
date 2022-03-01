CREATE SCHEMA IF NOT EXISTS `auth_db`;
USE `auth_db` ;

CREATE TABLE IF NOT EXISTS `auth_db`.`role` (
    `role` VARCHAR(20) UNIQUE NOT NULL,
    `description` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`role`));


CREATE TABLE IF NOT EXISTS `auth_db`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(150) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_role`
    FOREIGN KEY (`role`)
    REFERENCES `auth_db`.`role` (`role`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

