create table category (
   id int8 not null,
    is_archivied boolean,
    name varchar(255) not null,
    primary key (id)
);

create table cost (
   id int8 not null,
    is_archivied boolean,
    name varchar(255) not null,
    primary key (id)
);

create table difficult (
   id int8 not null,
    is_archivied boolean,
    name varchar(255) not null,
    primary key (id)
);


create table hashtag (
   id int8 not null,
    name varchar(255) not null,
    primary key (id)
);


create table ingredient (
   id int8 not null,
    description varchar(255),
    is_archivied boolean,
    name varchar(255) not null,
    primary key (id)
);


create table receipe (
   id int8 not null,
    advise varchar(255),
    calories varchar(255),
    date_creation timestamp,
    date_updated timestamp,
    description varchar(255),
    is_archivied boolean,
    link varchar(255),
    notes varchar(255),
    preparation varchar(255),
    time_cooking varchar(255),
    time_preparation varchar(255),
    time_rest varchar(255),
    title varchar(255) not null,
    id_category int8,
    id_cost int8,
    id_difficult int8,
    id_user_ricettario int8,
    primary key (id)
);


create table receipe_favorites_user (
   id int8 not null,
    id_receipe int8 not null,
    id_user_ricettario int8 not null,
    primary key (id)
);


create table receipe_hashtag (
   id_receipe int8 not null,
    id_hashtag int8 not null,
    primary key (id_receipe, id_hashtag)
);


create table receipe_ingredient (
   id int8 not null,
    is_archivied boolean,
    qty varchar(255),
    id_ingredient int8 not null,
    id_receipe int8 not null,
    primary key (id)
);


create table role (
   id int8 not null,
    role_type varchar(255),
    primary key (id)
);


create table user_ricettario (
   id int8 not null,
    name varchar(255) not null,
    surname varchar(255) not null,
    blocked boolean,
    date_creation timestamp,
    date_modified timestamp,
    email varchar(255),
    is_admin boolean,
    is_archivied boolean,
    password varchar(255),
    username varchar(255),
    primary key (id)
);


create table user_role (
   id_user int8 not null,
    id_role int8 not null,
    primary key (id_user, id_role)
);


alter table if exists user_ricettario
   drop constraint if exists UK_cq2fqquntpq93uwp3xrn5acy0;


alter table if exists user_ricettario
   add constraint UK_cq2fqquntpq93uwp3xrn5acy0 unique (email);


alter table if exists user_ricettario
   drop constraint if exists UK_3c0x6vyj5oet30r6rq2jthgs7;

alter table if exists user_ricettario
   add constraint UK_3c0x6vyj5oet30r6rq2jthgs7 unique (username);


alter table if exists receipe
   add constraint FKiaqxb54yqtj1h7euynrbi05xa
   foreign key (id_category)
   references category;


alter table if exists receipe
   add constraint FKrywv90rqwx6g6s3kghfnid16m
   foreign key (id_cost)
   references cost;


alter table if exists receipe
   add constraint FKmr55egtis61qfvuugooqgej45
   foreign key (id_difficult)
   references difficult;


alter table if exists receipe
   add constraint FKijrony9s8i1m9h4sedcw0fa94
   foreign key (id_user_ricettario)
   references user_ricettario;


alter table if exists receipe_favorites_user
   add constraint FKsemiem7mi53w3bj1idfo23wxq
   foreign key (id_receipe)
   references receipe;


alter table if exists receipe_favorites_user
   add constraint FK4fha04arrjwgm3mrmvyjvss0b
   foreign key (id_user_ricettario)
   references user_ricettario;


alter table if exists receipe_hashtag
   add constraint FKcl8402dyoiahxhe5g49qxuv7l
   foreign key (id_hashtag)
   references hashtag;


alter table if exists receipe_hashtag
   add constraint FKijjriygchs0sn80oogp79d7m3
   foreign key (id_receipe)
   references receipe;


alter table if exists receipe_ingredient
   add constraint FKd369skellrye5oeugcd0ehnvi
   foreign key (id_ingredient)
   references ingredient;


alter table if exists receipe_ingredient
   add constraint FK5ejflex1kayg3dtwpmwlcs5hv
   foreign key (id_receipe)
   references receipe;


alter table if exists user_role
   add constraint FK2aam9nt2tv8vcfymi3jo9c314
   foreign key (id_role)
   references role;


alter table if exists user_role
   add constraint FKlusregnqcmlq899an4vrhk86t
   foreign key (id_user)
   references user_ricettario;