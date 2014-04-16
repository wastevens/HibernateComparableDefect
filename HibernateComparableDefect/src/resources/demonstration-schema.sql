drop table if exists Foo;
create table Foo (id varchar(255) not null, deleted_at datetime, name varchar(255), primary key (id));
