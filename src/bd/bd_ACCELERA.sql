CREATE DATABASE  IF NOT EXISTS `db_inter_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_inter_1`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 192.168.0.16    Database: db_inter_1
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consulta_transportadoras`
--

DROP TABLE IF EXISTS `consulta_transportadoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta_transportadoras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transportadora_id` int NOT NULL,
  `objeto_da_consulta` varchar(255) DEFAULT NULL,
  `vinculo` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `validade` date DEFAULT NULL,
  `observacao` text,
  `usuario_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transportadora_id` (`transportadora_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `consulta_transportadoras_ibfk_1` FOREIGN KEY (`transportadora_id`) REFERENCES `transportadoras` (`id`),
  CONSTRAINT `consulta_transportadoras_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta_transportadoras`
--

LOCK TABLES `consulta_transportadoras` WRITE;
/*!40000 ALTER TABLE `consulta_transportadoras` DISABLE KEYS */;
INSERT INTO `consulta_transportadoras` VALUES (2,4,'STE0E40','FIXO','APROVADA','2025-05-01','*****',1),(3,3,'GENIVALDO','FIXO','APROVADA','2025-05-01','*****',1),(4,2,'BRY6677','FIXO','REPROVADA',NULL,NULL,3),(5,1,'JOAO','TERCEIRO','APROVADO','2025-05-06','LIBERADO CONSULTA BUONNY',8),(6,7,'GRT5544','AGREGADO','APROVADO','2025-05-08',NULL,2),(7,3,'HJL8769','FIXO','APROVADO','2025-05-08',NULL,2),(8,1,'PEDRO','FIXO','REPROVADA',NULL,'ARTIGO NAO LIBERAVEL',9),(9,9,'ANTONIO','TERCEIRO','APROVADO','2025-05-10',NULL,1),(10,2,'NBT6501','FIXO','APROVADO','2025-05-10',NULL,2),(11,3,'TGV4309','FIXO','APROVADO','2025-05-11',NULL,3);
/*!40000 ALTER TABLE `consulta_transportadoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controle_de_checklist`
--

DROP TABLE IF EXISTS `controle_de_checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `controle_de_checklist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transportadora_id` int DEFAULT NULL,
  `gestor` varchar(255) DEFAULT NULL,
  `placa` varchar(20) DEFAULT NULL,
  `equipamento` varchar(255) DEFAULT NULL,
  `vinculo` varchar(255) DEFAULT NULL,
  `status_cl` varchar(255) DEFAULT NULL,
  `validade` date DEFAULT NULL,
  `programacao` varchar(255) DEFAULT NULL,
  `problema_equipamento` varchar(255) DEFAULT NULL,
  `inicio_problema` date DEFAULT NULL,
  `observacao` text,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transportadora_id` (`transportadora_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `controle_de_checklist_ibfk_1` FOREIGN KEY (`transportadora_id`) REFERENCES `transportadoras` (`id`),
  CONSTRAINT `controle_de_checklist_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controle_de_checklist`
--

LOCK TABLES `controle_de_checklist` WRITE;
/*!40000 ALTER TABLE `controle_de_checklist` DISABLE KEYS */;
INSERT INTO `controle_de_checklist` VALUES (10,2,'NELSON','FTR5678','AUTOTRAC','AGREGADO','VENCIDO','2024-09-25','','',NULL,'',1),(11,3,'JOAO','BRY6788','AUTOTRAC','FIXO','VENCIDO','2024-11-30','','',NULL,'',1),(13,3,'KAREN','FRT6543','ONIXSAT','AGREGADO','VENCIDO','2024-11-06','','',NULL,'',1),(14,5,'TRESSO','GTR5689','OMNILINK','FIXO','INATIVADO',NULL,'','TRAVA DO BAU','2024-10-27','',3),(15,4,'JORGE','JHG7890','MAXTRAC','AGREGADO','REPROVADO',NULL,'','',NULL,'',1),(16,4,'KARIN','BGT5678','AUTOTRAC','FIXO','VENCIDO','2024-11-26','','',NULL,'',1),(17,4,NULL,'ABC1234','ONIXSAT','FIXO','APROVADO','2024-12-24',NULL,NULL,NULL,NULL,2),(18,2,NULL,'DEF5667','MAXTRAC','TERCEIRO','REPROVADO',NULL,NULL,'SENSOR DE PORTA DO CARONA',NULL,NULL,3),(19,4,'PEDRO','TGR5674','AUTOTRAC','AGREGADO','INATIVADO',NULL,NULL,'DISPOSITIVO DE BLOQUEIO','2024-11-30',NULL,1),(20,2,NULL,'BRY666655','ONIXSAT','FIXO','APROVADO','2025-01-06',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `controle_de_checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_consultas`
--

DROP TABLE IF EXISTS `log_consultas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_consultas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_inclusao` datetime DEFAULT NULL,
  `transportadora_id` int DEFAULT NULL,
  `vinculo` enum('FIXO','AGREGADO','TERCEIRO') DEFAULT NULL,
  `placa_veiculo` varchar(10) DEFAULT NULL,
  `placa_carreta` varchar(50) DEFAULT NULL,
  `consulta` enum('AG. DOCUMENTOS','EM ANALISE','APROVADA','REPROVADA') DEFAULT NULL,
  `condutor` varchar(100) DEFAULT NULL,
  `observacao` varchar(200) DEFAULT NULL,
  `sinal_tcell` enum('AGUARDANDO','OK') DEFAULT NULL,
  `sinal_brrisk` enum('AGUARDANDO','OK','NAO UTILIZA') DEFAULT NULL,
  `check_list` enum('AGUARDANDO','EMITIDO','APROVADO','REPROVADO') DEFAULT NULL,
  `rota` enum('AGUARDANDO','OK') DEFAULT NULL,
  `sm` enum('AGUARDANDO','OK') DEFAULT NULL,
  `data_finalizacao` datetime DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transportadora_id` (`transportadora_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `log_consultas_ibfk_1` FOREIGN KEY (`transportadora_id`) REFERENCES `transportadoras` (`id`),
  CONSTRAINT `log_consultas_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_consultas`
--

LOCK TABLES `log_consultas` WRITE;
/*!40000 ALTER TABLE `log_consultas` DISABLE KEYS */;
INSERT INTO `log_consultas` VALUES (1,'2023-09-15 08:30:00',1,'FIXO','ABC1234','DEF5678','APROVADA','ANTONIO','11987654321','OK','AGUARDANDO','EMITIDO','OK','OK','2023-09-15 12:30:00',1),(4,'2024-09-30 23:38:44',1,'AGREGADO','RFV5567','*****','APROVADA','TESTE','*****','OK','OK','APROVADO','OK','OK','2024-10-03 22:29:52',3),(5,'2024-09-30 23:53:59',2,'TERCEIRO','RTE4567','TRUCK','EM ANALISE','PEDRO','*****','OK','OK','EMITIDO','OK','OK','2024-10-10 21:01:32',1),(7,'2024-10-01 07:05:37',2,'AGREGADO','OOO9999','PPP0000','EM ANALISE','MARIANA','*****','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO','2024-10-01 08:55:37',1),(12,'2024-10-03 15:31:03',1,'AGREGADO','RTT5566','TRUCK','EM ANALISE','BRENO','*****','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO','2024-10-03 17:14:27',1),(15,'2024-09-30 23:38:44',1,'AGREGADO','FCV3214','BBB5555','APROVADA','LUIS','ATENCAO','OK','OK','APROVADO','OK','OK','2024-10-03 22:29:52',3),(16,'2024-09-30 23:53:59',2,'TERCEIRO','RTE4567','TRUCK','EM ANALISE','TRESSO','*****','OK','OK','EMITIDO','OK','OK','2024-10-10 21:01:32',1),(17,'2023-09-15 08:30:00',1,'FIXO','ABC1234','DEF5678','APROVADA','JOAO SILVA','8745698','OK','AGUARDANDO','EMITIDO','OK','OK','2023-09-15 12:30:00',1),(18,'2024-09-30 23:38:44',1,'AGREGADO','ERT5566','TRG8899','APROVADA','MARIA','*****','OK','OK','APROVADO','OK','OK','2024-10-03 22:29:52',3),(19,'2024-10-28 22:55:44',4,'FIXO','TRE4455','****','APROVADA','ADRIANA','*****','OK','OK','APROVADO','OK','OK','2024-10-25 11:26:58',NULL);
/*!40000 ALTER TABLE `log_consultas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_trabalhos_pendentes`
--

DROP TABLE IF EXISTS `log_trabalhos_pendentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_trabalhos_pendentes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_alteracao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `usuario_id` int NOT NULL,
  `campo_alterado` varchar(50) NOT NULL,
  `valor_antigo` text,
  `valor_novo` text,
  `transportadora_id` int DEFAULT NULL,
  `vinculo` varchar(50) DEFAULT NULL,
  `placa_veiculo` varchar(20) DEFAULT NULL,
  `placa_carreta` varchar(50) DEFAULT NULL,
  `consulta` varchar(50) DEFAULT NULL,
  `condutor` varchar(100) DEFAULT NULL,
  `observacao` text,
  `sinal_tcell` varchar(50) DEFAULT NULL,
  `sinal_brrisk` varchar(50) DEFAULT NULL,
  `check_list` varchar(50) DEFAULT NULL,
  `rota` varchar(50) DEFAULT NULL,
  `sm` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `transportadora_id` (`transportadora_id`),
  CONSTRAINT `log_trabalhos_pendentes_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `log_trabalhos_pendentes_ibfk_3` FOREIGN KEY (`transportadora_id`) REFERENCES `transportadoras` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_trabalhos_pendentes`
--

LOCK TABLES `log_trabalhos_pendentes` WRITE;
/*!40000 ALTER TABLE `log_trabalhos_pendentes` DISABLE KEYS */;
INSERT INTO `log_trabalhos_pendentes` VALUES (3,'2024-10-09 04:06:37',1,'CONSULTA','APROVADA','EM ANÁLISE',3,'TERCEIRO','BRY2222','BRY9999','EM ANÁLISE','TONHO','BR','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(4,'2024-10-09 04:07:45',2,'CONDUTOR','TESTE','HORA',2,'TERCEIRO','PPP8888','OOO7777','REPROVADA','HORA','DA VERDADE','OK','NÃO UTILIZA','REPROVADO','OK','OK'),(5,'2024-10-09 04:07:45',1,'OBSERVACAO','09876','DA VERDADE',2,'TERCEIRO','PPP8888','OOO7777','REPROVADA','HORA','DA VERDADE','OK','NÃO UTILIZA','REPROVADO','OK','OK'),(6,'2024-10-16 00:39:05',2,'PLACA_VEICULO','BRY2222','BRY1111',2,'TERCEIRO','BRY1111','BRY9999','EM ANÁLISE','TONHO','BR','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(7,'2024-10-16 00:39:53',1,'TRANSPORTADORA','SATEL','TB CARGO',1,'AGREGADO','HGT6789','GGG5566','EM ANÁLISE','THEO','0000','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(8,'2024-10-16 00:39:53',3,'VINCULO','FIXO','AGREGADO',1,'AGREGADO','HGT6789','GGG5566','EM ANÁLISE','THEO','0000','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(9,'2024-10-16 00:52:47',1,'CONDUTOR','TONHO','TONTO',2,'TERCEIRO','BRY1111','BRY9999','EM ANÁLISE','TONTO','BR','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(10,'2024-10-19 20:44:39',2,'TRANSPORTADORA','TB CARGO','JS TRANSPORTES',2,'AGREGADO','HGT6789','GGG5566','EM ANÁLISE','THEO','0000','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(11,'2024-10-19 20:48:39',1,'TRANSPORTADORA','TB CARGO','SATEL',4,'AGREGADO','OLX6544','*****','APROVADO','JOAO','3333','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO'),(12,'2024-10-20 18:39:53',8,'VINCULO','AGREGADO','FIXO',1,'FIXO','HGT6789','GGG5566','EM ANÁLISE','THEO','0000','AGUARDANDO','NÃO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO');
/*!40000 ALTER TABLE `log_trabalhos_pendentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabalhos_pendentes`
--

DROP TABLE IF EXISTS `trabalhos_pendentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabalhos_pendentes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_inclusao` datetime DEFAULT CURRENT_TIMESTAMP,
  `transportadora_id` int DEFAULT NULL,
  `vinculo` enum('FIXO','AGREGADO','TERCEIRO') NOT NULL,
  `placa_veiculo` varchar(10) NOT NULL,
  `placa_carreta` varchar(50) DEFAULT NULL,
  `consulta` enum('AG. DOCUMENTOS','EM ANALISE','APROVADA','REPROVADA') NOT NULL,
  `condutor` varchar(100) NOT NULL,
  `observacao` varchar(200) DEFAULT NULL,
  `sinal_tcell` enum('AGUARDANDO','OK') NOT NULL,
  `sinal_brrisk` enum('AGUARDANDO','OK','NAO UTILIZA') NOT NULL,
  `check_list` enum('AGUARDANDO','EMITIDO','APROVADO','REPROVADO') NOT NULL,
  `rota` enum('AGUARDANDO','OK') NOT NULL,
  `sm` enum('AGUARDANDO','OK') NOT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transportadora_id` (`transportadora_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `trabalhos_pendentes_ibfk_1` FOREIGN KEY (`transportadora_id`) REFERENCES `transportadoras` (`id`),
  CONSTRAINT `trabalhos_pendentes_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabalhos_pendentes`
--

LOCK TABLES `trabalhos_pendentes` WRITE;
/*!40000 ALTER TABLE `trabalhos_pendentes` DISABLE KEYS */;
INSERT INTO `trabalhos_pendentes` VALUES (9,'2024-10-03 14:36:18',4,'AGREGADO','BRY5553','BRY0123','APROVADA','MARIA','*****','OK','OK','APROVADO','OK','OK',1),(10,'2024-10-03 14:36:51',2,'AGREGADO','HGT6789','GGG5566','EM ANALISE','THEO','*****','AGUARDANDO','NAO UTILIZA','EMITIDO','AGUARDANDO','AGUARDANDO',2),(11,'2024-10-03 15:30:10',3,'AGREGADO','AAA5566','TTT7788','EM ANALISE','JOAO','******','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO',1),(13,'2024-10-01 15:31:40',2,'TERCEIRO','PPP8888','OOO7777','REPROVADA','PEDRO','*****','OK','OK','REPROVADO','OK','OK',2),(14,'2024-10-03 22:20:45',2,'TERCEIRO','TST5566','TRUCK','EM ANALISE','RENAN','*****','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO',1),(18,'2024-10-03 22:44:19',1,'AGREGADO','RRR5555','TTT6666','EM ANALISE','ANTONIO','*****','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO',2),(19,'2024-10-03 22:46:25',2,'TERCEIRO','BRY1111','BRY9999','EM ANALISE','ARLINDO','*****','OK','OK','APROVADO','OK','OK',1),(20,'2024-10-15 21:52:33',1,'AGREGADO','AKA8900','FGH7788','EM ANALISE','MILTON','*****','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO',1),(21,'2024-10-21 23:39:37',1,'TERCEIRO','POI9088','JKL9999','EM ANALISE','JOAO','*****','OK','NAO UTILIZA','EMITIDO','OK','AGUARDANDO',1),(22,'2024-10-31 23:41:10',2,'FIXO','TRE4567','*****','EM ANALISE','LUIS','*****','AGUARDANDO','NAO UTILIZA','AGUARDANDO','AGUARDANDO','AGUARDANDO',1);
/*!40000 ALTER TABLE `trabalhos_pendentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportadoras`
--

DROP TABLE IF EXISTS `transportadoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transportadoras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `fixo` int DEFAULT NULL,
  `agregado` int DEFAULT NULL,
  `terceiro` int DEFAULT NULL,
  `consulta_fixo` int DEFAULT NULL,
  `consulta_agregado` int DEFAULT NULL,
  `consulta_terceiro` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportadoras`
--

LOCK TABLES `transportadoras` WRITE;
/*!40000 ALTER TABLE `transportadoras` DISABLE KEYS */;
INSERT INTO `transportadoras` VALUES (1,'TB CARGO',30,30,0,180,180,3),(2,'JS TRANSPORTES',30,30,0,180,180,3),(3,'DALASTRA',30,10,0,180,180,3),(4,'SATEL',40,40,3,180,180,3),(5,'TB CARGO FEC A FEC B',60,30,0,180,180,3),(6,'HORIZONTE',50,50,3,90,45,3),(7,'BRAVEO',30,30,3,180,180,3),(8,'JD',0,0,0,0,0,0),(9,'TRANSPAESE',30,30,3,0,0,0),(10,'PRIVILEGE',45,45,3,180,180,3);
/*!40000 ALTER TABLE `transportadoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_transportadora`
--

DROP TABLE IF EXISTS `usuario_transportadora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_transportadora` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `transportadora_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transportadora_id` (`transportadora_id`),
  KEY `usuario_transportadora_ibfk_1` (`usuario_id`),
  CONSTRAINT `usuario_transportadora_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  CONSTRAINT `usuario_transportadora_ibfk_2` FOREIGN KEY (`transportadora_id`) REFERENCES `transportadoras` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_transportadora`
--

LOCK TABLES `usuario_transportadora` WRITE;
/*!40000 ALTER TABLE `usuario_transportadora` DISABLE KEYS */;
INSERT INTO `usuario_transportadora` VALUES (21,1,2),(22,2,3),(23,1,3),(24,1,4),(25,3,4),(26,3,2),(27,2,1),(28,2,6),(29,1,8),(30,7,6);
/*!40000 ALTER TABLE `usuario_transportadora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `permissao` enum('ADMINISTRADOR','GERENTE','COLABORADOR') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'JOAO TRESSO','28864364889','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','joao@288','ADMINISTRADOR'),(2,'Admin','99988877755','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','admin@999','ADMINISTRADOR'),(3,'YASMIN TRESSO','22255588866','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','yasmin@222','GERENTE'),(4,'USER 4','55566688898','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user4@555','COLABORADOR'),(7,'USER 5','11122233344','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user5@111','COLABORADOR'),(8,'USER 6','22233344455','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user6@222','COLABORADOR'),(9,'USER 7','33344455566','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user7@333','COLABORADOR'),(10,'USER 8','44455566677','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user8@444','COLABORADOR'),(11,'USER 9','66677788899','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user9@666','COLABORADOR'),(12,'USER 10','77788899900','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','user10@777','COLABORADOR');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-09 10:28:35
