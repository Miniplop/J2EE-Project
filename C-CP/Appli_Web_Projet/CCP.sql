DROP TABLE Utilisateur CASCADE CONSTRAINTS;
DROP TABLE Consommateur CASCADE CONSTRAINTS;
DROP TABLE Producteur CASCADE CONSTRAINTS;
DROP TABLE Mois CASCADE CONSTRAINTS;
DROP TABLE Semaine CASCADE CONSTRAINTS;
DROP TABLE Contrat CASCADE CONSTRAINTS;
DROP TABLE Produit CASCADE CONSTRAINTS;
DROP TABLE Disponibilite CASCADE CONSTRAINTS;
DROP SEQUENCE seq_utilisateur;
DROP SEQUENCE seq_contrat;
DROP SEQUENCE seq_produit;
DROP SEQUENCE seq_semaine;
DROP SEQUENCE seq_disponibilite;
CREATE SEQUENCE seq_utilisateur;
CREATE SEQUENCE seq_contrat;
CREATE SEQUENCE seq_produit;
CREATE SEQUENCE seq_semaine;
CREATE SEQUENCE seq_disponibilite;
CREATE TABLE Utilisateur (
  id      number(5) DEFAULT seq_utilisateur.nextval NOT NULL, 
  nom     varchar2(30) NOT NULL, 
  prenom  varchar2(30) NOT NULL, 
  email   varchar2(40) NOT NULL, 
  adresse varchar2(100) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Consommateur (
  id number(5) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Producteur (
  id number(5) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Mois (
  annee        varchar2(10) NOT NULL, 
  nom          varchar2(10) NOT NULL, 
  semaine_1_id number(5) NOT NULL, 
  semaine_2_id number(5) NOT NULL, 
  semaine_3_id number(5) NOT NULL, 
  semaine_4_id number(5) NOT NULL, 
  PRIMARY KEY (annee, 
  nom));
CREATE TABLE Semaine (
  id                number(5) DEFAULT seq_semaine.nextval NOT NULL, 
  numero            number(1) NOT NULL, 
  consommateur_1_id number(5), 
  consommateur_2_id number(5), 
  PRIMARY KEY (id));
CREATE TABLE Disponibilite (
  id                number(5) DEFAULT seq_disponibilite.nextval NOT NULL, 
  consommateur_id number(5) NOT NULL, 
  contrat_id number(5) NOT NULL, 
  numero_semaine number(5) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Contrat (
  id               number(10) DEFAULT seq_contrat.nextval NOT NULL, 
  quantite         number(10) NOT NULL, 
  valide           number(1) DEFAULT '2' NOT NULL, 
  produit_id       number(10) NOT NULL, 
  consommateur_id  number(5) NOT NULL, 
  debut_semaine_id number(5) DEFAULT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Produit (
  id            number(10) DEFAULT seq_produit.nextval NOT NULL, 
  nom           varchar2(100) NOT NULL, 
  unite         varchar2(10) NOT NULL, 
  quantite      number(10) NOT NULL, 
  duree         number(10) NOT NULL,
  producteur_id number(5) NOT NULL, 
  PRIMARY KEY (id));
ALTER TABLE Consommateur ADD CONSTRAINT FKConsommate65397 FOREIGN KEY (id) REFERENCES Utilisateur (id);
ALTER TABLE Contrat ADD CONSTRAINT FKContrat962754 FOREIGN KEY (produit_id) REFERENCES Produit (id);
ALTER TABLE Producteur ADD CONSTRAINT FKProducteur640769 FOREIGN KEY (id) REFERENCES Utilisateur (id);
ALTER TABLE Contrat ADD CONSTRAINT FKContrat296771 FOREIGN KEY (consommateur_id) REFERENCES Consommateur (id);
ALTER TABLE Mois ADD CONSTRAINT S1 FOREIGN KEY (semaine_1_id) REFERENCES Semaine (id);
ALTER TABLE Mois ADD CONSTRAINT S2 FOREIGN KEY (semaine_2_id) REFERENCES Semaine (id);
ALTER TABLE Mois ADD CONSTRAINT S3 FOREIGN KEY (semaine_3_id) REFERENCES Semaine (id);
ALTER TABLE Mois ADD CONSTRAINT S4 FOREIGN KEY (semaine_4_id) REFERENCES Semaine (id);
ALTER TABLE Contrat ADD CONSTRAINT FKContrat922840 FOREIGN KEY (debut_semaine_id) REFERENCES Semaine (id);
ALTER TABLE Produit ADD CONSTRAINT FKProduit675306 FOREIGN KEY (producteur_id) REFERENCES Producteur (id);
ALTER TABLE Semaine ADD CONSTRAINT permanant1 FOREIGN KEY (consommateur_1_id) REFERENCES Consommateur (id);
ALTER TABLE Semaine ADD CONSTRAINT permanant2 FOREIGN KEY (consommateur_2_id) REFERENCES Consommateur (id);
ALTER TABLE Disponibilite ADD CONSTRAINT FKconsommateurid FOREIGN KEY (consommateur_id) REFERENCES Consommateur (id);
ALTER TABLE Disponibilite ADD CONSTRAINT FKcontratid FOREIGN KEY (contrat_id) REFERENCES Contrat (id);
