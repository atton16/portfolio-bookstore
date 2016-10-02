-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 02, 2016 at 03:34 AM
-- Server version: 5.6.30
-- PHP Version: 5.5.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--
CREATE Database `bookstore`;
USE `bookstore`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListNode`( IN StartNode VARCHAR(21) )
BEGIN
  DECLARE rows BIGINT DEFAULT 0;
  DROP TABLE IF EXISTS reachedNode;
  CREATE TABLE reachedNode (
    NodeID VARCHAR(21) PRIMARY KEY
  ) ENGINE=HEAP;
  INSERT INTO reachedNode VALUES (StartNode );
  SET rows = ROW_COUNT();
  WHILE rows > 0 DO
    INSERT IGNORE INTO reachedNode
      SELECT DISTINCT NodeFrom
      FROM Graph AS e
      INNER JOIN reachedNode AS p ON e.NodeTo = p.NodeID;
    SET rows = ROW_COUNT();
    INSERT IGNORE INTO reachedNode
      SELECT DISTINCT NodeTo
      FROM Graph AS e
      INNER JOIN reachedNode AS p ON e.NodeFrom = p.NodeID;
    SET rows = rows + ROW_COUNT();
  END WHILE;
  SELECT * FROM reachedNode;
  DROP TABLE reachedNode;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `Admin`
--

CREATE TABLE IF NOT EXISTS `Admin` (
  `UserID` bigint(20) unsigned NOT NULL,
  `Level` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Admin`
--

INSERT INTO `Admin` (`UserID`, `Level`, `timestamp`) VALUES
(1, 99, '2016-10-02 00:56:33');

-- --------------------------------------------------------

--
-- Table structure for table `AdminLoginSessions`
--

