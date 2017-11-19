create table genre (
    id serial primary key,
    name varchar(255) not null
);

create unique index genre_name_ux on genre(name);

create table movie_genre (
	id serial primary key,
	movie_id int not null REFERENCES movie(id),
    genre_id int not null REFERENCES genre(id)
);