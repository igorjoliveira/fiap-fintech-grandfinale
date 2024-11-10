package br.com.fiap.fintechgrandfinale.domain.interfaces.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.Participante;

import java.util.List;

public interface IParticipanteRepository extends IBaseRepository<Participante> {
    List<Participante> getAll(int codigoUsuario, int codigoControleFinanceiro, String nome, String email);
}