CREATE TABLE IF NOT EXISTS `AdminLoginSessions` (
  `ID` bigint(20) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` bigint(20) unsigned NOT NULL,
  `JSESSIONID` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Ban`
--

CREATE TABLE IF NOT EXISTS `Ban` (
  `UserID` bigint(20) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Cart`
--

CREATE TABLE IF NOT EXISTS `Cart` (
  `UserID` bigint(20) unsigned NOT NULL,
  `PubID` bigint(20) unsigned NOT NULL,
  `AddTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RemoveTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Entity`
--

CREATE TABLE IF NOT EXISTS `Entity` (
  `ID` bigint(20) unsigned NOT NULL,
  `PubID` bigint(20) unsigned NOT NULL,
  `EntityID` text NOT NULL,
  `Class` text NOT NULL,
  `Type` text NOT NULL,
  `Caption` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Graph`
--

CREATE TABLE IF NOT EXISTS `Graph` (
  `ID` bigint(20) unsigned NOT NULL,
  `PubID` bigint(20) unsigned NOT NULL,
  `NodeFrom` text NOT NULL,
  `Edge` text NOT NULL,
  `NodeTo` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Stand-in structure for view `graphedge`
--
CREATE TABLE IF NOT EXISTS `graphedge` (
`ID` bigint(20) unsigned
,`NodeFrom` text
,`EdgeID` bigint(20) unsigned
,`Edge` text
,`EdgeCaption` text
,`NodeTo` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `graphnodefrom`
--
CREATE TABLE IF NOT EXISTS `graphnodefrom` (
`ID` bigint(20) unsigned
,`NodeFromID` bigint(20) unsigned
,`NodeFrom` text
,`NodeFromCaption` text
,`Edge` text
,`NodeTo` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `graphnodeto`
--
CREATE TABLE IF NOT EXISTS `graphnodeto` (
`ID` bigint(20) unsigned
,`NodeFrom` text
,`Edge` text
,`NodeToID` bigint(20) unsigned
,`NodeTo` text
,`NodeToCaption` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `graphoutput`
--
CREATE TABLE IF NOT EXISTS `graphoutput` (
`ID` bigint(20) unsigned
,`NodeFromID` bigint(20) unsigned
,`NodeFrom` text
,`NodeFromCaption` text
,`EdgeID` bigint(20) unsigned
,`Edge` text
,`EdgeCaption` text
,`NodeToID` bigint(20) unsigned
,`NodeTo` text
,`NodeToCaption` text
);

-- --------------------------------------------------------

--
-- Table structure for table `IPLog`
--

CREATE TABLE IF NOT EXISTS `IPLog` (
  `IPAddress` varchar(100) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Listing`
--

CREATE TABLE IF NOT EXISTS `Listing` (
  `PubID` bigint(20) unsigned NOT NULL,
  `Title` tinytext NOT NULL,
  `Authors` text NOT NULL,
  `Editors` text NOT NULL,
  `Type` tinytext NOT NULL,
  `Year` smallint(6) unsigned NOT NULL,
  `Venue` tinytext,
  `SellerID` bigint(20) unsigned NOT NULL,
  `Picture` tinytext NOT NULL,
  `Price` float unsigned NOT NULL,
  `Status` tinyint(1) unsigned NOT NULL,
  `SoldCount` int(11) unsigned NOT NULL DEFAULT '0',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Listing`
--

INSERT INTO `Listing` (`PubID`, `Title`, `Authors`, `Editors`, `Type`, `Year`, `Venue`, `SellerID`, `Picture`, `Price`, `Status`, `SoldCount`, `timestamp`) VALUES
(1, 'Harry Potter and the philosopher stone', 'J.K. Rowling', '', 'Book', 1999, '', 1, '/uploads/pic1.jpg', 199, 1, 0, '2016-10-01 01:12:17'),
(2, 'Winnie the Pooh', 'Christopher Robin', 'Piglet,Tiger', 'Conference', 1879, 'Kids', 1, '/uploads/pic2.jpg', 79, 1, 0, '2016-10-01 01:14:00'),
(3, 'The Maker of Swan', 'Paraic O''Donnell', '', 'Article', 2001, '', 1, '/uploads/pic3.jpg', 99, 1, 0, '2016-10-01 01:15:45'),
(4, 'Responsive Web Design', 'Ethan Marcotte', 'Jeremy Keith', 'Journal', 2010, '', 1, '/uploads/pic4.jpg', 299, 1, 0, '2016-10-01 01:26:27'),
(5, 'Mobile First', 'Luke Wroblewski', 'Jeffrey Zeldman', 'Article', 1790, '', 1, '/uploads/pic5.jpg', 219, 1, 0, '2016-10-01 01:29:24'),
(6, 'Ancient Tales', 'Duncan Long,Raz Algul', '', 'Master Thesis', 1890, '', 1, '/uploads/pic6.jpg', 99, 1, 0, '2016-10-01 01:31:11'),
(7, 'Enchantment The Art of Changing Heart, Minds, and Actions', 'Guy Kawasaki', 'Woz,Apple', 'Journal', 2016, '', 1, '/uploads/pic7.jpg', 449, 1, 0, '2016-10-01 01:32:47'),
(8, 'To Kill a Mocking Bird', 'Harper Lee', '', 'Book', 2020, '', 1, '/uploads/pic8.jpg', 799, 1, 0, '2016-10-01 01:33:39'),
(9, '1984', 'George Orwell', '', 'Article', 2000, '', 1, '/uploads/pic9.jpg', 399, 1, 0, '2016-10-01 01:34:26'),
(10, 'Pieces of Light', 'Charles Fernyhough', 'Andre Aciman', 'Conference', 2009, 'Harvard', 1, '/uploads/pic10.jpg', 299, 1, 0, '2016-10-01 01:35:40'),
(11, 'Jungle Book', 'IBM', '', 'Conference', 1809, 'IBM', 1, '/uploads/pic11.jpg', 199, 1, 0, '2016-10-01 01:39:14'),
(12, 'Big-Hearted People', 'Randy J. Schum', '', 'Ph.D.Thesis', 2016, '', 1, '/uploads/pic12.jpg', 699, 1, 0, '2016-10-01 01:40:25'),
(13, 'Code Quality', 'Diomidis Spinellis', '', 'Book', 1976, '', 1, '/uploads/pic13.jpg', 999, 1, 0, '2016-10-01 01:41:36'),
(14, 'Our Lent Things we carry', 'David Crumm', '', 'Book', 2011, '', 1, '/uploads/pic14.jpg', 300, 1, 0, '2016-10-01 01:42:32');

-- --------------------------------------------------------

--
-- Table structure for table `ListingStatistics`
--

CREATE TABLE IF NOT EXISTS `ListingStatistics` (
  `PubID` bigint(20) unsigned NOT NULL,
  `AddedToCart` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Viewed` bigint(20) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `LoginSessions`
--

CREATE TABLE IF NOT EXISTS `LoginSessions` (
  `ID` bigint(20) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` bigint(20) unsigned NOT NULL,
  `JSESSIONID` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `PageHits`
--

CREATE TABLE IF NOT EXISTS `PageHits` (
  `Page` varchar(100) NOT NULL,
  `Hits` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Transaction`
--

CREATE TABLE IF NOT EXISTS `Transaction` (
  `BuyerID` bigint(20) unsigned NOT NULL,
  `SellerID` bigint(20) unsigned NOT NULL,
  `PubID` bigint(20) unsigned NOT NULL,
  `OrderNumber` bigint(20) unsigned NOT NULL,
  `PurchaseTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SellingPrice` smallint(5) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UserID` bigint(20) unsigned NOT NULL,
  `Username` varchar(16) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `Nickname` tinytext,
  `Firstname` tinytext,
  `Lastname` tinytext,
  `Email` tinytext,
  `NewEmail` tinytext,
  `Birthyear` smallint(6) unsigned DEFAULT NULL,
  `Address` text NOT NULL,
  `CardNumber` tinytext NOT NULL,
  `TokenString` varchar(36) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserID`, `Username`, `Password`, `Nickname`, `Firstname`, `Lastname`, `Email`, `NewEmail`, `Birthyear`, `Address`, `CardNumber`, `TokenString`) VALUES
(1, 'admin', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin', 'admin', 'admin', 'admin', NULL, 1000, 'admin', '9999999999999999', NULL),
(2, 'admin2', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin2', 'admin2', 'admin2', 'admin2', NULL, 1000, 'admin2', '9999999999999999', NULL),
(3, 'admin3', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin3', 'admin3', 'admin3', 'admin3', NULL, 1000, 'admin3', '9999999999999999', NULL),
(4, 'admin4', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin4', 'admin4', 'admin4', 'admin4', NULL, 1000, 'admin4', '9999999999999999', NULL),
(5, 'admin5', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin5', 'admin5', 'admin5', 'admin5', NULL, 1000, 'admin5', '9999999999999999', NULL),
(6, 'admin6', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin6', 'admin6', 'admin6', 'admin6', NULL, 1000, 'admin6', '9999999999999999', NULL),
(7, 'admin7', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin7', 'admin7', 'admin7', 'admin7', NULL, 1000, 'admin7', '9999999999999999', NULL),
(8, 'admin8', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin8', 'admin8', 'admin8', 'admin8', NULL, 1000, 'admin8', '9999999999999999', NULL),
(9, 'admin9', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin9', 'admin9', 'admin9', 'admin9', NULL, 1000, 'admin9', '9999999999999999', NULL),
(10, 'admin10', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin10', 'admin10', 'admin10', 'admin10', NULL, 1000, 'admin10', '9999999999999999', NULL),
(11, 'admin11', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin11', 'admin11', 'admin11', 'admin11', NULL, 1000, 'admin11', '9999999999999999', NULL),
(12, 'admin12', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin12', 'admin12', 'admin12', 'admin12', NULL, 1000, 'admin12', '9999999999999999', NULL),
(13, 'admin13', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin13', 'admin13', 'admin13', 'admin13', NULL, 1000, 'admin13', '9999999999999999', NULL),
(14, 'admin14', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin14', 'admin14', 'admin14', 'admin14', NULL, 1000, 'admin14', '9999999999999999', NULL),
(15, 'admin15', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin15', 'admin15', 'admin15', 'admin15', NULL, 1000, 'admin15', '9999999999999999', NULL),
(16, 'admin16', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin16', 'admin16', 'admin16', 'admin16', NULL, 1000, 'admin16', '9999999999999999', NULL),
(17, 'admin17', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin17', 'admin17', 'admin17', 'admin17', NULL, 1000, 'admin17', '9999999999999999', NULL),
(18, 'admin18', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin18', 'admin18', 'admin18', 'admin18', NULL, 1000, 'admin18', '9999999999999999', NULL),
(19, 'admin19', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin19', 'admin19', 'admin19', 'admin19', NULL, 1000, 'admin19', '9999999999999999', NULL),
(20, 'admin20', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin20', 'admin20', 'admin20', 'admin20', NULL, 1000, 'admin20', '9999999999999999', NULL),
(21, 'admin21', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin21', 'admin21', 'admin21', 'admin21', NULL, 1000, 'admin21', '9999999999999999', NULL),
(22, 'user', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user', 'user', 'user', 'user', NULL, 1000, 'user', '9999999999999999', NULL),
(23, 'user2', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user2', 'user2', 'user2', 'user2', NULL, 1000, 'user2', '9999999999999999', NULL),
(24, 'user3', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user3', 'user3', 'user3', 'user3', NULL, 1000, 'user3', '9999999999999999', NULL),
(25, 'user4', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user4', 'user4', 'user4', 'user4', NULL, 1000, 'user4', '9999999999999999', NULL),
(26, 'user5', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user5', 'user5', 'user5', 'user5', NULL, 1000, 'user5', '9999999999999999', NULL),
(27, 'user6', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user6', 'user6', 'user6', 'user6', NULL, 1000, 'user6', '9999999999999999', NULL),
(28, 'user7', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user7', 'user7', 'user7', 'user7', NULL, 1000, 'user7', '9999999999999999', NULL),
(29, 'user8', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user8', 'user8', 'user8', 'user8', NULL, 1000, 'user8', '9999999999999999', NULL),
(30, 'user9', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user9', 'user9', 'user9', 'user9', NULL, 1000, 'user9', '9999999999999999', NULL),
(31, 'user10', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user10', 'user10', 'user10', 'user10', NULL, 1000, 'user10', '9999999999999999', NULL),
(32, 'user11', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user11', 'user11', 'user11', 'user11', NULL, 1000, 'user11', '9999999999999999', NULL),
(33, 'user12', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user12', 'user12', 'user12', 'user12', NULL, 1000, 'user12', '9999999999999999', NULL),
(34, 'user13', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user13', 'user13', 'user13', 'user13', NULL, 1000, 'user13', '9999999999999999', NULL),
(35, 'user14', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user14', 'user14', 'user14', 'user14', NULL, 1000, 'user14', '9999999999999999', NULL),
(36, 'user15', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user15', 'user15', 'user15', 'user15', NULL, 1000, 'user15', '9999999999999999', NULL),
(37, 'user16', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user16', 'user16', 'user16', 'user16', NULL, 1000, 'user16', '9999999999999999', NULL),
(38, 'user17', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user17', 'user17', 'user17', 'user17', NULL, 1000, 'user17', '9999999999999999', NULL),
(39, 'user18', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user18', 'user18', 'user18', 'user18', NULL, 1000, 'user18', '9999999999999999', NULL),
(40, 'user19', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user19', 'user19', 'user19', 'user19', NULL, 1000, 'user19', '9999999999999999', NULL),
(41, 'user20', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user20', 'user20', 'user20', 'user20', NULL, 1000, 'user20', '9999999999999999', NULL),
(42, 'user21', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user21', 'user21', 'user21', 'user21', NULL, 1000, 'user21', '9999999999999999', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Variable`
--

CREATE TABLE IF NOT EXISTS `Variable` (
  `Name` varchar(20) NOT NULL,
  `Value` int(10) unsigned DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Variable`
--

INSERT INTO `Variable` (`Name`, `Value`) VALUES
('OrderNumber', 0);

-- --------------------------------------------------------

--
-- Structure for view `graphedge`
--
DROP TABLE IF EXISTS `graphedge`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphedge` AS select `graph`.`ID` AS `ID`,`graph`.`NodeFrom` AS `NodeFrom`,`entity`.`ID` AS `EdgeID`,`graph`.`Edge` AS `Edge`,`entity`.`Caption` AS `EdgeCaption`,`graph`.`NodeTo` AS `NodeTo` from (`graph` join `entity`) where (`graph`.`Edge` = `entity`.`EntityID`);

-- --------------------------------------------------------

--
-- Structure for view `graphnodefrom`
--
DROP TABLE IF EXISTS `graphnodefrom`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphnodefrom` AS select `graph`.`ID` AS `ID`,`entity`.`ID` AS `NodeFromID`,`graph`.`NodeFrom` AS `NodeFrom`,`entity`.`Caption` AS `NodeFromCaption`,`graph`.`Edge` AS `Edge`,`graph`.`NodeTo` AS `NodeTo` from (`graph` join `entity`) where (`graph`.`NodeFrom` = `entity`.`EntityID`);

-- --------------------------------------------------------

--
-- Structure for view `graphnodeto`
--
DROP TABLE IF EXISTS `graphnodeto`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphnodeto` AS select `graph`.`ID` AS `ID`,`graph`.`NodeFrom` AS `NodeFrom`,`graph`.`Edge` AS `Edge`,`entity`.`ID` AS `NodeToID`,`graph`.`NodeTo` AS `NodeTo`,`entity`.`Caption` AS `NodeToCaption` from (`graph` join `entity`) where (`graph`.`NodeTo` = `entity`.`EntityID`);

-- --------------------------------------------------------

--
-- Structure for view `graphoutput`
--
DROP TABLE IF EXISTS `graphoutput`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphoutput` AS select `graphnodefrom`.`ID` AS `ID`,`graphnodefrom`.`NodeFromID` AS `NodeFromID`,`graphnodefrom`.`NodeFrom` AS `NodeFrom`,`graphnodefrom`.`NodeFromCaption` AS `NodeFromCaption`,`graphedge`.`EdgeID` AS `EdgeID`,`graphnodefrom`.`Edge` AS `Edge`,`graphedge`.`EdgeCaption` AS `EdgeCaption`,`graphnodeto`.`NodeToID` AS `NodeToID`,`graphnodefrom`.`NodeTo` AS `NodeTo`,`graphnodeto`.`NodeToCaption` AS `NodeToCaption` from ((`graphnodefrom` join `graphedge`) join `graphnodeto`) where ((`graphnodefrom`.`ID` = `graphedge`.`ID`) and (`graphnodefrom`.`ID` = `graphnodeto`.`ID`) and (`graphedge`.`ID` = `graphnodeto`.`ID`));

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `UserID` (`UserID`);

--
-- Indexes for table `AdminLoginSessions`
--
ALTER TABLE `AdminLoginSessions`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `JSESSIONID` (`JSESSIONID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `Ban`
--
ALTER TABLE `Ban`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `UserID` (`UserID`);

--
-- Indexes for table `Cart`
--
ALTER TABLE `Cart`
  ADD PRIMARY KEY (`UserID`,`PubID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `PubID` (`PubID`);

--
-- Indexes for table `Entity`
--
ALTER TABLE `Entity`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `PubID` (`PubID`);

--
-- Indexes for table `Graph`
--
ALTER TABLE `Graph`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `PubID` (`PubID`);

--
-- Indexes for table `IPLog`
--
ALTER TABLE `IPLog`
  ADD PRIMARY KEY (`IPAddress`);

--
-- Indexes for table `Listing`
--
ALTER TABLE `Listing`
  ADD PRIMARY KEY (`PubID`),
  ADD UNIQUE KEY `PubID` (`PubID`),
  ADD KEY `SellerID` (`SellerID`);

--
-- Indexes for table `ListingStatistics`
--
ALTER TABLE `ListingStatistics`
  ADD PRIMARY KEY (`PubID`);

--
-- Indexes for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `JSESSIONID` (`JSESSIONID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `PageHits`
--
ALTER TABLE `PageHits`
  ADD UNIQUE KEY `Page` (`Page`);

--
-- Indexes for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD PRIMARY KEY (`BuyerID`,`SellerID`,`PubID`,`OrderNumber`),
  ADD KEY `PubID` (`PubID`),
  ADD KEY `SellerID` (`SellerID`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `UserID` (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `Variable`
--
ALTER TABLE `Variable`
  ADD PRIMARY KEY (`Name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AdminLoginSessions`
--
ALTER TABLE `AdminLoginSessions`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Entity`
--
ALTER TABLE `Entity`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=781;
--
-- AUTO_INCREMENT for table `Graph`
--
ALTER TABLE `Graph`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=342;
--
-- AUTO_INCREMENT for table `Listing`
--
ALTER TABLE `Listing`
  MODIFY `PubID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `UserID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=43;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Admin`
--
ALTER TABLE `Admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `AdminLoginSessions`
--
ALTER TABLE `AdminLoginSessions`
  ADD CONSTRAINT `adminloginsessions_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `Ban`
--
ALTER TABLE `Ban`
  ADD CONSTRAINT `ban_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`) ON DELETE CASCADE,
  ADD CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `Entity`
--
ALTER TABLE `Entity`
  ADD CONSTRAINT `entity_ibfk_1` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Graph`
--
ALTER TABLE `Graph`
  ADD CONSTRAINT `graph_ibfk_1` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ListingStatistics`
--
ALTER TABLE `ListingStatistics`
  ADD CONSTRAINT `listingstatistics_ibfk_1` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`) ON DELETE CASCADE;

--
-- Constraints for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  ADD CONSTRAINT `loginsessions_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_4` FOREIGN KEY (`BuyerID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_5` FOREIGN KEY (`SellerID`) REFERENCES `User` (`UserID`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
