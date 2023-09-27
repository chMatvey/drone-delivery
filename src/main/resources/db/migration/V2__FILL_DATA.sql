insert into drones(serial_number, model, weight_limit, battery_capacity, state)
values ('DRN12345', 'Lightweight', 210, 100, 'IDLE'),
       ('DRN67890', 'Middleweight', 350, 100, 'IDLE'),
       ('DRN54321', 'Heavyweight', 500, 100, 'IDLE'),
       ('DRN98765', 'Cruiserweight', 420, 100, 'IDLE');


with image_id as (
    insert into medications_images (data)
    values (pg_read_binary_file('/tmp/images/aspirin.png'))
        returning id
)
insert into medications(name, weight, code, image_id)
values ('Aspirin', 100, 'ABC123', (SELECT id FROM image_id));


with image_id as (
    insert into medications_images (data)
    values (pg_read_binary_file('/tmp/images/ibuprofen.png'))
        returning id
)
insert into medications(name, weight, code, image_id)
values ('Ibuprofen', 120, 'DEF456', (SELECT id FROM image_id));


with image_id as (
    insert into medications_images (data)
    values (pg_read_binary_file('/tmp/images/acetaminophen.png'))
        returning id
)
insert into medications(name, weight, code, image_id)
values ('Acetaminophen', 250, 'GHI789', (SELECT id FROM image_id));