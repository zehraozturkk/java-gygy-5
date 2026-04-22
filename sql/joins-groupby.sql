select * from customers;

--joinss

--inner join: 2 tablonun kesiştiği nokta varsa kullanabil. başka bir tabloya katılabiliyorum
-- burada order tablosu ile customer tablosunu birleştirdim. order tablosunun peşine customer tablosunu ekledim
-- inner join veri 2.sindede varsa
select * from orders o
inner join customers c
on o.customer_id = c.customer_id;

--inner joinde ortak customer idler geliyor ama o 1 tane customer olup hiç sipariş vermemişse innerde görünmez. 
--bunu görebilmek için LEFT join kullanılır

INSERT INTO customers(customer_id, company_name, contact_name, contact_title, address, city, postal_code,country,phone,fax)
VALUES ('HALIT', 'Deneme', 'Halit Kalaycı', 'Abc','Abc','İstanbul','34788','Türkiye','+90', 'abc') -- hiçbi orderı olmayan müşteri

--başlangıç tablosu her zaman soldur. 2. si sağ
-- right join ile ben sipariş olsun olmasın tüm müşterileri görmek istersek
select * from orders o
right join customers c
on o.customer_id = c.customer_id;

--

select * from orders o
inner join employees e
on e.employee_id = o.employee_id

--

select * from orders o
inner join customers c
on o.customer_id = c.customer_id
inner join order_details od
on o.order_id = od.order_id
inner join products p
on od.product_id = p.product_id
where od.quantity>10
order by o.employee_id;

--GROUP BY

select c.country,c.city, count(*) from customers c
group by c.country, c.city;

-- count * dediğin an satırı sayar. ve burda eğer null değer varsa bunlara 1 der
-- * yerine bir sütun seçilirse o sütunda null olanlar 0 sayılır.
select s.company_name, count(o.order_id) from shippers s
left join orders o
on s.shipper_id = o.ship_via
group by s.shipper_id, s.company_name
order by count(o.order_id) desc

-- having : bu grupta şu mümkünse, varsa
select s.company_name, count(o.order_id) from shippers s
left join orders o
on s.shipper_id = o.ship_via
group by s.shipper_id, s.company_name
having counts(o.order_id) > 250


-- hangi müşteriler 10dan fazla sipairş vermiş

select c.contact_name, count(*) from orders o
join customers c on o.customer_id = c.customer_id
group by  o.customer_id, c.customer_id
having count(*) > 10
order by count(*)
--

--ÖDEV

--toplam ciro 50k büyük müşteriler
-- ciro hesaplama: quantity x unitprice
select c.customer_id, round(SUM(od.quantity * od.unit_price)) AS toplam_ciro from customers c
join orders o on c.customer_id = o.customer_id
join order_details od on o.order_id= od.order_id
group by c.customer_id
HAVING SUM(od.quantity * od.unit_price) > 50000;

-- her kategori için en az 5 farklı ürün satan kategoriler
select c.category_name, count(p.product_id) as total_product from categories c
join products p on c.category_id = p.category_id
group by c.category_name
having count(p.product_id)>=5
order by count(p.product_id) desc;

-- çalışan bazlı toplam satış tutarı(birim fiyat)

select e.first_name,e.last_name, SUM(od.unit_price * od.quantity) AS toplam_ciro
from employees e
join orders o on e.employee_id = o.employee_id
join order_details od on o.order_id= od.order_id
group by e.first_name, e.last_name
ORDER BY toplam_ciro DESC;


