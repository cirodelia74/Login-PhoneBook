
--
-- Struttura della tabella `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 1,
  `cognome` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `indirizzo` varchar(50) DEFAULT NULL,
  `citta` varchar(30) DEFAULT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `hash_password` varchar(100) NOT NULL,
  `salt` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

