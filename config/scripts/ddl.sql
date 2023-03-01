create table person (
                        id int primary key unique  generated always as identity ,
                        "name" text,
                        email text unique ,
                        cpf text unique ,
                        "level" json,
                        fingerPrintId int unique null);