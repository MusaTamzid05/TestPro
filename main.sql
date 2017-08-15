
USE java_project;


CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--
-- --------------------------------------------------------



CREATE TABLE IF NOT EXISTS `university_links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `link` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact_no` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `university_name` varchar(100) NOT NULL ,
   `city_name` varchar(100) NOT NULL ,

  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS `ssc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL ,
  `year` varchar(50) NOT NULL,
  `board` varchar(50) NOT NULL,
  `roll` varchar(50) NOT NULL,
  `reg` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS `hsc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL ,
  `year` varchar(50) NOT NULL,
  `board` varchar(50) NOT NULL,
  `roll` varchar(50) NOT NULL,
  `reg` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;

