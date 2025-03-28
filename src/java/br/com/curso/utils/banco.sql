create table estado(
   idestado serial primary key,
   nomeestado varchar(100) not null,
   siglaestado varchar(2) not null
);

insert into estado(nomeestado, siglaestado) values ('São Paulo', 'SP');