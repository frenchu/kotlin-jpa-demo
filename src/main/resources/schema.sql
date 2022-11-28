CREATE TABLE carrier
(
    id                 uuid PRIMARY KEY,
    name               varchar(120) NOT NULL UNIQUE,
    max_shipment_value decimal      NOT NULL
);

CREATE TABLE shipment
(
    id         uuid PRIMARY KEY,
    street     varchar(140)                 NOT NULL,
    city       varchar(100)                 NOT NULL,
    country    varchar(80)                  NOT NULL,
    carrier_id uuid REFERENCES carrier (id) NOT NULL
);
