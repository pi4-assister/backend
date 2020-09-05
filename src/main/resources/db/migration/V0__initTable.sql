CREATE TABLE application.customer
(
    id                VARBINARY(16) NOT NULL,
    photo_url         VARCHAR(255)  NULL,
    full_name         VARCHAR(255)  NOT NULL,
    person_identifier VARCHAR(45)   NOT NULL,
    is_legal_person   TINYINT       NOT NULL,
    bio               VARCHAR(500)  NULL,
    phone_number      VARCHAR(45)   NOT NULL,
    customer_type     VARCHAR(45)   NOT NULL,
    status            VARCHAR(45)   NOT NULL,
    landline_number   VARCHAR(45)   NULL,
    email             VARCHAR(255)  NOT NULL,
    password          VARCHAR(255)  NOT NULL,
    birthdate         TIMESTAMP     NOT NULL,
    address           VARCHAR(255)  NOT NULL,
    city              VARCHAR(255)  NOT NULL,
    state             VARCHAR(255)  NOT NULL,
    zip_code          VARCHAR(45)   NOT NULL,
    active            TINYINT       NOT NULL,
    created_at        TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE application.credit_card
(
    id               VARBINARY(16) NOT NULL,
    customer_id      VARBINARY(16) NOT NULL,
    token            VARCHAR(255)  NULL,
    last_four_digits VARCHAR(255)  NOT NULL,
    credit_card_name VARCHAR(255)  NOT NULL,
    expiration_date  VARCHAR(45)   NOT NULL,
    brand            VARCHAR(45)   NOT NULL,
    active           TINYINT       NOT NULL,
    created_at       TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_credit_card_customer_id_idx (customer_id ASC) VISIBLE,
    CONSTRAINT fk_credit_card_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE application.special_need_types
(
    id         VARBINARY(16) NOT NULL,
    name       VARCHAR(255)  NOT NULL,
    active     TINYINT       NOT NULL,
    created_at TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE application.special_needs
(
    id                   VARBINARY(16) NOT NULL,
    customer_id          VARBINARY(16) NOT NULL,
    special_need_type_id VARBINARY(16) NOT NULL,
    active               TINYINT       NOT NULL,
    created_at           TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_special_needs_customer_id_idx (customer_id ASC) VISIBLE,
    INDEX fk_special_needs_special_needs_type_id_idx (special_need_type_id ASC) VISIBLE,
    CONSTRAINT fk_special_needs_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_special_needs_special_needs_type_id
        FOREIGN KEY (special_need_type_id)
            REFERENCES application.special_need_types (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE application.service_type
(
    id         VARBINARY(16) NOT NULL,
    name       VARCHAR(255)  NOT NULL,
    active     VARCHAR(45)   NOT NULL,
    price      DECIMAL(6, 2) NOT NULL,
    created_at TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE application.rate
(
    id          VARBINARY(16) NOT NULL,
    score       DECIMAL(1, 1) NOT NULL,
    description VARCHAR(255)  NOT NULL,
    active      TINYINT       NOT NULL,
    created_at  TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE application.service
(
    id                  VARBINARY(16) NOT NULL,
    customer_client_id  VARBINARY(16) NOT NULL,
    customer_partner_id VARBINARY(16) NOT NULL,
    service_type_id     VARBINARY(16) NOT NULL,
    service_date        TIMESTAMP     NOT NULL,
    description         VARCHAR(500)  NOT NULL,
    rate_id             VARBINARY(16) NOT NULL,
    status              VARCHAR(255)  NOT NULL,
    created_at          TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_service_customer_id_idx (customer_client_id ASC) VISIBLE,
    INDEX fk_service_costumer_provider_id_idx (customer_partner_id ASC) VISIBLE,
    INDEX fk_service_rate_id_idx (rate_id ASC) VISIBLE,
    INDEX fk_service_service_type_idx (service_type_id ASC) VISIBLE,
    CONSTRAINT fk_service_customer_id
        FOREIGN KEY (customer_client_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_service_costumer_provider_id
        FOREIGN KEY (customer_partner_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_service_rate_id
        FOREIGN KEY (rate_id)
            REFERENCES application.rate (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_service_service_type
        FOREIGN KEY (service_type_id)
            REFERENCES application.service_type (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE application.customer_service
(
    id          VARBINARY(16) NOT NULL,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(255)  NOT NULL,
    active      TINYINT       NOT NULL,
    customer_id VARBINARY(16) NOT NULL,
    created_at  TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_customer_service_customer_id_idx (customer_id ASC) VISIBLE,
    CONSTRAINT fk_customer_service_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);