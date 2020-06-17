DROP SCHEMA IF EXISTS BioskopDB;
CREATE SCHEMA bioskop DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE BioskopDB;

CREATE TABLE korisnik (
username VARCHAR(20) NOT NULL PRIMARY KEY,
password VARCHAR(20) NOT NULL,
datumRegistracije DATE NOT NULL,
role VARCHAR(10) NOT NULL DEFAULT 'USER'
);

INSERT INTO korisnik(username, password, datumRegistracije, role) VALUES ('a', 'a', '2020-01-01', 'ADMIN');

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

INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) VALUES ('The Invisible Guest', 'Oriol Paulo', 'Mario Casas, 	Ana Wagener, José Coronado', 'Mystery, Thriller', 106, ' Warner Bros. Pictures', 'Spain', 2016, 'Spanish businessman Adrián Doria is out on bail after being arrested for the murder of his lover, Laura Vidal. His lawyer, Félix Leiva, hires prestigious defense attorney Virginia Goodman who visits him early one morning with the news that the prosecutor has found a witness who will be testifying in front of a judge soon, so he must tell the whole story quickly.');
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) VALUES ('The Loft', 'Erik Van Looy', 'Karl Urban, James Marsden, Wentworth Miller', 'Thriller', 103, ' Kinepolis Film Distribution', 'USA', 2014, '5 married men share ownership of an upmarket loft, which they use to discreetly meet their respective mistresses. When the body of a murdered woman is found in that loft, the men begin to suspect each other of having committed the gruesome crime, as they are the only ones with keys to the premises. Through flashbacks, which are intertwined with scenes from the present, the story is unraveled.');
INSERT INTO film(naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) VALUES ('Batman Returns', 'Tim Burton', 'Michael Keaton, Danny DeVito, Michelle Pfeiffer', 'Superhero film', 126, ' Warner Bros. Pictures', 'USA', 1992, 'In the prologue, Gotham City socialites Tucker and Esther Cobblepot become the parents of a deformed baby boy, Oswald. Disgusted by his appearance and wild demeanor, they confine the baby to a cage and ultimately throw him into the sewer, where he is discovered by a family of penguins underneath the abandoned zoo.');

