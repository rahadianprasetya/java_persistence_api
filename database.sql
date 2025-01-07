create table skill (
  id bigint NOT NULL DEFAULT nextval('sq_skill'),
  member_id bigint not null,
  name character varying(255),
  value int,
  constraint skill_pkey PRIMARY KEY (id),
  CONSTRAINT fk_member
  FOREIGN KEY(member_id)
  REFERENCES member(id),
  CONSTRAINT skill_unique UNIQUE (member_id, name)
)WITH (
     OIDS=FALSE
     );


alter table category drop column updateat;

alter table category add column updatedat timestamp without time zone;

update category set updatedat = now();



create table credential (
     id bigint NOT NULL DEFAULT nextval('sq_credential'),
     email character varying(255) NOT NULL,
     password character varying(255) NOT NULL,
     constraint credential_pkey PRIMARY KEY (id)

)WITH (
     OIDS=FALSE
     );

CREATE SEQUENCE public.sq_credential
 INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;

create table users (
   id bigint NOT NULL DEFAULT nextval('sq_user'),
   name character varying(255) NOT NULL,
   constraint user_pkey PRIMARY KEY (id)
)WITH (
 OIDS=FALSE
);

CREATE SEQUENCE public.sq_user
  INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;





CREATE SEQUENCE public.sq_wallet
  INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;

create table wallet(
  id bigint NOT NULL,
  user_id bigint not null,
  balance bigint,
  constraint wallet_pkey PRIMARY KEY (id),
  CONSTRAINT fk_users_wallet
  FOREIGN KEY(user_id)
  REFERENCES users(id)
)WITH (
     OIDS=FALSE
     );

create table brand(
   id bigint NOT NULL,
   name character varying(255) NOT NULL,
   description character varying(255),
   constraint brand_pkey PRIMARY KEY (id)
)WITH (
     OIDS=FALSE
     );

CREATE SEQUENCE public.sq_brand
  INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;

create table products(
  id bigint NOT NULL,
  brand_id bigint NOT NULL,
  name character varying(255) NOT NULL,
  price bigint not null,
  description character varying(255),
  constraint products_pkey PRIMARY KEY (id),
  CONSTRAINT fk_brand_products
      FOREIGN KEY(brand_id)
             REFERENCES brand(id)
)WITH (
     OIDS=FALSE
     );

CREATE SEQUENCE public.product
 INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;

create table users_like_products(
    user_id bigint NOT NULL,
    product_id bigint NOT NULL,
    constraint users_like_products_pkey PRIMARY KEY (user_id, product_id),
    CONSTRAINT fk_users_like_products
               FOREIGN KEY(user_id)
                       REFERENCES users(id),
    CONSTRAINT fk_products_like_users
               FOREIGN KEY(product_id)
                       REFERENCES products(id)
)WITH (
  OIDS=FALSE
);

create table employee(
   id bigint NOT NULL,
   type character varying(255) NOT NULL,
   name character varying(255) NOT NULL,
   totalemployee int,
   totalmanager int,
   constraint employee_pkey PRIMARY KEY (id)
)WITH (
 OIDS=FALSE
);

CREATE SEQUENCE public.sq_employee
  INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;

create table payment(
  id bigint NOT NULL,
  amount bigint NOT NULL,
  constraint payment_pkey PRIMARY KEY (id)
)WITH (
 OIDS=FALSE
);

CREATE SEQUENCE public.sq_payment
 INCREMENT 1
  MINVALUE 5
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;


create table paymentgopay(
  id bigint NOT null,
  gopayid character varying(255) NOT NULL,
  constraint paymentgopay_pkey PRIMARY KEY (id),
  CONSTRAINT fk_payment_gopay
             FOREIGN KEY(id)
                        REFERENCES payment(id)
)WITH (
 OIDS=FALSE
);

create table paymentcreditcard(
  id bigint NOT null,
  maskedcard character varying(255) NOT NULL,
  bank character varying(255) NOT NULL,
  constraint paymentcreditcard_pkey PRIMARY KEY (id),
  CONSTRAINT fk_paymentcreditcard
             FOREIGN KEY(id)
                     REFERENCES payment(id)
)WITH (
 OIDS=FALSE
);


SELECT * FROM paymentcreditcard;
SELECT * FROM paymentgopay;


create table transaction(
   id bigint NOT NULL,
   balance bigint NOT NULL,
   updatedat timestamp NOT NULL,
   constraint transaction_pkey PRIMARY KEY (id)
)WITH (
     OIDS=FALSE
);
CREATE SEQUENCE public.sq_transaction
    INCREMENT 1
    MINVALUE 5
    MAXVALUE 9223372036854775807
    START 5
    CACHE 1;

create table transactiondebit(
    id bigint NOT NULL,
    balance bigint NOT NULL,
    updatedat timestamp NOT NULL,
    debitamount bigint NOT NULL,
    constraint transactiondebit_pkey PRIMARY KEY (id)
)WITH (
 OIDS=FALSE
);

create table transactioncredit(
      id bigint NOT NULL,
      balance bigint NOT NULL,
      updatedat timestamp NOT NULL,
      creditamount bigint NOT NULL,
      constraint transactioncredit_pkey PRIMARY KEY (id)
)WITH (
  OIDS=FALSE
);

ALTER TABLE brand add column createdat timestamp;
ALTER TABLE brand add column updatedat timestamp;
ALTER TABLE brand add column version bigint;
select * from brand;