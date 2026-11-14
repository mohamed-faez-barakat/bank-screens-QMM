-- QMM database schema (sanitized demo schema)
-- No real users, passwords, email addresses, balances, or transaction records are included.

CREATE DATABASE IF NOT EXISTS qmmdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE qmmdb;

CREATE TABLE IF NOT EXISTS feedback (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  message TEXT NOT NULL,
  submitted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS transactions (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT DEFAULT NULL,
  target_user_id INT DEFAULT NULL,
  amount DOUBLE DEFAULT NULL,
  type VARCHAR(50) DEFAULT NULL,
  time DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  country VARCHAR(50) NOT NULL,
  balance DECIMAL(20,3) NOT NULL DEFAULT 0.000,
  points INT NOT NULL DEFAULT 0,
  last_login DATETIME DEFAULT NULL,
  role ENUM('admin','user') NOT NULL DEFAULT 'user',
  PRIMARY KEY (id),
  UNIQUE KEY unique_username (username),
  UNIQUE KEY unique_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
