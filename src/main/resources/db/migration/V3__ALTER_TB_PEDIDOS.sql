SET foreign_key_checks = 0;
ALTER TABLE `tb_pedido`
MODIFY COLUMN `id` bigint AUTO_INCREMENT;
SET foreign_key_checks = 1;