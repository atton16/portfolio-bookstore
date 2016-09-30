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
) ENGINE=InnoDB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Entity`
--

INSERT INTO `Entity` (`ID`, `EntityID`, `Class`, `Type`, `Caption`) VALUES
(1, 'P1', 'Node', 'Publication', 'Learning from Nonpropositional Data.'),
(2, 'P2', 'Node', 'Publication', 'Design Phase Consistency: A Tool for Reverse Engineering of UML Activity Diagrams to Their Original Scenarios in the Specification Phase.'),
(3, 'A1', 'Node', 'Author', 'Jay Pancham'),
(4, 'E1', 'Edge', 'DirectedLink', 'authored by'),
(5, 'A2', 'Node', 'Author', 'Richard Millham'),
(6, 'E2', 'Edge', 'DirectedLink', 'authored by'),
(7, 'P3', 'Node', 'Publication', 'Building Large ROLAP Data Cubes in Parallel.'),
(8, 'A3', 'Node', 'Author', 'Ying Chen'),
(9, 'E3', 'Edge', 'DirectedLink', 'authored by'),
(10, 'A4', 'Node', 'Author', 'Frank K. H. A. Dehne'),
(11, 'E4', 'Edge', 'DirectedLink', 'authored by'),
(12, 'A5', 'Node', 'Author', 'Todd Eavis'),
(13, 'E5', 'Edge', 'DirectedLink', 'authored by'),
(14, 'A6', 'Node', 'Author', 'Andrew Rau-Chaplin'),
(15, 'E6', 'Edge', 'DirectedLink', 'authored by'),
(16, 'P4', 'Node', 'Publication', 'Modeling the relative fitness of storage.'),
(17, 'A7', 'Node', 'Author', 'Michael P. Mesnier'),
(18, 'E7', 'Edge', 'DirectedLink', 'authored by'),
(19, 'A8', 'Node', 'Author', 'Matthew Wachs'),
(20, 'E8', 'Edge', 'DirectedLink', 'authored by'),
(21, 'A9', 'Node', 'Author', 'Raja R. Sambasivan'),
(22, 'E9', 'Edge', 'DirectedLink', 'authored by'),
(23, 'A10', 'Node', 'Author', 'Alice X. Zheng'),
(24, 'E10', 'Edge', 'DirectedLink', 'authored by'),
(25, 'A11', 'Node', 'Author', 'Gregory R. Ganger'),
(26, 'E11', 'Edge', 'DirectedLink', 'authored by'),
(27, 'P5', 'Node', 'Publication', 'Home Page'),
(28, 'A12', 'Node', 'Author', 'Hyunyeol Lee'),
(29, 'E12', 'Edge', 'DirectedLink', 'authored by'),
(30, 'P6', 'Node', 'Publication', 'Quantitive Evaluation on the Preservation of Polarimetric Information for PolSAR Filters.'),
(31, 'A13', 'Node', 'Author', 'Chaoyang Niu'),
(32, 'E13', 'Edge', 'DirectedLink', 'authored by'),
(33, 'A14', 'Node', 'Author', 'Guangming Sheng'),
(34, 'E14', 'Edge', 'DirectedLink', 'authored by'),
(35, 'A15', 'Node', 'Author', 'Debao Ma'),
(36, 'E15', 'Edge', 'DirectedLink', 'authored by'),
(37, 'A16', 'Node', 'Author', 'Junhua Zhang'),
(38, 'E16', 'Edge', 'DirectedLink', 'authored by'),
(39, 'P7', 'Node', 'Publication', 'Strukturgebundener Software-Entwurf auf relationaler Basis- Ein integrierender Ansatz.'),
(40, 'S1', 'Node', 'School', 'TH Darmstadt'),
(41, 'A17', 'Node', 'Author', 'Jürgen R. Bergmann'),
(42, 'E17', 'Edge', 'DirectedLink', 'authored by'),
(43, 'E18', 'Edge', 'DirectedLink', 'affiliated in'),
(44, 'P8', 'Node', 'Publication', 'An Experimental Evaluation of Wiener Filter Smoothing Techniques Applied to Under-Determined Audio Source Separation.'),
(45, 'A18', 'Node', 'Author', 'Emmanuel Vincent'),
(46, 'E19', 'Edge', 'DirectedLink', 'authored by'),
(47, 'P9', 'Node', 'Publication', 'Applied Geometry And Discrete Mathematics, Proceedings of a DIMACS Workshop, Providence, Rhode Island, USA, September 18, 1990'),
(48, 'A19', 'Node', 'Author', 'Peter Gritzmann'),
(49, 'E20', 'Edge', 'DirectedLink', 'edited by'),
(50, 'A20', 'Node', 'Author', 'Bernd Sturmfels'),
(51, 'E21', 'Edge', 'DirectedLink', 'edited by'),
(52, 'P10', 'Node', 'Publication', 'A Prototyping Technique with an Asychronous Specification Language.'),
(53, 'A21', 'Node', 'Author', 'Miroslav Svéda'),
(54, 'E22', 'Edge', 'DirectedLink', 'authored by'),
(55, 'P11', 'Node', 'Publication', 'Parallel Sorting with Serial Momories.'),
(56, 'A22', 'Node', 'Author', 'Robert Michael Owens'),
(57, 'E23', 'Edge', 'DirectedLink', 'authored by'),
(58, 'A23', 'Node', 'Author', 'Joseph JáJá'),
(59, 'E24', 'Edge', 'DirectedLink', 'authored by'),
(60, 'V1', 'Node', 'Venue', 'IEEE Trans. Computers'),
(61, 'E25', 'Edge', 'DirectedLink', 'published in'),
(62, 'P12', 'Node', 'Publication', 'Optimal capacity design under k-out-of-n and consecutive k-out-of-n type probabilistic constraints.'),
(63, 'A24', 'Node', 'Author', 'Merve Unuvar'),
(64, 'E26', 'Edge', 'DirectedLink', 'authored by'),
(65, 'A25', 'Node', 'Author', 'Eren Erman Ozguven'),
(66, 'E27', 'Edge', 'DirectedLink', 'authored by'),
(67, 'A26', 'Node', 'Author', 'András Prékopa'),
(68, 'E28', 'Edge', 'DirectedLink', 'authored by'),
(69, 'V2', 'Node', 'Venue', 'Annals OR'),
(70, 'E29', 'Edge', 'DirectedLink', 'published in'),
(71, 'P13', 'Node', 'Publication', 'Real-life performance of metric searching.'),
(72, 'A27', 'Node', 'Author', 'Vlastislav Dohnal'),
(73, 'E30', 'Edge', 'DirectedLink', 'authored by'),
(74, 'A28', 'Node', 'Author', 'Pavel Zezula'),
(75, 'E31', 'Edge', 'DirectedLink', 'authored by'),
(76, 'V3', 'Node', 'Venue', 'SIGSPATIAL Special'),
(77, 'E32', 'Edge', 'DirectedLink', 'published in'),
(78, 'P14', 'Node', 'Publication', 'The Role of Technology in Reducing Unnecessary Duplicate Diagnostic Imaging Examinations.'),
(79, 'A29', 'Node', 'Author', 'Janessa Griffith'),
(80, 'E33', 'Edge', 'DirectedLink', 'authored by'),
(81, 'A30', 'Node', 'Author', 'Elizabeth M. Borycki'),
(82, 'E34', 'Edge', 'DirectedLink', 'authored by'),
(83, 'A31', 'Node', 'Author', 'Andre W. Kushniruk'),
(84, 'E35', 'Edge', 'DirectedLink', 'authored by'),
(85, 'P15', 'Node', 'Publication', 'The Cyclic Antibandwidth Problem.'),
(86, 'A32', 'Node', 'Author', 'Ondrej Sýkora'),
(87, 'E36', 'Edge', 'DirectedLink', 'authored by'),
(88, 'A33', 'Node', 'Author', 'Lubomir Torok'),
(89, 'E37', 'Edge', 'DirectedLink', 'authored by'),
(90, 'A34', 'Node', 'Author', 'Imrich Vrto'),
(91, 'E38', 'Edge', 'DirectedLink', 'authored by'),
(92, 'V4', 'Node', 'Venue', 'Electronic Notes in Discrete Mathematics'),
(93, 'E39', 'Edge', 'DirectedLink', 'published in'),
(94, 'P16', 'Node', 'Publication', 'Parallel main-memory indexing for moving-object query and update workloads.'),
(95, 'A35', 'Node', 'Author', 'Darius Sidlauskas'),
(96, 'E40', 'Edge', 'DirectedLink', 'authored by'),
(97, 'A36', 'Node', 'Author', 'Simonas Saltenis'),
(98, 'E41', 'Edge', 'DirectedLink', 'authored by'),
(99, 'A37', 'Node', 'Author', 'Christian S. Jensen'),
(100, 'E42', 'Edge', 'DirectedLink', 'authored by'),
(101, 'P17', 'Node', 'Publication', 'Mining gene expression databases for local causal relationships using a simple constraint-based algorithm.'),
(102, 'A38', 'Node', 'Author', 'Mingyi Wang'),
(103, 'E43', 'Edge', 'DirectedLink', 'authored by'),
(104, 'A39', 'Node', 'Author', 'Huijuan Lu'),
(105, 'E44', 'Edge', 'DirectedLink', 'authored by'),
(106, 'A40', 'Node', 'Author', 'Zuozhou Chen'),
(107, 'E45', 'Edge', 'DirectedLink', 'authored by'),
(108, 'A41', 'Node', 'Author', 'Ping Wu'),
(109, 'E46', 'Edge', 'DirectedLink', 'authored by'),
(110, 'V5', 'Node', 'Venue', 'IJPRAI'),
(111, 'E47', 'Edge', 'DirectedLink', 'published in'),
(112, 'P18', 'Node', 'Publication', 'Use of cell morphology as an early bio-sensor for viral infection.'),
(113, 'A42', 'Node', 'Author', 'Xianting Ding'),
(114, 'E48', 'Edge', 'DirectedLink', 'authored by'),
(115, 'A43', 'Node', 'Author', 'Ningxia Liu'),
(116, 'E49', 'Edge', 'DirectedLink', 'authored by'),
(117, 'A44', 'Node', 'Author', 'K. Matsuo'),
(118, 'E50', 'Edge', 'DirectedLink', 'authored by'),
(119, 'A45', 'Node', 'Author', 'Mingzhu Sun'),
(120, 'E51', 'Edge', 'DirectedLink', 'authored by'),
(121, 'A46', 'Node', 'Author', 'Chih-Ming Ho'),
(122, 'E52', 'Edge', 'DirectedLink', 'authored by'),
(123, 'A47', 'Node', 'Author', 'Xin Zhao'),
(124, 'E53', 'Edge', 'DirectedLink', 'authored by'),
(125, 'P19', 'Node', 'Publication', 'Linear-Time Estimators for Propensity Scores.'),
(126, 'A48', 'Node', 'Author', 'Deepak Agarwal'),
(127, 'E54', 'Edge', 'DirectedLink', 'authored by'),
(128, 'A49', 'Node', 'Author', 'Lihong Li'),
(129, 'E55', 'Edge', 'DirectedLink', 'authored by'),
(130, 'A50', 'Node', 'Author', 'Alexander J. Smola'),
(131, 'E56', 'Edge', 'DirectedLink', 'authored by'),
(132, 'P20', 'Node', 'Publication', 'Querying Big, Dynamic, Distributed Data.'),
(133, 'A51', 'Node', 'Author', 'Minos N. Garofalakis'),
(134, 'E57', 'Edge', 'DirectedLink', 'authored by'),
(135, 'P21', 'Node', 'Publication', 'Embedding dualities for set partitions and for relational structures.'),
(136, 'A52', 'Node', 'Author', 'Vít Jelínek'),
(137, 'E58', 'Edge', 'DirectedLink', 'authored by'),
(138, 'A53', 'Node', 'Author', 'Martin Klazar'),
(139, 'E59', 'Edge', 'DirectedLink', 'authored by'),
(140, 'V6', 'Node', 'Venue', 'Eur. J. Comb.'),
(141, 'E60', 'Edge', 'DirectedLink', 'published in'),
(142, 'P22', 'Node', 'Publication', 'Regional Weather Prediction on Small Network of Workstations.'),
(143, 'A54', 'Node', 'Author', 'Adrianos Lachanas'),
(144, 'E61', 'Edge', 'DirectedLink', 'authored by'),
(145, 'A55', 'Node', 'Author', 'Paraskevas Evripidou'),
(146, 'E62', 'Edge', 'DirectedLink', 'authored by'),
(147, 'A56', 'Node', 'Author', 'Silas C. Michaelides'),
(148, 'E63', 'Edge', 'DirectedLink', 'authored by'),
(149, 'P23', 'Node', 'Publication', 'Memory Requirements for Silent Stabilization.'),
(150, 'A57', 'Node', 'Author', 'Shlomi Dolev'),
(151, 'E64', 'Edge', 'DirectedLink', 'authored by'),
(152, 'A58', 'Node', 'Author', 'Mohamed G. Gouda'),
(153, 'E65', 'Edge', 'DirectedLink', 'authored by'),
(154, 'A59', 'Node', 'Author', 'Marco Schneider'),
(155, 'E66', 'Edge', 'DirectedLink', 'authored by'),
(156, 'V7', 'Node', 'Venue', 'Acta Inf.'),
(157, 'E67', 'Edge', 'DirectedLink', 'published in'),
(158, 'P24', 'Node', 'Publication', 'Corrections to "Segmental minimum Bayes-risk decoding for automatic speech recognition".'),
(159, 'A60', 'Node', 'Author', 'Vaibhava Goel'),
(160, 'E68', 'Edge', 'DirectedLink', 'authored by'),
(161, 'A61', 'Node', 'Author', 'Shankar Kumar'),
(162, 'E69', 'Edge', 'DirectedLink', 'authored by'),
(163, 'A62', 'Node', 'Author', 'William Byrne'),
(164, 'E70', 'Edge', 'DirectedLink', 'authored by'),
(165, 'V8', 'Node', 'Venue', 'IEEE Trans. Audio, Speech & Language Processing'),
(166, 'E71', 'Edge', 'DirectedLink', 'published in'),
(167, 'P25', 'Node', 'Publication', 'Comparative Performance Evaluation of RF Localization for Wireless Capsule Endoscopy Applications.'),
(168, 'A63', 'Node', 'Author', 'Yunxing Ye'),
(169, 'E72', 'Edge', 'DirectedLink', 'authored by'),
(170, 'A64', 'Node', 'Author', 'Kaveh Pahlavan'),
(171, 'E73', 'Edge', 'DirectedLink', 'authored by'),
(172, 'A65', 'Node', 'Author', 'Guanqun Bao'),
(173, 'E74', 'Edge', 'DirectedLink', 'authored by'),
(174, 'A66', 'Node', 'Author', 'Pranay Swar'),
(175, 'E75', 'Edge', 'DirectedLink', 'authored by'),
(176, 'A67', 'Node', 'Author', 'Kaveh Ghaboosi'),
(177, 'E76', 'Edge', 'DirectedLink', 'authored by'),
(178, 'V9', 'Node', 'Venue', 'IJWIN'),
(179, 'E77', 'Edge', 'DirectedLink', 'published in'),
(180, 'P26', 'Node', 'Publication', 'On incentive-based inter-domain caching for content delivery in future internet architectures.'),
(181, 'A68', 'Node', 'Author', 'Kalika Suksomboon'),
(182, 'E78', 'Edge', 'DirectedLink', 'authored by'),
(183, 'A69', 'Node', 'Author', 'Yusheng Ji'),
(184, 'E79', 'Edge', 'DirectedLink', 'authored by'),
(185, 'A70', 'Node', 'Author', 'Michihiro Koibuchi'),
(186, 'E80', 'Edge', 'DirectedLink', 'authored by'),
(187, 'A71', 'Node', 'Author', 'Kensuke Fukuda'),
(188, 'E81', 'Edge', 'DirectedLink', 'authored by'),
(189, 'A72', 'Node', 'Author', 'Shunji Abe'),
(190, 'E82', 'Edge', 'DirectedLink', 'authored by'),
(191, 'A73', 'Node', 'Author', 'Motonori Nakamura'),
(192, 'E83', 'Edge', 'DirectedLink', 'authored by'),
(193, 'A74', 'Node', 'Author', 'Michihiro Aoki'),
(194, 'E84', 'Edge', 'DirectedLink', 'authored by'),
(195, 'A75', 'Node', 'Author', 'Shigeo Urushidani'),
(196, 'E85', 'Edge', 'DirectedLink', 'authored by'),
(197, 'A76', 'Node', 'Author', 'Shigeki Yamada'),
(198, 'E86', 'Edge', 'DirectedLink', 'authored by'),
(199, 'P27', 'Node', 'Publication', 'Generating Lookup Tables Using Evolutionary Algorithms.'),
(200, 'A77', 'Node', 'Author', 'Tim Hendtlass'),
(201, 'E87', 'Edge', 'DirectedLink', 'authored by'),
(202, 'P28', 'Node', 'Publication', 'Fast Scoring of Full Posterior PLDA Models.'),
(203, 'A78', 'Node', 'Author', 'Sandro Cumani'),
(204, 'E88', 'Edge', 'DirectedLink', 'authored by'),
(205, 'V10', 'Node', 'Venue', 'IEEE/ACM Trans. Audio, Speech & Language Processing'),
(206, 'E89', 'Edge', 'DirectedLink', 'published in'),
(207, 'P29', 'Node', 'Publication', 'A Framework for Evaluating ICA Methods of Artifact Removal from Multichannel EEG.'),
(208, 'A79', 'Node', 'Author', 'Kevin A. Glass'),
(209, 'E90', 'Edge', 'DirectedLink', 'authored by'),
(210, 'A80', 'Node', 'Author', 'Gwen A. Frishkoff'),
(211, 'E91', 'Edge', 'DirectedLink', 'authored by'),
(212, 'A81', 'Node', 'Author', 'Robert M. Frank'),
(213, 'E92', 'Edge', 'DirectedLink', 'authored by'),
(214, 'A82', 'Node', 'Author', 'Colin Davey'),
(215, 'E93', 'Edge', 'DirectedLink', 'authored by'),
(216, 'A83', 'Node', 'Author', 'Joseph Dien'),
(217, 'E94', 'Edge', 'DirectedLink', 'authored by'),
(218, 'A84', 'Node', 'Author', 'Allen D. Malony'),
(219, 'E95', 'Edge', 'DirectedLink', 'authored by'),
(220, 'A85', 'Node', 'Author', 'Don M. Tucker'),
(221, 'E96', 'Edge', 'DirectedLink', 'authored by'),
(222, 'P30', 'Node', 'Publication', 'On structural properties of generalized processes.'),
(223, 'A86', 'Node', 'Author', 'Vadim E. Kotov'),
(224, 'E97', 'Edge', 'DirectedLink', 'authored by'),
(225, 'A87', 'Node', 'Author', 'Ludmila Cherkasova'),
(226, 'E98', 'Edge', 'DirectedLink', 'authored by'),
(227, 'P31', 'Node', 'Publication', 'A partial redundant fault-secure high-level synthesis algorithm for RDR architectures.'),
(228, 'A88', 'Node', 'Author', 'Kazushi Kawamura'),
(229, 'E99', 'Edge', 'DirectedLink', 'authored by'),
(230, 'A89', 'Node', 'Author', 'Sho Tanaka'),
(231, 'E100', 'Edge', 'DirectedLink', 'authored by'),
(232, 'A90', 'Node', 'Author', 'Masao Yanagisawa'),
(233, 'E101', 'Edge', 'DirectedLink', 'authored by'),
(234, 'A91', 'Node', 'Author', 'Nozomu Togawa'),
(235, 'E102', 'Edge', 'DirectedLink', 'authored by'),
(236, 'P32', 'Node', 'Publication', 'Guest Editorial.'),
(237, 'A92', 'Node', 'Author', 'Francis N. Parr'),
(238, 'E103', 'Edge', 'DirectedLink', 'authored by'),
(239, 'V11', 'Node', 'Venue', 'IEEE Journal on Selected Areas in Communications'),
(240, 'E104', 'Edge', 'DirectedLink', 'published in'),
(241, 'P33', 'Node', 'Publication', 'Dynamic variational preferences.'),
(242, 'A93', 'Node', 'Author', 'Fabio Maccheroni'),
(243, 'E105', 'Edge', 'DirectedLink', 'authored by'),
(244, 'A94', 'Node', 'Author', 'Massimo Marinacci'),
(245, 'E106', 'Edge', 'DirectedLink', 'authored by'),
(246, 'A95', 'Node', 'Author', 'Aldo Rustichini'),
(247, 'E107', 'Edge', 'DirectedLink', 'authored by'),
(248, 'V12', 'Node', 'Venue', 'J. Economic Theory'),
(249, 'E108', 'Edge', 'DirectedLink', 'published in'),
(250, 'P34', 'Node', 'Publication', 'NooSphere: an activity-centric infrastructure for distributed interaction.'),
(251, 'A96', 'Node', 'Author', 'Steven Houben'),
(252, 'E109', 'Edge', 'DirectedLink', 'authored by'),
(253, 'A97', 'Node', 'Author', 'Søren Nielsen'),
(254, 'E110', 'Edge', 'DirectedLink', 'authored by'),
(255, 'A98', 'Node', 'Author', 'Morten Esbensen'),
(256, 'E111', 'Edge', 'DirectedLink', 'authored by'),
(257, 'A99', 'Node', 'Author', 'Jakob E. Bardram'),
(258, 'E112', 'Edge', 'DirectedLink', 'authored by'),
(259, 'P35', 'Node', 'Publication', 'Objektbasierte Integration heterogener Informationsysteme.'),
(260, 'A100', 'Node', 'Author', 'Susanne Busse'),
(261, 'E113', 'Edge', 'DirectedLink', 'authored by'),
(262, 'V13', 'Node', 'Venue', 'Datenbank Rundbrief'),
(263, 'E114', 'Edge', 'DirectedLink', 'published in'),
(264, 'P36', 'Node', 'Publication', 'Evaluation Of The Constraint Method-based Evolutionary Algorithm (CMEA) For A Three-objective Problem.'),
(265, 'A101', 'Node', 'Author', 'Sujay V. Kumar'),
(266, 'E115', 'Edge', 'DirectedLink', 'authored by'),
(267, 'A102', 'Node', 'Author', 'S. Ranji Ranjithan'),
(268, 'E116', 'Edge', 'DirectedLink', 'authored by'),
(269, 'P37', 'Node', 'Publication', 'Efficient scheduled stabilizing output feedback model predictive control for constrained nonlinear systems.'),
(270, 'A103', 'Node', 'Author', 'Zhaoyang Wan'),
(271, 'E117', 'Edge', 'DirectedLink', 'authored by'),
(272, 'A104', 'Node', 'Author', 'Mayuresh V. Kothare'),
(273, 'E118', 'Edge', 'DirectedLink', 'authored by'),
(274, 'V14', 'Node', 'Venue', 'IEEE Trans. Automat. Contr.'),
(275, 'E119', 'Edge', 'DirectedLink', 'published in'),
(276, 'P38', 'Node', 'Publication', 'On using bi-equational constraints in CAD construction.'),
(277, 'A105', 'Node', 'Author', 'Christopher W. Brown'),
(278, 'E120', 'Edge', 'DirectedLink', 'authored by'),
(279, 'A106', 'Node', 'Author', 'Scott McCallum'),
(280, 'E121', 'Edge', 'DirectedLink', 'authored by'),
(281, 'P39', 'Node', 'Publication', 'Round-Robin Resource Sharing Algorithm for Device-to-Device Communications Underlying SFN System.'),
(282, 'A107', 'Node', 'Author', 'Wenrong Gong'),
(283, 'E122', 'Edge', 'DirectedLink', 'authored by'),
(284, 'A108', 'Node', 'Author', 'Xiaoxiang Wang'),
(285, 'E123', 'Edge', 'DirectedLink', 'authored by'),
(286, 'A109', 'Node', 'Author', 'Mingming Li'),
(287, 'E124', 'Edge', 'DirectedLink', 'authored by'),
(288, 'A110', 'Node', 'Author', 'Zijia Huang'),
(289, 'E125', 'Edge', 'DirectedLink', 'authored by'),
(290, 'P40', 'Node', 'Publication', 'A simplified bit metric calculation method for high-order PSK.'),
(291, 'A111', 'Node', 'Author', 'Lei Wang'),
(292, 'E126', 'Edge', 'DirectedLink', 'authored by'),
(293, 'A112', 'Node', 'Author', 'Dazhuan Xu'),
(294, 'E127', 'Edge', 'DirectedLink', 'authored by'),
(295, 'A113', 'Node', 'Author', 'Xiaofei Zhang'),
(296, 'E128', 'Edge', 'DirectedLink', 'authored by'),
(297, 'V15', 'Node', 'Venue', 'SCIENCE CHINA Information Sciences'),
(298, 'E129', 'Edge', 'DirectedLink', 'published in'),
(299, 'P41', 'Node', 'Publication', 'An Improving Localization Scheme Using Mobile Anchors with Directional Antennas in Wireless Sensor Networks.'),
(300, 'A114', 'Node', 'Author', 'Young-Long Chen'),
(301, 'E130', 'Edge', 'DirectedLink', 'authored by'),
(302, 'A115', 'Node', 'Author', 'Tzu-Chieh Sun'),
(303, 'E131', 'Edge', 'DirectedLink', 'authored by'),
(304, 'A116', 'Node', 'Author', 'Neng-Chung Wang'),
(305, 'E132', 'Edge', 'DirectedLink', 'authored by'),
(306, 'A117', 'Node', 'Author', 'Shin You Shie'),
(307, 'E133', 'Edge', 'DirectedLink', 'authored by'),
(308, 'P42', 'Node', 'Publication', 'Parallelizing FPGA Technology Mapping Using Graphics Processing Units (GPUs).'),
(309, 'A118', 'Node', 'Author', 'Doris Chen'),
(310, 'E134', 'Edge', 'DirectedLink', 'authored by'),
(311, 'A119', 'Node', 'Author', 'Deshanand P. Singh'),
(312, 'E135', 'Edge', 'DirectedLink', 'authored by'),
(313, 'P43', 'Node', 'Publication', 'Performance of CC Code-based Spread Time CDMA System in AWGN Channel.'),
(314, 'A120', 'Node', 'Author', 'Liru Lu'),
(315, 'E136', 'Edge', 'DirectedLink', 'authored by'),
(316, 'A121', 'Node', 'Author', 'Vimal K. Dubey'),
(317, 'E137', 'Edge', 'DirectedLink', 'authored by'),
(318, 'P44', 'Node', 'Publication', 'Adaptive multimodal fusion by uncertainty compensation.'),
(319, 'A122', 'Node', 'Author', 'Vassilis Pitsikalis'),
(320, 'E138', 'Edge', 'DirectedLink', 'authored by'),
(321, 'A123', 'Node', 'Author', 'Athanassios Katsamanis'),
(322, 'E139', 'Edge', 'DirectedLink', 'authored by'),
(323, 'A124', 'Node', 'Author', 'George Papandreou'),
(324, 'E140', 'Edge', 'DirectedLink', 'authored by'),
(325, 'A125', 'Node', 'Author', 'Petros Maragos'),
(326, 'E141', 'Edge', 'DirectedLink', 'authored by'),
(327, 'P45', 'Node', 'Publication', 'The complex cepstrum applied to two-dimensional images.'),
(328, 'A126', 'Node', 'Author', 'James K. Lee'),
(329, 'E142', 'Edge', 'DirectedLink', 'authored by'),
(330, 'A127', 'Node', 'Author', 'Matthew Kabrisky'),
(331, 'E143', 'Edge', 'DirectedLink', 'authored by'),
(332, 'A128', 'Node', 'Author', 'Mark E. Oxley'),
(333, 'E144', 'Edge', 'DirectedLink', 'authored by'),
(334, 'A129', 'Node', 'Author', 'Steven K. Rogers'),
(335, 'E145', 'Edge', 'DirectedLink', 'authored by'),
(336, 'A130', 'Node', 'Author', 'Dennis W. Ruck'),
(337, 'E146', 'Edge', 'DirectedLink', 'authored by'),
(338, 'V16', 'Node', 'Venue', 'Pattern Recognition'),
(339, 'E147', 'Edge', 'DirectedLink', 'published in'),
(340, 'P46', 'Node', 'Publication', 'Effect of Training Sample on Reconstructing 3D Face Shapes from Feature Points.'),
(341, 'A131', 'Node', 'Author', 'Ashraf Y. A. Maghari'),
(342, 'E148', 'Edge', 'DirectedLink', 'authored by'),
(343, 'A132', 'Node', 'Author', 'Ibrahim Venkat'),
(344, 'E149', 'Edge', 'DirectedLink', 'authored by'),
(345, 'A133', 'Node', 'Author', 'Iman Yi Liao'),
(346, 'E150', 'Edge', 'DirectedLink', 'authored by'),
(347, 'A134', 'Node', 'Author', 'Bahari Belaton'),
(348, 'E151', 'Edge', 'DirectedLink', 'authored by'),
(349, 'P47', 'Node', 'Publication', 'Confidence on Approximate Query in Large Datasets.'),
(350, 'A135', 'Node', 'Author', 'Charles Wesley Ford'),
(351, 'E152', 'Edge', 'DirectedLink', 'authored by'),
(352, 'A136', 'Node', 'Author', 'Chia-Chu Chiang'),
(353, 'E153', 'Edge', 'DirectedLink', 'authored by'),
(354, 'A137', 'Node', 'Author', 'Hao Wu'),
(355, 'E154', 'Edge', 'DirectedLink', 'authored by'),
(356, 'A138', 'Node', 'Author', 'Radhika R. Chilka'),
(357, 'E155', 'Edge', 'DirectedLink', 'authored by'),
(358, 'A139', 'Node', 'Author', 'John R. Talburt'),
(359, 'E156', 'Edge', 'DirectedLink', 'authored by'),
(360, 'P48', 'Node', 'Publication', 'Non-uniform resampling in perspective compensated large scale 3D visualization.'),
(361, 'A140', 'Node', 'Author', 'Maria Shcherban'),
(362, 'E157', 'Edge', 'DirectedLink', 'authored by'),
(363, 'A141', 'Node', 'Author', 'Olli Suominen'),
(364, 'E158', 'Edge', 'DirectedLink', 'authored by'),
(365, 'A142', 'Node', 'Author', 'Atanas P. Gotchev'),
(366, 'E159', 'Edge', 'DirectedLink', 'authored by'),
(367, 'P49', 'Node', 'Publication', 'Stories from the Workplace: Using Mini-Modules Online to Increase Student Motivation and Learning.'),
(368, 'A143', 'Node', 'Author', 'Jonathan Balzotti'),
(369, 'E160', 'Edge', 'DirectedLink', 'authored by'),
(370, 'A144', 'Node', 'Author', 'Janet Roberts'),
(371, 'E161', 'Edge', 'DirectedLink', 'authored by'),
(372, 'P50', 'Node', 'Publication', 'A 0.75V 325µW 40dB-SFDR frequency-hopping synthesizer for wireless sensor networks in 90nm CMOS.'),
(373, 'A145', 'Node', 'Author', 'Emanuele Lopelli'),
(374, 'E162', 'Edge', 'DirectedLink', 'authored by'),
(375, 'A146', 'Node', 'Author', 'Johan van der Tang'),
(376, 'E163', 'Edge', 'DirectedLink', 'authored by'),
(377, 'A147', 'Node', 'Author', 'Kathleen Philips'),
(378, 'E164', 'Edge', 'DirectedLink', 'authored by'),
(379, 'A148', 'Node', 'Author', 'Arthur H. M. van Roermund'),
(380, 'E165', 'Edge', 'DirectedLink', 'authored by'),
(381, 'A149', 'Node', 'Author', 'Bert Gyselinckx'),
(382, 'E166', 'Edge', 'DirectedLink', 'authored by'),
(383, 'P51', 'Node', 'Publication', 'On multicast beamforming for minimum outage.'),
(384, 'A150', 'Node', 'Author', 'Vassilis Ntranos'),
(385, 'E167', 'Edge', 'DirectedLink', 'authored by'),
(386, 'A151', 'Node', 'Author', 'Nicholas D. Sidiropoulos'),
(387, 'E168', 'Edge', 'DirectedLink', 'authored by'),
(388, 'A152', 'Node', 'Author', 'Leandros Tassiulas'),
(389, 'E169', 'Edge', 'DirectedLink', 'authored by'),
(390, 'V17', 'Node', 'Venue', 'IEEE Trans. Wireless Communications'),
(391, 'E170', 'Edge', 'DirectedLink', 'published in'),
(392, 'P52', 'Node', 'Publication', 'Enabling Secure Discovery in a Pervasive Environment.'),
(393, 'A153', 'Node', 'Author', 'Slim Trabelsi'),
(394, 'E171', 'Edge', 'DirectedLink', 'authored by'),
(395, 'A154', 'Node', 'Author', 'Jean-Christophe R. Pazzaglia'),
(396, 'E172', 'Edge', 'DirectedLink', 'authored by'),
(397, 'A155', 'Node', 'Author', 'Yves Roudier'),
(398, 'E173', 'Edge', 'DirectedLink', 'authored by'),
(399, 'P53', 'Node', 'Publication', 'MJRTY: A Fast Majority Vote Algorithm.'),
(400, 'A156', 'Node', 'Author', 'Robert S. Boyer'),
(401, 'E174', 'Edge', 'DirectedLink', 'authored by'),
(402, 'A157', 'Node', 'Author', 'J. Strother Moore'),
(403, 'E175', 'Edge', 'DirectedLink', 'authored by'),
(404, 'P54', 'Node', 'Publication', 'Early Fault Detection in DSLs Using SMT Solving and Automated Debugging.'),
(405, 'A158', 'Node', 'Author', 'Sarmen Keshishzadeh'),
(406, 'E176', 'Edge', 'DirectedLink', 'authored by'),
(407, 'A159', 'Node', 'Author', 'Arjan J. Mooij'),
(408, 'E177', 'Edge', 'DirectedLink', 'authored by'),
(409, 'A160', 'Node', 'Author', 'Mohammad Reza Mousavi'),
(410, 'E178', 'Edge', 'DirectedLink', 'authored by'),
(411, 'P55', 'Node', 'Publication', 'Graph Transformation with Dependencies for the Specification of Interactive Systems.'),
(412, 'A161', 'Node', 'Author', 'Andrea Corradini'),
(413, 'E179', 'Edge', 'DirectedLink', 'authored by'),
(414, 'A162', 'Node', 'Author', 'Luciana Foss'),
(415, 'E180', 'Edge', 'DirectedLink', 'authored by'),
(416, 'A163', 'Node', 'Author', 'Leila Ribeiro'),
(417, 'E181', 'Edge', 'DirectedLink', 'authored by'),
(418, 'P56', 'Node', 'Publication', 'A Cooperative Framework of Service Chain for Digital Library.'),
(419, 'A164', 'Node', 'Author', 'Mengxing Huang'),
(420, 'E182', 'Edge', 'DirectedLink', 'authored by'),
(421, 'A165', 'Node', 'Author', 'Chunxiao Xing'),
(422, 'E183', 'Edge', 'DirectedLink', 'authored by'),
(423, 'A166', 'Node', 'Author', 'Ji-Jiang Yang'),
(424, 'E184', 'Edge', 'DirectedLink', 'authored by'),
(425, 'P57', 'Node', 'Publication', 'A combined approach of scheduling and power distribution strategies for MIMO mobile communications system.'),
(426, 'A167', 'Node', 'Author', 'Xinsheng Zhao'),
(427, 'E185', 'Edge', 'DirectedLink', 'authored by'),
(428, 'A168', 'Node', 'Author', 'Tao Ju'),
(429, 'E186', 'Edge', 'DirectedLink', 'authored by'),
(430, 'A169', 'Node', 'Author', 'Hui Li'),
(431, 'E187', 'Edge', 'DirectedLink', 'authored by'),
(432, 'A170', 'Node', 'Author', 'Chang Xin'),
(433, 'E188', 'Edge', 'DirectedLink', 'authored by'),
(434, 'A171', 'Node', 'Author', 'Egon Schulz'),
(435, 'E189', 'Edge', 'DirectedLink', 'authored by'),
(436, 'P58', 'Node', 'Publication', 'On the Degenerate Crossing Number.'),
(437, 'A172', 'Node', 'Author', 'Eyal Ackerman'),
(438, 'E190', 'Edge', 'DirectedLink', 'authored by'),
(439, 'A173', 'Node', 'Author', 'Rom Pinchasi'),
(440, 'E191', 'Edge', 'DirectedLink', 'authored by'),
(441, 'V18', 'Node', 'Venue', 'Discrete & Computational Geometry'),
(442, 'E192', 'Edge', 'DirectedLink', 'published in'),
(443, 'P59', 'Node', 'Publication', 'Essentials for blended learning - By Jared Stein and Charles R Graham.'),
(444, 'A174', 'Node', 'Author', 'Jesús García Laborda'),
(445, 'E193', 'Edge', 'DirectedLink', 'authored by'),
(446, 'V19', 'Node', 'Venue', 'BJET'),
(447, 'E194', 'Edge', 'DirectedLink', 'published in'),
(448, 'P60', 'Node', 'Publication', 'Performance Characteristics of End-Stations in an ATM Network as Viewed by Applications and Network.'),
(449, 'A175', 'Node', 'Author', 'Baiju V. Patel'),
(450, 'E195', 'Edge', 'DirectedLink', 'authored by'),
(451, 'A176', 'Node', 'Author', 'Chatschik Bisdikian'),
(452, 'E196', 'Edge', 'DirectedLink', 'authored by'),
(453, 'P61', 'Node', 'Publication', 'Lagrange/Vandermonde MUI eliminating user codes for quasi-synchronous CDMA in unknown multipath.'),
(454, 'A177', 'Node', 'Author', 'Anna Scaglione'),
(455, 'E197', 'Edge', 'DirectedLink', 'authored by'),
(456, 'A178', 'Node', 'Author', 'Georgios B. Giannakis'),
(457, 'E198', 'Edge', 'DirectedLink', 'authored by'),
(458, 'A179', 'Node', 'Author', 'Sergio Barbarossa'),
(459, 'E199', 'Edge', 'DirectedLink', 'authored by'),
(460, 'V20', 'Node', 'Venue', 'IEEE Trans. Signal Processing'),
(461, 'E200', 'Edge', 'DirectedLink', 'published in'),
(462, 'P62', 'Node', 'Publication', 'Dealing with missing values in large-scale studies: microarray data imputation and beyond.'),
(463, 'A180', 'Node', 'Author', 'Tero Aittokallio'),
(464, 'E201', 'Edge', 'DirectedLink', 'authored by'),
(465, 'V21', 'Node', 'Venue', 'Briefings in Bioinformatics'),
(466, 'E202', 'Edge', 'DirectedLink', 'published in'),
(467, 'P63', 'Node', 'Publication', 'The LP-OD System: Logic Programming Meets Outlier Detection.'),
(468, 'A181', 'Node', 'Author', 'Fabrizio Angiulli'),
(469, 'E203', 'Edge', 'DirectedLink', 'authored by'),
(470, 'A182', 'Node', 'Author', 'Gianluigi Greco'),
(471, 'E204', 'Edge', 'DirectedLink', 'authored by'),
(472, 'A183', 'Node', 'Author', 'Luigi Palopoli'),
(473, 'E205', 'Edge', 'DirectedLink', 'authored by'),
(474, 'A184', 'Node', 'Author', 'Domenico Trimboli'),
(475, 'E206', 'Edge', 'DirectedLink', 'authored by'),
(476, 'P64', 'Node', 'Publication', 'Impacts of NBTI/PBTI and Contact Resistance on Power-Gated SRAM With High-kappa Metal-Gate Devices.'),
(477, 'A185', 'Node', 'Author', 'Hao-I Yang'),
(478, 'E207', 'Edge', 'DirectedLink', 'authored by'),
(479, 'A186', 'Node', 'Author', 'Wei Hwang'),
(480, 'E208', 'Edge', 'DirectedLink', 'authored by'),
(481, 'A187', 'Node', 'Author', 'Ching-Te Chuang'),
(482, 'E209', 'Edge', 'DirectedLink', 'authored by'),
(483, 'V22', 'Node', 'Venue', 'IEEE Trans. VLSI Syst.'),
(484, 'E210', 'Edge', 'DirectedLink', 'published in'),
(485, 'P65', 'Node', 'Publication', 'Joint assessment of Network- and Perceived-QoS in video delivery networks.'),
(486, 'A188', 'Node', 'Author', 'Georgios Gardikis'),
(487, 'E211', 'Edge', 'DirectedLink', 'authored by'),
(488, 'A189', 'Node', 'Author', 'Georgios Xilouris'),
(489, 'E212', 'Edge', 'DirectedLink', 'authored by'),
(490, 'A190', 'Node', 'Author', 'Evangelos Pallis'),
(491, 'E213', 'Edge', 'DirectedLink', 'authored by'),
(492, 'A191', 'Node', 'Author', 'Anastasios Kourtis'),
(493, 'E214', 'Edge', 'DirectedLink', 'authored by'),
(494, 'V23', 'Node', 'Venue', 'Telecommunication Systems'),
(495, 'E215', 'Edge', 'DirectedLink', 'published in'),
(496, 'P66', 'Node', 'Publication', 'Combining Negative Binomial and Weibull Distributions for Yield and Reliability Prediction.'),
(497, 'A192', 'Node', 'Author', 'Thomas S. Barnett'),
(498, 'E216', 'Edge', 'DirectedLink', 'authored by'),
(499, 'A193', 'Node', 'Author', 'Matt Grady'),
(500, 'E217', 'Edge', 'DirectedLink', 'authored by'),
(501, 'A194', 'Node', 'Author', 'Kathleen G. Purdy'),
(502, 'E218', 'Edge', 'DirectedLink', 'authored by'),
(503, 'A195', 'Node', 'Author', 'Adit D. Singh'),
(504, 'E219', 'Edge', 'DirectedLink', 'authored by'),
(505, 'V24', 'Node', 'Venue', 'IEEE Design & Test of Computers'),
(506, 'E220', 'Edge', 'DirectedLink', 'published in'),
(507, 'P67', 'Node', 'Publication', 'Automated classification of non-functional requirements.'),
(508, 'A196', 'Node', 'Author', 'Jane Cleland-Huang'),
(509, 'E221', 'Edge', 'DirectedLink', 'authored by'),
(510, 'A197', 'Node', 'Author', 'Raffaella Settimi'),
(511, 'E222', 'Edge', 'DirectedLink', 'authored by'),
(512, 'A198', 'Node', 'Author', 'Xuchang Zou'),
(513, 'E223', 'Edge', 'DirectedLink', 'authored by'),
(514, 'A199', 'Node', 'Author', 'Peter Solc'),
(515, 'E224', 'Edge', 'DirectedLink', 'authored by'),
(516, 'V25', 'Node', 'Venue', 'Requir. Eng.'),
(517, 'E225', 'Edge', 'DirectedLink', 'published in'),
(518, 'P68', 'Node', 'Publication', 'A satisfaction-based model for affect recognition from conversational features in spoken dialog systems.'),
(519, 'A200', 'Node', 'Author', 'Syaheerah Lebai Lutfi'),
(520, 'E226', 'Edge', 'DirectedLink', 'authored by'),
(521, 'A201', 'Node', 'Author', 'Fernando Fernández-Martínez'),
(522, 'E227', 'Edge', 'DirectedLink', 'authored by'),
(523, 'A202', 'Node', 'Author', 'Juan Manuel Lucas-Cuesta'),
(524, 'E228', 'Edge', 'DirectedLink', 'authored by'),
(525, 'A203', 'Node', 'Author', 'Lorena Lopez-Lebon'),
(526, 'E229', 'Edge', 'DirectedLink', 'authored by'),
(527, 'A204', 'Node', 'Author', 'Juan Manuel Montero'),
(528, 'E230', 'Edge', 'DirectedLink', 'authored by'),
(529, 'V26', 'Node', 'Venue', 'Speech Communication'),
(530, 'E231', 'Edge', 'DirectedLink', 'published in'),
(531, 'P69', 'Node', 'Publication', 'Hybrid Partition Inverted Files: Experimental Validation.'),
(532, 'A205', 'Node', 'Author', 'Wensi Xi'),
(533, 'E232', 'Edge', 'DirectedLink', 'authored by'),
(534, 'A206', 'Node', 'Author', 'Ohm Sornil'),
(535, 'E233', 'Edge', 'DirectedLink', 'authored by'),
(536, 'A207', 'Node', 'Author', 'Ming Luo'),
(537, 'E234', 'Edge', 'DirectedLink', 'authored by'),
(538, 'A208', 'Node', 'Author', 'Edward A. Fox'),
(539, 'E235', 'Edge', 'DirectedLink', 'authored by'),
(540, 'P70', 'Node', 'Publication', 'Product and Service Performance Model for Information Quality: An Update.'),
(541, 'A209', 'Node', 'Author', 'Beverly K. Kahn'),
(542, 'E236', 'Edge', 'DirectedLink', 'authored by'),
(543, 'A210', 'Node', 'Author', 'Diane M. Strong'),
(544, 'E237', 'Edge', 'DirectedLink', 'authored by'),
(545, 'P71', 'Node', 'Publication', 'On the Complexity of Approximating the Independent Set Problem'),
(546, 'A211', 'Node', 'Author', 'Piotr Berman'),
(547, 'E238', 'Edge', 'DirectedLink', 'authored by'),
(548, 'A212', 'Node', 'Author', 'Georg Schnitger'),
(549, 'E239', 'Edge', 'DirectedLink', 'authored by'),
(550, 'V27', 'Node', 'Venue', 'Inf. Comput.'),
(551, 'E240', 'Edge', 'DirectedLink', 'published in'),
(552, 'P72', 'Node', 'Publication', 'Information Filtering: Selection Mechanisms in Learning Systems.'),
(553, 'A213', 'Node', 'Author', 'Shaul Markovitch'),
(554, 'E241', 'Edge', 'DirectedLink', 'authored by'),
(555, 'A214', 'Node', 'Author', 'Paul D. Scott'),
(556, 'E242', 'Edge', 'DirectedLink', 'authored by'),
(557, 'V28', 'Node', 'Venue', 'Machine Learning'),
(558, 'E243', 'Edge', 'DirectedLink', 'published in'),
(559, 'P73', 'Node', 'Publication', 'Tenant Oriented Lock Concurrency Control in the Shared Storage Multi-tenant Database.'),
(560, 'A215', 'Node', 'Author', 'Chengliang Sang'),
(561, 'E244', 'Edge', 'DirectedLink', 'authored by'),
(562, 'A216', 'Node', 'Author', 'Qingzhong Li'),
(563, 'E245', 'Edge', 'DirectedLink', 'authored by'),
(564, 'A217', 'Node', 'Author', 'Lanju Kong'),
(565, 'E246', 'Edge', 'DirectedLink', 'authored by'),
(566, 'P74', 'Node', 'Publication', 'Process for selecting and implementing a manuscript management system: Experiences of a new peer-reviewed journal.'),
(567, 'A218', 'Node', 'Author', 'Ruwaida M. Salem'),
(568, 'E247', 'Edge', 'DirectedLink', 'authored by'),
(569, 'A219', 'Node', 'Author', 'Natalie M. Culbertson'),
(570, 'E248', 'Edge', 'DirectedLink', 'authored by'),
(571, 'A220', 'Node', 'Author', 'Alison O''Connell'),
(572, 'E249', 'Edge', 'DirectedLink', 'authored by'),
(573, 'V29', 'Node', 'Venue', 'Learned Publishing'),
(574, 'E250', 'Edge', 'DirectedLink', 'published in'),
(575, 'P75', 'Node', 'Publication', 'DENdb: database of integrated human enhancers.'),
(576, 'A221', 'Node', 'Author', 'Haitham Ashoor'),
(577, 'E251', 'Edge', 'DirectedLink', 'authored by'),
(578, 'A222', 'Node', 'Author', 'Dimitrios Kleftogiannis'),
(579, 'E252', 'Edge', 'DirectedLink', 'authored by'),
(580, 'A223', 'Node', 'Author', 'Aleksandar Radovanovic'),
(581, 'E253', 'Edge', 'DirectedLink', 'authored by'),
(582, 'A224', 'Node', 'Author', 'Vladimir B. Bajic'),
(583, 'E254', 'Edge', 'DirectedLink', 'authored by'),
(584, 'V30', 'Node', 'Venue', 'Database'),
(585, 'E255', 'Edge', 'DirectedLink', 'published in'),
(586, 'P76', 'Node', 'Publication', 'Detecting Densely Distributed Graph Patterns for Fine-Grained Image Categorization.'),
(587, 'A225', 'Node', 'Author', 'Luming Zhang'),
(588, 'E256', 'Edge', 'DirectedLink', 'authored by'),
(589, 'A226', 'Node', 'Author', 'Yang Yang 0002'),
(590, 'E257', 'Edge', 'DirectedLink', 'authored by'),
(591, 'A227', 'Node', 'Author', 'Meng Wang'),
(592, 'E258', 'Edge', 'DirectedLink', 'authored by'),
(593, 'A228', 'Node', 'Author', 'Richang Hong'),
(594, 'E259', 'Edge', 'DirectedLink', 'authored by'),
(595, 'A229', 'Node', 'Author', 'Liqiang Nie'),
(596, 'E260', 'Edge', 'DirectedLink', 'authored by'),
(597, 'A230', 'Node', 'Author', 'Xuelong Li'),
(598, 'E261', 'Edge', 'DirectedLink', 'authored by'),
(599, 'V31', 'Node', 'Venue', 'IEEE Trans. Image Processing'),
(600, 'E262', 'Edge', 'DirectedLink', 'published in'),
(601, 'P77', 'Node', 'Publication', 'Reality check: an informal feedback tool.'),
(602, 'A231', 'Node', 'Author', 'Scott Grissom'),
(603, 'E263', 'Edge', 'DirectedLink', 'authored by'),
(604, 'P78', 'Node', 'Publication', 'Using Multiple Case Studies to Analyse Open Source Software Business Sustainability in Sub-Saharan Africa.'),
(605, 'A232', 'Node', 'Author', 'Sulayman K. Sowe'),
(606, 'E264', 'Edge', 'DirectedLink', 'authored by'),
(607, 'A233', 'Node', 'Author', 'Maurice McNaughton'),
(608, 'E265', 'Edge', 'DirectedLink', 'authored by'),
(609, 'P79', 'Node', 'Publication', 'Combining local appearance and holistic view: Dual-Source Deep Neural Networks for human pose estimation.'),
(610, 'A234', 'Node', 'Author', 'Xiaochuan Fan'),
(611, 'E266', 'Edge', 'DirectedLink', 'authored by'),
(612, 'A235', 'Node', 'Author', 'Kang Zheng'),
(613, 'E267', 'Edge', 'DirectedLink', 'authored by'),
(614, 'A236', 'Node', 'Author', 'Yuewei Lin'),
(615, 'E268', 'Edge', 'DirectedLink', 'authored by'),
(616, 'A237', 'Node', 'Author', 'Song Wang'),
(617, 'E269', 'Edge', 'DirectedLink', 'authored by'),
(618, 'P80', 'Node', 'Publication', 'Smart home web of objects-based IoT management model and methods for home data mining.'),
(619, 'A238', 'Node', 'Author', 'Jeu-Young Kim'),
(620, 'E270', 'Edge', 'DirectedLink', 'authored by'),
(621, 'A239', 'Node', 'Author', 'Hark-Jin Lee'),
(622, 'E271', 'Edge', 'DirectedLink', 'authored by'),
(623, 'A240', 'Node', 'Author', 'Jiyeon Son'),
(624, 'E272', 'Edge', 'DirectedLink', 'authored by'),
(625, 'A241', 'Node', 'Author', 'Jun-Hee Park'),
(626, 'E273', 'Edge', 'DirectedLink', 'authored by'),
(627, 'P81', 'Node', 'Publication', 'Using Object-Oriented Materialized Views to Answer Selection-Based Complex Queries.'),
(628, 'A242', 'Node', 'Author', 'Reda Alhajj'),
(629, 'E274', 'Edge', 'DirectedLink', 'authored by'),
(630, 'A243', 'Node', 'Author', 'Faruk Polat'),
(631, 'E275', 'Edge', 'DirectedLink', 'authored by'),
(632, 'V32', 'Node', 'Venue', 'Inf. Sci.'),
(633, 'E276', 'Edge', 'DirectedLink', 'published in'),
(634, 'P82', 'Node', 'Publication', 'Clustering Ensemble Tracking.'),
(635, 'A244', 'Node', 'Author', 'Guibo Zhu'),
(636, 'E277', 'Edge', 'DirectedLink', 'authored by'),
(637, 'A245', 'Node', 'Author', 'Jinqiao Wang'),
(638, 'E278', 'Edge', 'DirectedLink', 'authored by'),
(639, 'A246', 'Node', 'Author', 'Hanqing Lu'),
(640, 'E279', 'Edge', 'DirectedLink', 'authored by'),
(641, 'P83', 'Node', 'Publication', 'A data mining approach for analyzing semiconductor MES and FDC data to enhance overall usage effectiveness (OUE).'),
(642, 'A247', 'Node', 'Author', 'Chen-Fu Chien'),
(643, 'E280', 'Edge', 'DirectedLink', 'authored by'),
(644, 'A248', 'Node', 'Author', 'Alejandra Campero Diaz'),
(645, 'E281', 'Edge', 'DirectedLink', 'authored by'),
(646, 'A249', 'Node', 'Author', 'Yu-Bin Lan'),
(647, 'E282', 'Edge', 'DirectedLink', 'authored by'),
(648, 'V33', 'Node', 'Venue', 'Int. J. Computational Intelligence Systems'),
(649, 'E283', 'Edge', 'DirectedLink', 'published in'),
(650, 'P84', 'Node', 'Publication', 'Preference-Directed Graph Coloring.'),
(651, 'A250', 'Node', 'Author', 'Akira Koseki'),
(652, 'E284', 'Edge', 'DirectedLink', 'authored by'),
(653, 'A251', 'Node', 'Author', 'Hideaki Komatsu'),
(654, 'E285', 'Edge', 'DirectedLink', 'authored by'),
(655, 'A252', 'Node', 'Author', 'Toshio Nakatani'),
(656, 'E286', 'Edge', 'DirectedLink', 'authored by'),
(657, 'P85', 'Node', 'Publication', 'System for Oriya handwritten numeral recognition.'),
(658, 'A253', 'Node', 'Author', 'Nilamadhaba Tripathy'),
(659, 'E287', 'Edge', 'DirectedLink', 'authored by'),
(660, 'A254', 'Node', 'Author', 'M. Panda'),
(661, 'E288', 'Edge', 'DirectedLink', 'authored by'),
(662, 'A255', 'Node', 'Author', 'Umapada Pal'),
(663, 'E289', 'Edge', 'DirectedLink', 'authored by'),
(664, 'P86', 'Node', 'Publication', 'Hidden Harmony - Geometric Fantasies: The Rise of Complex Function Theory.'),
(665, 'A256', 'Node', 'Author', 'Gerald B. Folland'),
(666, 'E290', 'Edge', 'DirectedLink', 'authored by'),
(667, 'V34', 'Node', 'Venue', 'The American Mathematical Monthly'),
(668, 'E291', 'Edge', 'DirectedLink', 'published in'),
(669, 'P87', 'Node', 'Publication', 'Emergence of Names and Compositionality.'),
(670, 'A257', 'Node', 'Author', 'Jaak Henno'),
(671, 'E292', 'Edge', 'DirectedLink', 'authored by'),
(672, 'P88', 'Node', 'Publication', 'Integrated Configuration of Platform Products and Supply Chains for Mass Customization: A Game-Theoretic Approach.'),
(673, 'A258', 'Node', 'Author', 'George Q. Huang'),
(674, 'E293', 'Edge', 'DirectedLink', 'authored by'),
(675, 'A259', 'Node', 'Author', 'Xinyan Zhang'),
(676, 'E294', 'Edge', 'DirectedLink', 'authored by'),
(677, 'A260', 'Node', 'Author', 'Victor H. Y. Lo'),
(678, 'E295', 'Edge', 'DirectedLink', 'authored by'),
(679, 'V35', 'Node', 'Venue', 'IEEE Trans. Engineering Management'),
(680, 'E296', 'Edge', 'DirectedLink', 'published in'),
(681, 'P89', 'Node', 'Publication', 'ISCSLP SR Evaluation, UVA-CS_es System Description. A System Based on ANNs.'),
(682, 'A261', 'Node', 'Author', 'Carlos E. Vivaracho'),
(683, 'E297', 'Edge', 'DirectedLink', 'authored by'),
(684, 'P90', 'Node', 'Publication', 'Face recognition in multi-camera surveillance videos.'),
(685, 'A262', 'Node', 'Author', 'Le An'),
(686, 'E298', 'Edge', 'DirectedLink', 'authored by'),
(687, 'A263', 'Node', 'Author', 'Bir Bhanu'),
(688, 'E299', 'Edge', 'DirectedLink', 'authored by'),
(689, 'A264', 'Node', 'Author', 'Songfan Yang'),
(690, 'E300', 'Edge', 'DirectedLink', 'authored by'),
(691, 'P91', 'Node', 'Publication', 'Multiterminal source coding with complementary delivery'),
(692, 'A265', 'Node', 'Author', 'Akisato Kimura'),
(693, 'E301', 'Edge', 'DirectedLink', 'authored by'),
(694, 'A266', 'Node', 'Author', 'Tomohiko Uyematsu'),
(695, 'E302', 'Edge', 'DirectedLink', 'authored by'),
(696, 'V36', 'Node', 'Venue', 'CoRR'),
(697, 'E303', 'Edge', 'DirectedLink', 'published in'),
(698, 'P92', 'Node', 'Publication', 'Marker-assisted recognition of dynamic content in public spaces.'),
(699, 'A267', 'Node', 'Author', 'Andréa Britto Mattos'),
(700, 'E304', 'Edge', 'DirectedLink', 'authored by'),
(701, 'A268', 'Node', 'Author', 'Ricardo Herrmann'),
(702, 'E305', 'Edge', 'DirectedLink', 'authored by'),
(703, 'A269', 'Node', 'Author', 'Carlos Cardonha'),
(704, 'E306', 'Edge', 'DirectedLink', 'authored by'),
(705, 'A270', 'Node', 'Author', 'Diego Gallo'),
(706, 'E307', 'Edge', 'DirectedLink', 'authored by'),
(707, 'A271', 'Node', 'Author', 'Priscilla Avegliano'),
(708, 'E308', 'Edge', 'DirectedLink', 'authored by'),
(709, 'A272', 'Node', 'Author', 'Sergio Borger'),
(710, 'E309', 'Edge', 'DirectedLink', 'authored by'),
(711, 'P93', 'Node', 'Publication', 'The SIGRIS Project: A Remote Sensing System for Seismic Risk Management.'),
(712, 'A273', 'Node', 'Author', 'Marco Chini'),
(713, 'E310', 'Edge', 'DirectedLink', 'authored by'),
(714, 'A274', 'Node', 'Author', 'Christian Bignami'),
(715, 'E311', 'Edge', 'DirectedLink', 'authored by'),
(716, 'A275', 'Node', 'Author', 'Simone Atzori'),
(717, 'E312', 'Edge', 'DirectedLink', 'authored by'),
(718, 'A276', 'Node', 'Author', 'Carlo Alberto Brunori'),
(719, 'E313', 'Edge', 'DirectedLink', 'authored by'),
(720, 'A277', 'Node', 'Author', 'Christodoulos Kyriakopoulos'),
(721, 'E314', 'Edge', 'DirectedLink', 'authored by'),
(722, 'A278', 'Node', 'Author', 'Marco Moro'),
(723, 'E315', 'Edge', 'DirectedLink', 'authored by'),
(724, 'A279', 'Node', 'Author', 'Stefano Salvi'),
(725, 'E316', 'Edge', 'DirectedLink', 'authored by'),
(726, 'A280', 'Node', 'Author', 'Salvatore Stramondo'),
(727, 'E317', 'Edge', 'DirectedLink', 'authored by'),
(728, 'A281', 'Node', 'Author', 'Cristiano Tolomei'),
(729, 'E318', 'Edge', 'DirectedLink', 'authored by'),
(730, 'A282', 'Node', 'Author', 'Elisa Trasatti'),
(731, 'E319', 'Edge', 'DirectedLink', 'authored by'),
(732, 'A283', 'Node', 'Author', 'Simona Zoffoli'),
(733, 'E320', 'Edge', 'DirectedLink', 'authored by'),
(734, 'P94', 'Node', 'Publication', 'Overcoming Barriers To Knowledge Flow: Evidence-Based Attributes Enabling The Creation, Mobilization, and Diffusion of Knowledge.'),
(735, 'A284', 'Node', 'Author', 'Satrijo Tanudjojo'),
(736, 'E321', 'Edge', 'DirectedLink', 'authored by'),
(737, 'A285', 'Node', 'Author', 'Ashley Braganza'),
(738, 'E322', 'Edge', 'DirectedLink', 'authored by'),
(739, 'P95', 'Node', 'Publication', 'The application of a multi-attribute decision-making algorithm to learning management systems evaluation.'),
(740, 'A286', 'Node', 'Author', 'Nadire Cavus'),
(741, 'E323', 'Edge', 'DirectedLink', 'authored by'),
(742, 'E324', 'Edge', 'DirectedLink', 'published in'),
(743, 'P96', 'Node', 'Publication', 'To java.net and beyond: teaching networking concepts using the Java networking API.'),
(744, 'A287', 'Node', 'Author', 'Greg Gagne'),
(745, 'E325', 'Edge', 'DirectedLink', 'authored by'),
(746, 'P97', 'Node', 'Publication', 'An Object-Based Mode Decision Algorithm for Multi-view Video Coding.'),
(747, 'A288', 'Node', 'Author', 'Seo-Young Lee'),
(748, 'E326', 'Edge', 'DirectedLink', 'authored by'),
(749, 'A289', 'Node', 'Author', 'Kwang-Mu Shin'),
(750, 'E327', 'Edge', 'DirectedLink', 'authored by'),
(751, 'A290', 'Node', 'Author', 'Ki-Dong Chung'),
(752, 'E328', 'Edge', 'DirectedLink', 'authored by'),
(753, 'P98', 'Node', 'Publication', 'Relaxed and Hybridized Backstepping.'),
(754, 'A291', 'Node', 'Author', 'Humberto Stein Shiromoto'),
(755, 'E329', 'Edge', 'DirectedLink', 'authored by'),
(756, 'A292', 'Node', 'Author', 'Vincent Andrieu'),
(757, 'E330', 'Edge', 'DirectedLink', 'authored by'),
(758, 'A293', 'Node', 'Author', 'Christophe Prieur'),
(759, 'E331', 'Edge', 'DirectedLink', 'authored by'),
(760, 'E332', 'Edge', 'DirectedLink', 'published in'),
(761, 'P99', 'Node', 'Publication', 'Selection for Feature Gene Subset in Microarray Expression Profiles Based on an Improved Genetic Algorithm.'),
(762, 'A294', 'Node', 'Author', 'Chen Zhang'),
(763, 'E333', 'Edge', 'DirectedLink', 'authored by'),
(764, 'A295', 'Node', 'Author', 'Yanchun Liang'),
(765, 'E334', 'Edge', 'DirectedLink', 'authored by'),
(766, 'A296', 'Node', 'Author', 'Wei Xiong'),
(767, 'E335', 'Edge', 'DirectedLink', 'authored by'),
(768, 'A297', 'Node', 'Author', 'Hong-Wei Ge'),
(769, 'E336', 'Edge', 'DirectedLink', 'authored by'),
(770, 'P100', 'Node', 'Publication', 'Performance evaluation of a community structure finding algorithm using modularity and C-rand measures.'),
(771, 'A298', 'Node', 'Author', 'Harun Pirim'),
(772, 'E337', 'Edge', 'DirectedLink', 'authored by'),
(773, 'A299', 'Node', 'Author', 'Dilip Gautam'),
(774, 'E338', 'Edge', 'DirectedLink', 'authored by'),
(775, 'A300', 'Node', 'Author', 'Tanmay Bhowmik'),
(776, 'E339', 'Edge', 'DirectedLink', 'authored by'),
(777, 'A301', 'Node', 'Author', 'Andy D. Perkins'),
(778, 'E340', 'Edge', 'DirectedLink', 'authored by'),
(779, 'A302', 'Node', 'Author', 'Burak Eksioglu'),
(780, 'E341', 'Edge', 'DirectedLink', 'authored by');

-- --------------------------------------------------------

--
-- Table structure for table `Graph`
--

CREATE TABLE IF NOT EXISTS `Graph` (
  `ID` bigint(20) unsigned NOT NULL,
  `NodeFrom` text NOT NULL,
  `Edge` text NOT NULL,
  `NodeTo` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Graph`
