-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

ALTER TABLE book ADD author_id BIGINT;


drop table if exists author;
create table author
(
    id         bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine=InnoDB;

ALTER TABLE book ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author(id);

insert into author (first_name, last_name) values ('Craig', 'Walls');




