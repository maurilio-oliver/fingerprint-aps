create table test(
    id int primary key unique,
    content jsonb
)
insert into test(id, content) values(0, '{id:0, name:"maurilio"}')