--

INSERT INTO `Graph` (`ID`, `NodeFrom`, `Edge`, `NodeTo`) VALUES
(1, 'P2', 'E1', 'A1'),
(2, 'P2', 'E2', 'A2'),
(3, 'P3', 'E3', 'A3'),
(4, 'P3', 'E4', 'A4'),
(5, 'P3', 'E5', 'A5'),
(6, 'P3', 'E6', 'A6'),
(7, 'P4', 'E7', 'A7'),
(8, 'P4', 'E8', 'A8'),
(9, 'P4', 'E9', 'A9'),
(10, 'P4', 'E10', 'A10'),
(11, 'P4', 'E11', 'A11'),
(12, 'P5', 'E12', 'A12'),
(13, 'P6', 'E13', 'A13'),
(14, 'P6', 'E14', 'A14'),
(15, 'P6', 'E15', 'A15'),
(16, 'P6', 'E16', 'A16'),
(17, 'P7', 'E17', 'A17'),
(18, 'A17', 'E18', 'S1'),
(19, 'P8', 'E19', 'A18'),
(20, 'P9', 'E20', 'A19'),
(21, 'P9', 'E21', 'A20'),
(22, 'P10', 'E22', 'A21'),
(23, 'P11', 'E23', 'A22'),
(24, 'P11', 'E24', 'A23'),
(25, 'P11', 'E25', 'V1'),
(26, 'P12', 'E26', 'A24'),
(27, 'P12', 'E27', 'A25'),
(28, 'P12', 'E28', 'A26'),
(29, 'P12', 'E29', 'V2'),
(30, 'P13', 'E30', 'A27'),
(31, 'P13', 'E31', 'A28'),
(32, 'P13', 'E32', 'V3'),
(33, 'P14', 'E33', 'A29'),
(34, 'P14', 'E34', 'A30'),
(35, 'P14', 'E35', 'A31'),
(36, 'P15', 'E36', 'A32'),
(37, 'P15', 'E37', 'A33'),
(38, 'P15', 'E38', 'A34'),
(39, 'P15', 'E39', 'V4'),
(40, 'P16', 'E40', 'A35'),
(41, 'P16', 'E41', 'A36'),
(42, 'P16', 'E42', 'A37'),
(43, 'P17', 'E43', 'A38'),
(44, 'P17', 'E44', 'A39'),
(45, 'P17', 'E45', 'A40'),
(46, 'P17', 'E46', 'A41'),
(47, 'P17', 'E47', 'V5'),
(48, 'P18', 'E48', 'A42'),
(49, 'P18', 'E49', 'A43'),
(50, 'P18', 'E50', 'A44'),
(51, 'P18', 'E51', 'A45'),
(52, 'P18', 'E52', 'A46'),
(53, 'P18', 'E53', 'A47'),
(54, 'P19', 'E54', 'A48'),
(55, 'P19', 'E55', 'A49'),
(56, 'P19', 'E56', 'A50'),
(57, 'P20', 'E57', 'A51'),
(58, 'P21', 'E58', 'A52'),
(59, 'P21', 'E59', 'A53'),
(60, 'P21', 'E60', 'V6'),
(61, 'P22', 'E61', 'A54'),
(62, 'P22', 'E62', 'A55'),
(63, 'P22', 'E63', 'A56'),
(64, 'P23', 'E64', 'A57'),
(65, 'P23', 'E65', 'A58'),
(66, 'P23', 'E66', 'A59'),
(67, 'P23', 'E67', 'V7'),
(68, 'P24', 'E68', 'A60'),
(69, 'P24', 'E69', 'A61'),
(70, 'P24', 'E70', 'A62'),
(71, 'P24', 'E71', 'V8'),
(72, 'P25', 'E72', 'A63'),
(73, 'P25', 'E73', 'A64'),
(74, 'P25', 'E74', 'A65'),
(75, 'P25', 'E75', 'A66'),
(76, 'P25', 'E76', 'A67'),
(77, 'P25', 'E77', 'V9'),
(78, 'P26', 'E78', 'A68'),
(79, 'P26', 'E79', 'A69'),
(80, 'P26', 'E80', 'A70'),
(81, 'P26', 'E81', 'A71'),
(82, 'P26', 'E82', 'A72'),
(83, 'P26', 'E83', 'A73'),
(84, 'P26', 'E84', 'A74'),
(85, 'P26', 'E85', 'A75'),
(86, 'P26', 'E86', 'A76'),
(87, 'P27', 'E87', 'A77'),
(88, 'P28', 'E88', 'A78'),
(89, 'P28', 'E89', 'V10'),
(90, 'P29', 'E90', 'A79'),
(91, 'P29', 'E91', 'A80'),
(92, 'P29', 'E92', 'A81'),
(93, 'P29', 'E93', 'A82'),
(94, 'P29', 'E94', 'A83'),
(95, 'P29', 'E95', 'A84'),
(96, 'P29', 'E96', 'A85'),
(97, 'P30', 'E97', 'A86'),
(98, 'P30', 'E98', 'A87'),
(99, 'P31', 'E99', 'A88'),
(100, 'P31', 'E100', 'A89'),
(101, 'P31', 'E101', 'A90'),
(102, 'P31', 'E102', 'A91'),
(103, 'P32', 'E103', 'A92'),
(104, 'P32', 'E104', 'V11'),
(105, 'P33', 'E105', 'A93'),
(106, 'P33', 'E106', 'A94'),
(107, 'P33', 'E107', 'A95'),
(108, 'P33', 'E108', 'V12'),
(109, 'P34', 'E109', 'A96'),
(110, 'P34', 'E110', 'A97'),
(111, 'P34', 'E111', 'A98'),
(112, 'P34', 'E112', 'A99'),
(113, 'P35', 'E113', 'A100'),
(114, 'P35', 'E114', 'V13'),
(115, 'P36', 'E115', 'A101'),
(116, 'P36', 'E116', 'A102'),
(117, 'P37', 'E117', 'A103'),
(118, 'P37', 'E118', 'A104'),
(119, 'P37', 'E119', 'V14'),
(120, 'P38', 'E120', 'A105'),
(121, 'P38', 'E121', 'A106'),
(122, 'P39', 'E122', 'A107'),
(123, 'P39', 'E123', 'A108'),
(124, 'P39', 'E124', 'A109'),
(125, 'P39', 'E125', 'A110'),
(126, 'P40', 'E126', 'A111'),
(127, 'P40', 'E127', 'A112'),
(128, 'P40', 'E128', 'A113'),
(129, 'P40', 'E129', 'V15'),
(130, 'P41', 'E130', 'A114'),
(131, 'P41', 'E131', 'A115'),
(132, 'P41', 'E132', 'A116'),
(133, 'P41', 'E133', 'A117'),
(134, 'P42', 'E134', 'A118'),
(135, 'P42', 'E135', 'A119'),
(136, 'P43', 'E136', 'A120'),
(137, 'P43', 'E137', 'A121'),
(138, 'P44', 'E138', 'A122'),
(139, 'P44', 'E139', 'A123'),
(140, 'P44', 'E140', 'A124'),
(141, 'P44', 'E141', 'A125'),
(142, 'P45', 'E142', 'A126'),
(143, 'P45', 'E143', 'A127'),
(144, 'P45', 'E144', 'A128'),
(145, 'P45', 'E145', 'A129'),
(146, 'P45', 'E146', 'A130'),
(147, 'P45', 'E147', 'V16'),
(148, 'P46', 'E148', 'A131'),
(149, 'P46', 'E149', 'A132'),
(150, 'P46', 'E150', 'A133'),
(151, 'P46', 'E151', 'A134'),
(152, 'P47', 'E152', 'A135'),
(153, 'P47', 'E153', 'A136'),
(154, 'P47', 'E154', 'A137'),
(155, 'P47', 'E155', 'A138'),
(156, 'P47', 'E156', 'A139'),
(157, 'P48', 'E157', 'A140'),
(158, 'P48', 'E158', 'A141'),
(159, 'P48', 'E159', 'A142'),
(160, 'P49', 'E160', 'A143'),
(161, 'P49', 'E161', 'A144'),
(162, 'P50', 'E162', 'A145'),
(163, 'P50', 'E163', 'A146'),
(164, 'P50', 'E164', 'A147'),
(165, 'P50', 'E165', 'A148'),
(166, 'P50', 'E166', 'A149'),
(167, 'P51', 'E167', 'A150'),
(168, 'P51', 'E168', 'A151'),
(169, 'P51', 'E169', 'A152'),
(170, 'P51', 'E170', 'V17'),
(171, 'P52', 'E171', 'A153'),
(172, 'P52', 'E172', 'A154'),
(173, 'P52', 'E173', 'A155'),
(174, 'P53', 'E174', 'A156'),
(175, 'P53', 'E175', 'A157'),
(176, 'P54', 'E176', 'A158'),
(177, 'P54', 'E177', 'A159'),
(178, 'P54', 'E178', 'A160'),
(179, 'P55', 'E179', 'A161'),
(180, 'P55', 'E180', 'A162'),
(181, 'P55', 'E181', 'A163'),
(182, 'P56', 'E182', 'A164'),
(183, 'P56', 'E183', 'A165'),
(184, 'P56', 'E184', 'A166'),
(185, 'P57', 'E185', 'A167'),
(186, 'P57', 'E186', 'A168'),
(187, 'P57', 'E187', 'A169'),
(188, 'P57', 'E188', 'A170'),
(189, 'P57', 'E189', 'A171'),
(190, 'P58', 'E190', 'A172'),
(191, 'P58', 'E191', 'A173'),
(192, 'P58', 'E192', 'V18'),
(193, 'P59', 'E193', 'A174'),
(194, 'P59', 'E194', 'V19'),
(195, 'P60', 'E195', 'A175'),
(196, 'P60', 'E196', 'A176'),
(197, 'P61', 'E197', 'A177'),
(198, 'P61', 'E198', 'A178'),
(199, 'P61', 'E199', 'A179'),
(200, 'P61', 'E200', 'V20'),
(201, 'P62', 'E201', 'A180'),
(202, 'P62', 'E202', 'V21'),
(203, 'P63', 'E203', 'A181'),
(204, 'P63', 'E204', 'A182'),
(205, 'P63', 'E205', 'A183'),
(206, 'P63', 'E206', 'A184'),
(207, 'P64', 'E207', 'A185'),
(208, 'P64', 'E208', 'A186'),
(209, 'P64', 'E209', 'A187'),
(210, 'P64', 'E210', 'V22'),
(211, 'P65', 'E211', 'A188'),
(212, 'P65', 'E212', 'A189'),
(213, 'P65', 'E213', 'A190'),
(214, 'P65', 'E214', 'A191'),
(215, 'P65', 'E215', 'V23'),
(216, 'P66', 'E216', 'A192'),
(217, 'P66', 'E217', 'A193'),
(218, 'P66', 'E218', 'A194'),
(219, 'P66', 'E219', 'A195'),
(220, 'P66', 'E220', 'V24'),
(221, 'P67', 'E221', 'A196'),
(222, 'P67', 'E222', 'A197'),
(223, 'P67', 'E223', 'A198'),
(224, 'P67', 'E224', 'A199'),
(225, 'P67', 'E225', 'V25'),
(226, 'P68', 'E226', 'A200'),
(227, 'P68', 'E227', 'A201'),
(228, 'P68', 'E228', 'A202'),
(229, 'P68', 'E229', 'A203'),
(230, 'P68', 'E230', 'A204'),
(231, 'P68', 'E231', 'V26'),
(232, 'P69', 'E232', 'A205'),
(233, 'P69', 'E233', 'A206'),
(234, 'P69', 'E234', 'A207'),
(235, 'P69', 'E235', 'A208'),
(236, 'P70', 'E236', 'A209'),
(237, 'P70', 'E237', 'A210'),
(238, 'P71', 'E238', 'A211'),
(239, 'P71', 'E239', 'A212'),
(240, 'P71', 'E240', 'V27'),
(241, 'P72', 'E241', 'A213'),
(242, 'P72', 'E242', 'A214'),
(243, 'P72', 'E243', 'V28'),
(244, 'P73', 'E244', 'A215'),
(245, 'P73', 'E245', 'A216'),
(246, 'P73', 'E246', 'A217'),
(247, 'P74', 'E247', 'A218'),
(248, 'P74', 'E248', 'A219'),
(249, 'P74', 'E249', 'A220'),
(250, 'P74', 'E250', 'V29'),
(251, 'P75', 'E251', 'A221'),
(252, 'P75', 'E252', 'A222'),
(253, 'P75', 'E253', 'A223'),
(254, 'P75', 'E254', 'A224'),
(255, 'P75', 'E255', 'V30'),
(256, 'P76', 'E256', 'A225'),
(257, 'P76', 'E257', 'A226'),
(258, 'P76', 'E258', 'A227'),
(259, 'P76', 'E259', 'A228'),
(260, 'P76', 'E260', 'A229'),
(261, 'P76', 'E261', 'A230'),
(262, 'P76', 'E262', 'V31'),
(263, 'P77', 'E263', 'A231'),
(264, 'P78', 'E264', 'A232'),
(265, 'P78', 'E265', 'A233'),
(266, 'P79', 'E266', 'A234'),
(267, 'P79', 'E267', 'A235'),
(268, 'P79', 'E268', 'A236'),
(269, 'P79', 'E269', 'A237'),
(270, 'P80', 'E270', 'A238'),
(271, 'P80', 'E271', 'A239'),
(272, 'P80', 'E272', 'A240'),
(273, 'P80', 'E273', 'A241'),
(274, 'P81', 'E274', 'A242'),
(275, 'P81', 'E275', 'A243'),
(276, 'P81', 'E276', 'V32'),
(277, 'P82', 'E277', 'A244'),
(278, 'P82', 'E278', 'A245'),
(279, 'P82', 'E279', 'A246'),
(280, 'P83', 'E280', 'A247'),
(281, 'P83', 'E281', 'A248'),
(282, 'P83', 'E282', 'A249'),
(283, 'P83', 'E283', 'V33'),
(284, 'P84', 'E284', 'A250'),
(285, 'P84', 'E285', 'A251'),
(286, 'P84', 'E286', 'A252'),
(287, 'P85', 'E287', 'A253'),
(288, 'P85', 'E288', 'A254'),
(289, 'P85', 'E289', 'A255'),
(290, 'P86', 'E290', 'A256'),
(291, 'P86', 'E291', 'V34'),
(292, 'P87', 'E292', 'A257'),
(293, 'P88', 'E293', 'A258'),
(294, 'P88', 'E294', 'A259'),
(295, 'P88', 'E295', 'A260'),
(296, 'P88', 'E296', 'V35'),
(297, 'P89', 'E297', 'A261'),
(298, 'P90', 'E298', 'A262'),
(299, 'P90', 'E299', 'A263'),
(300, 'P90', 'E300', 'A264'),
(301, 'P91', 'E301', 'A265'),
(302, 'P91', 'E302', 'A266'),
(303, 'P91', 'E303', 'V36'),
(304, 'P92', 'E304', 'A267'),
(305, 'P92', 'E305', 'A268'),
(306, 'P92', 'E306', 'A269'),
(307, 'P92', 'E307', 'A270'),
(308, 'P92', 'E308', 'A271'),
(309, 'P92', 'E309', 'A272'),
(310, 'P93', 'E310', 'A273'),
(311, 'P93', 'E311', 'A274'),
(312, 'P93', 'E312', 'A275'),
(313, 'P93', 'E313', 'A276'),
(314, 'P93', 'E314', 'A277'),
(315, 'P93', 'E315', 'A278'),
(316, 'P93', 'E316', 'A279'),
(317, 'P93', 'E317', 'A280'),
(318, 'P93', 'E318', 'A281'),
(319, 'P93', 'E319', 'A282'),
(320, 'P93', 'E320', 'A283'),
(321, 'P94', 'E321', 'A284'),
(322, 'P94', 'E322', 'A285'),
(323, 'P95', 'E323', 'A286'),
(324, 'P95', 'E324', 'V19'),
(325, 'P96', 'E325', 'A287'),
(326, 'P97', 'E326', 'A288'),
(327, 'P97', 'E327', 'A289'),
(328, 'P97', 'E328', 'A290'),
(329, 'P98', 'E329', 'A291'),
(330, 'P98', 'E330', 'A292'),
(331, 'P98', 'E331', 'A293'),
(332, 'P98', 'E332', 'V14'),
(333, 'P99', 'E333', 'A294'),
(334, 'P99', 'E334', 'A295'),
(335, 'P99', 'E335', 'A296'),
(336, 'P99', 'E336', 'A297'),
(337, 'P100', 'E337', 'A298'),
(338, 'P100', 'E338', 'A299'),
(339, 'P100', 'E339', 'A300'),
(340, 'P100', 'E340', 'A301'),
(341, 'P100', 'E341', 'A302');

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
  `Authors` text NOT NULL,
  `Editors` text NOT NULL,
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
  `JSESSIONID` varchar(40)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table Structure for table 'AdminLoginSessions'
