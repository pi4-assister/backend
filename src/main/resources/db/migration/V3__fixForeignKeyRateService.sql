ALTER TABLE `application`.`rate`
    DROP FOREIGN KEY `fk_rate_service_Id`;
ALTER TABLE `application`.`rate`
    ADD INDEX `fk_rate_service_Id_idx` (`service_id` ASC) VISIBLE,
    DROP INDEX `fk_rate_service_Id_idx`;
;
ALTER TABLE `application`.`rate`
    ADD CONSTRAINT `fk_rate_service_Id`
        FOREIGN KEY (`service_id`)
            REFERENCES `application`.`service` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;
