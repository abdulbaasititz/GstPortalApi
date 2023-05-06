-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Feb 10, 2022 at 08:18 AM
-- Server version: 5.7.34
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `GstPortalDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `UserMaster`
--

CREATE TABLE `UserMaster` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(60) NOT NULL,
  `UserId` varchar(60) NOT NULL UNIQUE,
  `Password` varchar(60) NOT NULL,
  `Designation` varchar(100) NOT NULL,
  `RoleMasterId` int(11) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL,
  `CrAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CrBy` varchar(16) NOT NULL,
  `UpAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpBy` varchar(16) DEFAULT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `Email` varchar(32) NOT NULL,
  `IsDelete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
