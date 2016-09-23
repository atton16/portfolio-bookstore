-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 23, 2016 at 02:49 PM
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
  `EntityID` text NOT NULL,
  `Class` text NOT NULL,
  `Type` text NOT NULL,
  `Caption` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Entity`
--

INSERT INTO `Entity` (`ID`, `EntityID`, `Class`, `Type`, `Caption`) VALUES
(1, 'P1', 'Node', 'Publication', 'Crawling Hidden Objects with kNN Queries.'),
(2, 'A1', 'Node', 'Author', 'Hui Yan'),
(3, 'V1', 'Node', 'Venue', 'IEEE Trans. Knowl. Data Eng.'),
(4, 'S1', 'Node', 'School', 'Universität Linköping'),
(5, 'A2', 'Node', 'Author', 'Andrzej Bednarski'),
(6, 'P2', 'Node', 'Publication', 'A dynamic programming approach to optimal retargetable code generation for irregular architectures.'),
(7, 'A3', 'Node', 'Author', 'Zhiguo Gong'),
(8, 'E1', 'Edge', 'DirectedLink', 'authored by'),
(9, 'E2', 'Edge', 'DirectedLink', 'affiliated in'),
(10, 'E3', 'Edge', 'DirectedLink', 'authored by'),
(11, 'E4', 'Edge', 'DirectedLink', 'authored by'),
(12, 'E5', 'Edge', 'DirectedLink', 'published in');

-- --------------------------------------------------------

--
-- Table structure for table `Graph`
--

CREATE TABLE IF NOT EXISTS `Graph` (
  `ID` bigint(20) unsigned NOT NULL,
  `NodeFrom` text NOT NULL,
  `Edge` text NOT NULL,
  `NodeTo` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Graph`
--

INSERT INTO `Graph` (`ID`, `NodeFrom`, `Edge`, `NodeTo`) VALUES
(1, 'P2', 'E1', 'A2'),
(2, 'A2', 'E2', 'S1'),
(3, 'P1', 'E3', 'A1'),
(4, 'P1', 'E4', 'A3'),
(5, 'P1', 'E5', 'V1');

-- --------------------------------------------------------

