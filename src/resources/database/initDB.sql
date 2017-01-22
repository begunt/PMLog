-- -----------------------------------------------------
-- Schema pmlog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pmlog` DEFAULT CHARACTER SET utf8 ;
USE `pmlog` ;

-- -----------------------------------------------------
-- Table `pmlog`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pmlog`.`project` (
  `idProject` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `jiraProjectKeyA1QA` VARCHAR(45) NOT NULL,
  `jiraProjectKeyPM` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProject`),
  UNIQUE INDEX `idProject_UNIQUE` (`idProject` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pmlog`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pmlog`.`type` (
  `idType` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idType`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pmlog`.`field`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pmlog`.`field` (
  `idField` INT(11) NOT NULL,
  `idType` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `idProject` INT(11) NOT NULL,
  PRIMARY KEY (`idField`),
  UNIQUE INDEX `idProject_UNIQUE` (`idField` ASC),
  INDEX `FKProjectField_idx` (`idProject` ASC),
  INDEX `FKTypeField_idx` (`idType` ASC),
  CONSTRAINT `FKProjectField`
    FOREIGN KEY (`idProject`)
    REFERENCES `pmlog`.`project` (`idProject`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FKTypeField`
    FOREIGN KEY (`idType`)
    REFERENCES `pmlog`.`type` (`idType`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pmlog`.`dropdown`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pmlog`.`dropdown` (
  `idDropdown` INT(11) NOT NULL,
  `idField` INT(11) NOT NULL,
  `itemName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDropdown`),
  UNIQUE INDEX `idProject_UNIQUE` (`idDropdown` ASC),
  INDEX `FKFieldDropdown_idx` (`idField` ASC),
  CONSTRAINT `FKFieldDropdown`
    FOREIGN KEY (`idField`)
    REFERENCES `pmlog`.`field` (`idField`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
