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
            insert into log_consultas(consulta_id, transportadora_id, veiculo_id, data_alteracao, condutor_id,observacao_anterior,observacao_nova,status_anterior,status_novo,usuario_alteracao_id,vinculo_anterior,vinculo_novo)
            select
                   d.id,
                   d.transportadora_id,
                   d.veiculo_id,
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

create or alter trigger trg_LogAgenda
on agendas 
after UPDATE
as
    begin
            insert into log_agendas(agenda_id,checagem_sensor_anterior_id,checagem_sensor_novo_id,condutor_anterior_id,condutor_novo_id,consulta_anterior_id,
                                    consulta_nova_id,data_alteracao,observacao_anterior,observacao_nova,rota_anterior,rota_nova,sinal_brrisk_anterior,sinal_brrisk_novo,sinal_tcell_anterior,
                                    sinal_tcell_novo,sm_anterior,sm_novo,transportadora_id,usuario_alteracao_id,veiculo_id)
            select
                   d.id,
                   d.checagem_sensor_id,
                   i.checagem_sensor_id,
                   d.condutor_id,
                   i.condutor_id,
                   d.consulta_id,
                   i.consulta_id,
                   GETDATE(),
                   d.observacao,
                   i.observacao,
                   d.rota,
                   i.rota,
                   d.sinal_brrisk,
                   i.sinal_brrisk,
                   d.sinal_tcell,
                   i.sinal_tcell,
                   d.sm,
                   i.sm,
                   d.transportadora_id,
                   i.usuario_alteracao_id,
                   d.veiculo_id
            from deleted d
            join inserted i 
            on d.id = i.id
    end
go