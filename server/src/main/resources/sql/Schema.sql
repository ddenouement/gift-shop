CREATE SCHEMA IF NOT EXISTS giftdatabase;

DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS confirmation_token CASCADE;
DROP TABLE IF EXISTS state CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS wishlist_item CASCADE;
DROP TABLE IF EXISTS order_line CASCADE;
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE category (
                            uid SERIAL PRIMARY KEY NOT NULL,
                            category_name varchar(100) NOT NULL
);

CREATE TABLE confirmation_token (
                                      uid SERIAL PRIMARY KEY NOT NULL,
                                      token varchar(255) NOT NULL,
                                      create_date timestamp NOT NULL,
                                      user_id integer NOT NULL
);

CREATE TABLE orders (
                         uid SERIAL PRIMARY KEY NOT NULL,
                         address varchar(200),
                         cash_payment boolean NOT NULL,
                         post_delivery boolean NOT NULL,
                         order_date timestamp NOT NULL,
                         total_sum integer NOT NULL,
                         user_id integer NOT NULL,
                         state_id integer NOT NULL
);

CREATE TABLE order_line (
                              quantity integer NOT NULL,
                              order_id integer NOT NULL,
                              product_id integer NOT NULL
);

CREATE TABLE product (
                           uid SERIAL PRIMARY KEY NOT NULL,
                           product_name varchar(100) NOT NULL,
                           product_description varchar(500) NOT NULL,
                           price integer NOT NULL,
                           photo varchar(300)  NULL,
                           is_available boolean NOT NULL
);

CREATE TABLE product_category (
                                    category_id integer NOT NULL,
                                    product_id integer NOT NULL,
                                    PRIMARY KEY (category_id, product_id)
);

CREATE TABLE state (
                         uid SERIAL PRIMARY KEY NOT NULL,
                         state_name varchar(45) NOT NULL
);

CREATE TABLE role (
                        uid SERIAL PRIMARY KEY NOT NULL,
                        role_name varchar(45) NOT NULL
);

CREATE TABLE user (
                        uid SERIAL PRIMARY KEY NOT NULL,
                        firstname varchar(50) NOT NULL,
                        surname varchar(50) NOT NULL,
                        patronymic varchar(50) NOT NULL,
                        birth_date date NOT NULL,
                        is_activated boolean NOT NULL,
                        email varchar(100) NOT NULL,
                        password varchar(100) NOT NULL,
                        phone_number varchar(20) NOT NULL,
                        role_id integer NOT NULL
);

CREATE TABLE wishlist_item (
                                 product_id integer NOT NULL,
                                 user_id integer NOT NULL,
                                 PRIMARY KEY (product_id, user_id)
);

ALTER TABLE confirmation_token ADD FOREIGN KEY (user_id) REFERENCES user (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE orders ADD FOREIGN KEY (user_id) REFERENCES user (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE orders ADD FOREIGN KEY (state_id) REFERENCES state (uid) ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE order_line ADD FOREIGN KEY (order_id) REFERENCES orders (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE order_line ADD FOREIGN KEY (product_id) REFERENCES product (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE product_category ADD FOREIGN KEY (category_id) REFERENCES category (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE product_category ADD FOREIGN KEY (product_id) REFERENCES product (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE user ADD FOREIGN KEY (role_id) REFERENCES role (uid) ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE wishlist_item ADD FOREIGN KEY (product_id) REFERENCES product (uid) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE wishlist_item ADD FOREIGN KEY (user_id) REFERENCES user (uid) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE UNIQUE INDEX ON category (category_name);

CREATE INDEX ON orders (user_id);

CREATE INDEX ON order_line (order_id);

CREATE INDEX ON order_line (product_id);

CREATE INDEX ON product_category (category_id);

CREATE INDEX ON product_category (product_id);

CREATE UNIQUE INDEX ON state (state_name);

CREATE UNIQUE INDEX ON role (role_name);

CREATE UNIQUE INDEX ON user (email);

CREATE UNIQUE INDEX ON user (phone_number);

CREATE INDEX ON user (role_id);

CREATE INDEX ON wishlist_item (product_id);

CREATE INDEX ON wishlist_item (user_id);

