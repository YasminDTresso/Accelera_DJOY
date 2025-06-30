-- TABELAS PRINCIPAIS
CREATE TABLE pessoas (
  id INT NOT NULL PRIMARY KEY,
  data_nascimento DATE NOT NULL,
  registro_ativo BIT NOT NULL DEFAULT 1,
  telefone VARCHAR(25) NOT NULL,
  nome VARCHAR(150) NOT NULL,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE pessoas_fisicas (
  id INT NOT NULL PRIMARY KEY,
  cpf CHAR(11) NOT NULL UNIQUE
);

CREATE TABLE pessoas_juridicas (
  id INT NOT NULL PRIMARY KEY,
  cnpj CHAR(14) NOT NULL UNIQUE,
  nome_fantasia VARCHAR(150)
);

CREATE TABLE transportadoras (
  id INT NOT NULL PRIMARY KEY,
  tipo_servico VARCHAR(255),
  tipo_vinculo VARCHAR(255)
);

CREATE TABLE usuarios (
  id INT NOT NULL PRIMARY KEY,
  login VARCHAR(255) NOT NULL UNIQUE,
  permissao VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL
);

CREATE TABLE condutores (
  id INT NOT NULL PRIMARY KEY,
  cnh CHAR(11) NOT NULL UNIQUE
);

CREATE TABLE veiculos (
  id INT NOT NULL PRIMARY KEY,
  proprietario_id INT,
  placa VARCHAR(7) NOT NULL UNIQUE,
  placa_carreta VARCHAR(10) NOT NULL UNIQUE,
  chassi VARCHAR(17) NOT NULL UNIQUE,
  cor VARCHAR(50) NOT NULL,
  modelo VARCHAR(255) NOT NULL
);

CREATE TABLE checagem_sensores (
  id INT NOT NULL PRIMARY KEY,
  gestor_id INT,
  transportadora_id INT NOT NULL,
  usuario_alteracao_id INT,
  usuario_inclusao_id INT NOT NULL,
  veiculo_id INT,
  data_alteracao DATETIME,
  data_inclusao DATETIME NOT NULL,
  inicio_problema DATETIME,
  validade DATETIME,
  equipamento VARCHAR(255),
  observacao VARCHAR(1000),
  problema_equipamento VARCHAR(1000),
  status VARCHAR(255),
  vinculo VARCHAR(255)
);

CREATE TABLE consultas (
  id INT NOT NULL PRIMARY KEY,
  condutor_id INT,
  transportadora_id INT,
  usuario_alteracao_id INT,
  usuario_inclusao_id INT NOT NULL,
  veiculo_id INT,
  data_alteracao DATETIME,
  data_inclusao DATETIME NOT NULL,
  validade DATETIME,
  observacao VARCHAR(1000),
  status VARCHAR(255),
  vinculo VARCHAR(255)
);
CREATE TABLE agendas (
  id INT NOT NULL PRIMARY KEY,
  checagem_sensor_id INT,
  condutor_id INT,
  consulta_id INT,
  transportadora_id INT NOT NULL,
  usuario_alteracao_id INT,
  usuario_inclusao_id INT NOT NULL,
  veiculo_id INT,
  data_alteracao DATETIME,
  data_inclusao DATETIME NOT NULL,
  observacao VARCHAR(1000),
  rota VARCHAR(255) NOT NULL,
  sinal_brrisk VARCHAR(255) NOT NULL,
  sinal_tcell VARCHAR(255) NOT NULL,
  sm VARCHAR(255) NOT NULL
);

CREATE TABLE condutores_transportadoras (
  condutor_id INT NOT NULL,
  transportadora_id INT NOT NULL,
  PRIMARY KEY (condutor_id, transportadora_id)
);

CREATE TABLE socios_transportadoras (
  pessoa_id INT NOT NULL,
  transportadora_id INT NOT NULL,
  PRIMARY KEY (pessoa_id, transportadora_id)
);

CREATE TABLE usuarios_transportadoras (
  transportadora_id INT NOT NULL,
  usuario_id INT NOT NULL,
  PRIMARY KEY (transportadora_id, usuario_id)
);

CREATE TABLE log_agendas (
  agenda_id INT NOT NULL,
  data_alteracao DATETIME NOT NULL,
  checagem_sensor_anterior_id INT,
  checagem_sensor_novo_id INT,
  condutor_anterior_id INT,
  condutor_novo_id INT,
  consulta_anterior_id INT,
  consulta_nova_id INT,
  transportadora_id INT NOT NULL,
  usuario_alteracao_id INT NOT NULL,
  veiculo_id INT,
  observacao_anterior VARCHAR(1000),
  observacao_nova VARCHAR(1000),
  rota_anterior VARCHAR(255),
  rota_nova VARCHAR(255),
  sinal_brrisk_anterior VARCHAR(255),
  sinal_brrisk_novo VARCHAR(255),
  sinal_tcell_anterior VARCHAR(255),
  sinal_tcell_novo VARCHAR(255),
  sm_anterior VARCHAR(255),
  sm_novo VARCHAR(255),
  PRIMARY KEY (agenda_id, data_alteracao)
);

CREATE TABLE log_checagem_sensores (
  checagem_sensor_id INT NOT NULL,
  data_alteracao DATETIME NOT NULL,
  gestor_id INT,
  transportadora_id INT NOT NULL,
  usuario_alteracao_id INT NOT NULL,
  veiculo_anterior_id INT,
  veiculo_novo_id INT,
  inicio_problema_anterior DATETIME,
  inicio_problema_novo DATETIME,
  validade_anterior DATETIME,
  validade_nova DATETIME,
  equipamento_anterior VARCHAR(255),
  equipamento_novo VARCHAR(255),
  observacao_anterior VARCHAR(1000),
  observacao_nova VARCHAR(1000),
  problema_equipamento_anterior VARCHAR(1000),
  problema_equipamento_novo VARCHAR(1000),
  status_anterior VARCHAR(255),
  status_novo VARCHAR(255),
  vinculo_anterior VARCHAR(255),
  vinculo_novo VARCHAR(255),
  PRIMARY KEY (checagem_sensor_id, data_alteracao)
);

CREATE TABLE log_consultas (
  consulta_id INT NOT NULL,
  data_alteracao DATETIME NOT NULL,
  condutor_id INT,
  transportadora_id INT,
  usuario_alteracao_id INT NOT NULL,
  veiculo_id INT,
  observacao_anterior VARCHAR(1000),
  observacao_nova VARCHAR(1000),
  status_anterior VARCHAR(255),
  status_novo VARCHAR(255),
  vinculo_anterior VARCHAR(255),
  vinculo_novo VARCHAR(255),
  PRIMARY KEY (consulta_id, data_alteracao)
);

ALTER TABLE pessoas_fisicas ADD FOREIGN KEY (id) REFERENCES pessoas(id);
ALTER TABLE pessoas_juridicas ADD FOREIGN KEY (id) REFERENCES pessoas(id);
ALTER TABLE transportadoras ADD FOREIGN KEY (id) REFERENCES pessoas_juridicas(id);
ALTER TABLE usuarios ADD FOREIGN KEY (id) REFERENCES pessoas_fisicas(id);
ALTER TABLE veiculos ADD FOREIGN KEY (proprietario_id) REFERENCES pessoas(id);

ALTER TABLE socios_transportadoras ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE socios_transportadoras ADD FOREIGN KEY (pessoa_id) REFERENCES pessoas(id);

ALTER TABLE usuarios_transportadoras ADD FOREIGN KEY (usuario_id) REFERENCES usuarios(id);
ALTER TABLE usuarios_transportadoras ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);

