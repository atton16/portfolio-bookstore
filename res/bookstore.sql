-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2016 at 07:40 AM
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
(1, 99, '2016-10-02 00:56:33'),
(2, 99, '2016-10-04 06:51:28');

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
) ENGINE=InnoDB AUTO_INCREMENT=856 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Entity`
--

INSERT INTO `Entity` (`ID`, `PubID`, `EntityID`, `Class`, `Type`, `Caption`) VALUES
(781, 15, 'P15', 'Node', 'Publication', 'Harry Potter and the Philosopher Stone'),
(782, 15, 'A10', 'Node', 'Author', 'J.K. Rowling'),
(783, 15, 'E40', 'Edge', 'DirectedLink', 'authored by'),
(784, 16, 'P16', 'Node', 'Publication', 'Winnie the Pooh'),
(785, 16, 'A11', 'Node', 'Author', 'Christopher Robin'),
(786, 16, 'E41', 'Edge', 'DirectedLink', 'authored by'),
(787, 16, 'A12', 'Node', 'Author', 'Tiger'),
(788, 16, 'E42', 'Edge', 'DirectedLink', 'edited by'),
(789, 16, 'A13', 'Node', 'Author', 'Rabbit'),
(790, 16, 'E43', 'Edge', 'DirectedLink', 'edited by'),
(791, 16, 'V1', 'Node', 'Venue', 'Kids'),
(792, 16, 'E44', 'Edge', 'DirectedLink', 'published in'),
(793, 17, 'P17', 'Node', 'Publication', 'The Maker of Swan'),
(794, 17, 'A14', 'Node', 'Author', 'Paraic O''Donnell'),
(795, 17, 'E45', 'Edge', 'DirectedLink', 'authored by'),
(796, 17, 'A15', 'Node', 'Author', 'Helen Macdonald'),
(797, 17, 'E46', 'Edge', 'DirectedLink', 'edited by'),
(798, 18, 'P18', 'Node', 'Publication', 'Responsive Web Design'),
(799, 18, 'A16', 'Node', 'Author', 'Ethan Marcotte'),
(800, 18, 'E47', 'Edge', 'DirectedLink', 'authored by'),
(801, 18, 'A17', 'Node', 'Author', 'Jeremy Keith'),
(802, 18, 'E48', 'Edge', 'DirectedLink', 'edited by'),
(803, 19, 'P19', 'Node', 'Publication', 'Mobile First'),
(804, 19, 'A18', 'Node', 'Author', 'Luke Wroblewski'),
(805, 19, 'E49', 'Edge', 'DirectedLink', 'authored by'),
(806, 19, 'A19', 'Node', 'Author', 'Jeffrey Zeldman'),
(807, 19, 'E50', 'Edge', 'DirectedLink', 'edited by'),
(808, 19, 'V2', 'Node', 'Venue', 'A Book Apart'),
(809, 19, 'E51', 'Edge', 'DirectedLink', 'published in'),
(810, 20, 'P20', 'Node', 'Publication', 'Ancient Tales'),
(811, 20, 'A20', 'Node', 'Author', 'Duncan Long'),
(812, 20, 'E52', 'Edge', 'DirectedLink', 'authored by'),
(813, 20, 'A21', 'Node', 'Author', 'Raz Algul'),
(814, 20, 'E53', 'Edge', 'DirectedLink', 'authored by'),
(815, 21, 'P21', 'Node', 'Publication', 'Enchantment The Art of Changing Heart, Minds, and Actions'),
(816, 21, 'A22', 'Node', 'Author', 'Guy Kawasaki'),
(817, 21, 'E54', 'Edge', 'DirectedLink', 'authored by'),
(818, 21, 'A23', 'Node', 'Author', 'Woz'),
(819, 21, 'E55', 'Edge', 'DirectedLink', 'edited by'),
(820, 21, 'A24', 'Node', 'Author', 'Apple'),
(821, 21, 'E56', 'Edge', 'DirectedLink', 'edited by'),
(822, 21, 'E57', 'Edge', 'DirectedLink', 'published in'),
(823, 22, 'P22', 'Node', 'Publication', 'To Kill a Mocking Bird'),
(824, 22, 'A25', 'Node', 'Author', 'Harper Lee'),
(825, 22, 'E58', 'Edge', 'DirectedLink', 'authored by'),
(826, 22, 'E59', 'Edge', 'DirectedLink', 'published in'),
(827, 23, 'P23', 'Node', 'Publication', '1984'),
(828, 23, 'A26', 'Node', 'Author', 'George Orwell'),
(829, 23, 'E60', 'Edge', 'DirectedLink', 'authored by'),
(830, 23, 'E61', 'Edge', 'DirectedLink', 'published in'),
(831, 24, 'P24', 'Node', 'Publication', 'Pieces of Light'),
(832, 24, 'A27', 'Node', 'Author', 'Charles Fernyhough'),
(833, 24, 'E62', 'Edge', 'DirectedLink', 'authored by'),
(834, 24, 'A28', 'Node', 'Author', 'Andre Aciman'),
(835, 24, 'E63', 'Edge', 'DirectedLink', 'edited by'),
(836, 24, 'E64', 'Edge', 'DirectedLink', 'published in'),
(837, 25, 'P25', 'Node', 'Publication', 'Jungle Book'),
(838, 25, 'A29', 'Node', 'Author', 'IBM'),
(839, 25, 'E65', 'Edge', 'DirectedLink', 'authored by'),
(840, 25, 'E66', 'Edge', 'DirectedLink', 'published in'),
(841, 26, 'P26', 'Node', 'Publication', 'Big-Hearted People'),
(842, 26, 'A30', 'Node', 'Author', 'Randy J. Schum'),
(843, 26, 'E67', 'Edge', 'DirectedLink', 'authored by'),
(844, 27, 'P27', 'Node', 'Publication', 'Code Quality'),
(845, 27, 'A31', 'Node', 'Author', 'Diomidis Spinellis'),
(846, 27, 'E68', 'Edge', 'DirectedLink', 'authored by'),
(847, 27, 'A32', 'Node', 'Author', 'Scott Mayers'),
(848, 27, 'E69', 'Edge', 'DirectedLink', 'edited by'),
(849, 28, 'P28', 'Node', 'Publication', 'Our Lent Things we carry'),
(850, 28, 'A33', 'Node', 'Author', 'David Crumm'),
(851, 28, 'E70', 'Edge', 'DirectedLink', 'authored by'),
(852, 29, 'P29', 'Node', 'Publication', 'Harry Potter and The Deathly Hallow'),
(853, 29, 'E71', 'Edge', 'DirectedLink', 'authored by'),
(854, 29, 'A34', 'Node', 'Author', 'Pottermore'),
(855, 29, 'E72', 'Edge', 'DirectedLink', 'edited by');

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
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Graph`
--

