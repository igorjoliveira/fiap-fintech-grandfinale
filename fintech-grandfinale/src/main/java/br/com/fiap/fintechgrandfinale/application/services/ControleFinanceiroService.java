package br.com.fiap.fintechgrandfinale.application.services;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.ICarteiraDigitalRepository;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IControleFinanceiroRepository;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IParticipanteRepository;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;
import br.com.fiap.fintechgrandfinale.data.repositories.CarteiraDigitalRepository;
import br.com.fiap.fintechgrandfinale.data.repositories.ControleFinanceiroRepository;
import br.com.fiap.fintechgrandfinale.data.repositories.ParticipanteRepository;

import java.sql.SQLException;
import java.util.List;

public class ControleFinanceiroService implements IControleFinanceiroService {
    private IUsuarioService usuarioService;
    private IParticipanteRepository participanteRepository;
    private ICarteiraDigitalRepository carteiraDigitalRepository;
    private IControleFinanceiroRepository controleFinanceiroRepository;

    public ControleFinanceiroService() {
        this.usuarioService = new UsuarioService();
        this.participanteRepository = new ParticipanteRepository();
        this.carteiraDigitalRepository = new CarteiraDigitalRepository();
        this.controleFinanceiroRepository = new ControleFinanceiroRepository();
    }

    @Override
    public ControleFinanceiro getControleFinanceiro(int codigoUsuario, int codigo) {
        return this.controleFinanceiroRepository.getById(codigo);
    }

    @Override
    public List<ControleFinanceiro> getAllControleFinanceiro(int codigoUsuario, String descricao) {
        var lista = this.controleFinanceiroRepository.getAll(codigoUsuario, descricao);
        return lista != null ? lista : List.of();
    }

    @Override
    public List<Participante> getAllParticipante(int codigoUsuario, int codigoControleFinanceiro, String nome, String email) {
        var lista = this.participanteRepository.getAll(codigoUsuario, codigoControleFinanceiro, nome, email);
        return lista != null ? lista : List.of();
    }

    @Override
    public List<CarteiraDigital> getAllCarteiraDigital(int codigoUsuario, int codigoControleFinanceiro, int codigoInstituicaoFinanceira, String nome, String email) {
        var lista = this.carteiraDigitalRepository.getAll(codigoUsuario, codigoControleFinanceiro, codigoInstituicaoFinanceira, nome,email);
        return lista != null ? lista : List.of();
    }

    @Override
    public List<InstituicaoFinanceira> getAllInstituicoes() {
        return EnumUtils.getAllValues(InstituicaoFinanceira.class);
    }

    @Override
    public void insertControleFinanceiro(int codigoUsuario, ControleFinanceiro form) throws SQLException {
        var codigoControleFinanceiro = this.controleFinanceiroRepository.insert(form);
        var participante = new Participante(codigoUsuario, codigoControleFinanceiro, true, true);
        this.insertParticipante(codigoUsuario, participante);
    }

    @Override
    public void insertParticipante(int codigoUsuario, Participante form) throws SQLException {
        var usuario = this.usuarioService.getUser(form.getUsuario().getEmail());
        if(usuario != null) {
            form.setUsuario(usuario);
            form.setCodigoUsuario(usuario.getCodigo());
            this.participanteRepository.insert(form);
        }
        else {
            throw new RuntimeException("Usuário não encontrado para o email informado");
        }
    }

    @Override
    public void insertCarteira(int codigoUsuario, CarteiraDigital form) throws SQLException {
        var usuario = this.usuarioService.getUser(form.getParticipante().getUsuario().getEmail());

        if(usuario != null) {
            var participante = this.participanteRepository.getItem(form.getParticipante().getCodigoControleFinanceiro(), usuario.getCodigo());
            var carteira = new CarteiraDigital(participante.getCodigo(), form.getInstituicaoFinanceira());
            this.carteiraDigitalRepository.insert(carteira);
        }
        else{
            throw new RuntimeException("Usuário não encontrado para o controle financeiro informado.");
        }
    }

    @Override
    public void updateControleFinanceiro(int codigoUsuario, ControleFinanceiro form) {
        var controle = this.controleFinanceiroRepository.getById(form.getCodigo());
        controle.atualizarControleFinanceiro(form.getDescricao(), form.getAtivo());

        this.controleFinanceiroRepository.update(controle);
    }

    @Override
    public void updateParticipante(int codigoUsuario, Participante form) {
        var participante = this.participanteRepository.getById(form.getCodigo());
        var usuario = this.usuarioService.getUser(form.getUsuario().getEmail());
        var controle = this.controleFinanceiroRepository.getById(form.getCodigoControleFinanceiro());

        if(participante != null && usuario != null && controle != null) {
            participante.setUsuario(usuario);
            participante.setControleFinanceiro(controle);
            participante.atualizarParticipante(usuario.getCodigo(), controle.getCodigo(), form.getAtivo(), form.getProprietario());

            this.participanteRepository.update(participante);
        }
        else {
            throw new RuntimeException("Não foram encontrados dados para os códigos informados.");
        }
    }

    @Override
    public void updateCarteira(int codigoUsuario, CarteiraDigital form) throws SQLException {
        var carteira = this.carteiraDigitalRepository.getById(form.getCodigo());
        var usuario = this.usuarioService.getUser(form.getParticipante().getUsuario().getEmail());

        if(carteira != null && usuario != null) {
            var participante = this.participanteRepository.getItem(form.getParticipante().getCodigoControleFinanceiro(), usuario.getCodigo());
            carteira.atualizarCarteiraDigital(participante.getCodigo(), form.getInstituicaoFinanceira(), form.getAtivo());
            this.carteiraDigitalRepository.update(carteira);
        }
        else{
            throw new RuntimeException("Não foram encontrados dados válidos.");
        }
    }
}
