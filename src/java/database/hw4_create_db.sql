DROP DATABASE IF EXISTS daniel;

CREATE DATABASE daniel;

USE daniel;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `User`
-- ----------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(50) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address1` varchar(50) DEFAULT NULL,
  `address2` varchar(50) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zipcode` int(11) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
);


-- ----------------------------
--  Table structure for `Item`
-- ----------------------------

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `itemID` varchar(50) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  `imageFilePath` varchar(50) DEFAULT NULL,
  `rating` varchar(2) DEFAULT NULL,
  `watched` varchar(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`itemID`)
);	

-- ----------------------------
--  Table structure for `Rating`
-- ----------------------------

DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating` (
  `ratingID` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(50) NOT NULL REFERENCES user(id),
  `itemID` varchar(50) NOT NULL REFERENCES item(id),
  `name` varchar(100) DEFAULT NULL,
  `rating` varchar(2) DEFAULT NULL,
  `madeIt` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ratingID`)

);

BEGIN;
INSERT INTO item (itemID, name, imageFilePath, rating, watched, Description)
VALUES ("1", "Transformers", "./images/transformers.jpg", "0", "0",  "This CGI heavy, live action film introduces the autonomous robots
                                       known as the 'Transformers' who will soon change the life of a
                                       less than average college student."),
		("2","Spider-Man","./images/spiderman.jpg", "0","0",  "A mediocre, unnoticed photographer soon learns it is up to him to save the city!"),    
        ("3","Thor","./images/thor.jpg", "0","0",  "Gods and mortals collide in this Marvel film."),  
        ("4","Insidious","./images/insidious.jpg", "0","0",  "A boy finds that he is indeed stuck in a nightmare that he cannot wake up from."),  
        ("5","Paranormal Activity","./images/paranormalactivity.jpg", "0","0",  "Yet another found footage-style cliche ghost thriller."),  
        ("6","The Conjuring","./images/theconjuring.jpg", "0","0",  "Yet another cliche questioning of faith and exorcism by the Catholic Church.");  
COMMIT;  

BEGIN;
INSERT INTO user (userID, lastName, firstName, email, address1, address2, city, state, zipcode, country)
VALUES ("1", "Doe", "John", "email@example.com", "123 Address","APT#","Charlotte", "NC", "28262", "USA"),
						("2", "Doe", "Jane", "email@example.com", "123 Address","APT#","Charlotte", "NC", "28262", "USA");
COMMIT;                                     
                                       
                                                                            
                                       