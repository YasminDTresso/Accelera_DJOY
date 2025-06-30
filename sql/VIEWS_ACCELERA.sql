/*=================================================
					-Accelera-
					-VIEWS-
  =================================================*/
use db_accelera
go

/*
	====================================================================================
	View para detalhes das agendas
	====================================================================================
*/
  
  create or alter view vw_DetalhesAgendas as
    select      a.id as ID_Agenda,  
                a.data_inclusao as DataInclusaoAgenda,
                a.data_alteracao as DataAlteracaoAgenda,
                case 
                    when a.rota IN ('OK', 'CANCELADA')
                    --Agendas finalizadas 
                    then 'Finalizada'
                    --Convertendo porque a condição só pode retornar 1 tipo de dado
                    else CONCAT(dbo.fn_diasAgendasPendentes(a.id), ' dias')
                 end as slaDiasAtendimento,
                a.rota as RotaAgenda,
                a.observacao as ObservacaoAgenda,
                a.sinal_brrisk as SinalBrrisk,
                a.sinal_tcell as SinalTcell,
                a.sm as SM,
                cs.status as StatusChecagemSensor,
                p.nome as NomeCondutor,
                co.status as StatusConsultas,
                v.placa as PlacaVeiculo
    from agendas as a 
    --checagem sensor pode ser nulo
    left join checagem_sensores as cs
    on a.checagem_sensor_id = cs.id
    --consulta pode ser nula
    left join consultas co
    on a.consulta_id = co.id
    --condutor pode ser nulo
    left join condutores as c
    on a.condutor_id = c.id
    left join pessoas as p
    on c.id = p.id
    --veiculo pode ser nulo
    left join veiculos v
    on a.veiculo_id = v.id
  go

/*
	====================================================================================
	View para detalhes das agendas + veículo objeto da agenda
	====================================================================================
*/

  create or alter view vw_DetalhesAgendasComVeiculos as
    select
        a.id as ID_Agenda,
        a.data_inclusao as DataInclusaoAgenda,
        a.data_alteracao as DataAlteracaoAgenda,
        a.rota as RotaAgenda,
        a.observacao as ObservacaoAgenda,
        a.sinal_brrisk as SinalBrrisk,
        a.sinal_tcell as SinalTcell,
        a.sm as SM,
        v.modelo as ModeloVeiculo,
        v.placa as PlacaVeiculo,
        v.cor as CorVeiculo,
        c.cnh as CNHCondutor,
        p_condutor.nome as NomeCondutor,
        t.tipo_servico as TipoServicoTransportadora,
        t.tipo_vinculo as TipoVinculoTransportadora
    from
            agendas as a
        --veiculo pode ser nulo
        left join
            veiculos as v ON a.veiculo_id = v.id
        --condutor pode ser nulo
        left join
            condutores as c ON a.condutor_id = c.id
        left join
            pessoas as p_condutor ON c.id = p_condutor.id 
        --transporta não pode ser nula
        inner join
            transportadoras as t ON a.transportadora_id = t.id;
go

/*
	====================================================================================
	View para detalhes das agendas em aberto + veículo objeto da agenda
	====================================================================================
*/


create or alter view vw_UsuariosComTransportadoras as
    select
        u.id as ID_Usuario,
        u.login as LoginUsuario,
        u.permissao as PermissaoUsuario,
        u.status as StatusUsuario,
        t.id as ID_Transportadora,
        t.tipo_servico as TipoServicoTransportadora,
        t.tipo_vinculo as TipoVinculoTransportadora
    from
            usuarios as u
        inner join
            usuarios_transportadoras as ut ON u.id = ut.usuario_id
        inner join
            transportadoras as t ON ut.transportadora_id = t.id;
go

SELECT * FROM vw_DetalhesAgendas
GO