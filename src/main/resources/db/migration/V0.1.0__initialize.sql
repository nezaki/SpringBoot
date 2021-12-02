CREATE TABLE `example` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `example_string` VARCHAR(32) NOT NULL,
  `example_number` INT UNSIGNED NOT NULL,
  `example_boolean` TINYINT NOT NULL,
  `example_datetime` TIMESTAMP NOT NULL,
  `example_enum` VARCHAR(2) NOT NULL,
  `example_email` VARCHAR(255) NULL,
  `example_uuid` VARCHAR(36) NULL,
PRIMARY KEY (`id`));
