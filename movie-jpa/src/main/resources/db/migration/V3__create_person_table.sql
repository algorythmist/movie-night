create table person (
    id int not null auto_increment,
    name varchar(255) not null,
    actor boolean not null default false,
    director boolean not null default false,
    PRIMARY KEY(id)
);

create index person_name_idx on person(name);

create table movie_person (
	id int not null auto_increment,
	movie_id int not null,
    person_id int not null,
    PRIMARY KEY(id)
);