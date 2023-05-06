-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Feb 10, 2022 at 08:13 AM
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
-- Database: `BaseArchDb`
--

--
-- Dumping data for table `UserMaster`
--

INSERT INTO `UserMaster` (`Id`, `UserName`, `UserId`, `Password`, `Designation`, `RoleMasterId`, `IsActive`, `CrBy`,  `UpBy`, `PhoneNumber`, `Email`, `IsDelete`) VALUES
(1, 'AbdulBaasit', 'abdul', '1234', 'SuperAdmin', 1, 1,  'abdul',  'abdul', '8989898989', 'itzabdulbaasit@gmail.com', 0),
(2, 'Admin', 'admin', 'admin', 'Admin', 2, 1,  NULL, NULL, '8807372069', 'admin@interlogz.com', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
