INSERT INTO users VALUES(1,'Name1','123',false);
INSERT INTO users VALUES(2,'Name2','567',false);
INSERT INTO users VALUES(3,'Name3','123',false);
INSERT INTO users VALUES(4,'Name4','567',false);
INSERT INTO users VALUES(5,'Name5','123',false);
INSERT INTO users VALUES(6,'Name6','567',false);
INSERT INTO users VALUES(7,'Name7','123',false);
INSERT INTO users VALUES(8,'Name8','567',false);
INSERT INTO users VALUES(9,'Name9','123',false);
INSERT INTO users VALUES(10,'Name10','567',false);
INSERT INTO users VALUES(11,'Admin','admin',true);

select setval('users_user_id_seq',(select max(user_id) from users));

INSERT INTO offer VALUES(1,'Offer 1');
INSERT INTO offer VALUES(2,'Offer 2');
INSERT INTO offer VALUES(3,'Offer 3');
INSERT INTO offer VALUES(4,'Offer 4');
INSERT INTO offer VALUES(5,'Offer 5');
insert into offer values(100, 'Test offer');
insert into offer values(101,'test');

select setval('offer_offer_id_seq', (select max(offer_id) from offer));



INSERT INTO orders VALUES(1,5,2,'2016-12-16');
INSERT INTO orders VALUES(2,3,1,'2016-03-08');
INSERT INTO orders VALUES(3,10,5,'2016-05-12');
INSERT INTO orders VALUES(4,7,4,'2016-09-24');
INSERT INTO orders VALUES(5,2,3,'2016-01-11');
INSERT INTO orders VALUES(6,8,3,'2016-10-03');
INSERT INTO orders VALUES(7,6,4,'2016-05-28');
INSERT INTO orders VALUES(8,9,5,'2016-02-07');
INSERT INTO orders VALUES(9,4,2,'2016-11-15');
INSERT INTO orders VALUES(10,1,1,'2016-07-22');

select setval('orders_order_id_seq',(select max(order_id) from orders));

INSERT INTO attr VALUES(1,'type');
INSERT INTO attr VALUES(2,'traffic');
INSERT INTO attr VALUES(3,'price');
INSERT INTO attr VALUES(4,'period');
insert into attr values(5,'test');
insert into attr values(6,'test');

select setval('attr_attr_id_seq',(select max(attr_id) from attr));


INSERT INTO attr_values VALUES (1,1,'mobile');
INSERT INTO attr_values VALUES (1,2,'500 minutes');
INSERT INTO attr_values VALUES (1,3,'200 rub');
INSERT INTO attr_values VALUES (1,4,'30 days');
INSERT INTO attr_values VALUES (2,1,'internet');
INSERT INTO attr_values VALUES (2,2,'unlimited');
INSERT INTO attr_values VALUES (2,3,'350 rub');
INSERT INTO attr_values VALUES (2,4,'30 days');
INSERT INTO attr_values VALUES (3,1,'internet');
INSERT INTO attr_values VALUES (3,2,'50 gb');
INSERT INTO attr_values VALUES (3,3,'250 rub');
INSERT INTO attr_values VALUES (3,4,'30 days');
INSERT INTO attr_values VALUES (4,1,'tv');
INSERT INTO attr_values VALUES (4,2,'100 channels');
INSERT INTO attr_values VALUES (4,3,'200 rub');
INSERT INTO attr_values VALUES (4,4,'30 days');
INSERT INTO attr_values VALUES (5,1,'mobile');
INSERT INTO attr_values VALUES (5,2,'unlimited');
INSERT INTO attr_values VALUES (5,3,'500 rub');
INSERT INTO attr_values VALUES (5,4,'30 days');
insert into attr_values values(100,5,'test');
