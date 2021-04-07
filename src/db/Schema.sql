create table passports (
id serial primary key,
series varchar (20),
number integer,
endDate DATE
);
insert into passports (series, number, end_date) values ('4505', 123456, '2021-05-28');
insert into passports (series, number, end_date) values ('4505', 123457, '2021-05-27');
insert into passports (series, number, end_date) values ('4505', 123458, '2021-05-25');
insert into passports (series, number, end_date) values ('4505', 123459, '2021-05-28');