-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 22 jan. 2024 à 09:55
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cogip`
--
CREATE DATABASE IF NOT EXISTS `cogip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `cogip`;

-- --------------------------------------------------------

--
-- Structure de la table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS`company` (
  `id` int auto_increment PRIMARY KEY,
  `name` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `vat` varchar(255) NOT NULL UNIQUE,
  `type` ENUM('client','provider'),
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO company
  (name, country, vat, type)
VALUES
  ('BeCode', 'Belgium', 1234567890, 'cli'),
  ('ACME', 'United-States', 1234567891, 'provider'),
  ('A6K', 'Belgium', 1234567892, 'client'),
  ('Dubalais', 'France', 1234567893, 'provider');

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id` int auto_increment PRIMARY KEY,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `contact_company_id` int DEFAULT NULL,
  FOREIGN KEY (contact_company_id) REFERENCES company (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO contact
  (firstname, lastname, phone, email, contact_company_id)
VALUES
  ('Loulou', 'Dupont', '0478888888', 'loulou@dupont.com', 1),
  ('Toto', 'Dumarais', '0473456789', 'toto@dumarais.com', 1),
  ('Jaja', 'Dulak', '0474578962', 'jaja@dulak.com', 2),
  ('Titi', 'Dumont', '0474511962', 'titi@dumont.com', 3);


-- --------------------------------------------------------

--
-- Structure de la table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int auto_increment PRIMARY KEY,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `invoice_company_id` int,
  `invoice_contact_id` int,
  INDEX `invoiceCompanyId` (`invoice_company_id`),
  INDEX `invoiceContactId` (`invoice_contact_id`),
  CONSTRAINT `invcompid` FOREIGN KEY (`invoice_company_id`) REFERENCES `company`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `invconid` FOREIGN KEY (`invoice_contact_id`) REFERENCES `contact`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO invoice
  (invoice_company_id, invoice_contact_id)
VALUES
  (1,1),
  (1,2),
  (1,1)
  (2,3);
  

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int auto_increment PRIMARY KEY,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

-- ---------------------------------------------------------



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
