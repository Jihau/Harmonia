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

INSERT INTO `harmoniadb`.`server`
(`ServerId`,
 `OwnerId`,
 `ServerCategory`,
 `ServerName`)
VALUES
    (1,
        1,
        'Test',
        'Server test1');
INSERT INTO `harmoniadb`.`channel`
(`ChannelId`,
 `ChannelName`,
 `ChannelType`,
 `ServerId`,
 `CreationDate`)
VALUES
    (1,
        'TestChannel',
        'Text',
        1,
        NOW());
INSERT INTO `harmoniadb`.`channel`
(`ChannelId`,
 `ChannelName`,
 `ChannelType`,
 `ServerId`,
 `CreationDate`)
VALUES
    (2,
        'TestChannel2',
        'Text',
        1,
     NOW());
INSERT INTO `harmoniadb`.`direct_message`
(`DmessageId`,
 `Message_text`,
 `Timestamp`,
 `AuthorId`,
 `RecipientId`)
VALUES
    (1,
        'Hellooooo!',
     NOW(),
        1,
        2);
INSERT INTO `harmoniadb`.`direct_message`
(`DmessageId`,
 `Message_text`,
 `Timestamp`,
 `AuthorId`,
 `RecipientId`)
VALUES
    (2,
        'Hi, how are you?',
     NOW(),
        2,
        1);



INSERT INTO `harmoniadb`.`direct_message`
(`DmessageId`,
 `Message_text`,
 `Timestamp`,
 `AuthorId`,
 `RecipientId`)
VALUES
    (3,
        'Who are you?',
     NOW(),
        1,
        2);
INSERT INTO `harmoniadb`.`public_message`
(`PmessageId`,
 `AuthorId`,
 `ChannelId`,
 `Message_text`,
 `Timestamp`
 )
VALUES
    (1,
        1,
        1,
        'Hellooooo everyone!!!!!',
     NOW());
INSERT INTO `harmoniadb`.`public_message`
(`PmessageId`,
 `AuthorId`,
 `ChannelId`,
 `Message_text`,
 `Timestamp`
)
VALUES
    (2,
     2,
     1,
     'Who the heck are you !!!',
     NOW());

INSERT INTO `harmoniadb`.`public_message`
(`PmessageId`,
 `AuthorId`,
 `ChannelId`,
 `Message_text`,
 `Timestamp`)
VALUES
    (3,
        2,
        1,
        'Third message',
        NOW());





