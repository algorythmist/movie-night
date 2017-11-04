create table genre (
    id int not null auto_increment,
    name varchar(255) not null,
    PRIMARY KEY(id)
);

create table movie_genre (
	id int not null auto_increment,
	movie_id int not null,
    genre_id int not null,
    PRIMARY KEY(id)
);