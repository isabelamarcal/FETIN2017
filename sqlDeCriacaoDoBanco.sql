-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema meconsulta
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema meconsulta
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `meconsulta` DEFAULT CHARACTER SET utf8 ;
USE `meconsulta` ;

-- -----------------------------------------------------
-- Table `meconsulta`.`Clinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meconsulta`.`Clinica` (
  `idClinica` INT NOT NULL AUTO_INCREMENT,
  `endereco` VARCHAR(450) NULL,
  `cidade` VARCHAR(150) NULL,
  `estado` VARCHAR(2) NULL,
  PRIMARY KEY (`idClinica`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meconsulta`.`Medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meconsulta`.`Medico` (
  `idMedico` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `nomeCompleto` VARCHAR(150) NULL,
  `especialidade` VARCHAR(45) NULL,
  `cmr` VARCHAR(45) NULL,
  PRIMARY KEY (`idMedico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meconsulta`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meconsulta`.`Paciente` (
  `idPaciente` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `nomeCompleto` VARCHAR(150) NULL,
  `tipoSanguineo` VARCHAR(4) NULL,
  PRIMARY KEY (`idPaciente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meconsulta`.`Consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meconsulta`.`Consulta` (
  `data` INT NULL,
  `Clinica_idClinica` INT NOT NULL,
  `Medico_idMedico` INT NOT NULL,
  `Paciente_idPaciente` INT NOT NULL,
  PRIMARY KEY (`Clinica_idClinica`, `Medico_idMedico`, `Paciente_idPaciente`),
  INDEX `fk_Consulta_Medico1_idx` (`Medico_idMedico` ASC),
  INDEX `fk_Consulta_Paciente1_idx` (`Paciente_idPaciente` ASC),
  CONSTRAINT `fk_Consulta_Clinica`
    FOREIGN KEY (`Clinica_idClinica`)
    REFERENCES `meconsulta`.`Clinica` (`idClinica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consulta_Medico1`
    FOREIGN KEY (`Medico_idMedico`)
    REFERENCES `meconsulta`.`Medico` (`idMedico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consulta_Paciente1`
    FOREIGN KEY (`Paciente_idPaciente`)
    REFERENCES `meconsulta`.`Paciente` (`idPaciente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meconsulta`.`Clinica_has_Medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meconsulta`.`Clinica_has_Medico` (
  `Clinica_idClinica` INT NOT NULL,
  `Medico_idMedico` INT NOT NULL,
  PRIMARY KEY (`Clinica_idClinica`, `Medico_idMedico`),
  INDEX `fk_Clinica_has_Medico_Medico1_idx` (`Medico_idMedico` ASC),
  INDEX `fk_Clinica_has_Medico_Clinica1_idx` (`Clinica_idClinica` ASC),
  CONSTRAINT `fk_Clinica_has_Medico_Clinica1`
    FOREIGN KEY (`Clinica_idClinica`)
    REFERENCES `meconsulta`.`Clinica` (`idClinica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Clinica_has_Medico_Medico1`
    FOREIGN KEY (`Medico_idMedico`)
    REFERENCES `meconsulta`.`Medico` (`idMedico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