ALTER TABLE condutores ADD FOREIGN KEY (id) REFERENCES pessoas_fisicas(id);
ALTER TABLE condutores_transportadoras ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE condutores_transportadoras ADD FOREIGN KEY (condutor_id) REFERENCES condutores(id);

ALTER TABLE checagem_sensores ADD FOREIGN KEY (usuario_alteracao_id) REFERENCES usuarios(id);
ALTER TABLE checagem_sensores ADD FOREIGN KEY (usuario_inclusao_id) REFERENCES usuarios(id);
ALTER TABLE checagem_sensores ADD FOREIGN KEY (gestor_id) REFERENCES usuarios(id);
ALTER TABLE checagem_sensores ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE checagem_sensores ADD FOREIGN KEY (veiculo_id) REFERENCES veiculos(id);

ALTER TABLE consultas ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE consultas ADD FOREIGN KEY (usuario_alteracao_id) REFERENCES usuarios(id);
ALTER TABLE consultas ADD FOREIGN KEY (usuario_inclusao_id) REFERENCES usuarios(id);
ALTER TABLE consultas ADD FOREIGN KEY (veiculo_id) REFERENCES veiculos(id);
ALTER TABLE consultas ADD FOREIGN KEY (condutor_id) REFERENCES condutores(id);

ALTER TABLE agendas ADD FOREIGN KEY (usuario_inclusao_id) REFERENCES usuarios(id);
ALTER TABLE agendas ADD FOREIGN KEY (usuario_alteracao_id) REFERENCES usuarios(id);
ALTER TABLE agendas ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE agendas ADD FOREIGN KEY (veiculo_id) REFERENCES veiculos(id);
ALTER TABLE agendas ADD FOREIGN KEY (checagem_sensor_id) REFERENCES checagem_sensores(id);
ALTER TABLE agendas ADD FOREIGN KEY (condutor_id) REFERENCES condutores(id);
ALTER TABLE agendas ADD FOREIGN KEY (consulta_id) REFERENCES consultas(id);

