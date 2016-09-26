-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 24, 2016 at 07:01 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=729 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Entity`
--

INSERT INTO `Entity` (`ID`, `EntityID`, `Class`, `Type`, `Caption`) VALUES
(1, 'P1', 'Node', 'Publication', 'Google and the Digital Divide: The Bias of Online Knowledge.'),
(2, 'A1', 'Node', 'Author', 'Lynette Kvasny'),
(3, 'E1', 'Edge', 'DirectedLink', 'authored by'),
(4, 'V1', 'Node', 'Venue', 'JASIST'),
(5, 'E2', 'Edge', 'DirectedLink', 'published in'),
(6, 'P2', 'Node', 'Publication', 'Globally Linear Connection Method.'),
(7, 'A2', 'Node', 'Author', 'Stefan Brüning'),
(8, 'E3', 'Edge', 'DirectedLink', 'authored by'),
(9, 'V2', 'Node', 'Venue', 'New Generation Comput.'),
(10, 'E4', 'Edge', 'DirectedLink', 'published in'),
(11, 'P3', 'Node', 'Publication', 'Seeing the point of politics: exploring the use of CSAV techniques as aids to understanding the content of political debates in the Scottish Parliament.'),
(12, 'A3', 'Node', 'Author', 'Alastair Renton'),
(13, 'E5', 'Edge', 'DirectedLink', 'authored by'),
(14, 'V3', 'Node', 'Venue', 'Artif. Intell. Law'),
(15, 'E6', 'Edge', 'DirectedLink', 'published in'),
(16, 'P4', 'Node', 'Publication', 'An alternative approach to comprehensive Gröbner bases.'),
(17, 'A4', 'Node', 'Author', 'Akira Suzuki'),
(18, 'E7', 'Edge', 'DirectedLink', 'authored by'),
(19, 'A5', 'Node', 'Author', 'Yosuke Sato'),
(20, 'E8', 'Edge', 'DirectedLink', 'authored by'),
(21, 'P5', 'Node', 'Publication', 'Calibration free visually controlled manipulation of parts in a robotic manufacturing workcell.'),
(22, 'A6', 'Node', 'Author', 'Bijoy K. Ghosh'),
(23, 'E9', 'Edge', 'DirectedLink', 'authored by'),
(24, 'A7', 'Node', 'Author', 'Tzyh Jong Tarn'),
(25, 'E10', 'Edge', 'DirectedLink', 'authored by'),
(26, 'A8', 'Node', 'Author', 'Ning Xi'),
(27, 'E11', 'Edge', 'DirectedLink', 'authored by'),
(28, 'A9', 'Node', 'Author', 'Zhenyu Yu'),
(29, 'E12', 'Edge', 'DirectedLink', 'authored by'),
(30, 'A10', 'Node', 'Author', 'Di Xiao'),
(31, 'E13', 'Edge', 'DirectedLink', 'authored by'),
(32, 'P6', 'Node', 'Publication', 'RAS Member Survey [President''s Message].'),
(33, 'A11', 'Node', 'Author', 'David E. Orin'),
(34, 'E14', 'Edge', 'DirectedLink', 'authored by'),
(35, 'V4', 'Node', 'Venue', 'IEEE Robot. Automat. Mag.'),
(36, 'E15', 'Edge', 'DirectedLink', 'published in'),
(37, 'P7', 'Node', 'Publication', 'On-board the satellite interference detection with imperfect signal cancellation.'),
(38, 'A12', 'Node', 'Author', 'Christos Politis'),
(39, 'E16', 'Edge', 'DirectedLink', 'authored by'),
(40, 'A13', 'Node', 'Author', 'Sina Maleki'),
(41, 'E17', 'Edge', 'DirectedLink', 'authored by'),
(42, 'A14', 'Node', 'Author', 'Christos Tsinos'),
(43, 'E18', 'Edge', 'DirectedLink', 'authored by'),
(44, 'A15', 'Node', 'Author', 'Symeon Chatzinotas'),
(45, 'E19', 'Edge', 'DirectedLink', 'authored by'),
(46, 'A16', 'Node', 'Author', 'Björn E. Ottersten'),
(47, 'E20', 'Edge', 'DirectedLink', 'authored by'),
(48, 'P8', 'Node', 'Publication', 'Split-Row: A Reduced Complexity, High Throughput LDPC Decoder Architecture.'),
(49, 'A17', 'Node', 'Author', 'Tinoosh Mohsenin'),
(50, 'E21', 'Edge', 'DirectedLink', 'authored by'),
(51, 'A18', 'Node', 'Author', 'Bevan M. Baas'),
(52, 'E22', 'Edge', 'DirectedLink', 'authored by'),
(53, 'P9', 'Node', 'Publication', 'MOGE: GP classification problem decomposition using multi-objective optimization.'),
(54, 'A19', 'Node', 'Author', 'Andrew R. McIntyre'),
(55, 'E23', 'Edge', 'DirectedLink', 'authored by'),
(56, 'A20', 'Node', 'Author', 'Malcolm I. Heywood'),
(57, 'E24', 'Edge', 'DirectedLink', 'authored by'),
(58, 'P10', 'Node', 'Publication', 'Shi Threshold Arrangement.'),
(59, 'A21', 'Node', 'Author', 'Seunghyun Seo'),
(60, 'E25', 'Edge', 'DirectedLink', 'authored by'),
(61, 'V5', 'Node', 'Venue', 'Electr. J. Comb.'),
(62, 'E26', 'Edge', 'DirectedLink', 'published in'),
(63, 'P11', 'Node', 'Publication', '(S, U)-integral.'),
(64, 'A22', 'Node', 'Author', 'Erich-Peter Klement'),
(65, 'E27', 'Edge', 'DirectedLink', 'authored by'),
(66, 'A23', 'Node', 'Author', 'Radko Mesiar'),
(67, 'E28', 'Edge', 'DirectedLink', 'authored by'),
(68, 'A24', 'Node', 'Author', 'Endre Pap'),
(69, 'E29', 'Edge', 'DirectedLink', 'authored by'),
(70, 'P12', 'Node', 'Publication', 'Anti-Counterfeiting: Mixing the Physical and the Digital World.'),
(71, 'A25', 'Node', 'Author', 'Darko Kirovski'),
(72, 'E30', 'Edge', 'DirectedLink', 'authored by'),
(73, 'P13', 'Node', 'Publication', 'Joint source-channel coding via hybrid coding.'),
(74, 'A26', 'Node', 'Author', 'Paolo Minero'),
(75, 'E31', 'Edge', 'DirectedLink', 'authored by'),
(76, 'A27', 'Node', 'Author', 'Sung Hoon Lim'),
(77, 'E32', 'Edge', 'DirectedLink', 'authored by'),
(78, 'A28', 'Node', 'Author', 'Young-Han Kim 0001'),
(79, 'E33', 'Edge', 'DirectedLink', 'authored by'),
(80, 'P14', 'Node', 'Publication', 'Tutorial on the internet of everything.'),
(81, 'A29', 'Node', 'Author', 'Opher Etzion'),
(82, 'E34', 'Edge', 'DirectedLink', 'authored by'),
(83, 'A30', 'Node', 'Author', 'Fabiana Fournier'),
(84, 'E35', 'Edge', 'DirectedLink', 'authored by'),
(85, 'A31', 'Node', 'Author', 'Sarit Arcushin'),
(86, 'E36', 'Edge', 'DirectedLink', 'authored by'),
(87, 'P15', 'Node', 'Publication', 'Waiting-Time Distribution of M/DN/1 Queues Through Numerical Laplace Inversion.'),
(88, 'A32', 'Node', 'Author', 'John F. Shortle'),
(89, 'E37', 'Edge', 'DirectedLink', 'authored by'),
(90, 'A33', 'Node', 'Author', 'Martin J. Fischer'),
(91, 'E38', 'Edge', 'DirectedLink', 'authored by'),
(92, 'A34', 'Node', 'Author', 'Percy H. Brill'),
(93, 'E39', 'Edge', 'DirectedLink', 'authored by'),
(94, 'V6', 'Node', 'Venue', 'INFORMS Journal on Computing'),
(95, 'E40', 'Edge', 'DirectedLink', 'published in'),
(96, 'P16', 'Node', 'Publication', 'Home Page'),
(97, 'A35', 'Node', 'Author', 'Christian Erich Elger'),
(98, 'E41', 'Edge', 'DirectedLink', 'authored by'),
(99, 'P17', 'Node', 'Publication', 'Automatic document preparation by interacting GIS software packages using office programs.'),
(100, 'A36', 'Node', 'Author', 'Ibrahim Baz'),
(101, 'E42', 'Edge', 'DirectedLink', 'authored by'),
(102, 'A37', 'Node', 'Author', 'Abdurrahman Geymen'),
(103, 'E43', 'Edge', 'DirectedLink', 'authored by'),
(104, 'V7', 'Node', 'Venue', 'Advances in Engineering Software'),
(105, 'E44', 'Edge', 'DirectedLink', 'published in'),
(106, 'P18', 'Node', 'Publication', 'An Object Model for Conventional Operating Systems.'),
(107, 'A38', 'Node', 'Author', 'Prasun Dewan'),
(108, 'E45', 'Edge', 'DirectedLink', 'authored by'),
(109, 'A39', 'Node', 'Author', 'Eric Vasilik'),
(110, 'E46', 'Edge', 'DirectedLink', 'authored by'),
(111, 'V8', 'Node', 'Venue', 'Computing Systems'),
(112, 'E47', 'Edge', 'DirectedLink', 'published in'),
(113, 'P19', 'Node', 'Publication', 'Brewing Analytics Quality for Cloud Performance.'),
(114, 'A40', 'Node', 'Author', 'Li Chen'),
(115, 'E48', 'Edge', 'DirectedLink', 'authored by'),
(116, 'A41', 'Node', 'Author', 'Pooja Jain'),
(117, 'E49', 'Edge', 'DirectedLink', 'authored by'),
(118, 'A42', 'Node', 'Author', 'Kingsum Chow'),
(119, 'E50', 'Edge', 'DirectedLink', 'authored by'),
(120, 'A43', 'Node', 'Author', 'Emad Guirguis'),
(121, 'E51', 'Edge', 'DirectedLink', 'authored by'),
(122, 'A44', 'Node', 'Author', 'Tony Wu'),
(123, 'E52', 'Edge', 'DirectedLink', 'authored by'),
(124, 'V9', 'Node', 'Venue', 'CoRR'),
(125, 'E53', 'Edge', 'DirectedLink', 'published in'),
(126, 'P20', 'Node', 'Publication', 'Deeply nested IF-THEN-ELSE''s.'),
(127, 'A45', 'Node', 'Author', 'Michael H. Rosenbloom'),
(128, 'E54', 'Edge', 'DirectedLink', 'authored by'),
(129, 'V10', 'Node', 'Venue', 'SIGPLAN Notices'),
(130, 'E55', 'Edge', 'DirectedLink', 'published in'),
(131, 'P21', 'Node', 'Publication', 'New Results on the Complexity of the Max- and Min-Rep Problems.'),
(132, 'A46', 'Node', 'Author', 'Robert Ganian'),
(133, 'E56', 'Edge', 'DirectedLink', 'authored by'),
(134, 'P22', 'Node', 'Publication', 'A New Principle for Information Storage in an Enzymatic Pathway Model.'),
(135, 'A47', 'Node', 'Author', 'Bruno Delord'),
(136, 'E57', 'Edge', 'DirectedLink', 'authored by'),
(137, 'A48', 'Node', 'Author', 'Hugues Berry'),
(138, 'E58', 'Edge', 'DirectedLink', 'authored by'),
(139, 'A49', 'Node', 'Author', 'Emmanuel Guigon'),
(140, 'E59', 'Edge', 'DirectedLink', 'authored by'),
(141, 'A50', 'Node', 'Author', 'Stéphane Genet'),
(142, 'E60', 'Edge', 'DirectedLink', 'authored by'),
(143, 'V11', 'Node', 'Venue', 'PLoS Computational Biology'),
(144, 'E61', 'Edge', 'DirectedLink', 'published in'),
(145, 'P23', 'Node', 'Publication', 'Software engineering challenges of the "Net" generation.'),
(146, 'A51', 'Node', 'Author', 'Hossein Saiedian'),
(147, 'E62', 'Edge', 'DirectedLink', 'authored by'),
(148, 'V12', 'Node', 'Venue', 'Journal of Systems and Software'),
(149, 'E63', 'Edge', 'DirectedLink', 'published in'),
(150, 'P24', 'Node', 'Publication', 'Zur Komplexität von Überführungsfunktionen in Zellularräumen.'),
(151, 'S1', 'Node', 'School', 'Universität Karlsruhe'),
(152, 'A52', 'Node', 'Author', 'Heinrich Rust'),
(153, 'E64', 'Edge', 'DirectedLink', 'authored by'),
(154, 'E65', 'Edge', 'DirectedLink', 'affiliated in'),
(155, 'P25', 'Node', 'Publication', 'Predicting Phenotypic Diversity and the Underlying Quantitative Molecular Transitions.'),
(156, 'A53', 'Node', 'Author', 'Claudiu A. Giurumescu'),
(157, 'E66', 'Edge', 'DirectedLink', 'authored by'),
(158, 'A54', 'Node', 'Author', 'Paul W. Sternberg'),
(159, 'E67', 'Edge', 'DirectedLink', 'authored by'),
(160, 'A55', 'Node', 'Author', 'Anand R. Asthagiri'),
(161, 'E68', 'Edge', 'DirectedLink', 'authored by'),
(162, 'V13', 'Node', 'Venue', 'PLoS Computational Biology'),
(163, 'E69', 'Edge', 'DirectedLink', 'published in'),
(164, 'P26', 'Node', 'Publication', 'Parametric pattern router.'),
(165, 'A56', 'Node', 'Author', 'Tetsuo Asano'),
(166, 'E70', 'Edge', 'DirectedLink', 'authored by'),
(167, 'P27', 'Node', 'Publication', 'DPLfw: a framework for variable content document generation.'),
(168, 'A57', 'Node', 'Author', 'Abel Gómez'),
(169, 'E71', 'Edge', 'DirectedLink', 'authored by'),
(170, 'A58', 'Node', 'Author', 'María del Carmen Penadés'),
(171, 'E72', 'Edge', 'DirectedLink', 'authored by'),
(172, 'A59', 'Node', 'Author', 'José H. Canós'),
(173, 'E73', 'Edge', 'DirectedLink', 'authored by'),
(174, 'A60', 'Node', 'Author', 'Marcos R. S. Borges'),
(175, 'E74', 'Edge', 'DirectedLink', 'authored by'),
(176, 'A61', 'Node', 'Author', 'Manuel Llavador'),
(177, 'E75', 'Edge', 'DirectedLink', 'authored by'),
(178, 'P28', 'Node', 'Publication', 'Return on Investment of Software Product Line Traceability in the Short, Mid and Long Term.'),
(179, 'A62', 'Node', 'Author', 'Zineb Mcharfi'),
(180, 'E76', 'Edge', 'DirectedLink', 'authored by'),
(181, 'A63', 'Node', 'Author', 'Bouchra El Asri'),
(182, 'E77', 'Edge', 'DirectedLink', 'authored by'),
(183, 'A64', 'Node', 'Author', 'Ikram Dehmouch'),
(184, 'E78', 'Edge', 'DirectedLink', 'authored by'),
(185, 'A65', 'Node', 'Author', 'Asmaa Baya'),
(186, 'E79', 'Edge', 'DirectedLink', 'authored by'),
(187, 'A66', 'Node', 'Author', 'Abdelaziz Kriouile'),
(188, 'E80', 'Edge', 'DirectedLink', 'authored by'),
(189, 'P29', 'Node', 'Publication', 'Energy and Link Quality Based Routing for Data Gathering Tree in Wireless Sensor Networks Under TINYOS - 2.X'),
(190, 'A67', 'Node', 'Author', 'A. Sivagami'),
(191, 'E81', 'Edge', 'DirectedLink', 'authored by'),
(192, 'A68', 'Node', 'Author', 'K. Pavai'),
(193, 'E82', 'Edge', 'DirectedLink', 'authored by'),
(194, 'A69', 'Node', 'Author', 'D. Sridharan'),
(195, 'E83', 'Edge', 'DirectedLink', 'authored by'),
(196, 'A70', 'Node', 'Author', 'S. A. V. Satya Murty'),
(197, 'E84', 'Edge', 'DirectedLink', 'authored by'),
(198, 'V14', 'Node', 'Venue', 'CoRR'),
(199, 'E85', 'Edge', 'DirectedLink', 'published in'),
(200, 'P30', 'Node', 'Publication', 'A multipath interference cancellation technique for WCDMA downlink receivers.'),
(201, 'A71', 'Node', 'Author', 'Derong Liu'),
(202, 'E86', 'Edge', 'DirectedLink', 'authored by'),
(203, 'A72', 'Node', 'Author', 'Hossein Zare'),
(204, 'E87', 'Edge', 'DirectedLink', 'authored by'),
(205, 'V15', 'Node', 'Venue', 'Int. J. Communication Systems'),
(206, 'E88', 'Edge', 'DirectedLink', 'published in'),
(207, 'P31', 'Node', 'Publication', 'Widely Tunable 4th Order Switched Gm-C Band-Pass Filter Based on N-Path Filters.'),
(208, 'A73', 'Node', 'Author', 'Milad Darvishi'),
(209, 'E89', 'Edge', 'DirectedLink', 'authored by'),
(210, 'A74', 'Node', 'Author', 'Ronan A. R. van der Zee'),
(211, 'E90', 'Edge', 'DirectedLink', 'authored by'),
(212, 'A75', 'Node', 'Author', 'Eric A. M. Klumperink'),
(213, 'E91', 'Edge', 'DirectedLink', 'authored by'),
(214, 'A76', 'Node', 'Author', 'Bram Nauta'),
(215, 'E92', 'Edge', 'DirectedLink', 'authored by'),
(216, 'V16', 'Node', 'Venue', 'J. Solid-State Circuits'),
(217, 'E93', 'Edge', 'DirectedLink', 'published in'),
(218, 'P32', 'Node', 'Publication', 'Signal Estimation Using Wavelet Analysis of Solution Monitoring Data for Nuclear Safeguards.'),
(219, 'A77', 'Node', 'Author', 'Tom Burr'),
(220, 'E94', 'Edge', 'DirectedLink', 'authored by'),
(221, 'A78', 'Node', 'Author', 'Claire Longo'),
(222, 'E95', 'Edge', 'DirectedLink', 'authored by'),
(223, 'V17', 'Node', 'Venue', 'Axioms'),
(224, 'E96', 'Edge', 'DirectedLink', 'published in'),
(225, 'P33', 'Node', 'Publication', 'Refinement of objects and operations in Object-Z.'),
(226, 'A79', 'Node', 'Author', 'John Derrick'),
(227, 'E97', 'Edge', 'DirectedLink', 'authored by'),
(228, 'A80', 'Node', 'Author', 'Eerke A. Boiten'),
(229, 'E98', 'Edge', 'DirectedLink', 'authored by'),
(230, 'P34', 'Node', 'Publication', 'Fast rate distortion optimization for the emerging HEVC standard.'),
(231, 'A81', 'Node', 'Author', 'Michele Belotti Cassa'),
(232, 'E99', 'Edge', 'DirectedLink', 'authored by'),
(233, 'A82', 'Node', 'Author', 'Matteo Naccari'),
(234, 'E100', 'Edge', 'DirectedLink', 'authored by'),
(235, 'A83', 'Node', 'Author', 'Fernando Pereira 0001'),
(236, 'E101', 'Edge', 'DirectedLink', 'authored by'),
(237, 'P35', 'Node', 'Publication', 'A stochastic opinion dynamics model with multiple contents.'),
(238, 'A84', 'Node', 'Author', 'Julio Cesar Louzada Pinto'),
(239, 'E102', 'Edge', 'DirectedLink', 'authored by'),
(240, 'A85', 'Node', 'Author', 'Tijani Chahed'),
(241, 'E103', 'Edge', 'DirectedLink', 'authored by'),
(242, 'A86', 'Node', 'Author', 'Jérémie Jakubowicz'),
(243, 'E104', 'Edge', 'DirectedLink', 'authored by'),
(244, 'P36', 'Node', 'Publication', 'Hybrid decomposition heuristics for solving large-scale scheduling problems in semiconductor wafer fabrication.'),
(245, 'A87', 'Node', 'Author', 'Karthik Sourirajan'),
(246, 'E105', 'Edge', 'DirectedLink', 'authored by'),
(247, 'A88', 'Node', 'Author', 'Reha Uzsoy'),
(248, 'E106', 'Edge', 'DirectedLink', 'authored by'),
(249, 'V18', 'Node', 'Venue', 'J. Scheduling'),
(250, 'E107', 'Edge', 'DirectedLink', 'published in'),
(251, 'P37', 'Node', 'Publication', 'Home Page'),
(252, 'A89', 'Node', 'Author', 'Fu-Chiang Tsui'),
(253, 'E108', 'Edge', 'DirectedLink', 'authored by'),
(254, 'A90', 'Node', 'Author', 'Fuchiang (Rich) Tsui'),
(255, 'E109', 'Edge', 'DirectedLink', 'authored by'),
(256, 'P38', 'Node', 'Publication', 'Security in Wireless Actor & Sensor Networks (WASN): Towards A Hierarchical Re-Keying Design.'),
(257, 'A91', 'Node', 'Author', 'Fei Hu'),
(258, 'E110', 'Edge', 'DirectedLink', 'authored by'),
(259, 'A92', 'Node', 'Author', 'Xiaojun Cao'),
(260, 'E111', 'Edge', 'DirectedLink', 'authored by'),
(261, 'P39', 'Node', 'Publication', 'An ECG-on-Chip With 535 nW/Channel Integrated Lossless Data Compressor for Wireless Sensors.'),
(262, 'A93', 'Node', 'Author', 'Chacko John Deepu'),
(263, 'E112', 'Edge', 'DirectedLink', 'authored by'),
(264, 'A94', 'Node', 'Author', 'Xiaoyang Zhang'),
(265, 'E113', 'Edge', 'DirectedLink', 'authored by'),
(266, 'A95', 'Node', 'Author', 'Wen-Sin Liew'),
(267, 'E114', 'Edge', 'DirectedLink', 'authored by'),
(268, 'A96', 'Node', 'Author', 'David Liang Tai Wong'),
(269, 'E115', 'Edge', 'DirectedLink', 'authored by'),
(270, 'A97', 'Node', 'Author', 'Yong Lian'),
(271, 'E116', 'Edge', 'DirectedLink', 'authored by'),
(272, 'V19', 'Node', 'Venue', 'J. Solid-State Circuits'),
(273, 'E117', 'Edge', 'DirectedLink', 'published in'),
(274, 'P40', 'Node', 'Publication', 'Collaborative Filtering Based on Choosing a Different Number of Neighbors for Each User.'),
(275, 'A98', 'Node', 'Author', 'Antonio Hernando'),
(276, 'E118', 'Edge', 'DirectedLink', 'authored by'),
(277, 'A99', 'Node', 'Author', 'Jesús Bobadilla'),
(278, 'E119', 'Edge', 'DirectedLink', 'authored by'),
(279, 'A100', 'Node', 'Author', 'Francisco Serradilla'),
(280, 'E120', 'Edge', 'DirectedLink', 'authored by'),
(281, 'P41', 'Node', 'Publication', 'Tree Models and (Labeled) Categorial Grammar.'),
(282, 'A101', 'Node', 'Author', 'Yde Venema'),
(283, 'E121', 'Edge', 'DirectedLink', 'authored by'),
(284, 'V20', 'Node', 'Venue', 'Journal of Logic, Language and Information'),
(285, 'E122', 'Edge', 'DirectedLink', 'published in'),
(286, 'P42', 'Node', 'Publication', 'Users'' experiences of an emergency department patient admission predictive tool: A qualitative evaluation.'),
(287, 'A102', 'Node', 'Author', 'Melanie Jessup'),
(288, 'E123', 'Edge', 'DirectedLink', 'authored by'),
(289, 'A103', 'Node', 'Author', 'Julia Crilly'),
(290, 'E124', 'Edge', 'DirectedLink', 'authored by'),
(291, 'A104', 'Node', 'Author', 'Justin Boyle'),
(292, 'E125', 'Edge', 'DirectedLink', 'authored by'),
(293, 'A105', 'Node', 'Author', 'Marianne Wallis'),
(294, 'E126', 'Edge', 'DirectedLink', 'authored by'),
(295, 'A106', 'Node', 'Author', 'James Lind'),
(296, 'E127', 'Edge', 'DirectedLink', 'authored by'),
(297, 'A107', 'Node', 'Author', 'David Green'),
(298, 'E128', 'Edge', 'DirectedLink', 'authored by'),
(299, 'A108', 'Node', 'Author', 'Gerard Fitzgerald'),
(300, 'E129', 'Edge', 'DirectedLink', 'authored by'),
(301, 'V21', 'Node', 'Venue', 'Health Informatics Journal'),
(302, 'E130', 'Edge', 'DirectedLink', 'published in'),
(303, 'P43', 'Node', 'Publication', 'Energy efficiency analysis of a cooperative scheme for wireless local area networks.'),
(304, 'A109', 'Node', 'Author', 'Tatjana Predojev'),
(305, 'E131', 'Edge', 'DirectedLink', 'authored by'),
(306, 'A110', 'Node', 'Author', 'Jesus Alonso-Zarate'),
(307, 'E132', 'Edge', 'DirectedLink', 'authored by'),
(308, 'A111', 'Node', 'Author', 'Luis Alonso 0001'),
(309, 'E133', 'Edge', 'DirectedLink', 'authored by'),
(310, 'A112', 'Node', 'Author', 'Christos V. Verikoukis'),
(311, 'E134', 'Edge', 'DirectedLink', 'authored by'),
(312, 'P44', 'Node', 'Publication', 'Issues and challenges in orbital-free density functional calculations.'),
(313, 'A113', 'Node', 'Author', 'Valentin V. Karasiev'),
(314, 'E135', 'Edge', 'DirectedLink', 'authored by'),
(315, 'A114', 'Node', 'Author', 'Samuel B. Trickey'),
(316, 'E136', 'Edge', 'DirectedLink', 'authored by'),
(317, 'V22', 'Node', 'Venue', 'Computer Physics Communications'),
(318, 'E137', 'Edge', 'DirectedLink', 'published in'),
(319, 'P45', 'Node', 'Publication', 'QueryAgent: A General Query Processing Tool for Sensor Networks.'),
(320, 'A115', 'Node', 'Author', 'Weisong Shi'),
(321, 'E138', 'Edge', 'DirectedLink', 'authored by'),
(322, 'A116', 'Node', 'Author', 'Sivakumar Sellamuthu'),
(323, 'E139', 'Edge', 'DirectedLink', 'authored by'),
(324, 'A117', 'Node', 'Author', 'Kewei Sha'),
(325, 'E140', 'Edge', 'DirectedLink', 'authored by'),
(326, 'A118', 'Node', 'Author', 'Loren Schwiebert'),
(327, 'E141', 'Edge', 'DirectedLink', 'authored by'),
(328, 'P46', 'Node', 'Publication', 'A Tuning Technique for Switched-Capacitor Circuits.'),
(329, 'A119', 'Node', 'Author', 'Mustafa Keskin'),
(330, 'E142', 'Edge', 'DirectedLink', 'authored by'),
(331, 'A120', 'Node', 'Author', 'Nurcan Keskin'),
(332, 'E143', 'Edge', 'DirectedLink', 'authored by'),
(333, 'P47', 'Node', 'Publication', 'Iteratively reweighted blind deconvolution.'),
(334, 'A121', 'Node', 'Author', 'Brandoch Calef'),
(335, 'E144', 'Edge', 'DirectedLink', 'authored by'),
(336, 'P48', 'Node', 'Publication', 'An accelerated conjugate gradient algorithm with guaranteed descent and conjugacy conditions for unconstrained optimization.'),
(337, 'A122', 'Node', 'Author', 'Neculai Andrei'),
(338, 'E145', 'Edge', 'DirectedLink', 'authored by'),
(339, 'V23', 'Node', 'Venue', 'Optimization Methods and Software'),
(340, 'E146', 'Edge', 'DirectedLink', 'published in'),
(341, 'P49', 'Node', 'Publication', 'Fading rate and multipath delay effects on signal acquisition using sliding correlation in a multi-user environment.'),
(342, 'A123', 'Node', 'Author', 'Hu Lan'),
(343, 'E147', 'Edge', 'DirectedLink', 'authored by'),
(344, 'A124', 'Node', 'Author', 'Cyril J. Burkley'),
(345, 'E148', 'Edge', 'DirectedLink', 'authored by'),
(346, 'V24', 'Node', 'Venue', 'Wireless Personal Communications'),
(347, 'E149', 'Edge', 'DirectedLink', 'published in'),
(348, 'P50', 'Node', 'Publication', 'The COAST Object Database System.'),
(349, 'A125', 'Node', 'Author', 'Sven-Eric Lautemann'),
(350, 'E150', 'Edge', 'DirectedLink', 'authored by'),
(351, 'A126', 'Node', 'Author', 'Sabbas Apostolidis'),
(352, 'E151', 'Edge', 'DirectedLink', 'authored by'),
(353, 'A127', 'Node', 'Author', 'Alexander Doll'),
(354, 'E152', 'Edge', 'DirectedLink', 'authored by'),
(355, 'A128', 'Node', 'Author', 'Jan Haase'),
(356, 'E153', 'Edge', 'DirectedLink', 'authored by'),
(357, 'P51', 'Node', 'Publication', 'Repair of RAMs With Clustered Faults.'),
(358, 'A129', 'Node', 'Author', 'Bapiraju Vinnakota'),
(359, 'E154', 'Edge', 'DirectedLink', 'authored by'),
(360, 'A130', 'Node', 'Author', 'Jason Andrews'),
(361, 'E155', 'Edge', 'DirectedLink', 'authored by'),
(362, 'P52', 'Node', 'Publication', 'Dealing with missing values in large-scale studies: microarray data imputation and beyond.'),
(363, 'A131', 'Node', 'Author', 'Tero Aittokallio'),
(364, 'E156', 'Edge', 'DirectedLink', 'authored by'),
(365, 'V25', 'Node', 'Venue', 'Briefings in Bioinformatics'),
(366, 'E157', 'Edge', 'DirectedLink', 'published in'),
(367, 'P53', 'Node', 'Publication', 'Understanding and Reducing Web Delays.'),
(368, 'A132', 'Node', 'Author', 'Mazen Zari'),
(369, 'E158', 'Edge', 'DirectedLink', 'authored by'),
(370, 'A133', 'Node', 'Author', 'Hossein Saiedian'),
(371, 'E159', 'Edge', 'DirectedLink', 'authored by'),
(372, 'A134', 'Node', 'Author', 'Muhammad Naeem'),
(373, 'E160', 'Edge', 'DirectedLink', 'authored by'),
(374, 'V26', 'Node', 'Venue', 'IEEE Computer'),
(375, 'E161', 'Edge', 'DirectedLink', 'published in'),
(376, 'P54', 'Node', 'Publication', 'Konzeptionelle Metamodelle von IT-Governance-Referenzmodellen als Basis der Kombination und Integration in einer Multi-Modell-Umgebung.'),
(377, 'A135', 'Node', 'Author', 'Stefanie Alter'),
(378, 'E162', 'Edge', 'DirectedLink', 'authored by'),
(379, 'A136', 'Node', 'Author', 'Matthias Goeken'),
(380, 'E163', 'Edge', 'DirectedLink', 'authored by'),
(381, 'P55', 'Node', 'Publication', 'Development of Resources for a Bilingual Automatic Index System of Broadcast News in Basque and Spanish.'),
(382, 'A137', 'Node', 'Author', 'Germán Bordel'),
(383, 'E164', 'Edge', 'DirectedLink', 'authored by'),
(384, 'A138', 'Node', 'Author', 'Aitzol Ezeiza'),
(385, 'E165', 'Edge', 'DirectedLink', 'authored by'),
(386, 'A139', 'Node', 'Author', 'Karmele López de Ipiña'),
(387, 'E166', 'Edge', 'DirectedLink', 'authored by'),
(388, 'A140', 'Node', 'Author', 'M. Méndez'),
(389, 'E167', 'Edge', 'DirectedLink', 'authored by'),
(390, 'A141', 'Node', 'Author', 'Mikel Peñagarikano'),
(391, 'E168', 'Edge', 'DirectedLink', 'authored by'),
(392, 'A142', 'Node', 'Author', 'T. Rico'),
(393, 'E169', 'Edge', 'DirectedLink', 'authored by'),
(394, 'A143', 'Node', 'Author', 'C. Tovar'),
(395, 'E170', 'Edge', 'DirectedLink', 'authored by'),
(396, 'A144', 'Node', 'Author', 'Ekaitz Zulueta'),
(397, 'E171', 'Edge', 'DirectedLink', 'authored by'),
(398, 'P56', 'Node', 'Publication', 'A Logic of Arbitrary and Indefinite Objects.'),
(399, 'A145', 'Node', 'Author', 'Stuart C. Shapiro'),
(400, 'E172', 'Edge', 'DirectedLink', 'authored by'),
(401, 'P57', 'Node', 'Publication', 'End-to-end rate-distortion optimized mode selection for multiple description video coding.'),
(402, 'A146', 'Node', 'Author', 'Brian A. Heng'),
(403, 'E173', 'Edge', 'DirectedLink', 'authored by'),
(404, 'A147', 'Node', 'Author', 'John G. Apostolopoulos'),
(405, 'E174', 'Edge', 'DirectedLink', 'authored by'),
(406, 'A148', 'Node', 'Author', 'Jae S. Lim'),
(407, 'E175', 'Edge', 'DirectedLink', 'authored by'),
(408, 'P58', 'Node', 'Publication', 'Internet Improves Health Outcomes in Depression.'),
(409, 'A149', 'Node', 'Author', 'Gordana Culjak'),
(410, 'E176', 'Edge', 'DirectedLink', 'authored by'),
(411, 'A150', 'Node', 'Author', 'Mark Spranca'),
(412, 'E177', 'Edge', 'DirectedLink', 'authored by'),
(413, 'P59', 'Node', 'Publication', 'Fuzzy control of complex systems.'),
(414, 'A151', 'Node', 'Author', 'Mohammad Jamshidi'),
(415, 'E178', 'Edge', 'DirectedLink', 'authored by'),
(416, 'V27', 'Node', 'Venue', 'Soft Comput.'),
(417, 'E179', 'Edge', 'DirectedLink', 'published in'),
(418, 'P60', 'Node', 'Publication', 'Monotone Extensions of Boolean Data Sets.'),
(419, 'A152', 'Node', 'Author', 'Endre Boros'),
(420, 'E180', 'Edge', 'DirectedLink', 'authored by'),
(421, 'A153', 'Node', 'Author', 'Toshihide Ibaraki'),
(422, 'E181', 'Edge', 'DirectedLink', 'authored by'),
(423, 'A154', 'Node', 'Author', 'Kazuhisa Makino'),
(424, 'E182', 'Edge', 'DirectedLink', 'authored by'),
(425, 'P61', 'Node', 'Publication', 'An Extension of the FURIA Classification Algorithm to Low Quality Data.'),
(426, 'A155', 'Node', 'Author', 'Ana M. Palacios'),
(427, 'E183', 'Edge', 'DirectedLink', 'authored by'),
(428, 'A156', 'Node', 'Author', 'Luciano Sánchez'),
(429, 'E184', 'Edge', 'DirectedLink', 'authored by'),
(430, 'A157', 'Node', 'Author', 'Inés Couso'),
(431, 'E185', 'Edge', 'DirectedLink', 'authored by'),
(432, 'P62', 'Node', 'Publication', 'Randomness for free.'),
(433, 'A158', 'Node', 'Author', 'Krishnendu Chatterjee'),
(434, 'E186', 'Edge', 'DirectedLink', 'authored by'),
(435, 'A159', 'Node', 'Author', 'Laurent Doyen 0001'),
(436, 'E187', 'Edge', 'DirectedLink', 'authored by'),
(437, 'A160', 'Node', 'Author', 'Hugo Gimbert'),
(438, 'E188', 'Edge', 'DirectedLink', 'authored by'),
(439, 'A161', 'Node', 'Author', 'Thomas A. Henzinger'),
(440, 'E189', 'Edge', 'DirectedLink', 'authored by'),
(441, 'V28', 'Node', 'Venue', 'Inf. Comput.'),
(442, 'E190', 'Edge', 'DirectedLink', 'published in'),
(443, 'P63', 'Node', 'Publication', 'The tournament scheduling problem with absences.'),
(444, 'A162', 'Node', 'Author', 'Uwe Schauz'),
(445, 'E191', 'Edge', 'DirectedLink', 'authored by'),
(446, 'V29', 'Node', 'Venue', 'European Journal of Operational Research'),
(447, 'E192', 'Edge', 'DirectedLink', 'published in'),
(448, 'P64', 'Node', 'Publication', 'The Connected Scatterplot for Presenting Paired Time Series.'),
(449, 'A163', 'Node', 'Author', 'Steve Haroz'),
(450, 'E193', 'Edge', 'DirectedLink', 'authored by'),
(451, 'A164', 'Node', 'Author', 'Robert Kosara'),
(452, 'E194', 'Edge', 'DirectedLink', 'authored by'),
(453, 'A165', 'Node', 'Author', 'Steven L. Franconeri'),
(454, 'E195', 'Edge', 'DirectedLink', 'authored by'),
(455, 'V30', 'Node', 'Venue', 'IEEE Trans. Vis. Comput. Graph.'),
(456, 'E196', 'Edge', 'DirectedLink', 'published in'),
(457, 'P65', 'Node', 'Publication', 'Conditional information and information loss for flexible feature extraction.'),
(458, 'A166', 'Node', 'Author', 'Ryotaro Kamimura'),
(459, 'E197', 'Edge', 'DirectedLink', 'authored by'),
(460, 'P66', 'Node', 'Publication', 'Transformation-Based Bottom-Up Computation of the Well-Founded Model.'),
(461, 'A167', 'Node', 'Author', 'Stefan Brass'),
(462, 'E198', 'Edge', 'DirectedLink', 'authored by'),
(463, 'A168', 'Node', 'Author', 'Ulrich Zukowski'),
(464, 'E199', 'Edge', 'DirectedLink', 'authored by'),
(465, 'A169', 'Node', 'Author', 'Burkhard Freitag'),
(466, 'E200', 'Edge', 'DirectedLink', 'authored by'),
(467, 'P67', 'Node', 'Publication', 'System Refinement in Practice - Using a Formal Method to Modify Real-Life Knowledge.'),
(468, 'A170', 'Node', 'Author', 'Rainer Knauf'),
(469, 'E201', 'Edge', 'DirectedLink', 'authored by'),
(470, 'A171', 'Node', 'Author', 'Ilka Philippow'),
(471, 'E202', 'Edge', 'DirectedLink', 'authored by'),
(472, 'A172', 'Node', 'Author', 'Avelino J. Gonzalez'),
(473, 'E203', 'Edge', 'DirectedLink', 'authored by'),
(474, 'A173', 'Node', 'Author', 'Klaus P. Jantke'),
(475, 'E204', 'Edge', 'DirectedLink', 'authored by'),
(476, 'A174', 'Node', 'Author', 'Dirk Salecker'),
(477, 'E205', 'Edge', 'DirectedLink', 'authored by'),
(478, 'P68', 'Node', 'Publication', 'Robust 3-D/2-D registration of CT and MR to X-ray images based on gradient reconstruction.'),
(479, 'A175', 'Node', 'Author', 'Primoz Markelj'),
(480, 'E206', 'Edge', 'DirectedLink', 'authored by'),
(481, 'A176', 'Node', 'Author', 'Dejan Tomazevic'),
(482, 'E207', 'Edge', 'DirectedLink', 'authored by'),
(483, 'A177', 'Node', 'Author', 'Franjo Pernus'),
(484, 'E208', 'Edge', 'DirectedLink', 'authored by'),
(485, 'A178', 'Node', 'Author', 'Bostjan Likar'),
(486, 'E209', 'Edge', 'DirectedLink', 'authored by'),
(487, 'V31', 'Node', 'Venue', 'Int. J. Computer Assisted Radiology and Surgery'),
(488, 'E210', 'Edge', 'DirectedLink', 'published in'),
(489, 'P69', 'Node', 'Publication', 'Reducing Run Queue Contention in Shared Memory Multiprocessors.'),
(490, 'A179', 'Node', 'Author', 'Sivarama P. Dandamudi'),
(491, 'E211', 'Edge', 'DirectedLink', 'authored by'),
(492, 'V32', 'Node', 'Venue', 'IEEE Computer'),
(493, 'E212', 'Edge', 'DirectedLink', 'published in'),
(494, 'P70', 'Node', 'Publication', 'Health IT acceptance factors in long-term care facilities: A cross-sectional survey.'),
(495, 'A180', 'Node', 'Author', 'Ping Yu'),
(496, 'E213', 'Edge', 'DirectedLink', 'authored by'),
(497, 'A181', 'Node', 'Author', 'Haocheng Li'),
(498, 'E214', 'Edge', 'DirectedLink', 'authored by'),
(499, 'A182', 'Node', 'Author', 'Marie-Pierre Gagnon'),
(500, 'E215', 'Edge', 'DirectedLink', 'authored by'),
(501, 'V33', 'Node', 'Venue', 'I. J. Medical Informatics'),
(502, 'E216', 'Edge', 'DirectedLink', 'published in'),
(503, 'P71', 'Node', 'Publication', 'Predicting High-level Student Responses Using Conceptual Clustering.'),
(504, 'A183', 'Node', 'Author', 'Roberto S. Legaspi'),
(505, 'E217', 'Edge', 'DirectedLink', 'authored by'),
(506, 'A184', 'Node', 'Author', 'Raymund Sison'),
(507, 'E218', 'Edge', 'DirectedLink', 'authored by'),
(508, 'A185', 'Node', 'Author', 'Ken-ichi Fukui'),
(509, 'E219', 'Edge', 'DirectedLink', 'authored by'),
(510, 'A186', 'Node', 'Author', 'Masayuki Numao'),
(511, 'E220', 'Edge', 'DirectedLink', 'authored by'),
(512, 'P72', 'Node', 'Publication', 'On the performance of a discrete time RAKE.'),
(513, 'A187', 'Node', 'Author', 'Hatem Boujemaa'),
(514, 'E221', 'Edge', 'DirectedLink', 'authored by'),
(515, 'A188', 'Node', 'Author', 'Octavian Fratu'),
(516, 'E222', 'Edge', 'DirectedLink', 'authored by'),
(517, 'A189', 'Node', 'Author', 'Mohamed Siala'),
(518, 'E223', 'Edge', 'DirectedLink', 'authored by'),
(519, 'A190', 'Node', 'Author', 'Philippe Loubaton'),
(520, 'E224', 'Edge', 'DirectedLink', 'authored by'),
(521, 'P73', 'Node', 'Publication', 'A mobile environment for sketching-based skeleton generation.'),
(522, 'A191', 'Node', 'Author', 'Qingzheng Zheng'),
(523, 'E225', 'Edge', 'DirectedLink', 'authored by'),
(524, 'A192', 'Node', 'Author', 'Frederick W. B. Li'),
(525, 'E226', 'Edge', 'DirectedLink', 'authored by'),
(526, 'V34', 'Node', 'Venue', 'World Wide Web'),
(527, 'E227', 'Edge', 'DirectedLink', 'published in'),
(528, 'P74', 'Node', 'Publication', 'Two-Dimensional Ultrawideband Radar Imaging of a Target With Arbitrary Translation and Rotation.'),
(529, 'A193', 'Node', 'Author', 'Takuya Sakamoto'),
(530, 'E228', 'Edge', 'DirectedLink', 'authored by'),
(531, 'A194', 'Node', 'Author', 'Toru Sato'),
(532, 'E229', 'Edge', 'DirectedLink', 'authored by'),
(533, 'V35', 'Node', 'Venue', 'IEEE Trans. Geoscience and Remote Sensing'),
(534, 'E230', 'Edge', 'DirectedLink', 'published in'),
(535, 'P75', 'Node', 'Publication', 'Denial of service (DoS) attacks detection in MANETs using Bayesian classifiers.'),
(536, 'A195', 'Node', 'Author', 'M. Rmayti'),
(537, 'E231', 'Edge', 'DirectedLink', 'authored by'),
(538, 'A196', 'Node', 'Author', 'Youcef Begriche'),
(539, 'E232', 'Edge', 'DirectedLink', 'authored by'),
(540, 'A197', 'Node', 'Author', 'Rida Khatoun'),
(541, 'E233', 'Edge', 'DirectedLink', 'authored by'),
(542, 'A198', 'Node', 'Author', 'Lyes Khoukhi'),
(543, 'E234', 'Edge', 'DirectedLink', 'authored by'),
(544, 'A199', 'Node', 'Author', 'Dominique Gaïti'),
(545, 'E235', 'Edge', 'DirectedLink', 'authored by'),
(546, 'P76', 'Node', 'Publication', 'Classification of Aircraft Maneuvers for Fault Detection.'),
(547, 'A200', 'Node', 'Author', 'Nikunj C. Oza'),
(548, 'E236', 'Edge', 'DirectedLink', 'authored by'),
(549, 'A201', 'Node', 'Author', 'Kagan Tumer'),
(550, 'E237', 'Edge', 'DirectedLink', 'authored by'),
(551, 'A202', 'Node', 'Author', 'Irem Y. Tumer'),
(552, 'E238', 'Edge', 'DirectedLink', 'authored by'),
(553, 'A203', 'Node', 'Author', 'Edward M. Huff'),
(554, 'E239', 'Edge', 'DirectedLink', 'authored by'),
(555, 'P77', 'Node', 'Publication', 'On Computing Groebner Basis in the Rings of Differential Operators'),
(556, 'A204', 'Node', 'Author', 'Yao Sun'),
(557, 'E240', 'Edge', 'DirectedLink', 'authored by'),
(558, 'A205', 'Node', 'Author', 'Xiaodong Ma'),
(559, 'E241', 'Edge', 'DirectedLink', 'authored by'),
(560, 'A206', 'Node', 'Author', 'Dingkang Wang'),
(561, 'E242', 'Edge', 'DirectedLink', 'authored by'),
(562, 'V36', 'Node', 'Venue', 'CoRR'),
(563, 'E243', 'Edge', 'DirectedLink', 'published in'),
(564, 'P78', 'Node', 'Publication', 'Hybrid Global-Local Indexing for Efficient Peer-to-Peer Information Retrieval.'),
(565, 'A207', 'Node', 'Author', 'Chunqiang Tang'),
(566, 'E244', 'Edge', 'DirectedLink', 'authored by'),
(567, 'A208', 'Node', 'Author', 'Sandhya Dwarkadas'),
(568, 'E245', 'Edge', 'DirectedLink', 'authored by'),
(569, 'P79', 'Node', 'Publication', 'On stability of periodic solutions in non-homogeneous Hill''s equation.'),
(570, 'A209', 'Node', 'Author', 'Aurora Rodriguez'),
(571, 'E246', 'Edge', 'DirectedLink', 'authored by'),
(572, 'A210', 'Node', 'Author', 'Joaquin Collado'),
(573, 'E247', 'Edge', 'DirectedLink', 'authored by'),
(574, 'P80', 'Node', 'Publication', 'Proxy data transfer system with a stable control channel and dynamically changing data channels.'),
(575, 'A211', 'Node', 'Author', 'Akira Nagata'),
(576, 'E248', 'Edge', 'DirectedLink', 'authored by'),
(577, 'A212', 'Node', 'Author', 'Shinya Yamamura'),
(578, 'E249', 'Edge', 'DirectedLink', 'authored by'),
(579, 'A213', 'Node', 'Author', 'Masato Uchida'),
(580, 'E250', 'Edge', 'DirectedLink', 'authored by'),
(581, 'A214', 'Node', 'Author', 'Masato Tsuru'),
(582, 'E251', 'Edge', 'DirectedLink', 'authored by'),
(583, 'P81', 'Node', 'Publication', 'Forensic metrology: a new application field for measurement experts across techniques and ethics.'),
(584, 'A215', 'Node', 'Author', 'Alessandro Ferrero'),
(585, 'E252', 'Edge', 'DirectedLink', 'authored by'),
(586, 'A216', 'Node', 'Author', 'Veronica Scotti'),
(587, 'E253', 'Edge', 'DirectedLink', 'authored by'),
(588, 'V37', 'Node', 'Venue', 'IEEE Instrum. Meas. Mag.'),
(589, 'E254', 'Edge', 'DirectedLink', 'published in'),
(590, 'P82', 'Node', 'Publication', 'Perspectives Workshop: Theory and Practice of Argumentation Systems, 20.01. - 23.01.2008'),
(591, 'A217', 'Node', 'Author', 'Jürgen Dix'),
(592, 'E255', 'Edge', 'DirectedLink', 'edited by'),
(593, 'A218', 'Node', 'Author', 'Simon Parsons'),
(594, 'E256', 'Edge', 'DirectedLink', 'edited by'),
(595, 'A219', 'Node', 'Author', 'Henry Prakken'),
(596, 'E257', 'Edge', 'DirectedLink', 'edited by'),
(597, 'A220', 'Node', 'Author', 'Guillermo Ricardo Simari'),
(598, 'E258', 'Edge', 'DirectedLink', 'edited by'),
(599, 'P83', 'Node', 'Publication', 'ESPOON: Enforcing Encrypted Security Policies in Outsourced Environments.'),
(600, 'A221', 'Node', 'Author', 'Muhammad Rizwan Asghar'),
(601, 'E259', 'Edge', 'DirectedLink', 'authored by'),
(602, 'A222', 'Node', 'Author', 'Mihaela Ion'),
(603, 'E260', 'Edge', 'DirectedLink', 'authored by'),
(604, 'A223', 'Node', 'Author', 'Giovanni Russello'),
(605, 'E261', 'Edge', 'DirectedLink', 'authored by'),
(606, 'A224', 'Node', 'Author', 'Bruno Crispo'),
(607, 'E262', 'Edge', 'DirectedLink', 'authored by'),
(608, 'P84', 'Node', 'Publication', 'Measuring ranked list robustness for query performance prediction.'),
(609, 'A225', 'Node', 'Author', 'Yun Zhou'),
(610, 'E263', 'Edge', 'DirectedLink', 'authored by'),
(611, 'A226', 'Node', 'Author', 'W. Bruce Croft'),
(612, 'E264', 'Edge', 'DirectedLink', 'authored by'),
(613, 'V38', 'Node', 'Venue', 'Knowl. Inf. Syst.'),
(614, 'E265', 'Edge', 'DirectedLink', 'published in'),
(615, 'P85', 'Node', 'Publication', 'A Method of Automatic Translation of Words of Multiple Affixes In Scientific Literature.'),
(616, 'A227', 'Node', 'Author', 'Lei Wang'),
(617, 'E266', 'Edge', 'DirectedLink', 'authored by'),
(618, 'A228', 'Node', 'Author', 'Baobao Chang'),
(619, 'E267', 'Edge', 'DirectedLink', 'authored by'),
(620, 'A229', 'Node', 'Author', 'Janet Harkness'),
(621, 'E268', 'Edge', 'DirectedLink', 'authored by'),
(622, 'V39', 'Node', 'Venue', 'Int. J. of Asian Lang. Proc.'),
(623, 'E269', 'Edge', 'DirectedLink', 'published in'),
(624, 'P86', 'Node', 'Publication', 'Fabrication of microlens array based on multiple plane waves interference.'),
(625, 'A230', 'Node', 'Author', 'Gaoming Li'),
(626, 'E270', 'Edge', 'DirectedLink', 'authored by'),
(627, 'A231', 'Node', 'Author', 'Shou Liu'),
(628, 'E271', 'Edge', 'DirectedLink', 'authored by'),
(629, 'A232', 'Node', 'Author', 'Gouhua Liu'),
(630, 'E272', 'Edge', 'DirectedLink', 'authored by'),
(631, 'A233', 'Node', 'Author', 'Yishen Qiu'),
(632, 'E273', 'Edge', 'DirectedLink', 'authored by'),
(633, 'A234', 'Node', 'Author', 'Xiaoyun Chen'),
(634, 'E274', 'Edge', 'DirectedLink', 'authored by'),
(635, 'P87', 'Node', 'Publication', 'Orthogonal Beamforming Using Gram-Schmidt Orthogonalization for Multi-User MIMO Downlink System.'),
(636, 'A235', 'Node', 'Author', 'Kunitaka Matsumura'),
(637, 'E275', 'Edge', 'DirectedLink', 'authored by'),
(638, 'A236', 'Node', 'Author', 'Tomoaki Ohtsuki'),
(639, 'E276', 'Edge', 'DirectedLink', 'authored by'),
(640, 'P88', 'Node', 'Publication', 'Review of: Sue Welsh, Betsy Anagnostelis & Alison Cooke. Finding and using health and medical information on the Internet. London: Aslib-IMI, 2001. ISBN 0-85142-384-1.'),
(641, 'A237', 'Node', 'Author', 'Andrew Booth'),
(642, 'E277', 'Edge', 'DirectedLink', 'authored by'),
(643, 'V40', 'Node', 'Venue', 'Inf. Res.'),
(644, 'E278', 'Edge', 'DirectedLink', 'published in'),
(645, 'P89', 'Node', 'Publication', 'Security in Innovative New Operating Systems.'),
(646, 'A238', 'Node', 'Author', 'Cynthia E. Irvine'),
(647, 'E279', 'Edge', 'DirectedLink', 'authored by'),
(648, 'P90', 'Node', 'Publication', 'Introduction to the special issue on the Advanced CompuTational Software (ACTS) collection.'),
(649, 'A239', 'Node', 'Author', 'Ronald F. Boisvert'),
(650, 'E280', 'Edge', 'DirectedLink', 'authored by'),
(651, 'A240', 'Node', 'Author', 'L. Anthony Drummond'),
(652, 'E281', 'Edge', 'DirectedLink', 'authored by'),
(653, 'A241', 'Node', 'Author', 'Osni Marques'),
(654, 'E282', 'Edge', 'DirectedLink', 'authored by'),
(655, 'V41', 'Node', 'Venue', 'ACM Trans. Math. Softw.'),
(656, 'E283', 'Edge', 'DirectedLink', 'published in'),
(657, 'P91', 'Node', 'Publication', 'Stealth Breakpoints.'),
(658, 'A242', 'Node', 'Author', 'Amit Vasudevan'),
(659, 'E284', 'Edge', 'DirectedLink', 'authored by'),
(660, 'A243', 'Node', 'Author', 'Ramesh Yerraballi'),
(661, 'E285', 'Edge', 'DirectedLink', 'authored by'),
(662, 'P92', 'Node', 'Publication', 'Quickest Change Detection with Mismatched Post-Change Models.'),
(663, 'A244', 'Node', 'Author', 'Jingxian Wu'),
(664, 'E286', 'Edge', 'DirectedLink', 'authored by'),
(665, 'A245', 'Node', 'Author', 'Jing Yang'),
(666, 'E287', 'Edge', 'DirectedLink', 'authored by'),
(667, 'V42', 'Node', 'Venue', 'CoRR'),
(668, 'E288', 'Edge', 'DirectedLink', 'published in'),
(669, 'P93', 'Node', 'Publication', 'Categorizing and Assembling Web Services in a Composition Framework.'),
(670, 'A246', 'Node', 'Author', 'Rajesh Karunamurthy'),
(671, 'E289', 'Edge', 'DirectedLink', 'authored by'),
(672, 'A247', 'Node', 'Author', 'Ferhat Khendek'),
(673, 'E290', 'Edge', 'DirectedLink', 'authored by'),
(674, 'A248', 'Node', 'Author', 'Roch H. Glitho'),
(675, 'E291', 'Edge', 'DirectedLink', 'authored by'),
(676, 'P94', 'Node', 'Publication', 'Comparison of heuristic dynamic programming and dual heuristic programming adaptive critics for neurocontrol of a turbogenerator.'),
(677, 'A249', 'Node', 'Author', 'Ganesh K. Venayagamoorthy'),
(678, 'E292', 'Edge', 'DirectedLink', 'authored by'),
(679, 'A250', 'Node', 'Author', 'Ronald G. Harley'),
(680, 'E293', 'Edge', 'DirectedLink', 'authored by'),
(681, 'A251', 'Node', 'Author', 'Donald C. Wunsch'),
(682, 'E294', 'Edge', 'DirectedLink', 'authored by'),
(683, 'V43', 'Node', 'Venue', 'IEEE Trans. Neural Networks'),
(684, 'E295', 'Edge', 'DirectedLink', 'published in'),
(685, 'P95', 'Node', 'Publication', 'On the zeta Mahler measure function of the Jacobian determinant, condition numbers and the height of the generic discriminant.'),
(686, 'A252', 'Node', 'Author', 'Luis M. Pardo'),
(687, 'E296', 'Edge', 'DirectedLink', 'authored by'),
(688, 'A253', 'Node', 'Author', 'Mario Pardo'),
(689, 'E297', 'Edge', 'DirectedLink', 'authored by'),
(690, 'V44', 'Node', 'Venue', 'Appl. Algebra Eng. Commun. Comput.'),
(691, 'E298', 'Edge', 'DirectedLink', 'published in'),
(692, 'P96', 'Node', 'Publication', 'On the Security of an Efficient TTP-Free Mental Poker Protocol.'),
(693, 'A254', 'Node', 'Author', 'Jordi Castellà-Roca'),
(694, 'E299', 'Edge', 'DirectedLink', 'authored by'),
(695, 'A255', 'Node', 'Author', 'Josep Domingo-Ferrer'),
(696, 'E300', 'Edge', 'DirectedLink', 'authored by'),
(697, 'P97', 'Node', 'Publication', 'Legal idioms: a framework for evidential reasoning.'),
(698, 'A256', 'Node', 'Author', 'David A. Lagnado'),
(699, 'E301', 'Edge', 'DirectedLink', 'authored by'),
(700, 'A257', 'Node', 'Author', 'Norman E. Fenton'),
(701, 'E302', 'Edge', 'DirectedLink', 'authored by'),
(702, 'A258', 'Node', 'Author', 'Martin Neil'),
(703, 'E303', 'Edge', 'DirectedLink', 'authored by'),
(704, 'V45', 'Node', 'Venue', 'Argument & Computation'),
(705, 'E304', 'Edge', 'DirectedLink', 'published in'),
(706, 'P98', 'Node', 'Publication', 'Essential Transformations of the One Dimensional Cellular Automata Rule Space and Endomorphisms of Compact Abelian Groups.'),
(707, 'A259', 'Node', 'Author', 'Gianpiero Cattaneo'),
(708, 'E305', 'Edge', 'DirectedLink', 'authored by'),
(709, 'A260', 'Node', 'Author', 'Enrico Formenti'),
(710, 'E306', 'Edge', 'DirectedLink', 'authored by'),
(711, 'A261', 'Node', 'Author', 'Giancarlo Mauri'),
(712, 'E307', 'Edge', 'DirectedLink', 'authored by'),
(713, 'A262', 'Node', 'Author', 'A. Vaccaro'),
(714, 'E308', 'Edge', 'DirectedLink', 'authored by'),
(715, 'A263', 'Node', 'Author', 'Luciano Margara'),
(716, 'E309', 'Edge', 'DirectedLink', 'authored by'),
(717, 'P99', 'Node', 'Publication', 'Design Theory and Computer Science by Subrata Dasgupta. Cambridge Tracts in Theoretical Computer Science 15, CUP, Cambridge, 1991, 425 pages (incl. bibliography and index) (£30.00).'),
(718, 'A264', 'Node', 'Author', 'Iain D. Craig'),
(719, 'E310', 'Edge', 'DirectedLink', 'authored by'),
(720, 'V46', 'Node', 'Venue', 'Robotica'),
(721, 'E311', 'Edge', 'DirectedLink', 'published in'),
(722, 'P100', 'Node', 'Publication', 'A modified Manhattan distance with application for localization algorithms in ad-hoc WSNs.'),
(723, 'A265', 'Node', 'Author', 'Mohamed Shaheen Elgamel'),
(724, 'E312', 'Edge', 'DirectedLink', 'authored by'),
(725, 'A266', 'Node', 'Author', 'Abdulhalim Dandoush'),
(726, 'E313', 'Edge', 'DirectedLink', 'authored by'),
(727, 'V47', 'Node', 'Venue', 'Ad Hoc Networks'),
(728, 'E314', 'Edge', 'DirectedLink', 'published in');

-- --------------------------------------------------------

--
-- Table structure for table `Graph`
--

CREATE TABLE IF NOT EXISTS `Graph` (
  `ID` bigint(20) unsigned NOT NULL,
  `NodeFrom` text NOT NULL,
  `Edge` text NOT NULL,
  `NodeTo` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=315 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Graph`
