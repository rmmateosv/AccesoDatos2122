drop table cuento, revista,libro,socio;
drop type tipoDireccion;

create type tipoDireccion as(
	calle varchar(100),
	numero int,
	cp int
);

create table socio(
	id serial primary key,
	nombre varchar(100) not null,
	direccion tipoDireccion,
	fechaNac date
);

insert into socio values
	(default, 'Pepito Grillo', ('La luna',2,10300),'2000-01-01'),
	(default, 'Margarita', ('El sol',32,10300),'2001-01-01'),
	(default, 'Lucía', ('La estrella',27,10300),'2000-06-01'),
	(default, 'Elisa', ('El cometa',21,10300),'2002-07-01');

create table libro(
	isbn varchar(9)  primary key,
	titulo varchar(100),
	numEjemplares int,
	prestamos text[][]
);
create table cuento (
	edadMinima int,
	audio boolean
)inherits (libro);
create table revista(
	genero varchar(100),
	fechaPublicacion date
) inherits (libro);

insert into cuento values
	('c1','Caperucita', 5, null,3,false),
	('c2','Spiderman', 3, null,8,true),
	('c3','Garbancito', 9,null, 2,true);
	
insert into revista values
	('r1','Muy Interesante', 1,null, 'ciencia','2022-01-01'),
	('r2','Hola', 3,null,'corazón','2022-02-01'),
	('r3','Computer Hoy', 2,null,'tecnología','2022-10-01');	
	
