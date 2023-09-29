create sequence drones_audit_id_seq;

create table drone_audit
(
    id            int not null primary key default nextval('drones_audit_id_seq'),
    drone_id      int not null,
    battery_level int not null,

    constraint fk_drones foreign key(drone_id) references drones
)