--

INSERT INTO `Graph` (`ID`, `NodeFrom`, `Edge`, `NodeTo`) VALUES
(1, 'P1', 'E1', 'A1'),
(2, 'P1', 'E2', 'V1'),
(3, 'P2', 'E3', 'A2'),
(4, 'P2', 'E4', 'V2'),
(5, 'P3', 'E5', 'A3'),
(6, 'P3', 'E6', 'V3'),
(7, 'P4', 'E7', 'A4'),
(8, 'P4', 'E8', 'A5'),
(9, 'P5', 'E9', 'A6'),
(10, 'P5', 'E10', 'A7'),
(11, 'P5', 'E11', 'A8'),
(12, 'P5', 'E12', 'A9'),
(13, 'P5', 'E13', 'A10'),
(14, 'P6', 'E14', 'A11'),
(15, 'P6', 'E15', 'V4'),
(16, 'P7', 'E16', 'A12'),
(17, 'P7', 'E17', 'A13'),
(18, 'P7', 'E18', 'A14'),
(19, 'P7', 'E19', 'A15'),
(20, 'P7', 'E20', 'A16'),
(21, 'P8', 'E21', 'A17'),
(22, 'P8', 'E22', 'A18'),
(23, 'P9', 'E23', 'A19'),
(24, 'P9', 'E24', 'A20'),
(25, 'P10', 'E25', 'A21'),
(26, 'P10', 'E26', 'V5'),
(27, 'P11', 'E27', 'A22'),
(28, 'P11', 'E28', 'A23'),
(29, 'P11', 'E29', 'A24'),
(30, 'P12', 'E30', 'A25'),
(31, 'P13', 'E31', 'A26'),
(32, 'P13', 'E32', 'A27'),
(33, 'P13', 'E33', 'A28'),
(34, 'P14', 'E34', 'A29'),
(35, 'P14', 'E35', 'A30'),
(36, 'P14', 'E36', 'A31'),
(37, 'P15', 'E37', 'A32'),
(38, 'P15', 'E38', 'A33'),
(39, 'P15', 'E39', 'A34'),
(40, 'P15', 'E40', 'V6'),
(41, 'P16', 'E41', 'A35'),
(42, 'P17', 'E42', 'A36'),
(43, 'P17', 'E43', 'A37'),
(44, 'P17', 'E44', 'V7'),
(45, 'P18', 'E45', 'A38'),
(46, 'P18', 'E46', 'A39'),
(47, 'P18', 'E47', 'V8'),
(48, 'P19', 'E48', 'A40'),
(49, 'P19', 'E49', 'A41'),
(50, 'P19', 'E50', 'A42'),
(51, 'P19', 'E51', 'A43'),
(52, 'P19', 'E52', 'A44'),
(53, 'P19', 'E53', 'V9'),
(54, 'P20', 'E54', 'A45'),
(55, 'P20', 'E55', 'V10'),
(56, 'P21', 'E56', 'A46'),
(57, 'P22', 'E57', 'A47'),
(58, 'P22', 'E58', 'A48'),
(59, 'P22', 'E59', 'A49'),
(60, 'P22', 'E60', 'A50'),
(61, 'P22', 'E61', 'V11'),
(62, 'P23', 'E62', 'A51'),
(63, 'P23', 'E63', 'V12'),
(64, 'P24', 'E64', 'A52'),
(65, 'A52', 'E65', 'S1'),
(66, 'P25', 'E66', 'A53'),
(67, 'P25', 'E67', 'A54'),
(68, 'P25', 'E68', 'A55'),
(69, 'P25', 'E69', 'V13'),
(70, 'P26', 'E70', 'A56'),
(71, 'P27', 'E71', 'A57'),
(72, 'P27', 'E72', 'A58'),
(73, 'P27', 'E73', 'A59'),
(74, 'P27', 'E74', 'A60'),
(75, 'P27', 'E75', 'A61'),
(76, 'P28', 'E76', 'A62'),
(77, 'P28', 'E77', 'A63'),
(78, 'P28', 'E78', 'A64'),
(79, 'P28', 'E79', 'A65'),
(80, 'P28', 'E80', 'A66'),
(81, 'P29', 'E81', 'A67'),
(82, 'P29', 'E82', 'A68'),
(83, 'P29', 'E83', 'A69'),
(84, 'P29', 'E84', 'A70'),
(85, 'P29', 'E85', 'V14'),
(86, 'P30', 'E86', 'A71'),
(87, 'P30', 'E87', 'A72'),
(88, 'P30', 'E88', 'V15'),
(89, 'P31', 'E89', 'A73'),
(90, 'P31', 'E90', 'A74'),
(91, 'P31', 'E91', 'A75'),
(92, 'P31', 'E92', 'A76'),
(93, 'P31', 'E93', 'V16'),
(94, 'P32', 'E94', 'A77'),
(95, 'P32', 'E95', 'A78'),
(96, 'P32', 'E96', 'V17'),
(97, 'P33', 'E97', 'A79'),
(98, 'P33', 'E98', 'A80'),
(99, 'P34', 'E99', 'A81'),
(100, 'P34', 'E100', 'A82'),
(101, 'P34', 'E101', 'A83'),
(102, 'P35', 'E102', 'A84'),
(103, 'P35', 'E103', 'A85'),
(104, 'P35', 'E104', 'A86'),
(105, 'P36', 'E105', 'A87'),
(106, 'P36', 'E106', 'A88'),
(107, 'P36', 'E107', 'V18'),
(108, 'P37', 'E108', 'A89'),
(109, 'P37', 'E109', 'A90'),
(110, 'P38', 'E110', 'A91'),
(111, 'P38', 'E111', 'A92'),
(112, 'P39', 'E112', 'A93'),
(113, 'P39', 'E113', 'A94'),
(114, 'P39', 'E114', 'A95'),
(115, 'P39', 'E115', 'A96'),
(116, 'P39', 'E116', 'A97'),
(117, 'P39', 'E117', 'V19'),
(118, 'P40', 'E118', 'A98'),
(119, 'P40', 'E119', 'A99'),
(120, 'P40', 'E120', 'A100'),
(121, 'P41', 'E121', 'A101'),
(122, 'P41', 'E122', 'V20'),
(123, 'P42', 'E123', 'A102'),
(124, 'P42', 'E124', 'A103'),
(125, 'P42', 'E125', 'A104'),
(126, 'P42', 'E126', 'A105'),
(127, 'P42', 'E127', 'A106'),
(128, 'P42', 'E128', 'A107'),
(129, 'P42', 'E129', 'A108'),
(130, 'P42', 'E130', 'V21'),
(131, 'P43', 'E131', 'A109'),
(132, 'P43', 'E132', 'A110'),
(133, 'P43', 'E133', 'A111'),
(134, 'P43', 'E134', 'A112'),
(135, 'P44', 'E135', 'A113'),
(136, 'P44', 'E136', 'A114'),
(137, 'P44', 'E137', 'V22'),
(138, 'P45', 'E138', 'A115'),
(139, 'P45', 'E139', 'A116'),
(140, 'P45', 'E140', 'A117'),
(141, 'P45', 'E141', 'A118'),
(142, 'P46', 'E142', 'A119'),
(143, 'P46', 'E143', 'A120'),
(144, 'P47', 'E144', 'A121'),
(145, 'P48', 'E145', 'A122'),
(146, 'P48', 'E146', 'V23'),
(147, 'P49', 'E147', 'A123'),
(148, 'P49', 'E148', 'A124'),
(149, 'P49', 'E149', 'V24'),
(150, 'P50', 'E150', 'A125'),
(151, 'P50', 'E151', 'A126'),
(152, 'P50', 'E152', 'A127'),
(153, 'P50', 'E153', 'A128'),
(154, 'P51', 'E154', 'A129'),
(155, 'P51', 'E155', 'A130'),
(156, 'P52', 'E156', 'A131'),
(157, 'P52', 'E157', 'V25'),
(158, 'P53', 'E158', 'A132'),
(159, 'P53', 'E159', 'A133'),
(160, 'P53', 'E160', 'A134'),
(161, 'P53', 'E161', 'V26'),
(162, 'P54', 'E162', 'A135'),
(163, 'P54', 'E163', 'A136'),
(164, 'P55', 'E164', 'A137'),
(165, 'P55', 'E165', 'A138'),
(166, 'P55', 'E166', 'A139'),
(167, 'P55', 'E167', 'A140'),
(168, 'P55', 'E168', 'A141'),
(169, 'P55', 'E169', 'A142'),
(170, 'P55', 'E170', 'A143'),
(171, 'P55', 'E171', 'A144'),
(172, 'P56', 'E172', 'A145'),
(173, 'P57', 'E173', 'A146'),
(174, 'P57', 'E174', 'A147'),
(175, 'P57', 'E175', 'A148'),
(176, 'P58', 'E176', 'A149'),
(177, 'P58', 'E177', 'A150'),
(178, 'P59', 'E178', 'A151'),
(179, 'P59', 'E179', 'V27'),
(180, 'P60', 'E180', 'A152'),
(181, 'P60', 'E181', 'A153'),
(182, 'P60', 'E182', 'A154'),
(183, 'P61', 'E183', 'A155'),
(184, 'P61', 'E184', 'A156'),
(185, 'P61', 'E185', 'A157'),
(186, 'P62', 'E186', 'A158'),
(187, 'P62', 'E187', 'A159'),
(188, 'P62', 'E188', 'A160'),
(189, 'P62', 'E189', 'A161'),
(190, 'P62', 'E190', 'V28'),
(191, 'P63', 'E191', 'A162'),
(192, 'P63', 'E192', 'V29'),
(193, 'P64', 'E193', 'A163'),
(194, 'P64', 'E194', 'A164'),
(195, 'P64', 'E195', 'A165'),
(196, 'P64', 'E196', 'V30'),
(197, 'P65', 'E197', 'A166'),
(198, 'P66', 'E198', 'A167'),
(199, 'P66', 'E199', 'A168'),
(200, 'P66', 'E200', 'A169'),
(201, 'P67', 'E201', 'A170'),
(202, 'P67', 'E202', 'A171'),
(203, 'P67', 'E203', 'A172'),
(204, 'P67', 'E204', 'A173'),
(205, 'P67', 'E205', 'A174'),
(206, 'P68', 'E206', 'A175'),
(207, 'P68', 'E207', 'A176'),
(208, 'P68', 'E208', 'A177'),
(209, 'P68', 'E209', 'A178'),
(210, 'P68', 'E210', 'V31'),
(211, 'P69', 'E211', 'A179'),
(212, 'P69', 'E212', 'V32'),
(213, 'P70', 'E213', 'A180'),
(214, 'P70', 'E214', 'A181'),
(215, 'P70', 'E215', 'A182'),
(216, 'P70', 'E216', 'V33'),
(217, 'P71', 'E217', 'A183'),
(218, 'P71', 'E218', 'A184'),
(219, 'P71', 'E219', 'A185'),
(220, 'P71', 'E220', 'A186'),
(221, 'P72', 'E221', 'A187'),
(222, 'P72', 'E222', 'A188'),
(223, 'P72', 'E223', 'A189'),
(224, 'P72', 'E224', 'A190'),
(225, 'P73', 'E225', 'A191'),
(226, 'P73', 'E226', 'A192'),
(227, 'P73', 'E227', 'V34'),
(228, 'P74', 'E228', 'A193'),
(229, 'P74', 'E229', 'A194'),
(230, 'P74', 'E230', 'V35'),
(231, 'P75', 'E231', 'A195'),
(232, 'P75', 'E232', 'A196'),
(233, 'P75', 'E233', 'A197'),
(234, 'P75', 'E234', 'A198'),
(235, 'P75', 'E235', 'A199'),
(236, 'P76', 'E236', 'A200'),
(237, 'P76', 'E237', 'A201'),
(238, 'P76', 'E238', 'A202'),
(239, 'P76', 'E239', 'A203'),
(240, 'P77', 'E240', 'A204'),
(241, 'P77', 'E241', 'A205'),
(242, 'P77', 'E242', 'A206'),
(243, 'P77', 'E243', 'V36'),
(244, 'P78', 'E244', 'A207'),
(245, 'P78', 'E245', 'A208'),
(246, 'P79', 'E246', 'A209'),
(247, 'P79', 'E247', 'A210'),
(248, 'P80', 'E248', 'A211'),
(249, 'P80', 'E249', 'A212'),
(250, 'P80', 'E250', 'A213'),
(251, 'P80', 'E251', 'A214'),
(252, 'P81', 'E252', 'A215'),
(253, 'P81', 'E253', 'A216'),
(254, 'P81', 'E254', 'V37'),
(255, 'P82', 'E255', 'A217'),
(256, 'P82', 'E256', 'A218'),
(257, 'P82', 'E257', 'A219'),
(258, 'P82', 'E258', 'A220'),
(259, 'P83', 'E259', 'A221'),
(260, 'P83', 'E260', 'A222'),
(261, 'P83', 'E261', 'A223'),
(262, 'P83', 'E262', 'A224'),
(263, 'P84', 'E263', 'A225'),
(264, 'P84', 'E264', 'A226'),
(265, 'P84', 'E265', 'V38'),
(266, 'P85', 'E266', 'A227'),
(267, 'P85', 'E267', 'A228'),
(268, 'P85', 'E268', 'A229'),
(269, 'P85', 'E269', 'V39'),
(270, 'P86', 'E270', 'A230'),
(271, 'P86', 'E271', 'A231'),
(272, 'P86', 'E272', 'A232'),
(273, 'P86', 'E273', 'A233'),
(274, 'P86', 'E274', 'A234'),
(275, 'P87', 'E275', 'A235'),
(276, 'P87', 'E276', 'A236'),
(277, 'P88', 'E277', 'A237'),
(278, 'P88', 'E278', 'V40'),
(279, 'P89', 'E279', 'A238'),
(280, 'P90', 'E280', 'A239'),
(281, 'P90', 'E281', 'A240'),
(282, 'P90', 'E282', 'A241'),
(283, 'P90', 'E283', 'V41'),
(284, 'P91', 'E284', 'A242'),
(285, 'P91', 'E285', 'A243'),
(286, 'P92', 'E286', 'A244'),
(287, 'P92', 'E287', 'A245'),
(288, 'P92', 'E288', 'V42'),
(289, 'P93', 'E289', 'A246'),
(290, 'P93', 'E290', 'A247'),
(291, 'P93', 'E291', 'A248'),
(292, 'P94', 'E292', 'A249'),
(293, 'P94', 'E293', 'A250'),
(294, 'P94', 'E294', 'A251'),
(295, 'P94', 'E295', 'V43'),
(296, 'P95', 'E296', 'A252'),
(297, 'P95', 'E297', 'A253'),
(298, 'P95', 'E298', 'V44'),
(299, 'P96', 'E299', 'A254'),
(300, 'P96', 'E300', 'A255'),
(301, 'P97', 'E301', 'A256'),
(302, 'P97', 'E302', 'A257'),
(303, 'P97', 'E303', 'A258'),
(304, 'P97', 'E304', 'V45'),
(305, 'P98', 'E305', 'A259'),
(306, 'P98', 'E306', 'A260'),
(307, 'P98', 'E307', 'A261'),
(308, 'P98', 'E308', 'A262'),
(309, 'P98', 'E309', 'A263'),
(310, 'P99', 'E310', 'A264'),
(311, 'P99', 'E311', 'V46'),
(312, 'P100', 'E312', 'A265'),
(313, 'P100', 'E313', 'A266'),
(314, 'P100', 'E314', 'V47');

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

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UserID` bigint(20) unsigned NOT NULL,
  `Username` varchar(16) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `Nickname` tinytext  NOT NULL,
  `Firstname` tinytext NOT NULL,
  `Lastname` tinytext  NOT NULL,
  `Email` tinytext NOT NULL,
  `NewEmail` tinytext  NOT NULL,
  `Birthyear` smallint(6) unsigned NOT NULL,
  `Address` text NOT NULL,
  `CardNumber` tinytext NOT NULL,
  `TokenString` varchar(20) NOT NULL
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
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=729;
--
-- AUTO_INCREMENT for table `Graph`
--
ALTER TABLE `Graph`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=315;
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


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
