CREATE DATABASE hotel;
USE hotel;

CREATE TABLE role (
	id BIGINT AUTO_INCREMENT NOT NULL, 
	type_role VARCHAR(255) UNIQUE, 
	PRIMARY KEY (id)
);

CREATE TABLE utilisateur (
	id BIGINT AUTO_INCREMENT NOT NULL, 
	mail VARCHAR(255) UNIQUE, 
	password VARCHAR(255), 
	lastname VARCHAR(255), 
	login VARCHAR(255) UNIQUE, 
	firstname VARCHAR(255), 
	id_role BIGINT, 
	PRIMARY KEY (id)
);

CREATE TABLE client(
	id BIGINT AUTO_INCREMENT NOT NULL, 
	mail VARCHAR(255) UNIQUE, 
	lastname VARCHAR(255), 
	firstname VARCHAR(255),
	PRIMARY KEY (id)
);

create table etage(
	id BIGINT AUTO_INCREMENT NOT NULL, 
	num_etage int(11),
	PRIMARY KEY (id)
);

create table type_chambre(
	id BIGINT AUTO_INCREMENT NOT NULL, 
	suite varchar(200) not null,
	PRIMARY KEY (id)
);

create table chambre(
	id BIGINT AUTO_INCREMENT NOT NULL, 
	nbr_place int(11) not null,
	prix float not null,
	id_etage BIGINT, 
	id_type BIGINT,
	PRIMARY KEY (id)
);


CREATE TABLE facture(
	id BIGINT AUTO_INCREMENT NOT NULL, 
	date_debut DATETIME,
	date_fin DATETIME, 
	facture_type VARCHAR(255), 
	id_client BIGINT,
	id_chambre BIGINT,
	PRIMARY KEY (id)
);

ALTER TABLE utilisateur ADD CONSTRAINT fk_utilisateur_id_role FOREIGN KEY (id_role) REFERENCES role (id);
ALTER TABLE chambre ADD CONSTRAINT fk_chambre_id_type FOREIGN KEY (id_type) REFERENCES type_chambre (id);
ALTER TABLE chambre ADD CONSTRAINT fk_chambre_id_etage FOREIGN KEY (id_etage) REFERENCES etage (id);
ALTER TABLE facture ADD CONSTRAINT fk_facture_id_client FOREIGN KEY (id_client) REFERENCES client (id);
ALTER TABLE facture ADD CONSTRAINT fk_facture_id_chambre FOREIGN KEY (id_chambre) REFERENCES chambre (id);
