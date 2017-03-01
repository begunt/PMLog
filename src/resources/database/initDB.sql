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
  `modelFieldName` VARCHAR(45) NOT NULL,
  `required` VARCHAR(45) NULL,
  `tooltip` VARCHAR(200) NULL,
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

-- -----------------------------------------------------
-- Table `pmlog`.`report`
-- -----------------------------------------------------
CREATE TABLE `pmlog`.`report` (
  `idreport` INT NOT NULL,
  `selectedProject` INT NOT NULL,
  `timestamp` VARCHAR(45) NOT NULL,
  `person` VARCHAR(45) NOT NULL,
  `product` VARCHAR(45) NOT NULL,
  `project` VARCHAR(45) NOT NULL,
  `activity` VARCHAR(45) NOT NULL,
  `sprint` VARCHAR(45) NULL,
  `build` VARCHAR(45) NULL,
  `devices` VARCHAR(45) NULL,
  `environment` VARCHAR(45) NULL,
  `time` VARCHAR(45) NULL,
  `comment` VARCHAR(45) NULL,
  `link` VARCHAR(45) NULL,
  `numberOfCheckedStories` INT NULL,
  `numberOfReopenedStories` INT NULL,
  `linkToReopenedStories` VARCHAR(45) NULL,
  `numberOfCheckedDefects` INT NULL,
  `numberOfReopenedDefects` INT NULL,
  `linkToReopenedDefects` VARCHAR(45) NULL,
  `milestone` VARCHAR(45) NULL,
  `testruns` VARCHAR(45) NULL,
  `numberOfCheckedCases` INT NULL,
  `reportcol` VARCHAR(45) NULL,
  `linkToTask` VARCHAR(200) NULL,
  PRIMARY KEY (`idreport`));