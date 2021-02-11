USE `franchising` ;

INSERT INTO `franchising`.`prodotto` (`Codice`, `prezzo`, `marca`, `nome`, `descrizione`) VALUES ('123456789', '60', 'HiTech.com', 'Cuffie Gaming 200x ', 'Cuffie da gaming di ottima fattura');
INSERT INTO `franchising`.`prodotto` (`Codice`, `prezzo`, `marca`, `nome`, `descrizione`) VALUES ('098765432', '80', 'HiTech.com', 'Alimentatore 650 W Nero', 'Alimentatore da 650 W');
INSERT INTO `franchising`.`prodotto` (`Codice`, `prezzo`, `marca`, `nome`, `descrizione`) VALUES ('102938475', '95', 'HiTech.com', 'Scheda Madre AB350-Gaming', 'Gigabyte AB350-Gaming Scheda Madre, AMD B350, Nero');
INSERT INTO `franchising`.`prodotto` (`Codice`, `prezzo`, `marca`, `nome`, `descrizione`) VALUES ('564738291', '70', 'HiTech.com', 'G402 Hyperion Fury Nero', 'G402 Hyperion Fury FPS Mouse Gaming, 4000 DPI, Design Leggero, 8 Pulsanti Programmabili, Compatibile con PC/Mac/Laptop, Nero');

INSERT INTO `franchising`.`utente` (`Username`, `CF`, `password`, `Nome`, `Cognome`, `email`, `Type`, `Indirizzo`, `Citta`, `CAP`, `Provincia`, `Regione`) VALUES ('TonyEs97', 'spsntn97m09e932x', 'Antonio97','Antonio',  'Espsosito', 'a.esposito335@studenti.unisa.it', '1', 'Via Milano 42', 'Marcianise', '81025', 'CE', 'Campania');
INSERT INTO `franchising`.`utente` (`Username`, `CF`, `password`, `Nome`, `Cognome`, `email`, `Type`, `Indirizzo`, `Citta`, `CAP`, `Provincia`, `Regione`) VALUES ('MauFAb00', 'nnsocf00d22e999b', 'Maurizio00', 'Maurizio',  'Fabrocile', 'maufab00@emailprova.it', '9', 'Via nonloso 55', 'Marcianise', '81025', 'CE', 'Campania');

INSERT INTO `franchising`.`categoria` (`Nome`) VALUES ('Componenti PC');
INSERT INTO `franchising`.`categoria` (`Nome`) VALUES ('Periferiche di Input');
INSERT INTO `franchising`.`categoria` (`Nome`) VALUES ('Gaming');
INSERT INTO `franchising`.`categoria` (`Nome`) VALUES ('Periferiche Audio');

INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('123456789', 'Gaming');
INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('123456789', 'Periferiche Audio');
INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('098765432', 'Componenti PC');
INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('102938475', 'Componenti PC');
INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('102938475', 'Gaming');
INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('564738291', 'Periferiche di Input');
INSERT INTO `franchising`.`appartiene` (`Prodotto_Codice`, `Categoria_Nome`) VALUES ('564738291', 'Gaming');


