CREATE TABLE IF NOT EXISTS client
(
    id                BIGSERIAL PRIMARY KEY,
    full_name         VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cargo_order
(
    id                BIGSERIAL PRIMARY KEY,
    create_date       DATE,
    close_date        DATE,
    client            bigint REFERENCES client(id),
    cargo             bigint REFERENCES cargo(id)
);

CREATE TABLE IF NOT EXISTS cargo
(
    id                BIGSERIAL PRIMARY KEY,
    weight            INTEGER,
    invoice           bigint REFERENCES invoice(id),
    cargo_order       bigint
);

CREATE TABLE IF NOT EXISTS invoice
(
    id                BIGSERIAL PRIMARY KEY,
    title             varchar(255),
    type              varchar(255),
    date_supply       DATE
);

CREATE TABLE IF NOT EXISTS unload_place
(
    id                BIGSERIAL PRIMARY KEY,
    status            varchar(255),
    cargo_order       bigint REFERENCES cargo_order(id)
);
