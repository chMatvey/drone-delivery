create sequence drones_id_seq;

create table drones
(
    id               int          not null primary key default nextval('drones_id_seq'),
    serial_number    varchar(100) not null,
    model            varchar(20)  not null,
    weight_limit     integer      not null,
    battery_capacity integer      not null,
    state            varchar(20)  not null,
    created_at       timestamp    not null             default now()
);