create table movie (
    id serial primary key,
    title varchar(255) not null,
    year int not null,
    plot text,
	releaseDate date, 
	duration int,
	rating decimal(32,12),
	imageUrl varchar(255)
);

create index movie_title_ix on movie(title);
create unique index movie_title_year_ux on movie(title, year);