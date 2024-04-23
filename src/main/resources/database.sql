-- auto-generated definition
create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(32) not null,
    password char(32)    not null
);

