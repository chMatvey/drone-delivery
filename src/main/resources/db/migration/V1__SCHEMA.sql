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


create sequence medications_id_seq;
create sequence medications_images_id_seq;

create table medications_images
(
    id   int not null primary key default nextval('medications_images_id_seq'),
    data bytea
);

create table medications
(
    id         int          not null primary key default nextval('medications_id_seq'),
    name       varchar(100) not null,
    weight     integer      not null,
    code       varchar(100) not null,
    image_id   integer,
    created_at timestamp    not null             default now(),

    constraint fk_medications_images foreign key (image_id) references medications_images on delete set null
);


create sequence deliveries_id_seq;

create table deliveries
(
    id                        int       not null primary key default nextval('deliveries_id_seq'),
    drone_id                  int       not null,
    available_weight_capacity int       not null,
    completed                 boolean   not null             default false,
    created_at                timestamp not null             default now(),
    updated_at                timestamp not null             default now(),

    constraint fk_drones foreign key (drone_id) references drones
);

create table delivery_items
(
    delivery_id   int not null,
    medication_id int not null,

    constraint delivery_items_pkey primary key (delivery_id, medication_id),
    constraint fk_deliveries foreign key (delivery_id) references deliveries on delete cascade,
    constraint fk_medications foreign key (medication_id) references medications
);


