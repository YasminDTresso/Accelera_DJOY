/*=================================================
					-Accelera-
					-TRIGGERS-
  =================================================*/
use db_accelera
go

/*
	============================
	logConsulta após alteração
	============================
*/
create or alter trigger trg_LogConsulta
on consultas
after UPDATE
as
    begin
            insert into log_consultas(consulta_id, data_alteracao, condutor_id,observacao_anterior,observacao_nova,status_anterior,status_novo,usuario_alteracao_id,vinculo_anterior,vinculo_novo)
            select
                   d.id,
                   GETDATE(),
                   d.condutor_id,
                   d.observacao,
                   i.observacao,
                   d.status,
                   i.status,
                   i.usuario_alteracao_id,
                   d.vinculo,
                   i.vinculo
            from deleted d
            join inserted i 
            on d.id = i.id
    end
go

/*
	============================
	logChecagemSensor após alteração
	============================
*/
create or alter trigger trg_LogChecagemSensor
on checagem_sensores
after UPDATE
as
    begin
            insert into log_checagem_sensores(checagem_sensor_id,data_alteracao,equipamento_anterior,equipamento_novo,gestor_id,inicio_problema_anterior,inicio_problema_novo,observacao_anterior,observacao_nova
                                              ,problema_equipamento_anterior, problema_equipamento_novo,status_anterior,status_novo,transportadora_id,usuario_alteracao_id,validade_anterior,validade_nova,veiculo_anterior_id
                                              ,veiculo_novo_id,vinculo_anterior,vinculo_novo)
            select
                    d.id,
                    GETDATE(),
                    d.equipamento,
                    i.equipamento,
                    d.gestor_id,
                    d.inicio_problema,
                    i.inicio_problema,
                    d.observacao,
                    i.observacao,
                    d.problema_equipamento,
                    i.problema_equipamento,
                    d.status,
                    i.status,
                    d.transportadora_id,
                    i.usuario_alteracao_id,
                    d.validade,
                    i.validade,
                    d.veiculo_id,
                    i.veiculo_id,
                    d.vinculo,
                    i.vinculo
            from deleted d
            join inserted i 
            on d.id = i.id
    end
go

/*
	============================
	logAgenda após alteração
	============================
*/