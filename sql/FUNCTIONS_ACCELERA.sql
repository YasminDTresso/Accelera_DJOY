/*=================================================
					-Accelera-
					-FUNCTIONS-
  =================================================*/

  use db_accelera
  go

 /*
	=============================================================================================
				Fun��o para exibir a quantidade de dias que uma agenda est� pendente
	=============================================================================================
*/

  create or alter function fn_diasAgendasPendentes (@idAgenda int)
  returns int
  as
	 begin
		  declare @qtdDias int

		  --Calculando a diferen�a de dias entre a data atual e data de inclus�o da agenda
		  select @qtdDias = DATEDIFF(DAY, data_inclusao, GETDATE())
		  from agendas
		  where id = @idAgenda and rota not in ('OK', 'CANCELADA')

		  --Retorno: Caso a agenda j� estiver concluida, retornar como 0 dias
		  return ISNULL(@qtdDias, 0)
	end
 go