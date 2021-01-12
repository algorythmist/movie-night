create table person (
    id serial primary key,
    name varchar(255) not null
);

create index person_name_ix on person(name);

create table movie_actor (
	id serial primary key,
	movie_id int not null REFERENCES movie(id),
    actor_id int not null REFERENCES person(id)
);

create table movie_director (
	id serial primary key,
	movie_id int not null REFERENCES movie(id),
    director_id int not null REFERENCES person(id)
);