#关联表
CREATE TABLE `student` (
  `student_id` int(10) NOT NULL AUTO_INCREMENT,
  `sname` varchar(40) DEFAULT 's',
  `sex` char(1) DEFAULT NULL,
  `t_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `fk_id` (`t_id`),
  CONSTRAINT `fk_id` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `student` (
  `student_id` int(10) NOT NULL AUTO_INCREMENT,
  `sname` varchar(40) DEFAULT 's',
  `sex` char(1) DEFAULT NULL,
  `t_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;