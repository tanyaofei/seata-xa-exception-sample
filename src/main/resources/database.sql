-- auto-generated definition
create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(32) not null
);

insert into `user` (username) values ('1'), ('2'), ('3'), ('4'), ('5'), ('6');
