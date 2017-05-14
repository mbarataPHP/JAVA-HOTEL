
-- DATABASE hotel

delete from role;
delete from utilisateur;



INSERT INTO `role` (`id`, `type_role`) VALUES
(1, 'Directeur de l’hôtel'),
(2, 'Directeur du restaurant'),
(3, 'Directeur d’hébergement'),
(4, 'Chef de réception'),
(5, 'Gouvernante générale'),
(6, 'Chef de maintenance');


-- password = 123456
INSERT INTO `utilisateur` (`id`, `lastname`, `mail`, `firstname`, `password`, `id_role`) VALUES
(1, 'St-Pierre', 'JeanSt-Pierre@jourrapide.com', 'Jean', '5c39f5841494f5ba4e350f4985abbcb6753228d8db2e79d34e4bad78c955263a3ae013157392cfdb08bc3a47928a1f79e6b08c8ed3b9af5665ecdf7a812812e2', 1);

