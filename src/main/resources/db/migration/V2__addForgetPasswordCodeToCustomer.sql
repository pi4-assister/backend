ALTER TABLE `application`.`customer`
ADD COLUMN `forget_password_code` VARCHAR(45) NULL AFTER `encrypted_password`;
