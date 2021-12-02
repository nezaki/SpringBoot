truncate table `example`;

INSERT INTO `example`
    (`id`, `example_string`, `example_number`, `example_boolean`, `example_datetime`, `example_enum`, `example_email`, `example_uuid`)
    VALUES
    (1, 'test', 12, 1, '2021-01-02 03:04:05', '1', 'test@example.com', '6CA99996-8621-4499-A6C7-8C9A41D5A057');
