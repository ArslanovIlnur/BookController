create table if not exists books(
    id bigint primary key,
    name varchar(255),
    author varchar(255),
    in_stock boolean
)