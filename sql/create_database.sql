DROP SCHEMA IF EXISTS bioskop;
CREATE SCHEMA bioskop DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE bioskop;

CREATE TABLE tipProjekcije(
    id INTEGER PRIMARY KEY NOT NULL,
    naziv VARCHAR(15) NOT NULL
);

INSERT INTO tipProjekcije(naziv) VALUES ('2D'),('3D'),('4D')


CREATE TABLE sala(
    id INTEGER PRIMARY KEY,
    naziv varchar(10) NOT NULL
);

INSERT INTO sala(naziv) VALUES ('Sala 1');
INSERT INTO sala(naziv) VALUES ('Sala 2');
INSERT INTO sala(naziv) VALUES ('Sala 3');

CREATE TABLE sediste (
id BIGINT AUTO_INCREMENT,
salaId BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (salaId) REFERENCES sala (id) ON DELETE CASCADE
);

CREATE TABLE film (
id INTEGER PRIMARY KEY,
naziv VARCHAR (50) NOT NULL,
reziser VARCHAR (30) NOT NULL,
glumci VARCHAR (150) NOT NULL,
zanrovi VARCHAR(50) NOT NULL,
trajanje INT NOT NULL,
distributer VARCHAR (30) NOT NULL,
zemljaPorekla VARCHAR(20) NOT NULL,
godinaProizvodnje INT NOT NULL,
opis TEXT NOT NULL
);

CREATE TABLE korisnik (
username VARCHAR(20) NOT NULL PRIMARY KEY,
password VARCHAR(20) NOT NULL,
datumRegistracije DATE NOT NULL,
role VARCHAR(10) NOT NULL DEFAULT 'USER'
);

INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('a', 'a', '2020-01-01', 'ADMIN');

CREATE TABLE projekcija (
id BIGINT AUTO_INCREMENT,
filmId BIGINT NOT NULL,
tipProjekcijeId BIGINT NOT NULL,
salaId BIGINT NOT NULL,
vremePrikazivanja DATETIME NOT NULL,
cena BIGINT NOT NULL,
administrator VARCHAR(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (filmId) REFERENCES film (id) ON DELETE CASCADE,
FOREIGN KEY (tipProjekcijeId) REFERENCES tipProjekcije (id) ON DELETE CASCADE,
FOREIGN KEY (salaId) REFERENCES sala (id) ON DELETE CASCADE,
FOREIGN KEY (administrator) REFERENCES korisnik (username) ON DELETE CASCADE
);

CREATE TABLE karta (
id BIGINT AUTO_INCREMENT,
projekcijaId BIGINT NOT NULL,
sedisteId BIGINT NOT NULL,
vremeProdaje DATE NOT NULL,
korisnikId VARCHAR(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (projekcijaId) REFERENCES projekcija (id) ON DELETE CASCADE,
FOREIGN KEY (sedisteId) REFERENCES sediste (id) ON DELETE CASCADE,
FOREIGN KEY (korisnikId) REFERENCES korisnik (username) ON DELETE CASCADE
);