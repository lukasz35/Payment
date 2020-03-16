insert into employees ("emp_id","emp_name","emp_surname") values ( default, 'Emogene', 'Lyons') ON CONFLICT (emp_id) DO NOTHING;
insert into employees ("emp_id","emp_name","emp_surname") values ( default, 'John', 'McCray') ON CONFLICT (emp_id) DO NOTHING;
insert into employees ("emp_id","emp_name","emp_surname") values ( default, 'Margaret', 'Douglas') ON CONFLICT (emp_id) DO NOTHING;
insert into employees ("emp_id","emp_name","emp_surname") values ( default, 'Alisa', 'Huddle') ON CONFLICT (emp_id) DO NOTHING;
insert into employees ("emp_id","emp_name","emp_surname") values ( default, 'Amy', 'Lapinski') ON CONFLICT (emp_id) DO NOTHING;


insert into payments (pay_id, pay_emp_id, pay_payment, pay_date) values ( default, 1, 2500 , current_date - interval '1 months') ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;
insert into payments (pay_id, pay_emp_id, pay_payment, pay_date) values ( default, 2, 3000 , current_date - interval '4 months') ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;
insert into payments (pay_id, pay_emp_id, pay_payment, pay_date) values ( default, 3, 2750 , current_date - interval '2 months') ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;
insert into payments (pay_id, pay_emp_id, pay_payment, pay_date) values ( default, 4, 4400 , current_date) ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;
insert into payments (pay_id, pay_emp_id, pay_payment, pay_date) values ( default, 5, 7000 , current_date - interval '3 months') ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;
insert into payments (pay_id, pay_emp_id, pay_payment, pay_date) values ( default, 5, 7000 , current_date - interval '3 months') ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;


CREATE OR REPLACE
FUNCTION run_insert()
 RETURNS void AS
'begin
for r in 1..200 loop
 insert into payments
 	(pay_id, pay_emp_id, pay_payment, pay_date)
 	values ( default
 			, floor(random() * (select count(*) from public.employees) + 1)::int
 			, round((random() * (10000-1000+1) + 1)::numeric, 2)
 			, current_date - ''1 months''::INTERVAL * ROUND(RANDOM() * 100)
 		   )
 			ON CONFLICT ((start_of_month(pay_date)), pay_emp_id) DO NOTHING;
 end loop;
 end;'
 LANGUAGE plpgsql;

select run_insert();
