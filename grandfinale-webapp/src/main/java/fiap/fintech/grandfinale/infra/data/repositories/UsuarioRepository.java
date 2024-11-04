package fiap.fintech.grandfinale.infra.data.repositories;

import fiap.fintech.grandfinale.domain.entities.Usuario;
import fiap.fintech.grandfinale.domain.interfaces.repositories.IUsuarioRepository;

import java.util.List;

public class UsuarioRepository extends BaseRepository<Usuario> implements IUsuarioRepository {
    @Override
    public List<Usuario> getAll() {
        return List.of();
    }

    @Override
    public Usuario getById(int id) {
        return null;
    }

    @Override
    public void insert(Usuario usuario) {

    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(int id) {

    }
}
