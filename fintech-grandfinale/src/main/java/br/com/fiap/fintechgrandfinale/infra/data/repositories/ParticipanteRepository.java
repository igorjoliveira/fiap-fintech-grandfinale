package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IParticipanteRepository;
import java.util.List;

public class ParticipanteRepository extends BaseRepository<Participante> implements IParticipanteRepository {
    @Override
    public List<Participante> getAll() {
        return List.of();
    }

    @Override
    public Participante getById(int id) {
        return null;
    }

    @Override
    public int insert(Participante participante) {
        return 0;
    }

    @Override
    public void update(Participante participante) {

    }

    @Override
    public void delete(int id) {

    }
}