create table person (
    id int primary key unique generated as identity,
    "name" text,
    email text unique,
    cpf text unique ,
    "level" jsonb,
    pathFingerprint text
);

create table tables (
    id int primary key unique generated as identity,
    "name" text,
    "description" text,
    createdAt timestamp,
    createdAtBy int;
)

