-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 27-01-2022 a las 14:12:06
-- Versión del servidor: 8.0.27-0ubuntu0.20.04.1
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ocio_open`
--
CREATE DATABASE IF NOT EXISTS `ocio_open` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `ocio_open`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `assistants`
--

CREATE TABLE `assistants` (
  `event_id` float NOT NULL,
  `assistant` varchar(200) NOT NULL,
  `attendance` tinyint NOT NULL,
  `excuse` varchar(2000) DEFAULT NULL,
  `createdAt` date NOT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `assistants`
--

INSERT INTO `assistants` (`event_id`, `assistant`, `attendance`, `excuse`, `createdAt`, `updatedAt`) VALUES
(18, 'rubensantibanezacosta@alumno.ieselrincon.es', 1, NULL, '2021-12-06', '2021-12-06'),
(18, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-07', '2021-12-07'),
(19, 'rubensantibanezacosta@alumno.ieselrincon.es', 1, NULL, '2021-12-06', '2021-12-06'),
(22, 'rubensantibanezacosta@alumno.ieselrincon.es', 0, NULL, '2021-12-09', '2021-12-09'),
(22, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-08', '2021-12-09'),
(37, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-09', '2021-12-09'),
(38, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-09', '2021-12-11'),
(44, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-09', '2021-12-09'),
(45, 'rubensantibanezacosta@alumno.ieselrincon.es', 1, NULL, '2021-12-09', '2021-12-09'),
(45, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-09', '2021-12-24'),
(46, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-09', '2021-12-09'),
(48, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2021-12-15', '2021-12-15'),
(48, 'santi195950@gmail.com', 1, NULL, '2021-12-11', '2021-12-11'),
(51, 'rubensantibanezacosta902@gmail.com', 0, NULL, '2022-01-19', '2022-01-19'),
(52, 'rubensantibanezacosta902@gmail.com', 1, NULL, '2022-01-20', '2022-01-20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comments`
--

