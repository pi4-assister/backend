ALTER TABLE `application`.`charge`
    DROP FOREIGN KEY `fk_charge_customer_id`;
ALTER TABLE `application`.`charge`
    DROP COLUMN `customer_id`,
    DROP INDEX `fk_charge_customer_id_idx` ;
;