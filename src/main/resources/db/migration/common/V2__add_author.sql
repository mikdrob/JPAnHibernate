-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

ALTER TABLE book ADD author_id BIGINT;


drop table if exists author;
create table author
(
    id         bigint not null,
    first_name varchar(255),
    last_name  varchar(255),
    primary key (id)
) engine=InnoDB;

ALTER TABLE book ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author(id);




