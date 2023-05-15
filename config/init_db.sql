CREATE SEQUENCE printed_product_id_seq;
create table printed_product
(
    id                int not null
        constraint printed_product_pk
        primary key DEFAULT nextval('printed_product_id_seq'),
    name              varchar(100) not null,
    type              varchar(45)  not null,
    author            varchar(100) default '',
    issue             int   default 0,
    "publishingHouse" varchar(100) default ''
);
ALTER TABLE public.printed_product owner to postgres;
