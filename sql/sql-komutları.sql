Create database eticaret;

create table users(
	-- isim tür özel-durumlar
	id serial primary key,
	name varchar(100) not null,
	surname varchar(100) not null,
	email varchar(100) not null,
	password varchar (100) not null,
	register_date timestamp() default current_timestamp --varsayılan olarak şu anki zamanı ata
)



create table carts(
	-- isim tür özel-durumlar
	id serial primary key,
	user_id integer unique not null, 
	foreign key (user_id) references users(id)
)

Alter table users add column age int;


-- references kısmı tamamen foreign key oluşturmak içindir.

--DML
--insert
insert into users(name, surname, email, password)
values ('yusuf', 'uldz', 'zyusfr@gmail.com', '123')

--update
--update ve delete'e bir filtre vermeeden bu komutlar bütün tablo için çalışır. where ile kısıtlanmalı
update users set password = '222' where id = 1

update users set age = '25' where id= 2;

--bağlı olduğun bir foreign key varsa o veri silinmez ilk alt tablodan silmeliyiz

--asc: default artan
--desc: azalan
select * from users order by age desc; 

-- hazır fonksiyonlar

--bir tablodaki veri sayııs:
select count(*) from users;


select * from users where name ilike '%Ze';