
INSERT INTO CORES (nome_cor) VALUES ('VERMELHO');
INSERT INTO CORES (nome_cor) VALUES ('VERDE');
INSERT INTO CORES (nome_cor) VALUES ('AZUL');

INSERT INTO MARCAS (nome_marca) VALUES ('FIAT');
INSERT INTO MARCAS (nome_marca) VALUES ('SUZUKI');

INSERT INTO MODELOS (nome_modelo, marca_id) VALUES ('PALIO', 1);
INSERT INTO MODELOS (nome_modelo, marca_id) VALUES ('GSX-S1000', 2);

INSERT INTO VEICULOS (modelo_id, cor_id, placa, tipo) VALUES (1, 1, 'BRA1A123', 'CARRO');
INSERT INTO VEICULOS (modelo_id, cor_id, placa, tipo) VALUES (2, 2, 'BRA2B234', 'MOTO');

INSERT INTO ESTADOS (nome_estado) VALUES ('SÃO PAULO');
INSERT INTO ESTADOS (nome_estado) VALUES ('MINAS GERAIS');

INSERT INTO CIDADES (nome_cidade, estado_id) VALUES ('SANTOS', 1);
INSERT INTO CIDADES (nome_cidade, estado_id) VALUES ('BELO HORIZONTE', 2);

INSERT INTO ENDERECOS (logradouro, numero, cidade_id) VALUES ('AVENIDA ANA COSTA', 1, 1);
INSERT INTO ENDERECOS (logradouro, numero, cidade_id) VALUES ('AVENIDA AFONSO PENA', 1, 2);

INSERT INTO TELEFONES (telefone) VALUES ('11111111');
INSERT INTO TELEFONES (telefone) VALUES ('22222222');

INSERT INTO ESTABELECIMENTOS (nome, cnpj, endereco_id, telefone_id, vagas_carro, vagas_moto)
    VALUES ('LOREM', '11111111111111', 1, 1, 30, 20);

INSERT INTO ESTABELECIMENTOS (nome, cnpj, endereco_id, telefone_id, vagas_carro, vagas_moto)
    VALUES ('IPSUM', '22222222222222', 2, 2, 20, 10);
