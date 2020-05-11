use motordeport;
create table car_brand
(
    id         bigint auto_increment
        primary key,
    brand_name varchar(255) not null
);

create table cars
(
    id                   bigint auto_increment
        primary key,
    seats                int          not null,
    load_capacity        int          not null,
    luggage_compartment  bit          not null,
    navigator            bit          not null,
    air_conditioning     bit          not null,
    car_brand            varchar(255) not null,
    car_number           varchar(255) not null,
    car_class            varchar(255) not null,
    car_technical_status varchar(255) not null,
    car_status           varchar(255) not null,
    constraint car_number
        unique (car_number)
);

create table employment_status
(
    id               bigint auto_increment
        primary key,
    id_driver        varchar(255) not null,
    value_employment varchar(255) not null
);

create table flights
(
    id            bigint auto_increment
        primary key,
    end_date      varchar(255) not null,
    flight_number varchar(255) not null,
    flight_status varchar(255) not null,
    start_date    varchar(255) not null,
    car_id        text         not null,
    driver_id     text         not null,
    request       text         not null
);

create table requests
(
    id                  bigint auto_increment
        primary key,
    air_conditioning    bit          not null,
    car_class           varchar(255) not null,
    load_capacity       int          not null,
    luggage_compartment bit          not null,
    navigator           bit          not null,
    seats               int          not null,
    driver_id           text         not null,
    request_status      varchar(255) not null
);

create table tokens
(
    id    bigint auto_increment
        primary key,
    token varchar(255) not null,
    user  varchar(255) not null
);

create table users
(
    id           bigint auto_increment
        primary key,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    phone_number varchar(255) not null,
    role         varchar(255) not null,
    password     varchar(255) not null,
    email        varchar(255) not null,
    constraint email
        unique (email),
    constraint phone_number
        unique (phone_number)
);

