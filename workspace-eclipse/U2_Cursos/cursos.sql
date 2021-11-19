drop database if exists cursos;
create database cursos;
use cursos;

create table usuario(
	usuario varchar(20) primary key,
    clave blob not null,
    perfil enum('Admin','Alumno')  not null
) engine Innodb;

insert into usuario values ('admin', 
							 sha2('admin',0),
                             'Admin');

create table curso(
	id int auto_increment primary key,
    nombre varchar(255)  not null,
    horas int  not null,
    nivel enum('Principiante','Avanzado','Medio')  not null
) engine Innodb;

create table matricula(
	curso int,
    alumno varchar(20),
    fecha date  not null,
    nota int null,
    primary key (curso, alumno),
    foreign key (curso) references curso(id) 
		on update cascade on delete restrict,
	foreign key (alumno) references usuario(usuario) 
		on update cascade on delete restrict		
) engine InnoDB;

delimiter //
create procedure estadistica()
begin
	select id, nombre, count(*), min(nota), max(nota),
		avg(nota)
        from matricula m inner join curso c
        on m.curso = c.id
	group by c.id;
		
end//
-- Login devuelve el perfil del usuario si existe
-- Si no, devuelve NE
create function login(pUsuario varchar(20), 
                      pClave varchar(100))
	returns varchar(255) 
    deterministic
begin
	declare vPerfil varchar(255);
    
    select perfil
		into vPerfil
		from usuario
        where usuario = pUsuario and
			clave = sha2(pClave,0);
    if(vPerfil is null) then
		set vPerfil = 'NE';
	end if;
    return vPerfil;
end//


