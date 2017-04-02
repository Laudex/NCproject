CREATE TABLE users
(user_id serial NOT NULL,
name VARCHAR(200),
password VARCHAR(200),
is_admin boolean,
PRIMARY KEY (user_id));

CREATE TABLE offer
(offer_id serial NOT NULL,
name VARCHAR(200),
PRIMARY KEY(offer_id));

CREATE TABLE orders
(order_id serial NOT NULL,
user_id int,
offer_id int,
start_date VARCHAR(200), 
PRIMARY KEY (order_id),
FOREIGN KEY(user_id) REFERENCES users(user_id),
FOREIGN KEY(offer_id) REFERENCES offer(offer_id));



CREATE TABLE attr
(attr_id serial,
name VARCHAR(200),
PRIMARY KEY(attr_id));

CREATE TABLE attr_values
(offer_id serial NOT NULL,
attr_id serial NOT NULL,
value text,
PRIMARY KEY(offer_id,attr_id),
FOREIGN KEY(offer_id) REFERENCES offer(offer_id),
FOREIGN KEY(attr_id) REFERENCES attr(attr_id));

