-- Insert Directors
INSERT INTO director (name, birthdate)
VALUES ('Christopher Nolan', '1970-07-30'),
       ('Quentin Tarantino', '1963-03-27'),
       ('Martin Scorsese', '1942-11-17'),
       ('Greta Gerwig', '1983-08-04'),
       ('Steven Spielberg', '1946-12-18'),
       ('James Cameron', '1954-08-16'),
       ('David Fincher', '1962-08-28'),
       ('Ridley Scott', '1937-11-30'),
       ('Spike Lee', '1957-03-20'),
       ('Stanley Kubrick', '1928-07-26');

-- Insert Actors
INSERT INTO actors (name, birthdate)
VALUES ('Leonardo DiCaprio', '1974-11-11'),
       ('Tom Hardy', '1977-09-15'),
       ('Elliot Page', '1987-02-21'),
       ('Samuel L. Jackson', '1948-12-21'),
       ('Robert De Niro', '1943-08-17'),
       ('Meryl Streep', '1949-06-22'),
       ('Tom Hanks', '1956-07-09'),
       ('Brad Pitt', '1963-12-18'),
       ('Scarlett Johansson', '1984-11-22'),
       ('Angelina Jolie', '1975-06-04'),
       ('Keanu Reeves', '1964-09-02'),
       ('Natalie Portman', '1981-06-09'),
       ('Johnny Depp', '1963-06-09');

-- Insert Genres
INSERT INTO genres (name)
VALUES ('Action'),
       ('Science Fiction'),
       ('Crime'),
       ('Drama'),
       ('Comedy'),
       ('Adventure'),
       ('Thriller'),
       ('Fantasy'),
       ('Mystery'),
       ('Romance');

-- Insert Movies
INSERT INTO movies (title, director_id, rating, release_date, summary)
VALUES ('Inception', 1, 8.8, '2010-07-16', 'A mind-bending heist in dreams within dreams.'),
       ('Pulp Fiction', 2, 8.9, '1994-10-14', 'A crime masterpiece with an ensemble cast.'),
       ('The Irishman', 3, 7.8, '2019-11-27', 'A mob hitman reflects on his criminal past.'),
       ('Little Women', 4, 7.8, '2019-12-25', 'A timeless story of four sisters growing up in America.'),
       ('Jurassic Park', 5, 8.1, '1993-06-11', 'Dinosaurs come back to life in a thrilling adventure.'),
       ('Titanic', 6, 7.8, '1997-12-19', 'A tragic love story set on a sinking ship.'),
       ('The Shawshank Redemption', 7, 9.3, '1994-09-23', 'A man\'s journey through the prison system.'),
       ('Avatar', 8, 7.8, '2009-12-18', 'Exploring the alien world of Pandora.'),
       ('Blade Runner', 9, 8.1, '1982-06-25', 'A dystopian future with replicants and humans.'),
       ('Do the Right Thing', 10, 7.9, '1989-06-30', 'Racial tensions in a Brooklyn neighborhood.');

-- Insert Movie-Actor Relationships
INSERT INTO movie_actor (movie_id, actor_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),  -- Inception
       (2, 3),
       (2, 4),
       (2, 12), -- Pulp Fiction
       (3, 4),
       (3, 5),
       (3, 6),  -- The Irishman
       (4, 6),
       (4, 9),
       (4, 10), -- Little Women
       (5, 1),
       (5, 11),
       (5, 12), -- Jurassic Park
       (6, 1),
       (6, 2),
       (6, 7),  -- Titanic
       (7, 4),
       (7, 9),
       (7, 10), -- The Shawshank Redemption
       (8, 1),
       (8, 2),
       (8, 4),  -- Avatar
       (9, 4),
       (9, 5),
       (9, 8),  -- Blade Runner
       (10, 9),
       (10, 10),
       (10, 13);
-- Do the Right Thing

-- Insert Movie-Genre Relationships
INSERT INTO movie_genre (movie_id, genre_id)
VALUES (1, 1),
       (1, 2), -- Inception (Action, Sci-fi)
       (2, 3),
       (2, 7), -- Pulp Fiction (Crime, Thriller)
       (3, 3),
       (3, 4), -- The Irishman (Crime, Drama)
       (4, 4),
       (4, 5), -- Little Women (Drama, Comedy)
       (5, 1),
       (5, 7), -- Jurassic Park (Action, Adventure)
       (6, 4),
       (6, 8), -- Titanic (Drama, Romance)
       (7, 4),
       (7, 8), -- The Shawshank Redemption (Drama, Mystery)
       (8, 2),
       (8, 7), -- Avatar (Sci-fi, Thriller)
       (9, 2),
       (9, 7), -- Blade Runner (Sci-fi, Thriller)
       (10, 3),
       (10, 9); -- Do the Right Thing (Crime, Romance);

INSERT INTO movies (title, director_id, rating, release_date, summary)
VALUES ('The Godfather', 1, 9.2, '1972-03-24', 'The story of a powerful Mafia family.'),
       ('The Dark Knight', 1, 9.0, '2008-07-18', 'A superhero movie with a dark twist.');

INSERT INTO director (name, birthdate)
VALUES ('Hayao Miyazaki', '1941-01-05');
INSERT INTO movies (title, director_id, rating, release_date, summary)
VALUES ('Spirited Away', 11, 8.7, '2001-07-20', 'A heartwarming anime film.');

INSERT INTO director (name, birthdate)
VALUES ('Bong Joon-ho', '1969-09-14');

INSERT INTO movies (title, director_id, rating, release_date, summary)
VALUES ('Parasite', 12, 8.6, '2019-05-30', 'A dark comedy thriller about class inequality.');
INSERT INTO actors (name, birthdate)
VALUES ('Rumi Hiiragi', '1987-02-05'),
       ('Miyu Irino', '1988-02-19'),
       ('Mari Natsuki', '1954-05-22'),
       ('Song Kang-ho', '1967-01-17'),
       ('Lee Sun-kyun', '1975-03-01'),
       ('Cho Yeo-jeong', '1978-02-10');

INSERT INTO movie_actor (movie_id, actor_id)
VALUES
    (4, 20), (4, 21), (4, 22), -- Spirited Away
    (5, 23), (5, 24), (5, 25); -- Parasite
INSERT INTO movie_genre (movie_id, genre_id)
VALUES
    (66, 1), (66, 2), -- Spirited Away (Animation, Fantasy)
    (67, 3), (68, 5), (69, 7); -- Parasite (Comedy, Thriller, Drama)