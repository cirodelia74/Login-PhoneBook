--
-- Struttura della tabella `rubricatic`
--

CREATE TABLE `rubricatic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cognome` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `indirizzo` varchar(50) DEFAULT NULL,
  `citta` varchar(30) DEFAULT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_user`) REFERENCES login(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
