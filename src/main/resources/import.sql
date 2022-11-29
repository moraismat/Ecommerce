INSERT INTO categoria (name) VALUES ("ELETRODOMÉSTICO");
INSERT INTO categoria (name) VALUES ("INFORMATICA");
INSERT INTO categoria (name) VALUES ("MÓVEL");

INSERT INTO produto (name, price, categoria_id) VALUES ("Smart TV Samsung 4k 50 polegadas", 3000.00, 1)
INSERT INTO produto (name, price, categoria_id) VALUES ("Geladeira Brastemp", 2500.00, 1)
INSERT INTO produto (name, price, categoria_id) VALUES ("Microondas Eletrolux", 400.00, 1)
INSERT INTO produto (name, price, categoria_id) VALUES ("Notebook Lenovo 3i", 4000.00, 2)
INSERT INTO produto (name, price, categoria_id) VALUES ("Mesa 4 cadeiras", 150.00, 3)

INSERT INTO endereco (logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ("Rua Barão de Maua", "520", "Ao lado do Pão de Açucar", "Morumbi", "57963-889", "São Paulo", "SP")
INSERT INTO cliente (name, EMAIL, CPF, telefone) VALUES ("Fernando Lima Santos", "fernando@gmail.com", 123456789, 92998563215, 1)

INSERT INTO pedido (total, cliente_id) VALUES (0.0, 1)
INSERT INTO item_pedido (desconto, preco, quantidade, pedido_id, produto_id) VALUES (0.2, 2500, 1, 1,2)
INSERT INTO item_pedido (desconto, preco, quantidade, pedido_id, produto_id) VALUES (0.1, 400, 1, 1,3)
