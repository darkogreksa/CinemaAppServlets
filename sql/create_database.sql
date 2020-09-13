USE BioskopDB;

CREATE TABLE korisnik (
username VARCHAR(20) NOT NULL PRIMARY KEY,
password VARCHAR(20) NOT NULL,
datumRegistracije DATE NOT NULL,
role VARCHAR(10) NOT NULL DEFAULT 'USER'
);

INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('a', 'a', '2020-01-01 20:00:00.00 ', 'ADMIN');
INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('b', 'b', '2020-02-02 21:00:00.00', 'USER');
INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('pera', '123', '2020-09-09 12:00:00.00', 'USER');

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
obrisan BIT NOT NULL
);

INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('The Invisible Guest', 'Oriol Paulo', 'Mario Casas, Ana Wagener, José Coronado', 'Mystery, Thriller', 106, ' Warner Bros. Pictures', 'Spain', 2016, 'Spanish businessman Adrián Doria is out on bail after being arrested for the murder of his lover, Laura Vidal. His lawyer, Félix Leiva, hires prestigious defense attorney Virginia Goodman who visits him early one morning with the news that the prosecutor has found a witness who will be testifying in front of a judge soon, so he must tell the whole story quickly.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('The Loft', 'Erik Van Looy', 'Karl Urban, James Marsden, Wentworth Miller', 'Thriller', 103, ' Kinepolis Film Distribution', 'USA', 2014, '5 married men share ownership of an upmarket loft, which they use to discreetly meet their respective mistresses. When the body of a murdered woman is found in that loft, the men begin to suspect each other of having committed the gruesome crime, as they are the only ones with keys to the premises. Through flashbacks, which are intertwined with scenes from the present, the story is unraveled.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('Batman Returns', 'Tim Burton', 'Michael Keaton, Danny DeVito, Michelle Pfeiffer', 'Superhero film', 126, ' Warner Bros. Pictures', 'USA', 1992, 'In the prologue, Gotham City socialites Tucker and Esther Cobblepot become the parents of a deformed baby boy, Oswald. Disgusted by his appearance and wild demeanor, they confine the baby to a cage and ultimately throw him into the sewer, where he is discovered by a family of penguins underneath the abandoned zoo.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('First cow', 'Kelly Reichardt', 'John Magaro, Orion Lee, Toby Jones', 'Drama', 121, ' A24', 'USA', 2019, 'In the present day, a woman is walking with her dog when she comes across a skull buried in the ground. She digs up two skeletons, side by side.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('The Godfather', 'Francis Ford Coppola', 'Marlon Brando, Al Pacino, James Caan', 'Crime film', 177, 'Paramount Pictures', 'USA', 1972, 'The Godfather is a 1972 American crime film directed by Francis Ford Coppola who co-wrote the screenplay with Mario Puzo, based on Puzos best-selling 1969 novel of the same name', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('Psycho', 'Alfred Hitchcock', 'Anthony Perkins, Vera Miles, John Gavin' , 'Psychological horror thriller', 109, 'Paramount Pictures', 'USA', 1960, 'Psycho is a 1960 American psychological horror thriller film produced and directed by Alfred Hitchcock. The screenplay, written by Joseph Stefano, was based on the 1959 novel of the same name by Robert Bloch.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('Schindlers List', 'Steven Spielberg', 'Liam Neeson, Ben Kingsley, Ralph Fiennes' , 'Epic historical drama', 195, 'Universal Pictures', 'USA', 1993, 'Schindlers List is a 1993 American epic historical drama film directed and produced by Steven Spielberg and written by Steven Zaillian. It is based on the 1982 non-fiction novel Schindlers Ark by Australian novelist Thomas Keneally.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('Pulp Fiction', 'Quentin Tarantino', 'John Travolta, Samuel L. Jackson, Uma Thurman' , 'Crime, Drama', 154, 'Miramax films', 'USA', 1994, 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('The Silence of the Lambs', 'Jonathan Demme', 'Jodie Foster, Anthony Hopkins, Scott Glenn' , 'Psychological horror', 118, 'Orion Pictures', 'USA', 1991, 'The Silence of the Lambs is a 1991 American psychological horror film directed by Jonathan Demme from a screenplay written by Ted Tally, adapted from Thomas Harris 1988 novel of the same name.', 0);
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan) VALUES ('Interstellar', 'Christopher Nolan', 'Matthew McConaughey, Anne Hathaway, Jessica Chastain' , 'Sci - Fi', 169, 'Paramount Pictures', 'USA', 2014, 'Set in a dystopian future where humanity is struggling to survive, the film follows a group of astronauts who travel through a wormhole near Saturn in search of a new home for mankind.', 0);


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
INSERT INTO sediste VALUES(7);
INSERT INTO sediste VALUES(8);
INSERT INTO sediste VALUES(9);
INSERT INTO sediste VALUES(10);

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
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (1, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (2, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (3, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (4, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (5, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (6, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (7, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (8, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (9, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (10, 2, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (1, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (2, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (3, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (4, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (5, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (6, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (7, 3, 0);
INSERT INTO sediste_sala (redniBroj, sala_id,zauzeto) VALUES (8, 3, 0);

DROP TABLE projekcija;

CREATE TABLE projekcija(
    id INTEGER NOT NULL PRIMARY KEY,
    tipProjekcije_id  INTEGER NOT NULL,
    sala_id INTEGER NOT NULL,
    vremePrikazivanja DATETIME NOT NULL,
    cenaKarte DOUBLE NOT NULL,
    username VARCHAR(30) NOT NULL,
    film_id INTEGER NOT NULL,
    obrisan BIT NOT NULL,
    FOREIGN KEY(tipProjekcije_id) REFERENCES tipProjekcije(id) ON DELETE CASCADE,
    FOREIGN KEY(sala_id) REFERENCES sala(id) ON DELETE CASCADE,
    FOREIGN KEY(username) REFERENCES korisnik(username) ON DELETE CASCADE,
    FOREIGN KEY(film_id) REFERENCES film(id) ON DELETE CASCADE   
);

INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-11 20:00:00.00', 250.00, 'a', 1, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-10 21:00:00.00', 250.00, 'a', 2, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-13 22:00:00.00', 250.00, 'a', 2, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 2, '2020-02-12 21:00:00.00', 350.00, 'a', 2, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-10 21:00:00.00', 250.00, 'a', 3, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 2, '2020-02-13 22:00:00.00', 250.00, 'a', 3, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 2, '2020-02-12 22:00:00.00', 350.00, 'a', 3, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 3, '2020-02-10 22:00:00.00', 450.00, 'a', 4, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 1, '2020-02-13 22:00:00.00', 350.00, 'a', 4, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 2, '2020-02-12 21:00:00.00', 350.00, 'a', 4, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 3, '2020-02-10 21:00:00.00', 550.00, 'a', 5, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-13 22:00:00.00', 550.00, 'a', 5, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-12 11:00:00.00', 550.00, 'a', 5, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-10 21:00:00.00', 450.00, 'a', 6, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 2, '2020-02-13 22:00:00.00', 650.00, 'a', 6, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-12 21:00:00.00', 350.00, 'a', 6, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 2, '2020-02-10 21:00:00.00', 350.00, 'a', 7, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-12 21:00:00.00', 650.00, 'a', 7, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 1, '2020-02-10 21:00:00.00', 250.00, 'a', 8, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-13 19:00:00.00', 250.00, 'a', 8, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 2, '2020-02-12 21:00:00.00', 350.00, 'a', 8, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (1, 1, '2020-02-10 21:00:00.00', 250.00, 'a', 9, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 2, '2020-02-13 22:00:00.00', 250.00, 'a', 9, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 2, '2020-02-11 21:00:00.00', 350.00, 'a', 9, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-11 21:00:00.00', 250.00, 'a', 10, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (2, 3, '2020-02-11 22:00:00.00', 250.00, 'a', 10, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-11 21:00:00.00', 350.00, 'a', 10, 0);
INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id, obrisan) VALUES (3, 3, '2020-02-11 21:00:00.00', 350.00, 'a', 10, 0);

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

INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (1, 1,'2020-02-5 13:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (1, 2,'2020-02-5 14:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (1, 3,'2020-02-5 13:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (2, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (2, 1,'2020-02-6 16:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (2, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (3, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (3, 1,'2020-02-6 16:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (3, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (4, 2,'2020-02-9 14:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (4, 1,'2020-02-6 16:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (4, 4,'2020-02-8 11:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (5, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (5, 1,'2020-02-6 16:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (6, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (6, 2,'2020-02-9 14:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (7, 1,'2020-02-6 16:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (7, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (8, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (8, 1,'2020-02-6 16:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (9, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (9, 2,'2020-02-9 14:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (10, 1,'2020-02-6 16:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (10, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (11, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (11, 1,'2020-02-6 16:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (12, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (12, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (13, 1,'2020-02-6 16:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (13, 4,'2020-02-8 11:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (14, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (14, 1,'2020-02-6 16:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (15, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (15, 2,'2020-02-9 14:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (15, 1,'2020-02-6 16:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (16, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (16, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (17, 1,'2020-02-6 16:00:00.00', 'b');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (17, 4,'2020-02-8 11:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (18, 2,'2020-02-9 14:00:00.00', 'pera');
INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username) VALUES (18, 1,'2020-02-6 16:00:00.00', 'pera');
