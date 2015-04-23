SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS Disponibilite CASCADE;
DROP TABLE IF EXISTS Mois CASCADE;
DROP TABLE IF EXISTS Permanence CASCADE;
DROP TABLE IF EXISTS Consommateur CASCADE;
DROP TABLE IF EXISTS Contrat CASCADE;
DROP TABLE IF EXISTS Semaine CASCADE;
DROP TABLE IF EXISTS Produit CASCADE;
DROP TABLE IF EXISTS Producteur CASCADE;
DROP TABLE IF EXISTS Utilisateur CASCADE;
SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE Utilisateur (
  id             int NOT NULL AUTO_INCREMENT, 
  nom            varchar(30) NOT NULL, 
  prenom         varchar(30) NOT NULL, 
  email          varchar(40) NOT NULL, 
  adresse        varchar(100) NOT NULL,
  PRIMARY KEY (id));
CREATE TABLE Consommateur (
  id int NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Producteur (
  id int NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Mois (
  annee        varchar(10) NOT NULL, 
  nom          varchar(10) NOT NULL, 
  semaine_1_id int, 
  semaine_2_id int, 
  semaine_3_id int, 
  Semaine_4_id int, 
  PRIMARY KEY (annee, 
  nom));
CREATE TABLE Semaine (
  id     int NOT NULL AUTO_INCREMENT, 
  numero INT NOT NULL,
  consommateur_1_id int,
  consommateur_2_id int,
  PRIMARY KEY (id));
CREATE TABLE Contrat (
  id               int NOT NULL AUTO_INCREMENT, 
  quantite         INT NOT NULL, 
  valide           INT DEFAULT '0' NOT NULL, 
  produit_id       int NOT NULL, 
  debut_semaine_id int, 
  consommateur_id  int NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Produit (
  id            int NOT NULL AUTO_INCREMENT, 
  nom           varchar(100) NOT NULL, 
  unite         varchar(10) NOT NULL, 
  quantite      INT NOT NULL, 
  duree         INT NOT NULL, 
  producteur_id int NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Permanence (
  Semaineid       int NOT NULL, 
  Consommateurid  int, 
  Consommateurid2 int, 
  PRIMARY KEY (Semaineid));
CREATE TABLE Disponibilite (
  id             int NOT NULL AUTO_INCREMENT, 
  Consommateurid int NOT NULL, 
  Contratid      int NOT NULL, 
  numero_semaine INT NOT NULL, 
  PRIMARY KEY (id));
ALTER TABLE Contrat ADD CONSTRAINT FKContrat962754 FOREIGN KEY (produit_id) REFERENCES Produit (id);
ALTER TABLE Contrat ADD CONSTRAINT FKContrat641569 FOREIGN KEY (consommateur_id) REFERENCES Utilisateur (id);
ALTER TABLE Contrat ADD CONSTRAINT FKContrat922840 FOREIGN KEY (debut_semaine_id) REFERENCES Semaine (id);
ALTER TABLE Produit ADD CONSTRAINT FKProduit65084 FOREIGN KEY (producteur_id) REFERENCES Utilisateur (id);
ALTER TABLE Producteur ADD CONSTRAINT FKProducteur640769 FOREIGN KEY (id) REFERENCES Utilisateur (id);
ALTER TABLE Consommateur ADD CONSTRAINT FKConsommate65397 FOREIGN KEY (id) REFERENCES Utilisateur (id);
ALTER TABLE Permanence ADD CONSTRAINT FKPermanence732394 FOREIGN KEY (Semaineid) REFERENCES Semaine (id);
ALTER TABLE Permanence ADD CONSTRAINT FKPermanence876738 FOREIGN KEY (Consommateurid) REFERENCES Consommateur (id);
ALTER TABLE Permanence ADD CONSTRAINT FKPermanence126638 FOREIGN KEY (Consommateurid2) REFERENCES Consommateur (id);
ALTER TABLE Mois ADD CONSTRAINT FKMois77181 FOREIGN KEY (semaine_1_id) REFERENCES Semaine (id);
ALTER TABLE Mois ADD CONSTRAINT FKMois106972 FOREIGN KEY (semaine_2_id) REFERENCES Semaine (id);
ALTER TABLE Mois ADD CONSTRAINT FKMois136763 FOREIGN KEY (semaine_3_id) REFERENCES Semaine (id);
ALTER TABLE Mois ADD CONSTRAINT FKMois487006 FOREIGN KEY (Semaine_4_id) REFERENCES Semaine (id);
ALTER TABLE Semaine ADD CONSTRAINT permanant1 FOREIGN KEY (consommateur_1_id) REFERENCES Consommateur (id);
ALTER TABLE Semaine ADD CONSTRAINT permanant2 FOREIGN KEY (consommateur_2_id) REFERENCES Consommateur (id);
ALTER TABLE Disponibilite ADD CONSTRAINT FKDisponibil914684 FOREIGN KEY (Consommateurid) REFERENCES Consommateur (id);
ALTER TABLE Disponibilite ADD CONSTRAINT FKDisponibil514441 FOREIGN KEY (Contratid) REFERENCES Contrat (id);



-- Peuplement de la base Utilisateur/Consomateur/Producteur 

INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Jean','Tiner@hotmail.fr','19 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Martin','Martin@hotmail.fr','20 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Destroyat','Destroyat@hotmail.fr','21 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Loisel','Loisel@hotmail.fr','22 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Poncet','Poncet@hotmail.fr','23 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Bchini','Bchini@hotmail.fr','24 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Erler','Erler@hotmail.fr','25 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Vallier','Loisel@hotmail.fr','26 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Mounier','Mounier@hotmail.fr','27 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Kardashian','Kardashian@hotmail.fr','28 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES ('Kévin','Dupont','Dupont@hotmail.fr','29 impasse du pont carpin' );


INSERT INTO Producteur (id) VALUES ('1');
INSERT INTO Producteur (id) VALUES ('2');
INSERT INTO Producteur (id) VALUES ('3');
INSERT INTO Producteur (id) VALUES ('4');
INSERT INTO Producteur (id) VALUES ('5');


INSERT INTO Consommateur (id) VALUES ('6');
INSERT INTO Consommateur (id) VALUES ('7');
INSERT INTO Consommateur (id) VALUES ('8');
INSERT INTO Consommateur (id) VALUES ('9');
INSERT INTO Consommateur (id) VALUES ('10');

-- Création des produits 

INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Carotte', 'kg', '20', '2', '1');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Tomate', 'kg', '10', '3', '1');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Poireaux', 'piece(s)', '15', '2', '1');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Choux', 'kg', '35', '1', '2');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Orange', 'kg', '13', '2', '2');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Poire', 'kg', '8', '2', '2');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Banane', 'kg', '37', '4', '3');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Litchi', 'kg', '16', '3', '3');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Mangue', 'kg', '26', '1', '3');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Porc', 'kg', '8', '2', '4');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Boeuf', 'kg', '10', '3', '4');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Agneau', 'kg', '9', '2', '4');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Poulet', 'kg', '7', '3', '4');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Mouton', 'kg', '8', '2', '4');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Dinde', 'kg', '6', '2', '4');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Amande', 'kg', '35', '1', '5');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Noix', 'kg', '32', '1', '5');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Pistache', 'kg', '52', '2', '5');
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES ('Raisins secs', 'kg', '63', '3', '5');

