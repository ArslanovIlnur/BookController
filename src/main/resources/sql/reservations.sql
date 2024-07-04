create table if not exists reservations(
    id bigint primary key,
    book_id bigint not null,
    user_id bigint not null,
    foreign key book_id references books(id),
    foreign key user_id references users(id)
)