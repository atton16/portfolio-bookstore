-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 27, 2016 at 11:42 AM
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
-- Table structure for table `Graph`
--

CREATE TABLE IF NOT EXISTS `Graph` (
  `ID` bigint(20) unsigned NOT NULL,
  `NodeFrom` text NOT NULL,
  `Edge` text NOT NULL,
  `NodeTo` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Graph`
--

INSERT INTO `Graph` (`ID`, `NodeFrom`, `Edge`, `NodeTo`) VALUES
(1, 'P1', 'E1', 'A1'),
(2, 'P1', 'E2', 'V1'),
(3, 'P2', 'E3', 'A2'),
(4, 'P2', 'E4', 'A3'),
(5, 'P2', 'E5', 'A4'),
(6, 'P2', 'E6', 'A5'),
(7, 'P2', 'E7', 'A6'),
(8, 'P3', 'E8', 'A7'),
(9, 'P3', 'E9', 'A8'),
(10, 'P3', 'E10', 'A9'),
(11, 'P3', 'E11', 'V2'),
(12, 'P4', 'E12', 'A10'),
(13, 'P4', 'E13', 'A11'),
(14, 'P4', 'E14', 'V3'),
(15, 'P5', 'E15', 'A12'),
(16, 'P5', 'E16', 'A13'),
(17, 'P5', 'E17', 'V4'),
(18, 'P6', 'E18', 'A14'),
(19, 'P6', 'E19', 'A15'),
(20, 'P6', 'E20', 'A16'),
(21, 'P7', 'E21', 'A17'),
(22, 'P7', 'E22', 'A18'),
(23, 'P7', 'E23', 'A19'),
(24, 'P7', 'E24', 'V5'),
(25, 'P8', 'E25', 'A20'),
(26, 'P8', 'E26', 'A21'),
(27, 'P9', 'E27', 'A22'),
(28, 'P9', 'E28', 'A23'),
(29, 'P9', 'E29', 'V6'),
(30, 'P10', 'E30', 'A24'),
(31, 'P10', 'E31', 'A25'),
(32, 'P10', 'E32', 'A26'),
(33, 'P10', 'E33', 'A27'),
(34, 'P10', 'E34', 'A28'),
(35, 'P11', 'E35', 'A29'),
(36, 'P12', 'E36', 'A30'),
(37, 'P12', 'E37', 'V7'),
(38, 'P13', 'E38', 'A31'),
(39, 'P13', 'E39', 'A32'),
(40, 'P13', 'E40', 'A33'),
(41, 'P14', 'E41', 'A34'),
(42, 'P14', 'E42', 'A35'),
(43, 'P14', 'E43', 'A36'),
(44, 'P15', 'E44', 'A37'),
(45, 'P15', 'E45', 'A38'),
(46, 'P16', 'E46', 'A39'),
(47, 'P16', 'E47', 'A40'),
(48, 'P16', 'E48', 'A41'),
(49, 'P16', 'E49', 'A42'),
(50, 'P17', 'E50', 'A43'),
(51, 'P17', 'E51', 'A44'),
(52, 'P18', 'E52', 'A45'),
(53, 'P18', 'E53', 'A46'),
(54, 'P18', 'E54', 'A47'),
(55, 'P18', 'E55', 'A48'),
(56, 'P19', 'E56', 'A49'),
(57, 'P20', 'E57', 'A50'),
(58, 'P20', 'E58', 'A51'),
(59, 'P20', 'E59', 'A52'),
(60, 'P20', 'E60', 'V8'),
(61, 'P21', 'E61', 'A53'),
(62, 'P21', 'E62', 'A54'),
(63, 'P21', 'E63', 'A55'),
(64, 'P21', 'E64', 'V9'),
(65, 'P22', 'E65', 'A56'),
(66, 'P22', 'E66', 'A57'),
(67, 'P22', 'E67', 'A58'),
(68, 'P22', 'E68', 'A59'),
(69, 'P23', 'E69', 'A60'),
(70, 'P24', 'E70', 'A61'),
(71, 'P24', 'E71', 'A62'),
(72, 'P25', 'E72', 'A63'),
(73, 'P25', 'E73', 'A64'),
(74, 'P25', 'E74', 'A65'),
(75, 'P25', 'E75', 'A66'),
(76, 'P26', 'E76', 'A67'),
(77, 'P26', 'E77', 'A68'),
(78, 'P26', 'E78', 'A69'),
(79, 'P26', 'E79', 'A70'),
(80, 'P27', 'E80', 'A71'),
(81, 'P27', 'E81', 'A72'),
(82, 'P27', 'E82', 'V10'),
(83, 'P28', 'E83', 'A73'),
(84, 'P28', 'E84', 'A74'),
(85, 'P29', 'E85', 'A75'),
(86, 'P29', 'E86', 'A76'),
(87, 'P29', 'E87', 'A77'),
(88, 'P30', 'E88', 'A78'),
(89, 'P30', 'E89', 'A79'),
(90, 'P30', 'E90', 'A80'),
(91, 'P31', 'E91', 'A81'),
(92, 'P31', 'E92', 'V11'),
(93, 'P32', 'E93', 'A82'),
(94, 'P32', 'E94', 'A83'),
(95, 'P33', 'E95', 'A84'),
(96, 'P33', 'E96', 'A85'),
(97, 'P33', 'E97', 'A86'),
(98, 'P33', 'E98', 'A87'),
(99, 'P33', 'E99', 'A88'),
(100, 'P33', 'E100', 'A89'),
(101, 'P33', 'E101', 'A90'),
(102, 'P33', 'E102', 'A91'),
(103, 'P33', 'E103', 'A92'),
(104, 'P33', 'E104', 'A93'),
(105, 'P33', 'E105', 'A94'),
(106, 'P33', 'E106', 'A95'),
(107, 'P33', 'E107', 'A96'),
(108, 'P33', 'E108', 'A97'),
(109, 'P33', 'E109', 'A98'),
(110, 'P33', 'E110', 'A99'),
(111, 'P34', 'E111', 'A100'),
(112, 'P34', 'E112', 'A101'),
(113, 'P34', 'E113', 'A102'),
(114, 'P34', 'E114', 'V12'),
(115, 'P35', 'E115', 'A103'),
(116, 'P35', 'E116', 'A104'),
(117, 'P35', 'E117', 'A105'),
(118, 'P36', 'E118', 'A106'),
(119, 'P36', 'E119', 'A107'),
(120, 'P36', 'E120', 'A108'),
(121, 'P36', 'E121', 'A109'),
(122, 'P36', 'E122', 'V13'),
(123, 'P37', 'E123', 'A110'),
(124, 'P37', 'E124', 'A111'),
(125, 'P37', 'E125', 'A112'),
(126, 'P38', 'E126', 'A113'),
(127, 'P38', 'E127', 'A114'),
(128, 'P39', 'E128', 'A115'),
(129, 'A115', 'E129', 'S1'),
(130, 'P40', 'E130', 'A116'),
(131, 'P41', 'E131', 'A117'),
(132, 'P41', 'E132', 'A118'),
(133, 'P42', 'E133', 'A119'),
(134, 'P42', 'E134', 'A120'),
(135, 'P43', 'E135', 'A121'),
(136, 'P43', 'E136', 'A122'),
(137, 'P44', 'E137', 'A123'),
(138, 'P44', 'E138', 'A124'),
(139, 'P44', 'E139', 'V14'),
(140, 'P45', 'E140', 'A125'),
(141, 'P45', 'E141', 'V15'),
(142, 'P46', 'E142', 'A126'),
(143, 'P47', 'E143', 'A127'),
(144, 'P47', 'E144', 'V16'),
(145, 'P48', 'E145', 'A128'),
(146, 'P48', 'E146', 'A129'),
(147, 'P48', 'E147', 'A130'),
(148, 'P49', 'E148', 'A131'),
(149, 'P49', 'E149', 'A132'),
(150, 'P49', 'E150', 'A133'),
(151, 'P49', 'E151', 'A134'),
(152, 'P49', 'E152', 'A135'),
(153, 'P49', 'E153', 'A136'),
(154, 'P50', 'E154', 'A137'),
(155, 'P50', 'E155', 'A138'),
(156, 'P50', 'E156', 'A139'),
(157, 'P50', 'E157', 'V17'),
(158, 'P51', 'E158', 'A140'),
(159, 'P51', 'E159', 'V18'),
(160, 'P52', 'E160', 'A141'),
(161, 'P52', 'E161', 'A142'),
(162, 'P52', 'E162', 'A143'),
(163, 'P53', 'E163', 'A144'),
(164, 'P53', 'E164', 'A145'),
(165, 'P53', 'E165', 'A146'),
(166, 'P53', 'E166', 'V19'),
(167, 'P54', 'E167', 'A147'),
(168, 'P54', 'E168', 'A148'),
(169, 'P54', 'E169', 'A149'),
(170, 'P54', 'E170', 'A150'),
(171, 'P54', 'E171', 'A151'),
(172, 'P55', 'E172', 'A152'),
(173, 'P55', 'E173', 'V20'),
(174, 'P56', 'E174', 'A153'),
(175, 'P56', 'E175', 'A154'),
(176, 'P57', 'E176', 'A155'),
(177, 'P57', 'E177', 'A156'),
(178, 'P57', 'E178', 'A157'),
(179, 'P57', 'E179', 'A158'),
(180, 'P58', 'E180', 'A159'),
(181, 'P58', 'E181', 'A160'),
(182, 'P58', 'E182', 'A161'),
(183, 'P58', 'E183', 'A162'),
(184, 'P58', 'E184', 'A163'),
(185, 'P59', 'E185', 'A164'),
(186, 'P59', 'E186', 'A165'),
(187, 'P59', 'E187', 'A166'),
(188, 'P59', 'E188', 'V21'),
(189, 'P60', 'E189', 'A167'),
(190, 'P60', 'E190', 'A168'),
(191, 'P60', 'E191', 'V15'),
(192, 'P61', 'E192', 'A169'),
(193, 'P61', 'E193', 'A170'),
(194, 'P61', 'E194', 'A171'),
(195, 'P61', 'E195', 'A172'),
(196, 'P62', 'E196', 'A173'),
(197, 'P62', 'E197', 'A174'),
(198, 'P62', 'E198', 'V22'),
(199, 'P63', 'E199', 'A175'),
(200, 'P63', 'E200', 'A176'),
(201, 'P63', 'E201', 'A177'),
(202, 'P63', 'E202', 'V23'),
(203, 'P64', 'E203', 'A178'),
(204, 'P64', 'E204', 'A179'),
(205, 'P64', 'E205', 'A180'),
(206, 'P64', 'E206', 'A181'),
(207, 'P64', 'E207', 'V24'),
(208, 'P65', 'E208', 'A182'),
(209, 'P65', 'E209', 'A183'),
(210, 'P65', 'E210', 'A184'),
(211, 'P66', 'E211', 'A185'),
(212, 'P66', 'E212', 'V25'),
(213, 'P67', 'E213', 'A186'),
(214, 'P67', 'E214', 'A187'),
(215, 'P67', 'E215', 'A188'),
(216, 'P67', 'E216', 'V3'),
(217, 'P68', 'E217', 'A189'),
(218, 'P68', 'E218', 'A190'),
(219, 'P68', 'E219', 'A191'),
(220, 'P68', 'E220', 'A192'),
(221, 'P68', 'E221', 'V26'),
(222, 'P69', 'E222', 'A193'),
(223, 'P69', 'E223', 'A194'),
(224, 'P69', 'E224', 'A195'),
(225, 'P69', 'E225', 'V27'),
(226, 'P70', 'E226', 'A196'),
(227, 'P70', 'E227', 'A197'),
(228, 'P70', 'E228', 'A198'),
(229, 'P70', 'E229', 'V28'),
(230, 'P71', 'E230', 'A199'),
(231, 'P71', 'E231', 'V29'),
(232, 'P72', 'E232', 'A200'),
(233, 'P72', 'E233', 'A201'),
(234, 'P72', 'E234', 'A202'),
(235, 'P72', 'E235', 'A203'),
(236, 'P73', 'E236', 'A204'),
(237, 'P74', 'E237', 'A205'),
(238, 'P74', 'E238', 'A206'),
(239, 'P74', 'E239', 'A207'),
(240, 'P75', 'E240', 'A208'),
(241, 'P75', 'E241', 'A209'),
(242, 'P76', 'E242', 'A210'),
(243, 'P76', 'E243', 'A211'),
(244, 'P76', 'E244', 'A212'),
(245, 'P76', 'E245', 'A213'),
(246, 'P76', 'E246', 'A214'),
(247, 'P76', 'E247', 'A215'),
(248, 'P76', 'E248', 'A216'),
(249, 'P77', 'E249', 'A217'),
(250, 'P77', 'E250', 'A218'),
(251, 'P77', 'E251', 'A219'),
(252, 'P77', 'E252', 'A220'),
(253, 'P77', 'E253', 'A221'),
(254, 'P77', 'E254', 'A222'),
(255, 'P77', 'E255', 'A223'),
(256, 'P78', 'E256', 'A224'),
(257, 'P78', 'E257', 'A225'),
(258, 'P78', 'E258', 'V30'),
(259, 'P79', 'E259', 'A226'),
(260, 'P79', 'E260', 'A227'),
(261, 'P79', 'E261', 'V15'),
(262, 'P80', 'E262', 'A228'),
(263, 'P80', 'E263', 'A229'),
(264, 'P80', 'E264', 'V31'),
(265, 'P81', 'E265', 'A230'),
(266, 'P81', 'E266', 'A231'),
(267, 'P81', 'E267', 'A232'),
(268, 'P81', 'E268', 'A233'),
(269, 'P82', 'E269', 'A234'),
(270, 'P82', 'E270', 'A235'),
(271, 'P82', 'E271', 'A236'),
(272, 'P82', 'E272', 'V30'),
(273, 'P83', 'E273', 'A237'),
(274, 'P83', 'E274', 'V32'),
(275, 'P84', 'E275', 'A238'),
(276, 'P84', 'E276', 'A239'),
(277, 'P85', 'E277', 'A240'),
(278, 'P85', 'E278', 'A241'),
(279, 'P86', 'E279', 'A242'),
(280, 'P86', 'E280', 'A243'),
(281, 'P86', 'E281', 'A244'),
(282, 'P86', 'E282', 'V17'),
(283, 'P87', 'E283', 'A245'),
(284, 'P87', 'E284', 'A246'),
(285, 'P87', 'E285', 'V33'),
(286, 'P88', 'E286', 'A247'),
(287, 'P88', 'E287', 'V34'),
(288, 'P89', 'E288', 'A248'),
(289, 'P89', 'E289', 'V35'),
(290, 'P90', 'E290', 'A249'),
(291, 'P90', 'E291', 'A250'),
(292, 'P90', 'E292', 'A251'),
(293, 'P90', 'E293', 'A252'),
(294, 'P90', 'E294', 'V36'),
(295, 'P91', 'E295', 'A253'),
(296, 'P91', 'E296', 'A254'),
(297, 'P91', 'E297', 'A255'),
(298, 'P91', 'E298', 'A256'),
(299, 'P91', 'E299', 'A257'),
(300, 'P91', 'E300', 'V8'),
(301, 'P92', 'E301', 'A258'),
(302, 'P92', 'E302', 'A259'),
(303, 'P92', 'E303', 'A260'),
(304, 'P92', 'E304', 'A261'),
(305, 'P92', 'E305', 'A262'),
(306, 'P92', 'E306', 'V37'),
(307, 'P93', 'E307', 'A263'),
(308, 'P93', 'E308', 'A264'),
(309, 'P94', 'E309', 'A265'),
(310, 'P94', 'E310', 'A266'),
(311, 'P94', 'E311', 'A267'),
(312, 'P94', 'E312', 'A268'),
(313, 'P95', 'E313', 'A269'),
(314, 'P95', 'E314', 'A270'),
(315, 'P95', 'E315', 'A271'),
(316, 'P95', 'E316', 'V38'),
(317, 'P96', 'E317', 'A272'),
(318, 'P96', 'E318', 'A273'),
(319, 'P97', 'E319', 'A274'),
(320, 'A274', 'E320', 'S2'),
(321, 'P98', 'E321', 'A275'),
(322, 'P99', 'E322', 'A276'),
(323, 'P99', 'E323', 'A277'),
(324, 'P99', 'E324', 'A278'),
(325, 'P99', 'E325', 'A279'),
(326, 'P99', 'E326', 'A280'),
(327, 'P99', 'E327', 'A281'),
(328, 'P99', 'E328', 'A282'),
(329, 'P99', 'E329', 'A283'),
(330, 'P99', 'E330', 'A284'),
(331, 'P100', 'E331', 'A285'),
(332, 'P100', 'E332', 'A286'),
(333, 'P100', 'E333', 'A287'),
(334, 'P100', 'E334', 'V39');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Graph`
--
ALTER TABLE `Graph`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Graph`
--
ALTER TABLE `Graph`
  MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=335;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
