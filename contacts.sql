CREATE DATABASE contacts;
USE contacts;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL COMMENT 'Person Phonebook table',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (3,'Igor Medveded','8 812 83 45 55','Kostroma, Russia');
INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (4,'Bill McGonough','1 767 86 77 87','New Jersey 908900');
INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (6,'Elina Dowell','1 9323 293 ','Chicago 8989');
INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (8,'Clint Ashwood','1239 11203 123','Alaska, USA');
INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (9,'Kiori Tosima','90 210 912 8 98','Tokio, Japan');
INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (10,'Charles Champogny','5 8838 238 23','Lion, France');
INSERT INTO `person` (`id`,`full_name`,`phone`,`address`) VALUES (11,'Igbo Obagama','46 628 3282 81','Brassaville, Congo');
