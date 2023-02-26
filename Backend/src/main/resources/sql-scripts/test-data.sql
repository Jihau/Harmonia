
TRUNCATE `harmoniadb`.`tbl_server`;
TRUNCATE `harmoniadb`.`user`;
INSERT INTO `harmoniadb`.`user`
( `email`,
 `password`,
 `Profile_icon`,
 `Username`)
VALUES
    (
     'test1@gmail.com',
     '$2a$10$vCg60WndguI8zG9UmkhlSeT1agAyzW9jXebzzik6ZKLIWsSMHVoCm',
     'https://i.imgur.com/yfhVP8e.png',
     'test1');

INSERT INTO `harmoniadb`.`user`
(
 `email`,
 `password`,
 `Profile_icon`,
 `Username`)
VALUES
    (
     'test2@gmail.com',
     '$2a$10$vCg60WndguI8zG9UmkhlSeT1agAyzW9jXebzzik6ZKLIWsSMHVoCm',
     'https://i.imgur.com/yfhVP8e.png',
     'test2');