INSERT INTO `Graph` (`ID`, `PubID`, `NodeFrom`, `Edge`, `NodeTo`) VALUES
(342, 15, 'P15', 'E40', 'A10'),
(343, 16, 'P16', 'E41', 'A11'),
(344, 16, 'P16', 'E42', 'A12'),
(345, 16, 'P16', 'E43', 'A13'),
(346, 16, 'P16', 'E44', 'V1'),
(347, 17, 'P17', 'E45', 'A14'),
(348, 17, 'P17', 'E46', 'A15'),
(349, 18, 'P18', 'E47', 'A16'),
(350, 18, 'P18', 'E48', 'A17'),
(351, 19, 'P19', 'E49', 'A18'),
(352, 19, 'P19', 'E50', 'A19'),
(353, 19, 'P19', 'E51', 'V2'),
(354, 20, 'P20', 'E52', 'A20'),
(355, 20, 'P20', 'E53', 'A21'),
(356, 21, 'P21', 'E54', 'A22'),
(357, 21, 'P21', 'E55', 'A23'),
(358, 21, 'P21', 'E56', 'A24'),
(359, 21, 'P21', 'E57', 'V2'),
(360, 22, 'P22', 'E58', 'A25'),
(361, 22, 'P22', 'E59', 'V1'),
(362, 23, 'P23', 'E60', 'A26'),
(363, 23, 'P23', 'E61', 'V1'),
(364, 24, 'P24', 'E62', 'A27'),
(365, 24, 'P24', 'E63', 'A28'),
(366, 24, 'P24', 'E64', 'V2'),
(367, 25, 'P25', 'E65', 'A29'),
(368, 25, 'P25', 'E66', 'V1'),
(369, 26, 'P26', 'E67', 'A30'),
(370, 27, 'P27', 'E68', 'A31'),
(371, 27, 'P27', 'E69', 'A32'),
(372, 28, 'P28', 'E70', 'A33'),
(373, 29, 'P29', 'E71', 'A10'),
(374, 29, 'P29', 'E72', 'A34');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Listing`
--

INSERT INTO `Listing` (`PubID`, `Title`, `Authors`, `Editors`, `Type`, `Year`, `Venue`, `SellerID`, `Picture`, `Price`, `Status`, `SoldCount`, `timestamp`) VALUES
(15, 'Harry Potter and the Philosopher Stone', 'J.K. Rowling', '', 'Book', 1999, '', 1, '/uploads/pic1.jpg', 199.99, 1, 0, '2016-10-02 11:16:22'),
(16, 'Winnie the Pooh', 'Christopher Robin', 'Tiger,Rabbit', 'Conference', 1980, 'Kids', 1, '/uploads/pic2.jpg', 49.5, 1, 0, '2016-10-02 11:17:28'),
(17, 'The Maker of Swan', 'Paraic O''Donnell', 'Helen Macdonald', 'Article', 2001, '', 1, '/uploads/pic3.jpg', 210, 1, 0, '2016-10-02 11:18:37'),
(18, 'Responsive Web Design', 'Ethan Marcotte', 'Jeremy Keith', 'Book', 1990, '', 1, '/uploads/pic4.jpg', 300, 1, 0, '2016-10-02 11:19:25'),
(19, 'Mobile First', 'Luke Wroblewski', 'Jeffrey Zeldman', 'Conference', 2008, 'A Book Apart', 1, '/uploads/pic5.jpg', 299, 1, 0, '2016-10-02 11:20:41'),
(20, 'Ancient Tales', 'Duncan Long,Raz Algul', '', 'Article', 1890, '', 1, '/uploads/pic6.jpg', 280, 1, 0, '2016-10-02 11:21:28'),
(21, 'Enchantment The Art of Changing Heart, Minds, and Actions', 'Guy Kawasaki', 'Woz,Apple', 'Conference', 1987, 'A Book Apart', 1, '/uploads/pic7.jpg', 300, 1, 0, '2016-10-02 11:22:19'),
(22, 'To Kill a Mocking Bird', 'Harper Lee', '', 'Conference', 2005, 'Kids', 2, '/uploads/pic8.jpg', 29.9, 1, 0, '2016-10-02 11:23:27'),
(23, '1984', 'George Orwell', '', 'Conference', 2007, 'Kids', 2, '/uploads/pic9.jpg', 39.8, 1, 0, '2016-10-02 11:24:02'),
(24, 'Pieces of Light', 'Charles Fernyhough', 'Andre Aciman', 'Conference', 2009, 'A Book Apart', 2, '/uploads/pic10.jpg', 280, 1, 0, '2016-10-02 11:24:43'),
(25, 'Jungle Book', 'IBM', '', 'Conference', 1998, 'Kids', 2, '/uploads/pic11.jpg', 29, 1, 0, '2016-10-02 11:25:23'),
(26, 'Big-Hearted People', 'Randy J. Schum', '', 'Book', 2013, '', 2, '/uploads/pic12.jpg', 239, 1, 0, '2016-10-02 11:26:05'),
(27, 'Code Quality', 'Diomidis Spinellis', 'Scott Mayers', 'Master Thesis', 2011, '', 2, '/uploads/pic13.jpg', 390, 1, 0, '2016-10-02 11:27:01'),
(28, 'Our Lent Things we carry', 'David Crumm', '', 'Ph.D.Thesis', 2016, '', 2, '/uploads/pic14.jpg', 390, 1, 0, '2016-10-02 11:27:40'),
(29, 'Harry Potter and The Deathly Hallow', 'J.K. Rowling', 'Pottermore', 'Book', 2015, '', 2, '/uploads/pic15.jpg', 580.6, 1, 0, '2016-10-02 11:28:18');

-- --------------------------------------------------------

--
-- Table structure for table `ListingStatistics`
--

CREATE TABLE IF NOT EXISTS `ListingStatistics` (
  `PubID` bigint(20) unsigned NOT NULL,
  `AddedToCart` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Viewed` bigint(20) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserID`, `Username`, `Password`, `Nickname`, `Firstname`, `Lastname`, `Email`, `NewEmail`, `Birthyear`, `Address`, `CardNumber`, `TokenString`) VALUES
(1, 'admin', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin', 'admin', 'admin', 'atton16@gmail.com', NULL, 1000, 'admin', '9999999999999999', NULL),
(2, 'admin2', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin2', 'admin2', 'admin2', 'atton16@me.com', NULL, 1000, 'admin2', '9999999999999999', NULL),
(3, 'user', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user', 'user', 'user', 'user', NULL, 1000, 'user', '9999999999999999', NULL),
(4, 'user2', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user2', 'user2', 'user2', 'user2', NULL, 1000, 'user2', '9999999999999999', NULL),
(5, 'user3', '$2a$10$GYqbUOQjUSEMfs4oGuwc3.m0e25cEtFHAzLl6b0Aztt/bu1HXwdZa', 'user3', 'user3', 'user3', 'user3', NULL, 1000, 'user3', '9999999999999999', NULL);

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
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=856;
--
-- AUTO_INCREMENT for table `Graph`
--
ALTER TABLE `Graph`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=375;
--
-- AUTO_INCREMENT for table `Listing`
--
ALTER TABLE `Listing`
  MODIFY `PubID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `UserID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
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
