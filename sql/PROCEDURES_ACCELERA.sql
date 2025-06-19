/*=================================================
					-Accelera-
					-PROCEDURES-
  =================================================*/

use db_accelera
go

/*
	====================
	Criar novo usuário
	====================
*/
create or alter procedure sp_criarUsuario
(
	-- Declaração dos parâmetros recebidos
    @v_cpf CHAR(11), 
    @v_login VARCHAR(255), 
    @v_permissao VARCHAR(255), 
    @v_senha VARCHAR(255), 
    @v_nome VARCHAR(150) = NULL, 
    @v_dtaNascimento DATE = NULL, 
    @v_email VARCHAR(255) = NULL, 
    @v_telefone VARCHAR(25) = NULL
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot
	--Declaração das chaves idPessoa e idPessoaFisica

	declare @v_idPessoa int
	declare @v_idPessoaFisica int

   -- Caso a pessoa fisica exista, preencher os parametros
    SELECT @v_idPessoaFisica = pf.id, 
           @v_idPessoa = p.id,
           @v_nome = p.nome,
           @v_dtaNascimento = p.data_nascimento,
           @v_email = p.email,
           @v_telefone = p.telefone
    FROM pessoas_fisicas pf
    JOIN pessoas p ON pf.id = p.id
    WHERE pf.cpf = @v_cpf

	 -- Se a pessoa não existir, verificar se os parâmetros obrigatórios foram preenchidos
    if @v_idPessoa IS NULL AND @v_idPessoaFisica IS NULL AND @v_nome IS NULL AND @v_dtaNascimento IS NULL AND @v_email IS NULL AND @v_telefone IS NULL
    begin
        print 'A pessoa física não existe. Favor informar nome, data de nascimento, e-mail e telefone.'
        return
    end

	--Caso não exista, criar pessoa e pessoafisica
	if @v_idPessoa is null
	begin
		insert into pessoas(data_nascimento, telefone, nome, email)
			 values	(@v_dtaNascimento, @v_telefone, @v_nome, @v_email)

		set @v_idPessoa = SCOPE_IDENTITY()
	end

	if @v_idPessoaFisica is null
	begin
		insert into pessoas_fisicas(id, cpf)
			 values	(@v_idPessoa, @v_cpf)
	
		set @v_idPessoaFisica = @v_idPessoa
	end

	--Verificando se há usuario vinculado à PessoaFisica
	if not exists (select id from usuarios where id = @v_idPessoaFisica)
	begin
		insert into usuarios (id,login,permissao,senha)
		values        (@v_idPessoaFisica, @v_login, @v_permissao, @v_senha)
	end
COMMIT
end
go

/*
	====================
	Editar Usuario
	====================
*/
create or alter procedure sp_editarUsuario
(
	-- Declaração dos parâmetros recebidos
	@v_idUsuario int,
    @v_permissao VARCHAR(255) = null, 
    @v_senha VARCHAR(255) = null 
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Verificar se o usuário existe
	if not exists (select 1 from usuarios where id = @v_idUsuario)
	begin
			print 'Usuario não encontrado'
	end

	--Atualizar dados
	update usuarios set		permissao = ISNULL(@v_permissao, permissao), senha = ISNULL(@v_senha, senha)
					where	id = @v_idUsuario
COMMIT
end
go

/*
	====================
	Criar nova consulta
	====================
*/
create or alter procedure sp_criarConsulta(
	@v_idUsuarioInclusao int,
	@v_idTransportadora int,
	@v_idCondutor int,
	@v_idVeiculo int,
	@v_validade datetime,
	@v_observacao NVARCHAR(MAX),
	@v_status varchar(255),
	@v_vinculo varchar(255)
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Declaração da varíavel para armazenar a data de inclusão
	declare @v_dataInclusao datetime = getdate()

	--Criando consulta
	insert into consultas(usuario_inclusao_id,usuario_alteracao_id,transportadora_id,condutor_id,veiculo_id, data_inclusao,data_alteracao,observacao,status,validade,vinculo)
			values		 (@v_idUsuarioInclusao, NULL, @v_idTransportadora, @v_idCondutor, @v_idVeiculo,
						  @v_dataInclusao, NULL, @v_observacao, @v_status, @v_validade, @v_vinculo) 
	COMMIT
end
go

/*
	====================
	Editar consulta
	====================
*/

create or alter procedure sp_editarConsulta(
	@v_idConsulta int,
	@v_idCondutor int = NULL,
	@v_idUsuarioAlteracao int,
	@v_idVeiculo int = NULL,
	@v_validade datetime = NULL,
	@v_observacao NVARCHAR(MAX) = NULL,
	@v_status varchar(255) = NULL,
	@v_vinculo varchar(255) = NULL
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Declaração da varíavel para armazenar a data de alteração
	declare @v_dataAlteracao datetime = getdate()

	--Verificar se a consulta existe
	if not exists (select 1 from consultas where id = @v_idConsulta)
	begin
			print 'Consulta não encontrada'
	end

	--Editando consulta
	update	consultas 
	   set	condutor_id = ISNULL(@v_idCondutor, condutor_id), validade = ISNULL(@v_validade, validade), observacao = ISNULL(@v_observacao, observacao), status = ISNULL(@v_status, status),
			vinculo = ISNULL(@v_vinculo, vinculo), veiculo_id = ISNULL(@v_idVeiculo, veiculo_id), usuario_alteracao_id = @v_idUsuarioAlteracao, data_alteracao = @v_dataAlteracao
	where	id = @v_idConsulta
	COMMIT
end
go

/*
	====================
	Criar nova checagem de Sensor
	====================
*/
create or alter procedure sp_criarChecagemSensor(
	@v_idUsuarioInclusao int,
	@v_idTransportadora int,
	@v_idVeiculo int,
	@v_idGestor int,
	@v_validade datetime,
	@v_inicioProblema datetime,
	@v_equipamento varchar(255),
	@v_problemaEquipamento NVARCHAR(MAX),
	@v_observacao NVARCHAR(MAX),
	@v_status varchar(255),
	@v_vinculo varchar(255)
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Declaração da varíavel para armazenar a data de inclusão
	declare @v_dataInclusao datetime = getdate()

	--Checando se o usuário definido como gestor tem a permissão GESTOR
	if not exists (select 1 from usuarios where id = @v_idGestor and permissao = 'GESTOR')
	begin
		print 'Usuario definido como gestor não tem a permissão'
	end
	else
	begin
		--Criando checagem de sensor
		insert into checagem_sensores(data_alteracao,data_inclusao,equipamento,gestor_id,inicio_problema,observacao,problema_equipamento,status,transportadora_id,usuario_alteracao_id
									,usuario_inclusao_id,validade,veiculo_id,vinculo)
				values				(null, @v_dataInclusao, @v_equipamento, @v_idGestor, @v_inicioProblema, @v_observacao, @v_problemaEquipamento, @v_status
									 , @v_idTransportadora, null, @v_idUsuarioInclusao, @v_validade, @v_idVeiculo, @v_vinculo)
	end
	COMMIT
end
go

/*
	==========================
	Editar checagem de Sensor
	==========================
*/

create or alter procedure sp_editarChecagemSensor(
	@v_idChecagemSensor int,
	@v_idUsuarioAlteracao int,
	@v_idVeiculo int = null,
	@v_idGestor int = null,
	@v_validade datetime = null,
	@v_problemaEquipamento NVARCHAR(MAX) = null,
	@v_observacao NVARCHAR(MAX) = null,
	@v_status varchar(255) = null,
	@v_vinculo varchar(255) = null
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Declaração da varíavel para armazenar a data de inclusão
	declare @v_dataAlteracao datetime = getdate()

	--Verificar se a checagem de sensor existe
	if not exists (select 1 from checagem_sensores where id = @v_idChecagemSensor)
	begin
			print 'Checagem de Sensor não encontrada'
	end

	--Checando se o usuário definido como gestor tem a permissão GESTOR
	if not exists (select 1 from usuarios where id = @v_idGestor and permissao = 'GESTOR')
	begin
		print 'Usuario definido como gestor não tem a permissão'
	end
	else
	begin
		--Alterando checagem de sensor
		update checagem_sensores set usuario_alteracao_id = ISNULL(@v_idUsuarioAlteracao, usuario_alteracao_id), veiculo_id = ISNULL(@v_idVeiculo, veiculo_id), gestor_id = ISNULL(@v_idGestor, gestor_id), 
									validade = ISNULL(@v_validade, validade), problema_equipamento = ISNULL(@v_problemaEquipamento, problema_equipamento), observacao = ISNULL(@v_observacao, observacao), 
									status = ISNULL(@v_status, status), vinculo = ISNULL(@v_vinculo, vinculo), data_alteracao = @v_dataAlteracao
								where id = @v_idChecagemSensor
	end
	COMMIT
end
go

select * from checagem_sensores
go

/*
	====================
	Criar nova agenda
	====================
*/

create or alter procedure sp_criarAgenda(
	@v_idUsuarioInclusao int,
	@v_idTransportadora int,
	@v_idCondutor int,
	@v_idVeiculo int, 
	@v_idConsulta int = null,
	@v_idChecagemSensor int = null,
	@v_observacao NVARCHAR(MAX),
	@v_rota varchar(255),
	@v_sinalBRRISK varchar(255),
	@v_sinalTCELL varchar(255),
	@v_SM varchar(255)
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Declaração da varíavel para armazenar a data de inclusão
	declare @v_dataInclusao datetime = getdate()

	--Criando agenda
	insert into agendas(checagem_sensor_id,condutor_id,consulta_id,data_alteracao,data_inclusao,observacao,rota,sinal_brrisk,sinal_tcell,sm,transportadora_id,usuario_alteracao_id,
						usuario_inclusao_id,veiculo_id)
			values		(@v_idChecagemSensor, @v_idCondutor, @v_idConsulta,null, @v_dataInclusao, @v_observacao, @v_rota, @v_sinalBRRISK, @v_sinalTCELL, @v_SM, @v_idTransportadora,
						 null, @v_idUsuarioInclusao, @v_idVeiculo)

	COMMIT
end
go

/*
	====================
	Editar agenda
	====================
*/

create or alter procedure sp_editarAgenda(
	@v_idAgenda int,
	@v_idUsuarioAlteracao int,
	@v_idCondutor int = null,
	@v_idVeiculo int = null, 
	@v_idConsulta int = null,
	@v_idChecagemSensor int = null,
	@v_observacao NVARCHAR(MAX) = null,
	@v_rota varchar(255) = null,
	@v_sinalBRRISK varchar(255) = null,
	@v_sinalTCELL varchar(255) = null,
	@v_SM varchar(255) = null
)
as
begin
	BEGIN TRANSACTION --Para que seja executado no SpringBoot

	--Declaração da varíavel para armazenar a data de inclusão
	declare @v_dataAlteracao datetime = getdate()

	--Verificar se a agenda existe
	if not exists (select 1 from agendas where id = @v_idAgenda)
	begin
			print 'Agenda não encontrada'
	end

	--Alterando agenda
	update agendas set		usuario_alteracao_id = ISNULL(@v_idUsuarioAlteracao, usuario_alteracao_id), condutor_id = ISNULL(@v_idCondutor, condutor_id), veiculo_id = ISNULL(@v_idVeiculo, veiculo_id),
							consulta_id = ISNULL(@v_idConsulta, consulta_id), checagem_sensor_id = ISNULL(@v_idChecagemSensor, checagem_sensor_id), observacao = ISNULL(@v_observacao, observacao),
							rota = ISNULL(@v_rota, rota), sinal_brrisk = ISNULL(@v_sinalBRRISK, sinal_brrisk),
							sinal_tcell = ISNULL(@v_sinalTCELL, sinal_tcell), sm = ISNULL(@v_SM, sm), data_alteracao = @v_dataAlteracao
				   where	id = @v_idAgenda

	COMMIT
end
go