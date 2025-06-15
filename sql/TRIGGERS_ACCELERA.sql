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
            insert into log_consultas(consulta_id, data_alteracao,observacao_anterior,observacao_nova,status_anterior,status_novo,usuario_alteracao_id,vinculo_anterior,vinculo_novo)
            select
                   d.id,
                   GETDATE(),
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
