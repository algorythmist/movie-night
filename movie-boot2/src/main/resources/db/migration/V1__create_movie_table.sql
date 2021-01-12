create table movie (
    id serial primary key,
    title varchar(255) not null,
    year int not null,
    plot text,
	release_date date,
	duration int,
	rating decimal(32,12),
	image_url varchar(255)
);

create index movie_title_ix on movie(title);
