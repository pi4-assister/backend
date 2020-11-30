ALTER TABLE `application`.`credit_card` 
ADD COLUMN `expiration_date` VARCHAR(45) NOT NULL AFTER `holder_name`;