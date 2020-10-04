CREATE TABLE role (
   id  VARBINARY(16) NOT NULL,
   desc varchar(10)  NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    customer_id VARBINARY(16) NOT NULL,
    role_id VARBINARY(16) NOT NULL,
    INDEX fk_role_customer_id_idx (customer_id ASC) VISIBLE,
    INDEX fk_role_role_id_idx (role_id ASC) VISIBLE,
    CONSTRAINT fk_role_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES application.customer (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_role_role_id
        FOREIGN KEY (role_id)
            REFERENCES application.role (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
);