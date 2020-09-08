DROP SCHEMA IF EXISTS BioskopDB;
USE BioskopDB;

CREATE TABLE korisnik (
username VARCHAR(20) NOT NULL PRIMARY KEY,
password VARCHAR(20) NOT NULL,
datumRegistracije DATE NOT NULL,
role VARCHAR(10) NOT NULL DEFAULT 'USER'
);

INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('a', 'a', '2020-01-01 20:00:00.00 ', 'ADMIN');
INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('b', 'b', '2020-02-02 21:00:00.00', 'USER');

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
opis TEXT NOT NULL,
obrisan BOOLEAN NOT NULL
);

INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('The Invisible Guest', 'Oriol Paulo', 'Mario Casas, 	Ana Wagener, José Coronado', 'Mystery, Thriller', 106, ' Warner Bros. Pictures', 'Spain', 2016, 'Spanish businessman Adrián Doria is out on bail after being arrested for the murder of his lover, Laura Vidal. His lawyer, Félix Leiva, hires prestigious defense attorney Virginia Goodman who visits him early one morning with the news that the prosecutor has found a witness who will be testifying in front of a judge soon, so he must tell the whole story quickly.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('The Loft', 'Erik Van Looy', 'Karl Urban, James Marsden, Wentworth Miller', 'Thriller', 103, ' Kinepolis Film Distribution', 'USA', 2014, '5 married men share ownership of an upmarket loft, which they use to discreetly meet their respective mistresses. When the body of a murdered woman is found in that loft, the men begin to suspect each other of having committed the gruesome crime, as they are the only ones with keys to the premises. Through flashbacks, which are intertwined with scenes from the present, the story is unraveled.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('Batman Returns', 'Tim Burton', 'Michael Keaton, Danny DeVito, Michelle Pfeiffer', 'Superhero film', 126, ' Warner Bros. Pictures', 'USA', 1992, 'In the prologue, Gotham City socialites Tucker and Esther Cobblepot become the parents of a deformed baby boy, Oswald. Disgusted by his appearance and wild demeanor, they confine the baby to a cage and ultimately throw him into the sewer, where he is discovered by a family of penguins underneath the abandoned zoo.', 0);

CREATE TABLE tipProjekcije(
    id INTEGER PRIMARY KEY NOT NULL,
    naziv VARCHAR(15) NOT NULL
);

INSERT INTO tipProjekcije(naziv) VALUES ('2D');
INSERT INTO tipProjekcije(naziv) VALUES ('3D');
INSERT INTO tipProjekcije(naziv) VALUES ('4D');

CREATE TABLE sala(
    id INTEGER PRIMARY KEY,
    naziv varchar(45) NOT NULL
);

INSERT INTO sala(naziv) VALUES ('SALA1');
INSERT INTO sala(naziv) VALUES ('SALA2');
INSERT INTO sala(naziv) VALUES ('SALA3');

CREATE TABLE sediste(
    redniBroj INTEGER PRIMARY KEY NOT NULL
);

INSERT INTO sediste VALUES(1);
INSERT INTO sediste VALUES(2);
INSERT INTO sediste VALUES(3);
INSERT INTO sediste VALUES(4);
INSERT INTO sediste VALUES(5);
INSERT INTO sediste VALUES(6);

CREATE TABLE sediste_sala(
    redniBroj INTEGER NOT NULL,
    sala_id INTEGER NOT NULL,
    zauzeto BIT NOT NULL,
    FOREIGN KEY(redniBroj) REFERENCES sediste(redniBroj) ON DELETE CASCADE,
    FOREIGN KEY(sala_id) REFERENCES sala(id) ON DELETE CASCADE
);

INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (1, 1, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (2, 1, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (3, 1, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (4, 1, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (5, 1, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (6, 1, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (1, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (2, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (3, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (4, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (5, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (6, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (1, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (2, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (3, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (4, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (5, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (6, 3, 0);

DROP TABLE projekcija;

CREATE TABLE projekcija(
    id INTEGER NOT NULL PRIMARY KEY,
    tipProjekcije_id  INTEGER NOT NULL,
    sala_id INTEGER NOT NULL,
    vremePrikazivanja DATETIME NOT NULL,
    cenaKarte DOUBLE NOT NULL,
    username VARCHAR(30) NOT NULL,
    film_id INTEGER NOT NULL,
    obrisan BOOLEAN NOT NULL,
    FOREIGN KEY(tipProjekcije_id) REFERENCES tipProjekcije(id) ON DELETE CASCADE,
    FOREIGN KEY(sala_id) REFERENCES sala(id) ON DELETE CASCADE,
    FOREIGN KEY(username) REFERENCES korisnik(username) ON DELETE CASCADE,
    FOREIGN KEY(film_id) REFERENCES film(id) ON DELETE CASCADE   
);

INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id,obrisan) VALUES (1, 1, '2020-08-10 20:00:00.00', 200.00, 'b', 1, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id,obrisan) VALUES (2, 2, '2020-08-10 21:00:00.00', 300.00, 'b', 2, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id,obrisan) VALUES (3, 3, '2020-08-10 20:00:00.00', 250.00, 'b', 3, 0);

CREATE TABLE karta(
    id INTEGER NOT NULL PRIMARY KEY,
    projekcija_id INTEGER NOT NULL,
    sediste_redniBroj INTEGER NOT NULL,
    vremeProdaje DATETIME NOT NULL,
    username VARCHAR(45) NOT NULL,
    FOREIGN KEY(projekcija_id) REFERENCES projekcija(id),
    FOREIGN KEY(sediste_redniBroj) REFERENCES sediste(redniBroj),
    FOREIGN KEY(username) REFERENCES korisnik(username)  
);

INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (1, 1,'2020-08-10 20:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (2, 2,'2020-08-10 21:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (3, 3,'2020-08-10 22:00:00.00', 'b');

SELECT * FROM projekcija;
