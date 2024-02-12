-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-03-11 18:18:34.86

-- tables
-- Table: age_category
CREATE TABLE age_category (
    category_code int  NOT NULL,
    category_name varchar(128)  NOT NULL,
    CONSTRAINT age_category_pk PRIMARY KEY (category_code)
);

-- Table: city
CREATE TABLE city (
    id serial  NOT NULL,
    city_name varchar(100)  NOT NULL,
    CONSTRAINT city_pk PRIMARY KEY (id)
);

-- Table: contact
CREATE TABLE contact (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    first_name varchar(256)  NOT NULL,
    last_name varchar(256)  NOT NULL,
    date_of_birth date  NOT NULL,
    CONSTRAINT contact_pk PRIMARY KEY (id)
);

-- Table: group_result
CREATE TABLE group_result (
    tournament_group_id int  NOT NULL,
    stats_id int  NOT NULL,
    points int  NOT NULL,
    position int  NOT NULL,
    CONSTRAINT group_result_pk PRIMARY KEY (tournament_group_id)
);

-- Table: match
CREATE TABLE match (
    id serial  NOT NULL,
    tournament_id int  NOT NULL,
    match_format_code int  NOT NULL,
    date date  NOT NULL,
    team_a_score int  NULL,
    team_b_score int  NULL,
    CONSTRAINT match_pk PRIMARY KEY (id)
);

-- Table: match_format
CREATE TABLE match_format (
    format_code int  NOT NULL,
    format_name varchar(128)  NOT NULL,
    CONSTRAINT match_format_pk PRIMARY KEY (format_code)
);

-- Table: match_round
CREATE TABLE match_round (
    match_id int  NOT NULL,
    team_a_id int  NOT NULL,
    team_b_id int  NOT NULL,
    CONSTRAINT match_round_pk PRIMARY KEY (match_id,team_a_id,team_b_id)
);

-- Table: participation
CREATE TABLE participation (
    team_id int  NOT NULL,
    tournament_id int  NOT NULL,
    CONSTRAINT participation_pk PRIMARY KEY (team_id,tournament_id)
);

-- Table: player_amount
CREATE TABLE player_amount (
    amount_code int  NOT NULL,
    amount_name varchar(128)  NOT NULL,
    CONSTRAINT player_amount_pk PRIMARY KEY (amount_code)
);

-- Table: role
CREATE TABLE role (
    role_code int  NOT NULL,
    role_name varchar(128)  NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (role_code)
);

-- Table: stadium
CREATE TABLE stadium (
    id serial  NOT NULL,
    city_id int  NOT NULL,
    name varchar(100)  NOT NULL,
    CONSTRAINT stadium_pk PRIMARY KEY (id)
);

-- Table: stats
CREATE TABLE stats (
    id serial  NOT NULL,
    played int  NOT NULL,
    won int  NOT NULL,
    lost int  NOT NULL,
    draw int  NOT NULL,
    goal_for int  NOT NULL,
    goal_against int  NOT NULL,
    CONSTRAINT stats_pk PRIMARY KEY (id)
);

-- Table: team
CREATE TABLE team (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    category_code int  NOT NULL,
    stats_id int  NOT NULL,
    team_name varchar(128)  NOT NULL,
    team_logo bytea  NOT NULL,
    team_coach_name varchar(256)  NULL,
    CONSTRAINT team_pk PRIMARY KEY (id)
);

-- Table: team_player
CREATE TABLE team_player (
    id serial  NOT NULL,
    team_id int  NOT NULL,
    first_name varchar(256)  NOT NULL,
    last_name varchar(256)  NOT NULL,
    CONSTRAINT team_player_pk PRIMARY KEY (id)
);

-- Table: tournament
CREATE TABLE tournament (
    id serial  NOT NULL,
    age_category_code int  NOT NULL,
    player_amount_code int  NOT NULL,
    city_id int  NOT NULL,
    stadium_id int  NOT NULL,
    name varchar(128)  NOT NULL,
    start_date date  NOT NULL,
    end_date date  NOT NULL,
    participation_prise int  NOT NULL,
    prize varchar(255)  NOT NULL,
    additional_info varchar(255)  NOT NULL,
    CONSTRAINT tournament_pk PRIMARY KEY (id)
);

-- Table: tournament_bracket
CREATE TABLE tournament_bracket (
    id serial  NOT NULL,
    tournament_id int  NOT NULL,
    team_id int  NOT NULL,
    round varchar(5)  NOT NULL,
    parent_id int  NULL,
    CONSTRAINT tournament_bracket_pk PRIMARY KEY (id)
);

