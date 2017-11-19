create table person (
    id int not null auto_increment,
    name varchar(255) not null,
    PRIMARY KEY(id)
);

create index person_name_idx on person(name);

create table movie_actor (
	id int not null auto_increment,
	movie_id int not null,
    actor_id int not null,
    PRIMARY KEY(id)
);

create table movie_director (
	id int not null auto_increment,
	movie_id int not null,
    director_id int not null,
    PRIMARY KEY(id)
);