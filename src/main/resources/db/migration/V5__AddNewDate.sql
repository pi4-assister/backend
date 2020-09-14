ALTER TABLE `application`.`service`
ADD COLUMN `final_date` TIMESTAMP NOT NULL AFTER `start_date`,
CHANGE COLUMN `service_date` `start_date` TIMESTAMP NOT NULL ;
