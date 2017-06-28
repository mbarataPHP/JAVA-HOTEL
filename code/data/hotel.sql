SET SQL_SAFE_UPDATES = 0;
-- DATABASE hotel
delete from utilisateur;
delete from client;
delete from role;
delete from chambre;
delete from etage;
delete from type_chambre;
delete from menu;
delete from facture;
SET SQL_SAFE_UPDATES = 1;


INSERT INTO `role` (`id`, `type_role`) VALUES
(1, 'Directeur de l’hôtel'),
(2, 'Directeur du restaurant'),
(3, 'Directeur d’hébergement'),
(4, 'Chef de réception'),
(5, 'Gouvernante générale'),
(6, 'Chef de maintenance');


INSERT INTO menu(id, nom_menu, prix) VALUES
(1, 'menu 1', 12.65),
(3, 'menu 2', 10.37),
(2, 'menu 3', 32.83);


-- password = 123456
INSERT INTO `utilisateur` (`id`, `lastname`, login, `mail`, `firstname`, `password`, `id_role`) VALUES
(1, 'St-Pierre', 'jst-pierre', 'JeanSt-Pierre@jourrapide.com', 'Jean', '123456', 1),
(2, 'St-paul', 'jst-paul', 'JeanSt-Pierre@paul.com', 'Jean', '123456', 3),
(3, 'Konovalova', 'ata-Konovalova', 'ata@Konovalova.ru', 'ata', '123456', 4),
(4, 'cartman', 'eric-cartman', 'eric@cartman.com', 'eric', '123456', 2),
(5, 'Zoidberg ', 'john-zoidberg', 'john@zoidberg.com', 'John', '123456', 5),
(6, 'Rodríguez ', 'bender-rodríguez', 'bender@rodríguez.com', 'Bender', '123456', 6);


INSERT INTO client(id, lastname, firstname, mail) VALUES
(1, 'toto', 'dude', 'toto@dude.com'),
(2, 'lolo', 'connard', 'lolo@connard.com'),
(3, 'putain', 'dddd', 'putain@dddd.com');


INSERT INTO etage(id, num_etage) VALUES
(1, 0),
(2, 1),
(3, 2);

INSERT INTO type_chambre(id, suite) VALUES
(1, "lit simple"),
(2, "lit double"),
(3, "grand lit double"),
(4, "deux lits"),
(5, "suite"),
(6, "suite royal");


INSERT INTO chambre(id, nbr_place, id_etage, prix, id_type) VALUES
(1, 2, 1, 250, 2),
(2, 2, 1, 300, 3),
(3, 2, 2, 400, 4),
(4, 1, 3, 120, 1),
(5, 2, 2, 650, 5),
(6, 2, 1, 1050, 6),
(7, 1, 2, 140, 1);