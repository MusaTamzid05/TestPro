
USE java_project;


CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;

INSERT INTO `admin` (`userName`, `password`) VALUES
('root', 'password');


--
-- Dumping data for table `admin`
--
-- --------------------------------------------------------



CREATE TABLE IF NOT EXISTS `university_link` (
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




CREATE TABLE IF NOT EXISTS `exam_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL ,
  `year` varchar(50) NOT NULL,
  `board` varchar(50) NOT NULL,
  `roll` varchar(50) NOT NULL,
  `reg` varchar(50) NOT NULL,
  `exam_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS `university_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `link` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;


CREATE TABLE IF NOT EXISTS `university_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Other_students` varchar(255) ,
  `Campus` varchar(255) ,
  `Undergraduates` varchar(255) ,
  `Postgraduates` varchar(255) ,
  `Doctoral_students` varchar(255) ,
  `Website` varchar(255) ,
  `Motto_in_English` varchar(255) ,
  `Academic_staff` varchar(255) ,
   `TYPE` varchar(255) ,
  `Students` varchar(255) ,
  `Vice_Chancellor` varchar(255) ,
  `Established` varchar(255) ,
  `Location` varchar(255) ,
  `uni_id` int(11) ,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;

