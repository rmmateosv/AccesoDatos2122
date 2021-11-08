drop database if exists biblioteca;
create database biblioteca;

use biblioteca;
--
create table libro(
	isbn varchar(50) primary key,	
	titulo varchar(255) not null,
	autor varchar(100) not null,
	fechaLanzamiento date not null,
	numEjemplares int not null
)engine Innodb;

create table socio(
	dni varchar(9) primary key, 
    nombre varchar(100) not  null,
	fechaN date not null,
	activo boolean not null default true
)engine Innodb;

create table prestamo(
	socio varchar(9),
    libro varchar(50),
    fechap date not null,
    fechad date not null,
    devuelto boolean not null default false,
    primary key(socio,libro),
    foreign key (socio) references socio(dni)
		on update cascade on delete restrict,
	foreign key (libro) references libro(isbn)
		on update cascade on delete restrict
)engine Innodb;