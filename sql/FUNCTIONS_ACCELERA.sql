/*=================================================
					-Accelera-
					-FUNCTIONS-
  =================================================*/

  use db_accelera
  go

 /*
	=============================================================================================
				Função para exibir a quantidade de dias que uma agenda está pendente
	=============================================================================================
*/

  create or alter function fn_diasAgendasPendentes (@idAgenda int)
  returns int
  as
	 begin
		  declare @qtdDias int

		  --Calculando a diferença de dias entre a data atual e data de inclusão da agenda
		  select @qtdDias = DATEDIFF(DAY, data_inclusao, GETDATE())
		  from agendas
		  where id = @idAgenda and rota not in ('OK', 'CANCELADA')

		  --Retorno: Caso a agenda já estiver concluida, retornar como 0 dias
		  return ISNULL(@qtdDias, 0)
	end
 go