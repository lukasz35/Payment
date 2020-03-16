CREATE TABLE IF NOT EXISTS employees
(
    "emp_id" SERIAL PRIMARY KEY NOT NULL,
    "emp_name" VARCHAR (100) NOT NULL,
    "emp_surname" VARCHAR (100) NOT NULL
);


CREATE TABLE IF NOT EXISTS payments
(
    "pay_id" SERIAL PRIMARY KEY NOT NULL,
    "pay_emp_id" INTEGER REFERENCES employees("emp_id"),
    "pay_payment" FLOAT NOT NULL,
    "pay_date" DATE NOT NULL
);


CREATE OR REPLACE function start_of_month(p_date date)
    returns date
as
'select date_trunc(''month'', p_date)::date'
    language SQL
    immutable;

CREATE UNIQUE INDEX IF NOT EXISTS sal_payment_date_emp_unique ON payments ((start_of_month(pay_date)), pay_emp_id);