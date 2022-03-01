
-- -----------------------------------------------------
-- Schema demo_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `demo_db`;
USE `demo_db` ;

-- -----------------------------------------------------
-- Table `demo_db`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demo_db`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `demo_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demo_db`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `role` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`));