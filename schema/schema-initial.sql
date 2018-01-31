CREATE TABLE `dummy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `full_name` varchar(55) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `password_hash` VARCHAR(40) NOT NULL,
  `password_salt` VARCHAR(10) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

--
-- username: admin || password: 12345678Aa*
--
INSERT INTO `user` VALUES (1,NULL,NULL,0,'Admin User','admin@example.com','admin','52663e0d333e88d1c41013a3891b743c201c0229','biuIbnvLrD','Bangladesh','1');