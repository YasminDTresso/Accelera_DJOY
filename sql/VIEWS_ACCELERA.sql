/*=================================================
					-Accelera-
					-VIEWS-
  =================================================*/
use db_accelera
go

  create view vw_DetalhesAgendasComVeiculos as
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
INNER JOIN
    veiculos as v ON a.veiculo_id = v.id
INNER JOIN
    condutores as c ON a.condutor_id = c.id
INNER JOIN
    pessoas as p_condutor ON c.id = p_condutor.id -- Assumindo que condutor.id referencia pessoas.id
INNER JOIN
    transportadoras as t ON a.transportadora_id = t.id;
go


CREATE VIEW vw_UsuariosComTransportadoras as
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
INNER JOIN
    usuarios_transportadoras as ut ON u.id = ut.usuario_id
INNER JOIN
    transportadoras as t ON ut.transportadora_id = t.id;
go

select * from vw_DetalhesAgendasComVeiculos;

select * from vw_UsuariosComTransportadoras; 
go
