#Base_local

drop database RedFlix;
create database Redflix;

use Redflix;

#Base_remota:Se uso la del grupo 12

use redflix144

create table director(
    dir_id int primary key,
    dir_nombre char(20),
    dir_apellido char(20),
    dir_nacionalidad char(40)
);
insert into director values (101, "Hayo","Miyazaki", "japones");
insert into director values (102, "Joss", "Whedon", "estadounidense");
insert into director values (103, "Christopher", "Nolan", "estadounidense");
insert into director values (104, "Bong", "Joon-ho", "coreano");
insert into director values (105, "Vincent", "Ward", "neozelandes");
create table contenido(
    con_id  int primary key,
    con_nombre char(40)
);
insert into contenido values (1,"Los Vengadores");
insert into contenido values (2,"Interestelar");
insert into contenido values (3,"El viaje de Chihiro");
insert into contenido values (4,"Parasitos");
insert into contenido values (5,"Mas alla de los sueño");
insert into contenido values (6,"The walking dead");
insert into contenido values (7,"Viaje a las estrellas: la serie original");
insert into contenido values (8,"Glow");
insert into contenido values (9,"La casa de papel");
insert into contenido values (10,"Friends");
insert into contenido values (11,"Arrow");
insert into contenido values (12,"The big bang theory");
insert into contenido values (13,"Vikingos");
create table pelicula(
    pel_id int primary key,
    pel_resumen char(255),
    pel_agno int,
    pel_director int,
    CONSTRAINT fk_con_pel FOREIGN KEY (pel_id) REFERENCES contenido(con_id),
    CONSTRAINT fk_dir_pel FOREIGN KEY (pel_director) REFERENCES director(dir_id)
);
insert into pelicula values (1,"Pelicula de superheroes basada en Marvel Comics. Nick Fury director de SHIELD recluta a Tony Stark, Steve Rogers, Bruce Banner y Thor para forma un equipo y evitar que Loki, hermano de Thor, se apodere de la tierra.",1990,102);
insert into pelicula values (2,"Pelicula de ciencia ficción, donde la humanidad lucha por sobrevivir. La pelicula cuenta una historia de un grupo de astronautas que viajan a traves de un agujero de gusano en busca de un nuevo hogar.",2014,103);
insert into pelicula values (3,"Pelicula de animación japonesa. Es la historia de una niña de 12 años, quien se ve atrapada por un mundo mágico y sobrenatural, teniendo como misión buscar su libertad y la de sus padres y regresar al mundo real.",2001,101);
insert into pelicula values (4,"Pelicula de drama, suspenso y humor negro. Toca temas como las diferencias sociales y vulnerabilidad del espiritu humano.",2019,104);
insert into pelicula values (5,"Pelicula de drama, narra una historia trágica de una familia, donde el padre va en busca de sus esposa al mas allá para recuperarla.",1998,105);
create table serie(
    ser_id int primary key,
    ser_episodios int,
    ser_temporadas int,
    CONSTRAINT fk_con_ser FOREIGN KEY (ser_id) REFERENCES contenido(con_id)
);
insert into serie values (6,153,11);
insert into serie values (7,80,3);
insert into serie values (8,30,3);
insert into serie values (9,31,4);
insert into serie values (10,236,10);
insert into serie values (11,170,8);
insert into serie values (12,279,12);
insert into serie values (13,79,6);
create table usuario(
    usu_alias char(20) primary key,
    usu_nombre char(20),
    usu_apellido char(20),
    usu_email char(100),
    usu_celular char(10),
    usu_fecha_nacimiento date,
    usu_contrasegna char(50)
);
insert into usuario values ("lucky","Pedro","Perez","Ped.Perez@redflix.com","3056668800",'1953-02-07',"ez1111Pe");
insert into usuario values ("malopez","Maria","Lopez","Mar.Lopez@redflix.com","3009252568",'1990-10-05',"ez197Ma");
insert into usuario values ("diva","Ana","Diaz","Ana.Diaz@redflix.com","3022306280",'1962-10-25',"az197An");
insert into usuario values ("dreamer","Luis","Rojas","Lui.Rojas@redflix.com","3102418200",'1975-06-26',"as1115Lu");
insert into usuario values ("ninja","Andres","Cruz","And.Cruz@redflix.com","3051100650",'1954-06-07',"uz1115An");
insert into usuario values ("neon","Nelson","Ruiz","Nel.Ruiz@redflix.com","3135834320",'1957-01-10',"iz1110Ne");
insert into usuario values ("rose","Claudia","Mendez","Cla.Mendez@redflix.com","3021051606",'1953-10-20',"ez197Cl");
insert into usuario values ("green","Jorge","Rodriguez","Jor.Rodriguez@redflix.com","3004769896",'1993-03-10',"ez1101Jo");
create table transmision(
    tra_usuario char(20),
    tra_contenido int,
    tra_fecha_hora datetime,
    CONSTRAINT pk_transmision PRIMARY KEY (tra_usuario,tra_contenido,tra_fecha_hora)
);
insert into transmision values ("lucky",1,'2017-10-25 20:00:00');
insert into transmision values ("lucky",4,'2019-03-15 18:30:00');
insert into transmision values ("lucky",9,'2019-05-20 20:30:00');
insert into transmision values ("malopez",1,'2018-05-20 20:30:00');
insert into transmision values ("malopez",9,'2020-01-20 20:30:00');
insert into transmision values ("diva",2,'2019-05-20 20:30:00');
insert into transmision values ("diva",3,'2018-06-22 21:30:00');
insert into transmision values ("diva",6,'2020-03-17 15:30:20');
insert into transmision values ("dreamer",6,'2020-03-17 15:30:20');
insert into transmision values ("dreamer",7,'2020-04-10 18:30:20');
insert into transmision values ("ninja",8,'2020-02-17 20:30:20');
insert into transmision values ("ninja",9,'2020-02-20 16:30:20');
insert into transmision values ("ninja",11,'2020-03-27 18:30:20');
insert into transmision values ("rose",10,'2020-03-20 21:30:20');
insert into transmision values ("green",2,'2020-01-10 17:30:20');
insert into transmision values ("green",4,'2020-02-15 20:30:20');
insert into transmision values ("green",5,'2020-03-17 18:30:20');