-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2025 at 08:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qmmdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `message` text NOT NULL,
  `submitted_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `username`, `message`, `submitted_at`) VALUES
(1, 'sami', 'easy to use , i LOVE this app!', '2025-05-23 21:30:49'),
(2, 'samia', 'wooow', '2025-05-23 23:11:14'),
(3, 'sami', 'this app is unbelievable', '2025-05-26 18:25:40'),
(4, 'test1', 'bad app', '2025-05-26 18:39:24'),
(5, 'salma1998', 'cool app !', '2025-05-29 15:48:26'),
(6, 'sami', 'x', '2025-05-29 16:55:15');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `target_user_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `user_id`, `target_user_id`, `amount`, `type`, `time`) VALUES
(1, 7, NULL, 1, 'deposit', '2025-05-26 23:39:25'),
(2, 8, NULL, 100000, 'deposit', '2025-05-26 23:41:14'),
(3, 8, NULL, 500, 'deposit', '2025-05-26 23:42:01'),
(4, 8, 1, 100, 'transfer', '2025-05-26 23:42:13'),
(5, 8, 1, 100, 'transfer', '2025-05-26 23:43:28'),
(6, 9, NULL, 1, 'deposit', '2025-05-29 18:46:25'),
(7, 9, NULL, 1, 'deposit', '2025-05-29 18:46:35'),
(8, 9, NULL, 50, 'deposit', '2025-05-29 18:47:05'),
(9, 1, NULL, 1000, 'deposit', '2025-05-29 19:36:24'),
(10, 1, NULL, 1, 'deposit', '2025-05-29 20:17:07');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `country` varchar(50) NOT NULL,
  `balance` decimal(20,3) NOT NULL DEFAULT 0.000,
  `points` int(11) NOT NULL DEFAULT 0,
  `last_login` datetime DEFAULT NULL,
  `role` enum('admin','user') NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `username`, `password`, `country`, `balance`, `points`, `last_login`, `role`) VALUES
(1, 'sami', 'rami', 'sami@rami.com', 'sami', 'samirami123', 'Jordan', 1226.000, 108241, '2025-05-29 20:16:54', 'user'),
(3, 'samia', 'hamoodeh', 'samia@hamoodeh.com', 'samia', 'samiasamia8', 'Jordan', 1010160.455, 10, '2025-05-24 02:11:06', 'user'),
(4, 'saif', 'khalaf', 'saif.khalaf8@gmail.com', 'saifkhalaf-1', 'saifsaif88', 'Palestine', 90101.000, 1111210, '2025-05-23 20:16:17', 'user'),
(5, 'Qusai', 'Khalaf', 'qusai.khalaf8@gamil.com', 'Qusai', 'qusai123', 'Jordan', 0.000, 0, '2025-05-29 21:23:03', 'admin'),
(6, 'test', '1', 'test@gamil.com', 'test1', 'test1234', 'Jordan', 0.000, 0, '2025-05-26 21:41:25', 'user'),
(8, 'test3', 'test', 'test3@.', 'test3', 'tesy3123', 'Jordan', 100303.000, 9750, '2025-05-26 23:41:04', 'user'),
(11, 'wawa', 'wawa', '@.wawawawaawa', 'wawaa', 'wawawawa', 'Jordan', 0.000, 0, NULL, 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_username` (`username`),
  ADD UNIQUE KEY `unique_email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