--

CREATE TABLE IF NOT EXISTS `AdminLoginSessions` (
  `ID` bigint(20) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` bigint(20) unsigned NOT NULL,
  `JSESSIONID` varchar(40) NOT NULL
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
  `Nickname` tinytext   NULL,
  `Firstname` tinytext  NULL,
  `Lastname` tinytext   NULL,
  `Email` tinytext NOT NULL,
  `NewEmail` tinytext   NULL,
  `Birthyear` smallint(6) unsigned  NULL,
  `Address` text NOT NULL,
  `CardNumber` tinytext NOT NULL,
  `TokenString` varchar(36)  NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Table structure for table 'Pagehits'
--
CREATE TABLE IF NOT EXISTS `PageHits` (
	`Page` VARCHAR(100) NOT NULL,
    `Hits` int(11) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table 'MostAddedToCart'
--

CREATE TABLE IF NOT EXISTS `MostAddedToCart` (
	`Title` VARCHAR(100) NOT NULL,
    `Count` int(11) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table 'MostViewed'
--
CREATE TABLE IF NOT EXISTS `MostViewed` (
	`Title` VARCHAR(100) NOT NULL,
    `Count` int(11) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Indexes for table `AdminLoginSessions`
--
ALTER TABLE `AdminLoginSessions`
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
-- Indexes for table `MostAddedToCart`
--

ALTER TABLE `MostAddedToCart`
	ADD UNIQUE KEY `Title` (`Title`);

--
-- Indexes for table `MostViewed`
--

ALTER TABLE `MostViewed`
ADD UNIQUE KEY `Title` (`Title`);

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
  MODIFY `PubID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `LoginSessions`
--
ALTER TABLE `LoginSessions`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT;

-- AUTO_INCREMENT for table `AdminLoginSessions`
--
ALTER TABLE `AdminLoginSessions`
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
-- Constraints for table `AdminLoginSessions`
--
ALTER TABLE `AdminLoginSessions`
  ADD CONSTRAINT `adminloginsessions_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`);

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