CREATE TABLE `comments` (
  `comment_id` float NOT NULL,
  `event_id` float NOT NULL,
  `assistant` varchar(200) NOT NULL,
  `comment` varchar(2000) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `comments`
--

INSERT INTO `comments` (`comment_id`, `event_id`, `assistant`, `comment`, `date`) VALUES
(44, 45, 'rubensantibanezacosta@alumno.ieselrincon.es', 'Claro q si', '2021-12-09 13:25:33'),
(46, 48, 'santi195950@gmail.com', 'tradicion familiar', '2021-12-11 22:12:40'),
(47, 46, 'rubensantibanezacosta902@gmail.com', 'feooo', '2021-12-15 15:14:56'),
(49, 44, 'rubensantibanezacosta902@gmail.com', 'asdasdasdasd', '2021-12-16 13:07:38'),
(52, 44, 'rubensantibanezacosta902@gmail.com', 'comentario', '2021-12-17 16:18:07'),
(53, 45, 'rubensantibanezacosta902@gmail.com', 'aasfasfafs', '2021-12-24 16:24:28'),
(54, 45, 'rubensantibanezacosta902@gmail.com', 'sdfsdfsd', '2021-12-24 16:35:57'),
(55, 45, 'rubensantibanezacosta902@gmail.com', 'Hola', '2022-01-17 16:43:30'),
(64, 45, 'rubensantibanezacosta902@gmail.com', 'aaaaa', '2022-01-18 16:46:01'),
(65, 45, 'rubensantibanezacosta902@gmail.com', 'esta es la buena', '2022-01-18 16:50:56'),
(80, 45, 'rubensantibanezacosta902@gmail.com', 'holaaaaaaaaaaaaaaaaa', '2022-01-18 17:20:51'),
(82, 17, 'rubensantibanezacosta902@gmail.com', 'aqui no hay comentarios', '2022-01-18 18:00:30'),
(88, 18, 'rubensantibanezacosta902@gmail.com', 'otro evento', '2022-01-18 18:43:40'),
(90, 52, 'rubensantibanezacosta902@gmail.com', 'SDGSDGSDGSD', '2022-01-20 18:33:24'),
(91, 50, 'rubensantibanezacosta@alumno.ieselrincon.es', 'Holaaaaaa', '2022-01-25 19:19:42');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `events`
--

CREATE TABLE `events` (
  `event_id` float NOT NULL,
  `tittle` varchar(200) NOT NULL,
  `date` datetime NOT NULL,
  `zone` varchar(500) NOT NULL,
  `place` varchar(500) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `punctuation_avg` float NOT NULL,
  `organizer` varchar(200) NOT NULL,
  `image_id` int DEFAULT NULL,
  `createdAt` date NOT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `events`
--

INSERT INTO `events` (`event_id`, `tittle`, `date`, `zone`, `place`, `description`, `punctuation_avg`, `organizer`, `image_id`, `createdAt`, `updatedAt`) VALUES
(17, 'Primer evento de prueba', '2021-12-06 17:32:00', 'TNF', 'Cruce de Arinaga Calle Jonay 12', NULL, 0, 'rubensantibanezacosta902@gmail.com', 20, '2021-12-06', '2021-12-06'),
(18, 'Nuevo Futuro', '2021-12-19 19:00:00', 'GC', 'Aqui mismo', 'Inventada modificado', 4, 'rubensantibanezacosta@alumno.ieselrincon.es', 21, '2021-12-06', '2021-12-24'),
(19, 'Evento de prueba', '2021-12-06 18:00:00', 'GC', 'Aqui mismo', 'DEtalles inventados', 5, 'rubensantibanezacosta@alumno.ieselrincon.es', 21, '2021-12-06', '2021-12-09'),
(21, 'Prueba routes', '2021-12-08 11:27:00', 'VIRTUAL', 'https://github.com/', 'aaaa', 0, 'rubensantibanezacosta902@gmail.com', 21, '2021-12-08', '2021-12-08'),
(22, 'Prueba organizer modifica', '2021-12-10 11:05:00', 'GC', 'Aqui mismo es', NULL, 3, 'rubensantibanezacosta902@gmail.com', 21, '2021-12-08', '2021-12-16'),
(23, 'aaaaa correo', '2021-12-09 02:50:00', 'GC', 'aaaa', NULL, 0, 'rubensantibanezacosta902@gmail.com', 21, '2021-12-09', '2021-12-09'),
(37, 'asdasdasd', '2021-12-11 06:43:00', 'GC', 'asdasdasdasdas', NULL, 4, 'rubensantibanezacosta902@gmail.com', 21, '2021-12-09', '2021-12-17'),
(38, 'correo', '2021-12-12 06:44:00', 'GC', 'asdasdasd', NULL, 5, 'rubensantibanezacosta902@gmail.com', 21, '2021-12-09', '2021-12-14'),
(44, 'Correo 2 ', '2021-12-19 07:07:00', 'VIRTUAL', 'aaaaAqui mismo', NULL, 3, 'rubensantibanezacosta@alumno.ieselrincon.es', 21, '2021-12-09', '2021-12-24'),
(45, 'Nochevieja', '2022-01-01 00:00:00', 'GC', 'Mi casa', NULL, 3, 'rubensantibanezacosta902@gmail.com', 22, '2021-12-09', '2022-01-20'),
(46, 'qweqeqewqe', '2021-12-09 19:43:00', 'TNF', 'sadsdasdsdad', 'sadasda', 5, 'rubensantibanezacosta@alumno.ieselrincon.es', 22, '2021-12-09', '2021-12-15'),
(48, 'cena de nochebuena', '2021-12-24 13:22:00', 'GC', 'Olabearria', 'cena familiary de socios', 4, 'santi195950@gmail.com', 24, '2021-12-11', '2021-12-24'),
(49, 'Prueba Pino', '2021-12-15 20:03:00', 'TNF', 'Aqui en IesElñ rincon', 'Detalles', 0, 'rubensantibanezacosta902@gmail.com', 21, '2021-12-16', '2021-12-16'),
(50, 'eventooo adriannnn', '2021-12-19 20:33:00', 'TNF', 'Casa de Tiburcio Telde ', 'Detalles inventados', 0, 'rubensantibanezacosta902@gmail.com', 19, '2021-12-17', '2021-12-17'),
(51, 'New Event', '2022-01-20 18:12:00', 'GC', 'Aqui mismo', 'TExt example', 0, 'rubensantibanezacosta902@gmail.com', 23, '2022-01-19', '2022-01-19'),
(52, 'tESST EVENT', '2022-01-29 18:24:00', 'TNF', 'Aqui mismo', NULL, 0, 'rubensantibanezacosta902@gmail.com', 20, '2022-01-20', '2022-01-20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `images`
--

CREATE TABLE `images` (
  `id` int NOT NULL,
  `url` varchar(2000) NOT NULL,
  `createdAt` date NOT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `images`
--

INSERT INTO `images` (`id`, `url`, `createdAt`, `updatedAt`) VALUES
(19, '1638811976.jpg', '2021-12-06', '2021-12-06'),
(20, '1638812001.jpg', '2021-12-06', '2021-12-06'),
(21, '1638812301.jpeg', '2021-12-06', '2021-12-06'),
(22, '1639055977.jpg', '2021-12-09', '2021-12-09'),
(23, '1639076365.jpeg', '2021-12-09', '2021-12-09'),
(24, '1639261408.png', '2021-12-11', '2021-12-11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `punctuations`
--

CREATE TABLE `punctuations` (
  `event_id` float NOT NULL,
  `assistant` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `punctuation` float NOT NULL,
  `createdAt` date NOT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `punctuations`
--

INSERT INTO `punctuations` (`event_id`, `assistant`, `punctuation`, `createdAt`, `updatedAt`) VALUES
(18, 'rubensantibanezacosta902@gmail.com', 4, '2021-12-24', '2021-12-24'),
(19, 'rubensantibanezacosta@alumno.ieselrincon.es', 5, '2021-12-06', '2021-12-09'),
(22, 'rubensantibanezacosta902@gmail.com', 3, '2021-12-16', '2021-12-16'),
(37, 'rubensantibanezacosta902@gmail.com', 4, '2021-12-14', '2021-12-17'),
(38, 'rubensantibanezacosta902@gmail.com', 5, '2021-12-14', '2021-12-14'),
(44, 'rubensantibanezacosta902@gmail.com', 3, '2021-12-24', '2021-12-24'),
(45, 'rubensantibanezacosta902@gmail.com', 3, '2022-01-20', '2022-01-20'),
(46, 'rubensantibanezacosta902@gmail.com', 5, '2021-12-15', '2021-12-15'),
(48, 'rubensantibanezacosta902@gmail.com', 4, '2021-12-24', '2021-12-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `number` int NOT NULL,
  `role_key` varchar(500) NOT NULL,
  `permissions` varchar(2000) NOT NULL,
  `createdAt` date NOT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`number`, `role_key`, `permissions`, `createdAt`, `updatedAt`) VALUES
(1, '2202f26445c5f50ab4ccd90f13a4e75ffd9417745b0972138450623af6bf0881', 'login:auth,singup:auth,read:assistants,create:assistants,update:assistants,delete:assistants,readall:users,read:users,create:users,update:users,delete:users,read:comments,create:comments,update:comments,delete:comments,read:events,create:events,update:events,delete:events,read:images,create:images,update:images,delete:images,read:punctuations,create:punctuations,update:punctuations,delete:punctuations,read:zones,adminupdate:events,admindelete:events,profileadministration,fileadministration,eventsadministration,home,myevents,finalizedevents,comments,assistants,ranking,calendar,eventform,eventsbydate,administration,profileAdministration,eventsAdministration', '2021-11-09', '2021-12-09'),
(2, 'f4f034171368113349d2448e18ca09f0df39785f6087430de00fa3e93a07cd26', 'login:auth,singup:auth,read:assistants,create:assistants,update:assistants,delete:assistants,read:users,create:users,update:users,delete:users,read:comments,create:comments,update:comments,delete:comments,read:events,create:events,update:events,delete:events,read:images,create:images,update:images,read:punctuations,create:punctuations,update:punctuations,read:zones,home,myevents,finalizedevents,comments,assistants,ranking,calendar,eventform,eventsbydate', '2021-11-09', '2021-12-09');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `email` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `surname` varchar(400) NOT NULL,
  `image_url` varchar(2000) NOT NULL,
  `role` varchar(50) NOT NULL,
  `punctuation_avg` float NOT NULL,
  `createdAt` date NOT NULL,
  `lastconnection` datetime DEFAULT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`email`, `name`, `surname`, `image_url`, `role`, `punctuation_avg`, `createdAt`, `lastconnection`, `updatedAt`) VALUES
('rubensantibanezacosta@alumno.ieselrincon.es', 'RUBEN', 'SANTIBA�EZ ACOSTA', 'https://lh3.googleusercontent.com/a/AATXAJzWGrMlSLuRFyd-6biApoDmcpA_WZ1lturX0k-h=s96-c', 'user', 4.14286, '2021-12-06', '2022-01-25 19:20:08', '2022-01-25'),
('rubensantibanezacosta@gmail.com', 'Ruben', 'Santibañez', 'www', 'user', 0, '2022-01-22', '2022-01-22 14:36:42', '2022-01-22'),
('rubensantibanezacosta902@gmail.com', 'Ruben', 'Santibañez Acosta', 'https://lh3.googleusercontent.com/a-/AOh14GgjgHJCX6j5b6KjotaQjiyPONsEVnOl8VMDpbllMK8=s96-c', 'user', 4, '2021-12-06', '2022-01-24 18:45:42', '2022-01-24'),
('santi195950@gmail.com', 'Alfonso', 'Santibañez', 'https://lh3.googleusercontent.com/a/AATXAJyODKoXzOgHAIujyhKh2hc-fgpR9FqrKn0SUwQ8=s96-c', 'user', 4.125, '2021-12-11', '2021-12-11 22:25:40', '2021-12-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zones`
--

CREATE TABLE `zones` (
  `id` varchar(7) NOT NULL,
  `name` varchar(50) NOT NULL,
  `punctuationavg` float DEFAULT NULL,
  `createdAt` date NOT NULL,
  `updatedAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `zones`
--

INSERT INTO `zones` (`id`, `name`, `punctuationavg`, `createdAt`, `updatedAt`) VALUES
('GC', 'Gran Canaria', 2.77778, '2021-11-13', '2022-01-20'),
('TNF', 'Tenerife', 1, '2021-11-19', '2022-01-20'),
('VIRTUAL', 'Virtual', 1.5, '2021-11-13', '2022-01-20');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `assistants`
--
ALTER TABLE `assistants`
  ADD PRIMARY KEY (`event_id`,`assistant`),
  ADD KEY `FK_user_assistant` (`assistant`);

--
-- Indices de la tabla `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `U_user_comment` (`assistant`) USING BTREE,
  ADD KEY `U_event_comment` (`event_id`) USING BTREE;

--
-- Indices de la tabla `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `FK_users_events` (`organizer`),
  ADD KEY `FK_events_img` (`image_id`);

--
-- Indices de la tabla `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `punctuations`
--
ALTER TABLE `punctuations`
  ADD PRIMARY KEY (`event_id`,`assistant`) USING BTREE,
  ADD KEY `FK_userasisstant_punctuation` (`assistant`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`number`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `zones`
--
ALTER TABLE `zones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` float NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT de la tabla `events`
--
ALTER TABLE `events`
  MODIFY `event_id` float NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT de la tabla `images`
--
ALTER TABLE `images`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `assistants`
--
ALTER TABLE `assistants`
  ADD CONSTRAINT `FK_assistant_event` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_user_assistant` FOREIGN KEY (`assistant`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK_assistant_comment` FOREIGN KEY (`assistant`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_comments_events` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FK_events_img` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_users_events` FOREIGN KEY (`organizer`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `punctuations`
--
ALTER TABLE `punctuations`
  ADD CONSTRAINT `FK_punctuations_events` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_userasisstant_punctuation` FOREIGN KEY (`assistant`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
