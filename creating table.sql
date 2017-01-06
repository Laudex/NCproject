CREATE TABLE users
(user_id int NOT NULL,
is_admin boolean,
PRIMARY KEY (user_id));

CREATE TABLE offer
(offer_id int NOT NULL,
name VARCHAR(200),
PRIMARY KEY(offer_id));

CREATE TABLE orders
(order_id int NOT NULL,
user_id int,
offer_id int,
start_date date, 
PRIMARY KEY (order_id),
FOREIGN KEY(user_id) REFERENCES users(user_id),
FOREIGN KEY(offer_id) REFERENCES offer(offer_id));



CREATE TABLE attr
(attr_id int,
name VARCHAR(200),
PRIMARY KEY(attr_id));

CREATE TABLE attr_values
(offer_id int,
attr_id int,
value text,
FOREIGN KEY(offer_id) REFERENCES offer(offer_id),
FOREIGN KEY(attr_id) REFERENCES attr(attr_id));

