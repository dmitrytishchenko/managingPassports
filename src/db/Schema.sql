create table passports (
id serial primary key,
series varchar (20),
number integer,
endDate DATE
);
insert into passports (series, number, end_date) values ('4505', 123456, '2022-09-28');