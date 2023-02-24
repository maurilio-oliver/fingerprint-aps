
create table person (
                   id int primary key unique ,
                   "name" text,
                   email text,
                   fingerprint_id int,
                   lote jsonb
)

create table fingerprint (
    id int unique primary key,
    person_id int unique,
    lote jsonb
)

create table tables (
    id int unique primary key ,
    table_name text,
    description text,
    level jsonb,
    lote jsonb
    fields jsonb;
)

create table level_one()
create table level_two()
create table level_three()

