CREATE TABLE IF NOT EXISTS products (
    product_id  BIGSERIAL PRIMARY KEY,
    name        VARCHAR(250)                          NOT NULL,
    description VARCHAR(500)                          NOT NULL,
    price       NUMERIC(10, 2)                        NOT NULL,
    popularity  INT                                   NOT NULL,
    image_url   VARCHAR(500),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    created_by  VARCHAR(20)                           NOT NULL,
    updated_at  TIMESTAMP DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS contacts (
    contact_id    BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100)                          NOT NULL,
    email         VARCHAR(100)                          NOT NULL,
    mobile_number VARCHAR(15)                           NOT NULL,
    message       VARCHAR(500)                          NOT NULL,
    status        VARCHAR(50)                           NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    created_by    VARCHAR(20)                           NOT NULL,
    updated_at    TIMESTAMP DEFAULT NULL,
    updated_by    VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS customers
(
    customer_id   BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100)                          NOT NULL,
    email         VARCHAR(100)                          NOT NULL,
    mobile_number VARCHAR(15)                           NOT NULL,
    password_hash VARCHAR(500)                          NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    created_by    VARCHAR(20)                           NOT NULL,
    updated_at    TIMESTAMP DEFAULT NULL,
    updated_by    VARCHAR(20) DEFAULT NULL,
    CONSTRAINT unique_email UNIQUE (email),
    CONSTRAINT unique_mobile_number UNIQUE (mobile_number)
    );

CREATE TABLE IF NOT EXISTS address (
    address_id   BIGSERIAL PRIMARY KEY,
    customer_id  BIGINT NOT NULL UNIQUE,
    street       VARCHAR(150) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(100) NOT NULL,
    postal_code  VARCHAR(20)  NOT NULL,
    country      VARCHAR(100) NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(20) NOT NULL,
    updated_at   TIMESTAMP DEFAULT NULL,
    updated_by   VARCHAR(20) DEFAULT NULL,
    CONSTRAINT fk_customer
    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS roles (
    role_id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20) NOT NULL,
    updated_at  TIMESTAMP DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL,
    CONSTRAINT unique_name UNIQUE (name)
    );

INSERT INTO roles (name, created_at, created_by)
VALUES ('ROLE_USER', CURRENT_TIMESTAMP, 'DBA');

INSERT INTO roles (name, created_at, created_by)
VALUES ('ROLE_ADMIN', CURRENT_TIMESTAMP, 'DBA');

INSERT INTO roles (name, created_at, created_by)
VALUES ('ROLE_OPS_ENG', CURRENT_TIMESTAMP, 'DBA');

INSERT INTO roles (name, created_at, created_by)
VALUES ('ROLE_QA_ENG', CURRENT_TIMESTAMP, 'DBA');

CREATE TABLE IF NOT EXISTS customer_roles (
    customer_id BIGINT NOT NULL,
    role_id     BIGINT NOT NULL,
    PRIMARY KEY (customer_id, role_id),
    CONSTRAINT fk_customer_roles_customer
    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_customer_roles_role
    FOREIGN KEY (role_id)
    REFERENCES roles(role_id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS order_items
(
    order_item_id BIGSERIAL PRIMARY KEY,
    order_id      BIGINT NOT NULL,
    product_id    BIGINT NOT NULL,
    quantity      INT NOT NULL,
    price         DECIMAL(10, 2) NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by    VARCHAR(20) NOT NULL,
    updated_at    TIMESTAMP DEFAULT NULL,
    updated_by    VARCHAR(20) DEFAULT NULL,

    CONSTRAINT fk_orderitems_order
    FOREIGN KEY (order_id)
    REFERENCES orders(order_id),

    CONSTRAINT fk_orderitems_product
    FOREIGN KEY (product_id)
    REFERENCES products(product_id)
    );

CREATE TABLE IF NOT EXISTS orders
(
    order_id       BIGSERIAL PRIMARY KEY,
    customer_id    BIGINT NOT NULL,
    total_price    DECIMAL(10, 2) NOT NULL,
    payment_id     VARCHAR(200) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    order_status   VARCHAR(50) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by     VARCHAR(20) NOT NULL,
    updated_at     TIMESTAMP DEFAULT NULL,
    updated_by     VARCHAR(20) DEFAULT NULL,

    CONSTRAINT fk_orders_customer
    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
    );