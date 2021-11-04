CREATE TABLE `example` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `example_string` VARCHAR(32) NULL,
  `example_number` INT UNSIGNED NOT NULL,
  `example_boolean` TINYINT NOT NULL,
  `example_datetime` TIMESTAMP NULL,
PRIMARY KEY (`id`));
