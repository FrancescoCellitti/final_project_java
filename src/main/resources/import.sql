INSERT INTO categories (title) VALUES
('Action'),('Drama'),('Crime'),('Thriller'),('Sci-Fi'),
('Romance'),('Adventure'),('Animation'),('Biography'),('History');

INSERT INTO directors (name, surname, age) VALUES
('Francis Ford','Coppola',86),
('Christopher','Nolan',54),
('Quentin','Tarantino',61),
('Joon-ho','Bong',55),
('Frank','Darabont',66),
('David','Fincher',62),
('Ridley','Scott',86),
('James','Cameron',70),
('Peter','Jackson',63),
('Hayao','Miyazaki',83);

INSERT INTO films (title, description, duration_time, release_year, director_id) VALUES
('The Godfather','Power and family in the mafia world.',175,1972,1),
('The Dark Knight','A vigilante faces chaos in Gotham.',152,2008,2),
('Pulp Fiction','Intersecting crime stories in LA.',154,1994,3),
('Inception','Heists inside layered dreams.',148,2010,2),
('Interstellar','A mission beyond our galaxy.',169,2014,2),
('Parasite','Two families, one uneasy symbiosis.',132,2019,4),
('The Shawshank Redemption','Hope behind prison walls.',142,1994,5),
('Fight Club','An underground club changes a life.',139,1999,6),
('Se7en','Two detectives hunt a serial killer.',127,1995,6),
('Gladiator','A general seeks justice in Rome.',155,2000,7);

INSERT INTO films_categories (film_id, category_id) VALUES
(1,3),(1,2),
(2,1),(2,3),
(3,3),(3,2),
(4,5),(4,4),
(5,5),(5,2),
(6,4),(6,2),
(7,2),(7,3),
(8,2),(8,4),
(9,4),(9,3),
(10,1),(10,2);