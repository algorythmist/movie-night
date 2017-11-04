create table movie (
    id int not null auto_increment,
    title varchar(255) not null,
    year int not null,
    plot text,
    PRIMARY KEY(id)
);