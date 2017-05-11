DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS customer CASCADE;


CREATE TABLE IF NOT EXISTS category
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(40),
  department  VARCHAR(40),
  description VARCHAR(400)
);

CREATE TABLE IF NOT EXISTS supplier
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR(40)
);


CREATE TABLE IF NOT EXISTS product
(
  id              SERIAL PRIMARY KEY,
  name            VARCHAR(40),
  defaultprice    FLOAT,
  defaultcurrency VARCHAR(40),
  description     VARCHAR(400),
  category_id     INT REFERENCES category (id),
  supplier_id     INT REFERENCES supplier (id)
);

CREATE TABLE IF NOT EXISTS cart
(
  id         SERIAL PRIMARY KEY,
  user_id    VARCHAR(100),
  quantity   INT,
  product_id INT REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS orders
(
  id         SERIAL PRIMARY KEY,
  user_id    VARCHAR(100),
  quantity   INT,
  status     VARCHAR(100),
  product_id INT REFERENCES product (id)

);

CREATE TABLE IF NOT EXISTS customer
(
  id               SERIAL PRIMARY KEY,
  user_id          VARCHAR(100),
  name             VARCHAR(100),
  username         VARCHAR(100) UNIQUE,
  email            VARCHAR(100) UNIQUE,
  password         VARCHAR(100),
  billing_address  VARCHAR(200),
  shipping_address VARCHAR(200),
  welcome_email    BOOLEAN,
  order_id         INT REFERENCES orders (id),
  cart_id          INT REFERENCES cart (id)
);