ALTER TABLE log_agendas ADD FOREIGN KEY (agenda_id) REFERENCES agendas(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (checagem_sensor_anterior_id) REFERENCES checagem_sensores(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (checagem_sensor_novo_id) REFERENCES checagem_sensores(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (consulta_anterior_id) REFERENCES consultas(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (consulta_nova_id) REFERENCES consultas(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (veiculo_id) REFERENCES veiculos(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (condutor_anterior_id) REFERENCES condutores(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (condutor_novo_id) REFERENCES condutores(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (usuario_alteracao_id) REFERENCES usuarios(id);
ALTER TABLE log_agendas ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);

ALTER TABLE log_checagem_sensores ADD FOREIGN KEY (checagem_sensor_id) REFERENCES checagem_sensores(id);
ALTER TABLE log_checagem_sensores ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE log_checagem_sensores ADD FOREIGN KEY (usuario_alteracao_id) REFERENCES usuarios(id);
ALTER TABLE log_checagem_sensores ADD FOREIGN KEY (veiculo_anterior_id) REFERENCES veiculos(id);
ALTER TABLE log_checagem_sensores ADD FOREIGN KEY (veiculo_novo_id) REFERENCES veiculos(id);
ALTER TABLE log_checagem_sensores ADD FOREIGN KEY (gestor_id) REFERENCES usuarios(id);

ALTER TABLE log_consultas ADD FOREIGN KEY (consulta_id) REFERENCES consultas(id);
ALTER TABLE log_consultas ADD FOREIGN KEY (condutor_id) REFERENCES condutores(id);
ALTER TABLE log_consultas ADD FOREIGN KEY (transportadora_id) REFERENCES transportadoras(id);
ALTER TABLE log_consultas ADD FOREIGN KEY (usuario_alteracao_id) REFERENCES usuarios(id);
ALTER TABLE log_consultas ADD FOREIGN KEY (veiculo_id) REFERENCES veiculos(id);

ALTER TABLE agendas ADD CHECK (rota IN ('CANCELADA', 'OK', 'AGUARDANDO'));
ALTER TABLE agendas ADD CHECK (sinal_brrisk IN ('NAO_UTILIZA', 'OK', 'AGUARDANDO'));
ALTER TABLE agendas ADD CHECK (sinal_tcell IN ('OK', 'AGUARDANDO'));
ALTER TABLE agendas ADD CHECK (sm IN ('OK', 'AGUARDANDO'));
ALTER TABLE checagem_sensores ADD CHECK (status IN ('CANCELADO', 'REPROVADO', 'APROVADO', 'EMITIDO', 'AGUARDANDO'));
ALTER TABLE checagem_sensores ADD CHECK (vinculo IN ('TERCEIRO', 'AGREGADO', 'FIXO'));
ALTER TABLE consultas ADD CHECK (status IN ('CANCELADA', 'REPROVADA', 'APROVADA', 'EM_ANALISE', 'AGUARDANDO_DOCUMENTOS'));
ALTER TABLE consultas ADD CHECK (vinculo IN ('TERCEIRO', 'AGREGADO', 'FIXO'));
ALTER TABLE log_agendas ADD CHECK (rota_anterior IN ('CANCELADA', 'OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (rota_nova IN ('CANCELADA', 'OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (sinal_brrisk_anterior IN ('NAO_UTILIZA', 'OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (sinal_brrisk_novo IN ('NAO_UTILIZA', 'OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (sinal_tcell_anterior IN ('OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (sinal_tcell_novo IN ('OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (sm_anterior IN ('OK', 'AGUARDANDO'));
ALTER TABLE log_agendas ADD CHECK (sm_novo IN ('OK', 'AGUARDANDO'));
ALTER TABLE log_checagem_sensores ADD CHECK (status_anterior IN ('CANCELADO', 'REPROVADO', 'APROVADO', 'EMITIDO', 'AGUARDANDO'));
ALTER TABLE log_checagem_sensores ADD CHECK (status_novo IN ('CANCELADO', 'REPROVADO', 'APROVADO', 'EMITIDO', 'AGUARDANDO'));
ALTER TABLE log_checagem_sensores ADD CHECK (vinculo_anterior IN ('TERCEIRO', 'AGREGADO', 'FIXO'));
ALTER TABLE log_checagem_sensores ADD CHECK (vinculo_novo IN ('TERCEIRO', 'AGREGADO', 'FIXO'));
ALTER TABLE log_consultas ADD CHECK (status_anterior IN ('CANCELADA', 'REPROVADA', 'APROVADA', 'EM_ANALISE', 'AGUARDANDO_DOCUMENTOS'));
ALTER TABLE log_consultas ADD CHECK (status_novo IN ('CANCELADA', 'REPROVADA', 'APROVADA', 'EM_ANALISE', 'AGUARDANDO_DOCUMENTOS'));
ALTER TABLE log_consultas ADD CHECK (vinculo_anterior IN ('TERCEIRO', 'AGREGADO', 'FIXO'));
ALTER TABLE log_consultas ADD CHECK (vinculo_novo IN ('TERCEIRO', 'AGREGADO', 'FIXO'));
ALTER TABLE usuarios ADD CHECK (permissao IN ('COLABORADOR', 'GESTOR', 'ADMINISTRADOR'));
ALTER TABLE usuarios ADD CHECK (status IN ('INATIVO', 'ATIVO'));
