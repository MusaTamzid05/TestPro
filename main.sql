
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

INSERT INTO `admin` (`id`, `userName`, `password`) VALUES
(1, 'admin', 'password');

-- --------------------------------------------------------



CREATE TABLE IF NOT EXISTS `university_links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `link` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=2 ;
