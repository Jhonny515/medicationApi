CREATE TABLE `tb_medicamento` (
  `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `principio_ativo` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `marca` varchar(255) NOT NULL,
  `fabricante` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  `preco_desconto` double DEFAULT "0.00",
  `termolabel` boolean NOT NULL DEFAULT false
);

CREATE TABLE `tb_med_injetavel` (
  `id_medicamento` bigint PRIMARY KEY NOT NULL,
  `tipo_aplicacao` ENUM ('ENDOVENOSA', 'INTRADERMICA', 'INTRAMUSCULAR', 'SUBCUTANEA') NOT NULL
);

CREATE TABLE `tb_med_sob_prescricao` (
  `id_medicamento` bigint PRIMARY KEY NOT NULL,
  `retencao` boolean NOT NULL DEFAULT false
);

CREATE TABLE `tb_pedido` (
  `id` bigint PRIMARY KEY NOT NULL,
  `id_cliente` bigint NOT NULL,
  `id_status` int NOT NULL COMMENT 'Um status pode estar em vários pedidos. Um pedido tem somente um status.'
);

CREATE TABLE `tb_status_pedido` (
  `id` int PRIMARY KEY,
  `status` varchar(255) COMMENT '1: EM CARRINHO;
2: FINALIZADO;
-- Novos status podem ser adicionados --'
);

CREATE TABLE `tb_carrinho` (
  `id` bigint PRIMARY KEY NOT NULL,
  `id_pedido` bigint NOT NULL,
  `id_medicamento` bigint NOT NULL,
  `qnt` int
);

ALTER TABLE `tb_status_pedido` COMMENT = 'Um status pode estar em vários pedidos. Um pedido tem somente um status.';

ALTER TABLE `tb_med_injetavel` ADD FOREIGN KEY (`id_medicamento`) REFERENCES `tb_medicamento` (`id`);

ALTER TABLE `tb_med_sob_prescricao` ADD FOREIGN KEY (`id_medicamento`) REFERENCES `tb_medicamento` (`id`);

ALTER TABLE `tb_pedido` ADD FOREIGN KEY (`id_status`) REFERENCES `tb_status_pedido` (`id`);

ALTER TABLE `tb_carrinho` ADD FOREIGN KEY (`id_pedido`) REFERENCES `tb_pedido` (`id`);

ALTER TABLE `tb_carrinho` ADD FOREIGN KEY (`id_medicamento`) REFERENCES `tb_medicamento` (`id`);