-- Table: tournament_group
CREATE TABLE tournament_group (
    id serial  NOT NULL,
    tournament_id int  NOT NULL,
    team_id int  NOT NULL,
    name varchar(1)  NOT NULL,
    CONSTRAINT tournament_group_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
    id serial  NOT NULL,
    role_code int  NOT NULL,
    email varchar(128)  NOT NULL,
    password varchar(128)  NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: contact_user (table: contact)
ALTER TABLE contact ADD CONSTRAINT contact_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: group_result_stats (table: group_result)
ALTER TABLE group_result ADD CONSTRAINT group_result_stats
    FOREIGN KEY (stats_id)
    REFERENCES stats (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: group_result_tournament_group (table: group_result)
ALTER TABLE group_result ADD CONSTRAINT group_result_tournament_group
    FOREIGN KEY (tournament_group_id)
    REFERENCES tournament_group (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: match_match_format (table: match)
ALTER TABLE match ADD CONSTRAINT match_match_format
    FOREIGN KEY (match_format_code)
    REFERENCES match_format (format_code)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: match_round_match (table: match_round)
ALTER TABLE match_round ADD CONSTRAINT match_round_match
    FOREIGN KEY (match_id)
    REFERENCES match (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: match_round_team_a (table: match_round)
ALTER TABLE match_round ADD CONSTRAINT match_round_team_a
    FOREIGN KEY (team_a_id)
    REFERENCES team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: match_round_team_b (table: match_round)
ALTER TABLE match_round ADD CONSTRAINT match_round_team_b
    FOREIGN KEY (team_b_id)
    REFERENCES team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: match_tournament (table: match)
ALTER TABLE match ADD CONSTRAINT match_tournament
    FOREIGN KEY (tournament_id)
    REFERENCES tournament (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: participation_team (table: participation)
ALTER TABLE participation ADD CONSTRAINT participation_team
    FOREIGN KEY (team_id)
    REFERENCES team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: participation_tournament (table: participation)
ALTER TABLE participation ADD CONSTRAINT participation_tournament
    FOREIGN KEY (tournament_id)
    REFERENCES tournament (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: team_age_category (table: team)
ALTER TABLE team ADD CONSTRAINT team_age_category
    FOREIGN KEY (category_code)
    REFERENCES age_category (category_code)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: team_player_team (table: team_player)
ALTER TABLE team_player ADD CONSTRAINT team_player_team
    FOREIGN KEY (team_id)
    REFERENCES team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: team_stats (table: team)
ALTER TABLE team ADD CONSTRAINT team_stats
    FOREIGN KEY (stats_id)
    REFERENCES stats (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: team_user (table: team)
ALTER TABLE team ADD CONSTRAINT team_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_age_category (table: tournament)
ALTER TABLE tournament ADD CONSTRAINT tournament_age_category
    FOREIGN KEY (age_category_code)
    REFERENCES age_category (category_code)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_bracket_team (table: tournament_bracket)
ALTER TABLE tournament_bracket ADD CONSTRAINT tournament_bracket_team
    FOREIGN KEY (team_id)
    REFERENCES team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_bracket_tournament (table: tournament_bracket)
ALTER TABLE tournament_bracket ADD CONSTRAINT tournament_bracket_tournament
    FOREIGN KEY (tournament_id)
    REFERENCES tournament (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_city (table: tournament)
ALTER TABLE tournament ADD CONSTRAINT tournament_city
    FOREIGN KEY (city_id)
    REFERENCES city (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_group_team (table: tournament_group)
ALTER TABLE tournament_group ADD CONSTRAINT tournament_group_team
    FOREIGN KEY (team_id)
    REFERENCES team (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_group_tournament (table: tournament_group)
ALTER TABLE tournament_group ADD CONSTRAINT tournament_group_tournament
    FOREIGN KEY (tournament_id)
    REFERENCES tournament (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_player_amount (table: tournament)
ALTER TABLE tournament ADD CONSTRAINT tournament_player_amount
    FOREIGN KEY (player_amount_code)
    REFERENCES player_amount (amount_code)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: tournament_stadium (table: tournament)
ALTER TABLE tournament ADD CONSTRAINT tournament_stadium
    FOREIGN KEY (stadium_id)
    REFERENCES stadium (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_role (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_role
    FOREIGN KEY (role_code)
    REFERENCES role (role_code)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

