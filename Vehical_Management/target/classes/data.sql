insert into user (username, password, user_type) values
('liya', 'liya', 'service_user'),
('boby', 'boby', 'admin_user');

insert into vehicle (id, brand, model, price) values
(100, 'bmw', 'm3', 10000),
(200, 'audi', 'r8', 20000);


insert into customer (id, name, location) values
(100, 'boby', 'ctr'),
(200, 'yuvraj', 'vellore');


insert into sold_vehicle(reg_no, vehicle_id, customer_id) values
(100, 100, 100),
(200, 200, 100),
(300, 200, 200);


insert into service_record (id, reg_no, service_status, due_date) values
(100, 100, 'serviced', sysdate()),
(200, 300, 'unserviced', sysdate());

insert into bill_of_material (name, price, quantity, service_id, id) values
('carburator', 1000, 1, 100, 1),
('suspension', 2000, 2, 100, 2);

