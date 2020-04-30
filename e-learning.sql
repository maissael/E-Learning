-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 18 avr. 2020 à 00:24
-- Version du serveur :  10.1.28-MariaDB
-- Version de PHP :  7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `e-learning`
--

-- --------------------------------------------------------

--
-- Structure de la table `element`
--

CREATE TABLE `element` (
  `id_element` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `nbr_place` int(11) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `temps` int(11) DEFAULT NULL,
  `titre_element` varchar(255) DEFAULT NULL,
  `id_formation` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `element`
--

INSERT INTO `element` (`id_element`, `date`, `nbr_place`, `prix`, `temps`, `titre_element`, `id_formation`) VALUES
(8, '2021-08-03 23:00:00', 10, 100, 60, 'TitreElement1', 23),
(9, '2021-09-03 23:00:00', 12, 100, 60, 'TitreElement2', 24),
(10, '2022-01-04 00:00:00', 14, 120, 60, 'TitreElement3', 25),
(11, '2022-06-03 23:00:00', 10, 100, 90, 'TitreElement4', 26),
(12, '2022-04-04 00:00:00', 10, 120, 60, 'TitreElement5', 27),
(13, '2021-08-03 23:00:00', 12, 100, 60, 'TitreElement6', 24);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id_formation` bigint(20) NOT NULL,
  `categorie` varchar(255) DEFAULT NULL,
  `etablissement` varchar(255) DEFAULT NULL,
  `objectif` varchar(255) DEFAULT NULL,
  `prerequis` varchar(255) DEFAULT NULL,
  `titre_formation` varchar(255) DEFAULT NULL,
  `formateur_id_user` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id_formation`, `categorie`, `etablissement`, `objectif`, `prerequis`, `titre_formation`, `formateur_id_user`) VALUES
(23, 'categorie1', 'Etablissement1', 'Objectif1', 'Prerequis1', 'TitreFormation1', 6),
(24, 'categorie2', 'Etablissement2', 'Objectif2', 'Prerequis2', 'TitreFormation2', 6),
(25, 'categorie3', 'Etablissement3', 'Objectif3', 'Prerequis3', 'TitreFormation3', 7),
(26, 'Categorie4', 'Etablissement4', 'Objectif4', 'Prerequis4', 'TitreFormation4', 7),
(27, 'Categorie5', 'Etablissement5', 'Objectif5', 'Prerequis5', 'TitreFormation5', 7);

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `id_review` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `note_contenu` int(11) DEFAULT NULL,
  `note_etablissement` int(11) DEFAULT NULL,
  `note_formateur` int(11) DEFAULT NULL,
  `beneficiaire_id_user` bigint(20) DEFAULT NULL,
  `element_id_element` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `email`, `password`, `role`, `username`) VALUES
(9, 'nahid@gmail.com', '$2a$10$hS8vsIW2dZVxkZSRPnbG7e.uci0aeC97fq0mpEe/E/DfhBVvQlK5y', 'beneficiaire', 'nahid'),
(8, 'haitam@gmail.com', '$2a$10$zsH5tGWNQJOCWH9iwkOsbuz0dtGW3Y6XmUf0SEOb4tvjo0jgEdqLG', 'beneficiaire', 'haitam'),
(6, 'maissa@gmail.com', '$2a$10$eQUth.VBXUTkB74wFpfqm.a/w08heYAW960bEk3EtF5nAIOmkF7Bi', 'formateur', 'maissa'),
(7, 'dounia@gmail.com', '$2a$10$VTlWgKzuvrI3yIXBZOVFd.oMMLqUQQmxvG2yQpUF6grEI3wi9z1VC', 'formateur', 'dounia');

-- --------------------------------------------------------

--
-- Structure de la table `user_element`
--

CREATE TABLE `user_element` (
  `id_element` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `element`
--
ALTER TABLE `element`
  ADD PRIMARY KEY (`id_element`),
  ADD KEY `FK5ydgved0m1xiaffyfsho52uq5` (`id_formation`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id_formation`),
  ADD KEY `FKq37nuoq6e94fvtq0feqe1uc9g` (`formateur_id_user`);

--
-- Index pour la table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id_review`),
  ADD KEY `FKrieemt0pwv7tap8cmhsf18gx7` (`beneficiaire_id_user`),
  ADD KEY `FK82cqs0h6qvyjqe3l1eqv1x4po` (`element_id_element`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Index pour la table `user_element`
--
ALTER TABLE `user_element`
  ADD KEY `FKulb5lijxx3t3l01q0kpgox6l` (`id_user`),
  ADD KEY `FKqh8ah6ufdeap7ihj4irso4bhf` (`id_element`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `element`
--
ALTER TABLE `element`
  MODIFY `id_element` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id_formation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `review`
--
ALTER TABLE `review`
  MODIFY `id_review` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
