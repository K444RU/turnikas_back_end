INSERT INTO role(role_code, role_name)
VALUES (1, 'Solo Player');
INSERT INTO role(role_code, role_name)
VALUES (2, 'Team of 11 players');

INSERT INTO "user"(role_code, email, password)
VALUES (1, 'admin@turnikas.com', 'admin123');
INSERT INTO "user"(role_code, email, password)
VALUES (2, 'treicarol@gmail.com', '123');
INSERT INTO "user"(role_code, email, password)
VALUES (1, 'denis@gmail.com', '123');
INSERT INTO "user"(role_code, email, password)
VALUES (1, 'vlades@gmail.com', '123');
INSERT INTO "user"(role_code, email, password)
VALUES (2, 'borisbritva@gmail.com', '123');

INSERT INTO contact(user_id, first_name, last_name, date_of_birth)
VALUES (1, 'admin', 'admin', '1996-06-02');
INSERT INTO contact(user_id, first_name, last_name, date_of_birth)
VALUES (2, 'Carol', 'Trei', '1997-10-16');
INSERT INTO contact(user_id, first_name, last_name, date_of_birth)
VALUES (3, 'Denis', 'Rästas', '1996-01-20');
INSERT INTO contact(user_id, first_name, last_name, date_of_birth)
VALUES (4, 'Vlad', 'Jagur', '1995-04-23');
INSERT INTO contact(user_id, first_name, last_name, date_of_birth)
VALUES (5, 'Boris', 'Britva', '1994-01-01');

INSERT INTO age_category(category_code, category_name)
VALUES (1, 'under 7 years old');
INSERT INTO age_category(category_code, category_name)
VALUES (2, '8-9 years old');
INSERT INTO age_category(category_code, category_name)
VALUES (3, '10-11 years old');
INSERT INTO age_category(category_code, category_name)
VALUES (4, '12-13 years old');
INSERT INTO age_category(category_code, category_name)
VALUES (5, '14-15 years old');
INSERT INTO age_category(category_code, category_name)
VALUES (6, '16-17 years old');
INSERT INTO age_category(category_code, category_name)
VALUES (7, '18+ years old');

ALTER TABLE team
    ALTER COLUMN team_logo DROP NOT NULL;

INSERT INTO team(user_id, category_code, stats_id, team_name, team_logo, team_coach_name)
VALUES (5, 2, 1, 'FC Barcelona', null, 'Valeri Biscelotti');
INSERT INTO team(user_id, category_code, stats_id, team_name, team_logo, team_coach_name)
VALUES (2, 1, 2, 'Real Madrid', null, 'Mark Bolotelli');
INSERT INTO team(user_id, category_code, stats_id, team_name, team_logo, team_coach_name)
VALUES (3, 1, 2, 'Chelsea', null, 'Andrea Boccelli');
INSERT INTO team(user_id, category_code, stats_id, team_name, team_logo, team_coach_name)
VALUES (4, 2, 4, 'Man City', null, 'Arturi Broccoli');

INSERT INTO stats(played, won, lost, draw, goal_for, goal_against)
VALUES (0, 0, 0, 0, 0, 0);
INSERT INTO stats(played, won, lost, draw, goal_for, goal_against)
VALUES (0, 0, 0, 0, 0, 0);
INSERT INTO stats(played, won, lost, draw, goal_for, goal_against)
VALUES (0, 0, 0, 0, 0, 0);
INSERT INTO stats(played, won, lost, draw, goal_for, goal_against)
VALUES (0, 0, 0, 0, 0, 0);

INSERT INTO city(id, city_name)
VALUES (1, 'Tallinn');
INSERT INTO city(id, city_name)
VALUES (2, 'Tartu');
INSERT INTO city(id, city_name)
VALUES (3, 'Narva');
INSERT INTO city(id, city_name)
VALUES (4, 'Pärnu');
INSERT INTO city(id, city_name)
VALUES (5, 'Viljandi');
INSERT INTO city(id, city_name)
VALUES (6, 'Haapsalu');

INSERT INTO stadium(id, city_id, name)
VALUES (1, 1, 'A. Le Coq Arena');
INSERT INTO stadium(id, city_id, name)
VALUES (2, 2, 'Tartu Tamme Kunstmuruväljak');
INSERT INTO stadium(id, city_id, name)
VALUES (3, 3, 'Narva Kreenholmi Staadium');
INSERT INTO stadium(id, city_id, name)
VALUES (4, 4, 'Pärnu Rannastaadion');
INSERT INTO stadium(id, city_id, name)
VALUES (5, 5, 'Viljandi Kunstmuruväljak');
INSERT INTO stadium(id, city_id, name)
VALUES (6, 6, 'Haapsalu Linnastaadion');

INSERT INTO player_amount(amount_code, amount_name)
VALUES (1, 4);
INSERT INTO player_amount(amount_code, amount_name)
VALUES (2, 8);
INSERT INTO player_amount(amount_code, amount_name)
VALUES (3, 16);
INSERT INTO player_amount(amount_code, amount_name)
VALUES (4, 32);
INSERT INTO player_amount(amount_code, amount_name)
VALUES (5, 64);
INSERT INTO player_amount(amount_code, amount_name)
VALUES (6, 128);

