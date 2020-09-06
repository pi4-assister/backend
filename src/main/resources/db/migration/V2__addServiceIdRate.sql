ALTER TABLE `application`.`rate`
    ADD COLUMN `service_id` VARBINARY(16) NULL AFTER `updated_at`,
    ADD INDEX `fk_rate_service_Id_idx` (`service_id` ASC) VISIBLE;
;
ALTER TABLE `application`.`rate`
    ADD CONSTRAINT `fk_rate_service_Id`
        FOREIGN KEY (`service_id`)
            REFERENCES `application`.`rate` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