--
-- Stand-in structure for view `graphedge`
--
CREATE TABLE IF NOT EXISTS `graphedge` (
`ID` bigint(20) unsigned
,`NodeFrom` text
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
,`NodeTo` text
,`NodeToCaption` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `graphoutput`
--
CREATE TABLE IF NOT EXISTS `graphoutput` (
`ID` bigint(20) unsigned
,`NodeFrom` text
,`NodeFromCaption` text
,`Edge` text
,`EdgeCaption` text
,`NodeTo` text
,`NodeToCaption` text
);

-- --------------------------------------------------------

--
-- Table structure for table `Listing`
--

CREATE TABLE IF NOT EXISTS `Listing` (
  `PubID` bigint(20) unsigned NOT NULL,
  `Title` tinytext NOT NULL,
  `Authors` text,
  `Editors` text,
  `Type` tinytext NOT NULL,
  `Year` smallint(6) unsigned NOT NULL,
  `Venue` tinytext,
  `SellerID` bigint(20) unsigned NOT NULL,
  `Picture` tinytext NOT NULL,
  `Price` smallint(5) unsigned NOT NULL,
  `Status` tinyint(1) unsigned NOT NULL,
  `SoldCount` int(11) unsigned NOT NULL DEFAULT '0',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `LoginSessions`
--

CREATE TABLE IF NOT EXISTS `LoginSessions` (
  `ID` bigint(20) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` bigint(20) unsigned NOT NULL,
  `JSESSIONID` int(11) unsigned NOT NULL
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
-- Table structure for table `Unactivated`
--

CREATE TABLE IF NOT EXISTS `Unactivated` (
  `ID` bigint(20) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` bigint(20) unsigned NOT NULL,
  `TokenString` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UserID` bigint(20) unsigned NOT NULL,
  `Username` varchar(16) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `Nickname` tinytext NOT NULL,
  `Firstname` tinytext NOT NULL,
  `Lastname` tinytext NOT NULL,
  `Email` tinytext NOT NULL,
  `Birthyear` smallint(6) unsigned NOT NULL,
  `Address` text NOT NULL,
  `CardNumber` tinytext NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserID`, `Username`, `Password`, `Nickname`, `Firstname`, `Lastname`, `Email`, `Birthyear`, `Address`, `CardNumber`) VALUES
(1, 'admin', '$2a$10$YhPMmAf4w/yW0O1qiT3.3eG.Q29Fb/rllUZV6Mh0WHKDKRUkLC6PG', 'admin', 'admin', 'admin', 'admin', 1000, 'admin', '9999999999999999');

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

CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphedge` AS select `graph`.`ID` AS `ID`,`graph`.`NodeFrom` AS `NodeFrom`,`graph`.`Edge` AS `Edge`,`entity`.`Caption` AS `EdgeCaption`,`graph`.`NodeTo` AS `NodeTo` from (`graph` join `entity`) where (`graph`.`Edge` = `entity`.`EntityID`) order by `graph`.`ID`;

-- --------------------------------------------------------

--
-- Structure for view `graphnodefrom`
--
DROP TABLE IF EXISTS `graphnodefrom`;

CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphnodefrom` AS select `graph`.`ID` AS `ID`,`graph`.`NodeFrom` AS `NodeFrom`,`entity`.`Caption` AS `NodeFromCaption`,`graph`.`Edge` AS `Edge`,`graph`.`NodeTo` AS `NodeTo` from (`graph` join `entity`) where (`graph`.`NodeFrom` = `entity`.`EntityID`) order by `graph`.`ID`;

-- --------------------------------------------------------

--
-- Structure for view `graphnodeto`
--
DROP TABLE IF EXISTS `graphnodeto`;

CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphnodeto` AS select `graph`.`ID` AS `ID`,`graph`.`NodeFrom` AS `NodeFrom`,`graph`.`Edge` AS `Edge`,`graph`.`NodeTo` AS `NodeTo`,`entity`.`Caption` AS `NodeToCaption` from (`graph` join `entity`) where (`graph`.`NodeTo` = `entity`.`EntityID`) order by `graph`.`ID`;

-- --------------------------------------------------------

--
-- Structure for view `graphoutput`
--
DROP TABLE IF EXISTS `graphoutput`;

CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `graphoutput` AS select `graphnodefrom`.`ID` AS `ID`,`graphnodefrom`.`NodeFrom` AS `NodeFrom`,`graphnodefrom`.`NodeFromCaption` AS `NodeFromCaption`,`graphnodefrom`.`Edge` AS `Edge`,`graphedge`.`EdgeCaption` AS `EdgeCaption`,`graphnodefrom`.`NodeTo` AS `NodeTo`,`graphnodeto`.`NodeToCaption` AS `NodeToCaption` from ((`graphnodefrom` join `graphedge`) join `graphnodeto`) where ((`graphnodefrom`.`ID` = `graphedge`.`ID`) and (`graphnodefrom`.`ID` = `graphnodeto`.`ID`) and (`graphedge`.`ID` = `graphnodeto`.`ID`)) order by `graphnodefrom`.`ID`;

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
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Graph`
--
ALTER TABLE `Graph`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Listing`
--
ALTER TABLE `Listing`
  ADD PRIMARY KEY (`PubID`),
  ADD UNIQUE KEY `PubID` (`PubID`),
  ADD KEY `SellerID` (`SellerID`);

--
-- Indexes for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `JSESSIONID` (`JSESSIONID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD PRIMARY KEY (`BuyerID`,`SellerID`,`PubID`),
  ADD KEY `PubID` (`PubID`),
  ADD KEY `SellerID` (`SellerID`);

--
-- Indexes for table `Unactivated`
--
ALTER TABLE `Unactivated`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `UserID` (`UserID`),
  ADD UNIQUE KEY `TokenString` (`TokenString`);

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
-- AUTO_INCREMENT for table `Entity`
--
ALTER TABLE `Entity`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `Graph`
--
ALTER TABLE `Graph`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `Listing`
--
ALTER TABLE `Listing`
  MODIFY `PubID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Unactivated`
--
ALTER TABLE `Unactivated`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `UserID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Admin`
--
ALTER TABLE `Admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`);

--
-- Constraints for table `Ban`
--
ALTER TABLE `Ban`
  ADD CONSTRAINT `ban_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`);

--
-- Constraints for table `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`),
  ADD CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`);

--
-- Constraints for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  ADD CONSTRAINT `loginsessions_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`);

--
-- Constraints for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`PubID`) REFERENCES `Listing` (`PubID`),
  ADD CONSTRAINT `transaction_ibfk_4` FOREIGN KEY (`BuyerID`) REFERENCES `User` (`UserID`),
  ADD CONSTRAINT `transaction_ibfk_5` FOREIGN KEY (`SellerID`) REFERENCES `User` (`UserID`);

--
-- Constraints for table `Unactivated`
--
ALTER TABLE `Unactivated`
  ADD CONSTRAINT `unactivated_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `User` (`UserID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
