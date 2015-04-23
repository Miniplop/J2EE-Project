
-- Peuplement de la base Utilisateur/Consomateur/Producteur 

INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Jean','Tiner@hotmail.fr','19 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Martin','Martin@hotmail.fr','20 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Destroyat','Destroyat@hotmail.fr','21 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Loisel','Loisel@hotmail.fr','22 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Poncet','Poncet@hotmail.fr','23 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Bchini','Bchini@hotmail.fr','24 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Erler','Erler@hotmail.fr','25 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Vallier','Loisel@hotmail.fr','26 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Mounier','Mounier@hotmail.fr','27 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Kardashian','Kardashian@hotmail.fr','28 impasse du pont carpin' );
INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES 
('Kévin','Dupont','Dupont@hotmail.fr','29 impasse du pont carpin' );


INSERT INTO Producteur (id) VALUES 
((SELECT id FROM Utilisateur WHERE id = '1'));
INSERT INTO Producteur (id) VALUES 
((SELECT id FROM Utilisateur WHERE id = '2'));
INSERT INTO Producteur (id) VALUES 
((SELECT id FROM Utilisateur WHERE id = '3'));
INSERT INTO Producteur (id) VALUES 
((SELECT id FROM Utilisateur WHERE id = '4'));
INSERT INTO Producteur (id) VALUES 
((SELECT id FROM Utilisateur WHERE id = '5'));


INSERT INTO Consommateur (id) VALUES
((SELECT id FROM Utilisateur WHERE id = '6'));
INSERT INTO Consommateur (id) VALUES
((SELECT id FROM Utilisateur WHERE id = '7'));
INSERT INTO Consommateur (id) VALUES
((SELECT id FROM Utilisateur WHERE id = '8'));
INSERT INTO Consommateur (id) VALUES
((SELECT id FROM Utilisateur WHERE id = '9'));
INSERT INTO Consommateur (id) VALUES
((SELECT id FROM Utilisateur WHERE id = '10'));

-- Création des produits 

INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Carotte', 'kg', '20', '2', (SELECT id FROM Producteur WHERE id = '1'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Tomate', 'kg', '10', '3', (SELECT id FROM Producteur WHERE id = '1'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Poireaux', 'piece(s)', '15', '2', (SELECT id FROM Producteur WHERE id = '1'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Choux', 'kg', '35', '1', (SELECT id FROM Producteur WHERE id = '2'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Orange', 'kg', '13', '2', (SELECT id FROM Producteur WHERE id = '2'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Poire', 'kg', '8', '2', (SELECT id FROM Producteur WHERE id = '2'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Banane', 'kg', '37', '4', (SELECT id FROM Producteur WHERE id = '3'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Litchi', 'kg', '16', '3', (SELECT id FROM Producteur WHERE id = '3'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Mangue', 'kg', '26', '1', (SELECT id FROM Producteur WHERE id = '3'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Porc', 'kg', '8', '2', (SELECT id FROM Producteur WHERE id = '4'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Boeuf', 'kg', '10', '3', (SELECT id FROM Producteur WHERE id = '4'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Agneau', 'kg', '9', '2', (SELECT id FROM Producteur WHERE id = '4'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Poulet', 'kg', '7', '3', (SELECT id FROM Producteur WHERE id = '4'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Mouton', 'kg', '8', '2', (SELECT id FROM Producteur WHERE id = '4'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Dinde', 'kg', '6', '2', (SELECT id FROM Producteur WHERE id = '4'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Amande', 'kg', '35', '1', (SELECT id FROM Producteur WHERE id = '5'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Noix', 'kg', '32', '1', (SELECT id FROM Producteur WHERE id = '5'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Pistache', 'kg', '52', '2', (SELECT id FROM Producteur WHERE id = '5'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Raisins secs', 'kg', '63', '3', (SELECT id FROM Producteur WHERE id = '5'));
INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES
('Bébé (frais)', 'kg', '50', '3', (SELECT id FROM Producteur WHERE id = '5'));

-- Création du Calendrier 

INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES 
('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('4',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('4',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('4',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('4',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('1',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('2',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('3',NULL,NULL);
INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES
('4',NULL,NULL);


INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES
('2015','Janvier', '1', '2', '3', '4');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES
('2015','Février', '5', '6', '7', '8');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES
('2015','Mars', '9', '10', '11', '12');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES
('2015','Avril', '13', '14', '15', '16');
INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES
('2015','Mai', '17', '18', '19', '20');

-- Création de Contrats

INSERT INTO Contrat ( quantite, valide, produit_id,consommateur_id,debut_semaine_id) VALUES 
('10','0', (SELECT id FROM Produit WHERE id = '3'),'6',(SELECT semaine_1_id FROM Mois WHERE nom = 'Janvier' AND annee = '2015'));

INSERT INTO Contrat ( quantite, valide,produit_id,consommateur_id,debut_semaine_id) VALUES 
('5','0',(SELECT id FROM Produit WHERE id = '2'),'7',(SELECT semaine_2_id FROM Mois WHERE nom = 'Février' AND annee = '2015'));

INSERT INTO Contrat ( quantite, valide,produit_id,consommateur_id,debut_semaine_id) VALUES 
('8','0',(SELECT id FROM Produit WHERE id = '7'), '8',(SELECT semaine_3_id FROM Mois WHERE nom = 'Janvier' AND annee = '2015'));

