-- Cria a base de dados e a usa
CREATE DATABASE IF NOT EXISTS dbVendas;
USE dbVendas;
drop database dbVendas;
-- Remove tabelas existentes, se houver
DROP TABLE IF EXISTS vendas, detalle, productos, provedor, config, clientes, usuarios;

-- Tabela de clientes
CREATE TABLE clientes (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  dni INT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  telefone BIGINT NOT NULL,
  direccion VARCHAR(255) NOT NULL
);

-- Tabela de configurações
CREATE TABLE config (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  ruc BIGINT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  telefone BIGINT NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  mensaje VARCHAR(255) NOT NULL
);

-- Tabela de provedores
CREATE TABLE provedor (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  ruc INT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  telefone BIGINT NOT NULL,
  direccion VARCHAR(255) NOT NULL
);

-- Tabela de produtos
CREATE TABLE productos (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  codigo INT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  provedor INT NOT NULL,
  stock INT NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (provedor) REFERENCES provedor(Id) ON DELETE CASCADE
);

-- Tabela de usuários
CREATE TABLE usuarios (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  usuario VARCHAR(50) NOT NULL,
  pass VARCHAR(50) NOT NULL,
  rol VARCHAR(50) NOT NULL
);

-- Tabela de vendas
CREATE TABLE vendas (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  cliente INT NOT NULL,
  vendedor VARCHAR(50) NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  dataVenda VARCHAR(10) NOT NULL,
  FOREIGN KEY (cliente) REFERENCES clientes(Id)
);

-- Tabela de detalhes da venda
CREATE TABLE detalle (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  id_pro INT NOT NULL,
  cantidad INT NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  id_venda INT NOT NULL,
  FOREIGN KEY (id_pro) REFERENCES productos(Id),
  FOREIGN KEY (id_venda) REFERENCES vendas(Id)
);

-- Inserir dados iniciais
INSERT INTO clientes (dni, nome, telefone, direccion) VALUES (456456, 'Ana Lopez', 925491523, 'México');
INSERT INTO config (ruc, nome, telefone, direccion, mensaje) VALUES (9247878, 'Vida Informatico', 925491523, 'Lima - Peru', 'Gracias por su preferencia');
INSERT INTO provedor (ruc, nome, telefone, direccion) VALUES (798787, 'Open Services', 925491523, 'Lima');
INSERT INTO productos (codigo, nome, provedor, stock, preco) VALUES (213564456, 'Laptop Lenovo', 1, 40, 5000.00);
INSERT INTO usuarios (nome, usuario, pass, rol) VALUES ('Marcos Felipe', 'marcosfelipe.sirgueira@gmail.com', 'admin', 'Administrador'), ('dev.utfpr', 'admin@gmail.com', 'admin', 'Administrador');
INSERT INTO vendas (cliente, vendedor, total, dataVenda) VALUES (1, 'Marcos Felipe', 50000.0, '26/07/2021');
INSERT INTO detalle (id_pro, cantidad, preco, id_venda) VALUES (1, 10, 5000.00, 1);

INSERT INTO clientes (dni, nome, telefone, direccion) VALUES
(456457, 'Jose Martinez', 925491524, 'Brasil'),
(456458, 'Maria Garcia', 925491525, 'Argentina'),
(456459, 'Carlos Silva', 925491526, 'Chile'),
(456460, 'Lucia Hernandes', 925491527, 'Colombia'),
(456461, 'Fernando Gomez', 925491528, 'Bolivia'),
(456462, 'Ana Torres', 925491529, 'Peru'),
(456463, 'Luis Ramirez', 925491530, 'Uruguay'),
(456464, 'Sofia Cruz', 925491531, 'Paraguay'),
(456465, 'Miguel Angel', 925491532, 'Ecuador'),
(456466, 'Patricia Juarez', 925491533, 'Venezuela'),
(456467, 'Ricardo Mora', 925491534, 'Guatemala'),
(456468, 'Elena Rodriguez', 925491535, 'Cuba'),
(456469, 'Pablo Escobar', 925491536, 'Bolivia'),
(456470, 'Manuel Santander', 925491537, 'Costa Rica'),
(456471, 'Isabel Allende', 925491538, 'Panama');

