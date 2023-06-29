CREATE TABLE `tb_medicamento` (
  `id` bigint PRIMARY KEY,
  `nome` varchar2,
  `principio_ativo` varchar2,
  `descricao` varchar2,
  `marca` varchar2,
  `fabricante` varchar2,
  `preco` double,
  `preco_desconto` double,
  `sob_prescricao` boolean,
  `retencao` boolean,
  `termolabel` boolean
);

CREATE TABLE `tb_med_injetavel` (
  `medicamento_id` bigint PRIMARY KEY,
  `tipo_aplicacao` ENUM ('ENDOVENOSA', 'INTRADERMICA', 'INTRAMUSCULAR', 'SUBCUTANEA')
);

CREATE TABLE `tb_pedido` (
  `id` bigint PRIMARY KEY,
  `id_cliente` bigint,
  `id_status` int COMMENT 'Um status pode estar em vários pedidos. Um pedido tem somente um status.'
);

CREATE TABLE `tb_status_pedido` (
  `id` int PRIMARY KEY,
  `status` varchar2 COMMENT '1: EM CARRINHO;
2: FINALIZADO;
-- Novos status podem ser adicionados --'
);

CREATE TABLE `tb_carrinho` (
  `id` bigint PRIMARY KEY,
  `id_pedido` bigint,
  `id_medicamento` bigint,
  `qnt` int
);

ALTER TABLE `tb_status_pedido` COMMENT = 'Um status pode estar em vários pedidos. Um pedido tem somente um status.';

ALTER TABLE `tb_med_injetavel` ADD FOREIGN KEY (`medicamento_id`) REFERENCES `tb_medicamento` (`id`);

ALTER TABLE `tb_pedido` ADD FOREIGN KEY (`id_status`) REFERENCES `tb_status_pedido` (`id`);

ALTER TABLE `tb_carrinho` ADD FOREIGN KEY (`id_pedido`) REFERENCES `tb_pedido` (`id`);

ALTER TABLE `tb_carrinho` ADD FOREIGN KEY (`id_medicamento`) REFERENCES `tb_medicamento` (`id`);
