-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2016 at 04:52 PM
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
  `Attribute` text NOT NULL,
  `Value` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Entity`
--

INSERT INTO `Entity` (`ID`, `EntityID`, `Attribute`, `Value`) VALUES
(1, 'P1', 'Class', 'Node'),
(2, 'P1', 'Type', 'Publication'),
(3, 'P1', 'Caption', 'Crawling Hidden Objects with kNN Queries.'),
(4, 'A1', 'Class', 'Node'),
(5, 'A1', 'Type', 'Author'),
(6, 'A1', 'Caption', 'Hui Yan'),
(7, 'V1', 'Class', 'Node'),
(8, 'V1', 'Type', 'Venue'),
(9, 'V1', 'Caption', 'IEEE Trans. Knowl. Data Eng.'),
(10, 'S1', 'Class', 'Node'),
(11, 'S1', 'Type', 'School'),
(12, 'S1', 'Caption', 'Universität Linköping'),
(13, 'A2', 'Class', 'Node'),
(14, 'A2', 'Type', 'Author'),
(15, 'A2', 'Caption', 'Andrzej Bednarski'),
(16, 'P2', 'Class', 'Node'),
(17, 'P2', 'Type', 'Publication'),
(18, 'P2', 'Caption', 'A dynamic programming approach to optimal retargetable code generation for irregular architectures.'),
(19, 'A3', 'Class', 'Node'),
(20, 'A3', 'Type', 'Author'),
(21, 'A3', 'Caption', 'Zhiguo Gong'),
(22, 'E1', 'Class', 'Edge'),
(23, 'E1', 'Type', 'DirectLink'),
(24, 'E1', 'Caption', 'authored by'),
(25, 'E2', 'Class', 'Edge'),
(26, 'E2', 'Type', 'DirectLink'),
(27, 'E2', 'Caption', 'affiliated in'),
(28, 'E3', 'Class', 'Edge'),
(29, 'E3', 'Type', 'DirectLink'),
(30, 'E3', 'Caption', 'authored by'),
(31, 'E4', 'Class', 'Edge'),
(32, 'E4', 'Type', 'DirectLink'),
(33, 'E4', 'Caption', 'authored by'),
(34, 'E5', 'Class', 'Edge'),
(35, 'E5', 'Type', 'DirectLink'),
(36, 'E5', 'Caption', 'published in');

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
-- Table structure for table `Listing`
--

CREATE TABLE IF NOT EXISTS `Listing` (
  `PubID` bigint(20) unsigned NOT NULL,
  `Title` tinytext NOT NULL,
  `Authors` text,
  `Editors` text,
  `Type` tinytext NOT NULL,
  `Year` smallint(6) NOT NULL,
  `Venue` tinytext,
  `SellerID` bigint(20) unsigned NOT NULL,
  `Picture` tinytext NOT NULL,
  `Price` smallint(5) unsigned NOT NULL,
  `Status` tinyint(1) NOT NULL,
  `SoldCount` int(11) NOT NULL DEFAULT '0',
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
  `JSESSIONID` int(11) NOT NULL
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
  `Nickname` tinytext  NULL,
  `Firstname` tinytext  NULL,
  `Lastname` tinytext  NULL,
  `Email` tinytext NOT NULL,
  `NewEmail` tinytext  NULL,
  `Birthyear` smallint(6)  NULL,
  `Address` text NOT NULL,
  `CardNumber` tinytext NOT NULL,
  `TokenString` varchar(20)  NULL
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
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
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



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
