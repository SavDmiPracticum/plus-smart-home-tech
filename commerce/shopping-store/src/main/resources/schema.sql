drop table if exists products;

create table if not exists products
(
    product_id       uuid primary key,
    product_name     varchar not null,
    description      varchar not null,
    image_src        varchar,
    quantity_state   varchar not null,
    product_state     varchar not null,
    rating           int,
    product_category varchar,
    price            float
);