-- Création du Calendrier 

INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('1', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('2', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('3', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('4', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('1', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('2', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('3', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('4', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('1', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('2', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('4',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('4',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('3', NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES ('4', NULL,NULL);


INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES ('2015', 'Janvier', '1', '2', '3', '4');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES ('2015', 'Février', '5', '6', '7', '8');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES ('2015', 'Mars', '9', '10', '11', '12');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES ('2015', 'Avril', '13', '14', '15', '16');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES ('2015', 'Mai', '17', '18', '19', '20');

-- Création de Contrats

INSERT INTO Contrat ( quantite, valide, produit_id,consommateur_id,debut_semaine_id) VALUES 
('10', '0', '3', '6', (SELECT semaine_1_id FROM Mois WHERE nom = 'Janvier' AND annee = '2015'));

INSERT INTO Contrat ( quantite, valide,produit_id,consommateur_id,debut_semaine_id) VALUES 
('5', '0','2', '7', (SELECT semaine_2_id FROM Mois WHERE nom = 'Février' AND annee = '2015'));

INSERT INTO Contrat ( quantite, valide,produit_id,consommateur_id,debut_semaine_id) VALUES 
('8', '0', '7', '8', (SELECT semaine_3_id FROM Mois WHERE nom = 'Janvier' AND annee = '2015'));

