create table movie (
    id int not null auto_increment,
    title varchar(255) not null,
    year int not null,
    plot text,
	releaseDate date, 
	duration int,
	rating decimal(32,12),
	imageUrl varchar(255),
    PRIMARY KEY(id)
);

create index movie_title_idx on movie(title);
create unique index movie_title_year_ux on movie(title, year);