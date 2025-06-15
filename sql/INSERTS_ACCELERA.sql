/*=================================================
					-Accelera-
					-INSERTS-
  =================================================*/
  
use db_accelera
go
/*
	===============================
				Pessoas
	===============================
*/
insert into pessoas(data_nascimento,email,nome,telefone)
			values	('2006-07-01','adm@gmail.com','Administrador','17999999999')
go

insert into pessoas(data_nascimento,email,nome,telefone)
			values	('2003-01-01','transportadora@gmail.com','Transportadora','08000800')
go

insert into pessoas(data_nascimento,email,nome,telefone)
			values	('1999-01-01','condutor@gmail.com','Joao','17333333333')
go

/*
	===============================
		   Pessoas Juridicas
	===============================
*/
insert into pessoas_juridicas(id,cnpj,nome_fantasia)
	   values				 (2, '00000000000000', 'Transportadora Lion')
go

/*
	===============================
		   Pessoas Físicas
	===============================
*/
insert into pessoas_fisicas(id,cpf)
	 values					(1,'00000000000')
go

insert into pessoas_fisicas(id,cpf)
	 values					(3,'12345678900')
go

/*
	===============================
		   Condutores
	===============================
*/
insert into condutores(id,cnh)
	values	(3, '12345678911')
go

/*
	===============================
		   Transportadoras
	===============================
*/
insert into transportadoras(id,tipo_servico,tipo_vinculo)
	values	(2,'Transporte de Alimentos','FIXO')
go

/*
	===============================
			Usuarios
	===============================
*/

exec sp_criarUsuario '00000000000', 'Admin','ADMINISTRADOR','123'
go

/*
Ou

insert into usuarios(id,login,permissao,senha)
	values	(1,'admin','ADMINISTRADOR','123')
go*/

/*
	===============================
		Condutores Transportadora
	===============================
*/
insert into condutores_transportadoras(condutor_id,transportadora_id)
	values	(3,2)
go


/*
	===============================
		Usuarios Transportadora
	===============================
*/
insert into usuarios_transportadoras(usuario_id,transportadora_id)
	values	(1,2)
go

/*
	===============================
		Socios Transportadora
	===============================
*/
insert into socios_transportadoras(pessoa_id,transportadora_id)
	values	(1,2)
go

/*
	===============================
			  Veiculos
	===============================
*/
insert into veiculos(proprietario_id,modelo,chassi,cor,placa,placa_carreta)
	values	(3,'Ford Ranger', '9BWZZZ377VT004251', 'Preto', 'ABC1234', 'DEF5678')
go

/*
	===============================
			Checagem de Sensor
	===============================
*/

 update usuarios set permissao = 'GESTOR' where id = 1
 go

 exec sp_criarChecagemSensor '1','2','3',null,'1','1','2024-01-01',null,'2023-01-01','Sensor 01','Interferência no sinal','Entrar em contato com fabricante','EM_ANALISE','AGREGADO'
 go

/*OU
insert into checagem_sensor(data_inclusao,data_alteracao,usuario_inclusao_id,usuario_alteracao_id, gestor_id,
							transportadora_id, vinculo, veiculo_id, inicio_problema, equipamento, problema_equipamento,observacao,
							status, validade)
	values	()
go*/

/*
	===============================
			Consulta
	===============================
*/

exec sp_criarConsulta '1', '2', '3', null, '1', '2025-01-01', null, 'Falta documentação', 'EM_ANALISE', 'AGREGADO'
go

/*Ou
insert into consultas(data_inclusao,data_alteracao,usuario_inclusao_id,usuario_alteracao_id,condutor_id,transportadora_id,veiculo_id,vinculo,
						observacao, status, validade)
	values	()
go*/

/*
	===============================
			Agendas
	===============================
*/



/*insert into agendas(data_inclusao, data_alteracao,consulta_id,checagem_sensor_id,usuario_inclusao_id,usuario_alteracao_id,
					transportadora_id,veiculo_id,condutor_id,rota,sinal_brrisk,sinal_tcell,sm,observacao)
	values	('2025-06-14', null, 1, 1, )
go*/


