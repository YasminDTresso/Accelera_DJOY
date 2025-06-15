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
create procedure sp_criarUsuario
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
	Criar nova consulta
	====================
*/
create procedure sp_criarConsulta(
	@v_idUsuarioInclusao int,
	@v_idTransportadora int,
	@v_idCondutor int,
	@v_idUsuarioAlteracao int = null,
	@v_idVeiculo int,
	@v_validade datetime,
	@v_dataAlteracao datetime = null,
	@v_observacao text,
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
			values		 (@v_idUsuarioInclusao, @v_idUsuarioAlteracao, @v_idTransportadora, @v_idCondutor, @v_idVeiculo,
						  @v_dataInclusao, @v_dataAlteracao, @v_observacao, @v_status, @v_Validade, @v_vinculo) 
	COMMIT
end
go

/*
	====================
	Criar nova checagem de Sensor
	====================
*/
create procedure sp_criarChecagemSensor(
	@v_idUsuarioInclusao int,
	@v_idTransportadora int,
	@v_idCondutor int,
	@v_idUsuarioAlteracao int = null,
	@v_idVeiculo int,
	@v_idGestor int,
	@v_validade datetime,
	@v_dataAlteracao datetime = null,
	@v_inicioProblema datetime,
	@v_equipamento varchar(255),
	@v_problemaEquipamento text,
	@v_observacao text,
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
		insert into checagem_sensor(data_alteracao,data_inclusao,equipamento,gestor_id,inicio_problema,observacao,problema_equipamento,status,transportadora_id,usuario_alteracao_id
									,usuario_inclusao_id,validade,veiculo_id,vinculo)
				values				(@v_dataAlteracao, @v_dataInclusao, @v_equipamento, @v_idGestor, @v_inicioProblema, @v_observacao, @v_problemaEquipamento, @v_status
									 , @v_idTransportadora, @v_idUsuarioAlteracao, @v_idUsuarioInclusao, @v_validade, @v_idVeiculo, @v_vinculo)
	end
	COMMIT
end
go

/*
	====================
	Criar nova agenda
	====================
*/

create procedure sp_criarAgenda(
	@v_idUsuarioInclusao int,
	@v_idUsuarioAlteracao int = null,
	@v_idTransportadora int,
	@v_idCondutor int,
	@v_idVeiculo int, 
	@v_idConsulta int = null,
	@v_idChecagemSensor int = null,
	@v_dataAlteracao datetime,
	@v_observacao text,
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
			values		(@v_idChecagemSensor, @v_idCondutor, @v_idConsulta,@v_dataAlteracao, @v_dataInclusao, @v_observacao, @v_rota, @v_sinalBRRISK, @v_sinalTCELL, @v_SM, @v_idTransportadora,
						 @v_idUsuarioAlteracao, @v_idUsuarioInclusao, @v_idVeiculo)

	COMMIT
end
go