INSERT INTO config (ruc, nome, telefone, direccion, mensaje) VALUES
(9247879, 'Tech Soluciones', 925491524, 'Santiago - Chile', 'Agradecemos su confianza'),
(9247880, 'Informatica Lima', 925491525, 'Lima - Peru', 'Su éxito es nuestro objetivo'),
(9247881, 'Compus Mundial', 925491526, 'Bogota - Colombia', 'Lo mejor en tecnología'),
(9247882, 'Soluciones Ya', 925491527, 'Buenos Aires - Argentina', 'Calidad y servicio'),
(9247883, 'Redes Conectadas', 925491528, 'Caracas - Venezuela', 'Conectando tus ideas'),
(9247884, 'Info Rápido', 925491529, 'La Paz - Bolivia', 'Rápido y seguro'),
(9247885, 'Tecno Advance', 925491530, 'Montevideo - Uruguay', 'Avance y calidad'),
(9247886, 'Mega Bytes', 925491531, 'Asuncion - Paraguay', 'El gigante de la tecnología'),
(9247887, 'Datos Seguros', 925491532, 'Quito - Ecuador', 'Seguridad en cada byte'),
(9247888, 'Redes y Datos', 925491533, 'Ciudad de Guatemala - Guatemala', 'Redes que piensan'),
(9247889, 'Innova Tech', 925491534, 'Havana - Cuba', 'Innovación y tecnología'),
(9247890, 'Pro Tech', 925491535, 'San José - Costa Rica', 'Profesionales en tecnología'),
(9247891, 'Estrella Digital', 925491536, 'Ciudad de Panamá - Panamá', 'Iluminando tu mundo digital'),
(9247892, 'Futuro Digital', 925491537, 'Tegucigalpa - Honduras', 'Creando el futuro'),
(9247893, 'Soluciones Integrales', 925491538, 'Managua - Nicaragua', 'Soluciones para todos');

INSERT INTO provedor (ruc, nome, telefone, direccion) VALUES
(798788, 'Global Services', 925491524, 'Lima'),
(798789, 'Direct Solutions', 925491525, 'Bogota'),
(798790, 'Rapid Tech', 925491526, 'Santiago'),
(798791, 'Clear View', 925491527, 'Montevideo'),
(798792, 'Precise Networks', 925491528, 'Buenos Aires'),
(798793, 'Tech Depot', 925491529, 'Asuncion'),
(798794, 'Network Dynamics', 925491530, 'Quito'),
(798795, 'Tech Logistics', 925491531, 'Caracas'),
(798796, 'Efficient Tech', 925491532, 'Guatemala City'),
(798797, 'CompuGlobal', 925491533, 'San Salvador'),
(798798, 'Pioneer Systems', 925491534, 'Havana'),
(798799, 'Tech Solutions', 925491535, 'San José'),
(798800, 'Innovatech Providers', 925491536, 'Panama City'),
(798801, 'Tech Wave', 925491537, 'Tegucigalpa'),
(798802, 'Solid Tech', 925491538, 'Managua');

INSERT INTO productos (codigo, nome, provedor, stock, preco) VALUES
(213564457, 'Laptop HP', 1, 35, 4500.00),
(213564458, 'Desktop Dell', 1, 20, 4000.00),
(213564459, 'Monitor Samsung', 2, 50, 1500.00),
(213564460, 'Teclado Mecanico', 5, 100, 250.00),
(213564461, 'Mouse Logitech', 6, 150, 120.00),
(213564462, 'Webcam HD', 7, 75, 340.00),
(213564463, 'Headset Sony', 8, 60, 450.00),
(213564464, 'Impressora Epson', 9, 40, 2200.00),
(213564465, 'Tablet Samsung', 10, 30, 2900.00),
(213564466, 'Smartphone Apple', 11, 25, 7500.00),
(213564467, 'Carregador Portatil', 12, 200, 199.99),
(213564468, 'HD Externo', 13, 45, 800.00),
(213564469, 'SSD 1TB', 14, 50, 600.00),
(213564470, 'Placa de Vídeo GTX 1080', 15, 10, 2500.00),
(213564471, 'Processador Ryzen 9', 1, 15, 4500.00);

INSERT INTO vendas (cliente, vendedor, total, dataVenda) VALUES
(2, 'Lucas Silva', 4800.0, '2021-07-01'),
(3, 'Sophia Costa', 3200.0, '2021-07-02'),
(4, 'Gabriel Dias', 1500.0, '2021-07-03'),
(5, 'Alice Santos', 2200.0, '2021-07-04'),
(6, 'Matheus Oliveira', 4500.0, '2021-07-05'),
(7, 'Isabella Souza', 7500.0, '2021-07-06'),
(8, 'Gustavo Azevedo', 600.0, '2021-07-07'),
(9, 'Julia Pereira', 2500.0, '2021-07-08'),
(10, 'Marcelo Lima', 800.0, '2021-07-09'),
(11, 'Mariana Almeida', 199.99, '2021-07-10'),
(12, 'Felipe Costa', 800.0, '2021-07-11'),
(13, 'Lara Cardoso', 600.0, '2021-07-12'),
(14, 'Bruno Rocha', 2500.0, '2021-07-13'),
(15, 'Clara Ferreira', 4500.0, '2021-07-14'),
(1, 'Eduardo Campos', 7500.0, '2021-07-15');

select provedor from productos;
select id from provedor;
-- Consulta para verificar os dados dos usuários
SELECT * FROM usuarios;
