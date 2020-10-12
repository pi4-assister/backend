CREATE TABLE application.customer
(
    id                 VARBINARY(16) NOT NULL,
    photo_url          VARCHAR(255)  NULL,
    full_name          VARCHAR(255)  NOT NULL,
    person_identifier  VARCHAR(45)   NOT NULL,
    bio                VARCHAR(500)  NULL,
    phone_number       VARCHAR(45)   NOT NULL,
    emergency_number   VARCHAR(45)   NULL,
    customer_type      VARCHAR(45)   NOT NULL,
    status             VARCHAR(45)   NOT NULL,
    email              VARCHAR(255)  NOT NULL,
    encrypted_password VARCHAR(500)  NOT NULL,
    birthdate          TIMESTAMP     NOT NULL,
    address            VARCHAR(255)  NOT NULL,
    city               VARCHAR(255)  NOT NULL,
    state              VARCHAR(255)  NOT NULL,
    zip_code           VARCHAR(45)   NOT NULL,
    created_at         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);



CREATE TABLE application.special_needs_type
(
    id         VARBINARY(16) NOT NULL,
    name       VARCHAR(255)  NOT NULL,
    active     TINYINT       NOT NULL DEFAULT 1,
    price      FLOAT         NOT NULL,
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE application.customer_special_needs
(
    id                    VARBINARY(16) NOT NULL,
    customer_id           VARBINARY(16) NOT NULL,
    special_needs_type_id VARBINARY(16) NOT NULL,
    active                TINYINT       NOT NULL DEFAULT 1,
    created_at            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_customer_special_needs_1_idx (customer_id ASC) VISIBLE,
    CONSTRAINT fk_customer_special_needs_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

create table rate
(
    id          varbinary(16) not null,
    score       float         not null,
    description varchar(255)  null,
    active      boolean       NOT NULL DEFAULT 1,
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    constraint rate_pk
        primary key (id)
);

create table service
(
    id                   varbinary(16) not null,
    customer_assister_id varbinary(16) not null,
    customer_client_id   varbinary(16) not null,
    start_date           TIMESTAMP     not null,
    final_date           TIMESTAMP     not null,
    status               varchar(255)  not null,
    description          varchar(500)  null,
    total_price          float         not null,
    rate_id              varbinary(16) not null,
    created_at           TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    constraint service_pk
        primary key (id),
    constraint service_customer_assister_id_fk
        foreign key (customer_assister_id) references customer (id),
    constraint service_customer_client_id_fk
        foreign key (customer_client_id) references customer (id),
    constraint service_rate_id_fk
        foreign key (rate_id) references rate (id)
);

create table credit_card
(
    id            varbinary(16) not null,
    customer_id   varbinary(16) not null,
    token         varchar(500)  not null,
    holder_number varchar(4)    not null,
    holder_name   varchar(255)  not null,
    brand         varchar(255)  not null,
    active        boolean       NOT NULL DEFAULT 1,
    created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    constraint credit_card_pk
        primary key (id),
    constraint credit_card_customer_id_fk
        foreign key (customer_id) references customer (id)
);

create table charge
(
    id             varbinary(16) not null,
    credit_card_id varbinary(16) not null,
    service_id     varbinary(16) not null,
    status         varchar(255)  not null,
    amount         float         not null,
    created_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    constraint charge_pk
        primary key (id),
    constraint charge_credit_card_id_fk
        foreign key (credit_card_id) references credit_card (id),
    constraint charge_service_id_fk
        foreign key (service_id) references service (id)
);