create table utilisateur(
id number primary key,
cin varchar2(12) unique not null,
pw varchar2(100) not null,
nom varchar2(100) not null,
prenom varchar2(100) not null,
adress varchar2(250) not null,
poste varchar2(20) not null,
tel varchar2(20) not null);

create sequence seq_user start with 1 increment by 1;

create trigger trg_user
before insert on utilisateur
for each row
begin
select seq_user.nextval
into :new.id
from dual;
end;
/

create table facture(
id number primary key,
cid number not null,
nservice number not null,
typeservice varchar2(100) not null,
nbrmois number not null,
district varchar2(255) not null,
rib varchar2(100) not null,
rip varchar2(100) not null,
detailconso varchar2(250) not null,
totalconso varchar2(250) not null,
taxe varchar2(20) not null,
sold varchar2(20) not null,
montan varchar2(20) not null,
state number not null
);

create sequence seq_facture start with 1 increment by 1;

create trigger trg_facture
before insert on facture
for each row
begin
select seq_facture.nextval
into :new.id
from dual;
end;
/

create table compteur(
id number primary key,
userid number not null,
marque varchar2(200) not null,
type varchar2(100) not null,
dataactivation DATE not null
);


create sequence seq_com start with 1 increment by 1;

create trigger trg_com
before insert on compteur
for each row
begin
select seq_com.nextval
into :new.id
from dual;
end;
/

create table consomation(
id number primary key,
cid number not null,
consoannuel varchar2(20) not null,
consocompteur varchar2(20) not null
);

create sequence seq_conso start with 1 increment by 1;

create trigger trg_conso
before insert on consomation
for each row
begin
select seq_conso.nextval
into :new.id
from dual;
end;
/

create table reclamation(
id number primary key,
userid number not null,
libelle varchar2(250) not null,
typerec varchar2(100) not null
);


create sequence seq_rec start with 1 increment by 1;

create trigger trg_rec
before insert on reclamation
for each row
begin
select seq_rec.nextval
into :new.id
from dual;
end;
/

create table rapport(
id number primary key,
userid number not null,
libelle varchar2(250) not null,
typerapport varchar2(100) not null
);

create sequence seq_rapport start with 1 increment by 1;

create trigger trg_rapport
before insert on rapport
for each row
begin
select seq_rapport.nextval
into :new.id
from dual;
end;
/

alter table consomation add constraint fk_compteur_conso foreign key(cid) references compteur(id);
alter table reclamation add constraint fk_user_rec foreign key(userid) references utilisateur(id);
alter table rapport add constraint fk_user_rapp foreign key(userid) references utilisateur(id);
alter table facture add constraint fk_compteur_facture foreign key(cid) references compteur(id);
alter table compteur add constraint fk_user_compteur foreign key(userid) references utilisateur(id);
