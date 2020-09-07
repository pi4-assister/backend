CREATE TABLE application.charge
(
    id             VARBINARY(16) NOT NULL,
    customer_id    VARBINARY(16) NOT NULL,
    credit_card_id VARBINARY(16) NOT NULL,
    service_id     VARBINARY(16) NOT NULL,
    status         VARCHAR(255)  NOT NULL,
    amount         DECIMAL(6, 2) NOT NULL,
    created_at     TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_charge_customer_id_idx (customer_id ASC) VISIBLE,
    INDEX fk_charge_credit_card_id_idx (credit_card_id ASC) VISIBLE,
    INDEX fk_charge_service_id_idx (service_id ASC) VISIBLE,
    CONSTRAINT fk_charge_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_charge_credit_card_id
        FOREIGN KEY (credit_card_id)
            REFERENCES application.credit_card (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_charge_service_id
        FOREIGN KEY (service_id)
            REFERENCES application.service (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);