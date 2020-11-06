-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 24, 2020 at 11:52 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `challenges`
--

CREATE TABLE `challenges` (
  `challengeid` int(11) NOT NULL,
  `challengename` varchar(50) NOT NULL,
  `suggest` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `challenges`
--

INSERT INTO `challenges` (`challengeid`, `challengename`, `suggest`) VALUES
(1, 'test', 'ROLE_ADMIN'),
(2, 'tesst', 'ROLE_USER'),
(3, '123123123', '123123123'),
(4, 'test challenge', 'day la dap an');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `messageid` int(11) NOT NULL,
  `messagecontent` varchar(200) DEFAULT NULL,
  `senderid` int(11) DEFAULT NULL,
  `receiverid` int(11) DEFAULT NULL,
  `datecreated` datetime(3) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`messageid`, `messagecontent`, `senderid`, `receiverid`, `datecreated`, `status`) VALUES
(1, '123', 2, 1, '2000-04-10 00:00:00.000', 1),
(2, '321', 1, 2, '2000-04-10 00:00:00.000', 1),
(3, 'admin gui bi sua', 1, 2, '2020-09-21 11:10:19.000', 3),
(4, 'user gui', 2, 1, '2020-09-21 11:13:04.000', 1),
(7, 'admin test gui', 1, 2, '2020-09-21 12:03:34.000', 2),
(10, 'test123', 1, 2, '2020-09-21 13:52:22.000', 3),
(11, 'test1', 1, 10, '2020-09-21 14:14:58.000', 2),
(12, '321', 1, 2, '2020-09-24 09:28:41.000', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `taskid` int(11) NOT NULL,
  `taskname` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `attachfile` varchar(500) DEFAULT NULL,
  `datecreated` datetime(3) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `parents` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`taskid`, `taskname`, `description`, `attachfile`, `datecreated`, `username`, `parents`) VALUES
(1, 'toan hoc', 'hoc toan', 'G:\\ProjectATTT\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\SecuritySpring\\upload\\FFmpegMediaInfo_1.2.zip', '2020-09-21 17:56:14.000', 'admin', 0),
(4, 'test', '123', 'Sachvui.Com-day-con-lam-giau-tap-1.epub', '2020-09-21 21:55:25.000', 'admin', 0),
(7, '213123', 'file', 'file', '2020-09-23 16:23:27.000', 'admin', 0),
(8, '123123', 'HD nghiep vu phong chong dich benh theo quy trinh BCM_1.0.doc', 'HD nghiep vu phong chong dich benh theo quy trinh BCM_1.0.doc', '2020-09-23 16:25:11.000', 'admin', 0),
(11, 'ádfasdfasdf', '[08-09-2020]Danh-sach-cac-server.xlsx', '[08-09-2020]Danh-sach-cac-server.xlsx', '2020-09-23 17:08:11.000', 'admin', 1),
(12, '123123123', '8-13-2020 3-40-54 PM.png', '8-13-2020 3-40-54 PM.png', '2020-09-23 17:12:03.000', 'admin', 1),
(13, 'ádfasdfasdfasdfasdfasdf', 'mau-them-danh-sach-cau-hoi (1).xlsx', 'mau-them-danh-sach-cau-hoi (1).xlsx', '2020-09-23 17:22:10.000', 'admin', 4),
(14, '', 'cmd.zip', 'cmd.zip', '2020-09-23 17:38:29.000', 'admin', 4),
(15, '123', '103449360_SR_SietKetNoi_10.60.92.234.xlsx', '103449360_SR_SietKetNoi_10.60.92.234.xlsx', '2020-09-23 19:10:32.000', 'admin', 11),
(16, '123', NULL, 'jmeter-plugins-manager-1.4.jar', '2020-09-24 00:43:54.000', 'user', 11);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `role` varchar(11) DEFAULT NULL,
  `enabled` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `username`, `password`, `fullname`, `email`, `phone`, `role`, `enabled`) VALUES
(1, 'admin', '$2a$10$5G.JBOyWXI0vjMhg.JwvYuIQvliQn8TRlvy3NDrwoZpxPd3i86/VK', 'Pham van vu', 'vupv8@viettel.vn', '0123456789', 'ADMIN', 1),
(2, 'user', '$2a$10$4UaZdwjXqfsgU5fVVLd9auzWzvZKImzl.QLXEcbE/N7vZjBiY6cfK', 'Pham van vu', 'vupv8@viettel.vn', '0398907953', 'USER', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `challenges`
--
ALTER TABLE `challenges`
  ADD PRIMARY KEY (`challengeid`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`messageid`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`taskid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `UserName` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `challenges`
--
ALTER TABLE `challenges`
  MODIFY `challengeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `messageid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `taskid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
