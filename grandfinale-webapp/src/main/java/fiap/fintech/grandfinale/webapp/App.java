package fiap.fintech.grandfinale.webapp;

import fiap.fintech.grandfinale.domain.entities.Usuario;
import fiap.fintech.grandfinale.domain.enums.Autenticador;
import fiap.fintech.grandfinale.domain.enums.Sexo;
import fiap.fintech.grandfinale.domain.utils.DateUtils;
import fiap.fintech.grandfinale.infra.data.repositories.UsuarioRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var repo = new UsuarioRepository();
        var users = repo.getAll();

        var usuario = new Usuario("Igor josé", "de Oliveira", Sexo.Masculino, DateUtils.parseDate("23/06/1993"), "igorjoliveira@outlook.com");
        usuario.definirAutenticador(Autenticador.Interno, "abc12345");

        var codigo = repo.insert(usuario);
        System.out.println(codigo);
    }
}
