-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema franchising
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema franchising
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `franchising` DEFAULT CHARACTER SET utf8 ;
USE `franchising` ;

-- -----------------------------------------------------
-- Table `franchising`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`categoria` (
  `Nome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Nome`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `franchising`.`prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`prodotto` (
  `Codice` VARCHAR(13) NOT NULL,
  `prezzo` FLOAT NOT NULL,
  `marca` VARCHAR(15) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `descrizione` VARCHAR(500) NULL DEFAULT NULL,
  `quantita` INT NULL DEFAULT '15',
  `img` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`Codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `franchising`.`appartiene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`appartiene` (
  `Prodotto_Codice` VARCHAR(13) NOT NULL,
  `Categoria_Nome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Prodotto_Codice`, `Categoria_Nome`),
  INDEX `fk_Prodotto_has_Categoria_Categoria1_idx` (`Categoria_Nome` ASC) VISIBLE,
  INDEX `fk_Prodotto_has_Categoria_Prodotto1_idx` (`Prodotto_Codice` ASC) VISIBLE,
  CONSTRAINT `fk_Prodotto_has_Categoria_Categoria1`
    FOREIGN KEY (`Categoria_Nome`)
    REFERENCES `franchising`.`categoria` (`Nome`),
  CONSTRAINT `fk_Prodotto_has_Categoria_Prodotto1`
    FOREIGN KEY (`Prodotto_Codice`)
    REFERENCES `franchising`.`prodotto` (`Codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `franchising`.`citta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`citta` (
  `Citta` VARCHAR(45) NOT NULL,
  `CAP` VARCHAR(5) NOT NULL,
  `Provincia` VARCHAR(2) NOT NULL,
  `Regione` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Citta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `franchising`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`utente` (
  `Username` VARCHAR(20) NOT NULL,
  `CF` VARCHAR(16) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(20) NOT NULL,
  `Cognome` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `Type` INT NOT NULL DEFAULT '9',
  `Indirizzo` VARCHAR(45) NOT NULL,
  `Citta` VARCHAR(45) NOT NULL,
  `CAP` VARCHAR(5) NOT NULL,
  `Provincia` VARCHAR(2) NOT NULL,
  `Regione` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Username`),
  INDEX `citta_idx` (`Citta` ASC, `CAP` ASC, `Provincia` ASC, `Regione` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `franchising`.`ordine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`ordine` (
  `Codice` INT NOT NULL,
  `RiferimentoCliente` VARCHAR(45) NOT NULL,
  `data` DATE NULL DEFAULT NULL,
  `Utente_Username` VARCHAR(20) NOT NULL,
  `MetodoPagamento` VARCHAR(45) NOT NULL DEFAULT 'Contanti Alla Consegna',
  `Numero_Spedizione` VARCHAR(20) NULL DEFAULT NULL,
  `Corriere` VARCHAR(20) NULL DEFAULT NULL,
  `Metodo_Spedizione` VARCHAR(20) NULL DEFAULT 'Ritiro in Negozio',
  `Indirizzo` VARCHAR(45) NOT NULL,
  `Citta` VARCHAR(45) NOT NULL,
  `CAP` VARCHAR(5) NOT NULL,
  `Provincia` VARCHAR(2) NOT NULL,
  `Regione` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Codice`, `Utente_Username`),
  INDEX `fk_Carrello_Cliente1_idx` (`Utente_Username` ASC) VISIBLE,
  INDEX `citta_2_idx` (`Citta` ASC, `CAP` ASC, `Provincia` ASC, `Regione` ASC) VISIBLE,
  CONSTRAINT `fk_Carrello_Cliente1`
    FOREIGN KEY (`Utente_Username`)
    REFERENCES `franchising`.`utente` (`Username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `franchising`.`contenuto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `franchising`.`contenuto` (
  `Ordine_Codice` INT NOT NULL,
  `Prodotto_Codice` VARCHAR(13) NOT NULL,
  `quantita` INT NOT NULL,
  `Utente_Username` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Ordine_Codice`, `Prodotto_Codice`, `Utente_Username`),
  INDEX `fk_Carrello_has_Prodotto_Prodotto1_idx` (`Prodotto_Codice` ASC) VISIBLE,
  INDEX `fk_Carrello_has_Prodotto_Carrello1_idx` (`Ordine_Codice` ASC) VISIBLE,
  INDEX `fk_Ordine_Cliente` (`Utente_Username` ASC) VISIBLE,
  CONSTRAINT `fk_Carrello_has_Prodotto_Carrello1`
    FOREIGN KEY (`Ordine_Codice`)
    REFERENCES `franchising`.`ordine` (`Codice`),
  CONSTRAINT `fk_Carrello_has_Prodotto_Prodotto1`
    FOREIGN KEY (`Prodotto_Codice`)
    REFERENCES `franchising`.`prodotto` (`Codice`),
  CONSTRAINT `fk_Ordine_Cliente`
    FOREIGN KEY (`Utente_Username`)
    REFERENCES `franchising`.`utente` (`Username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
