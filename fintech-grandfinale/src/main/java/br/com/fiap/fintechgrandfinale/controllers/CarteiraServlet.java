package br.com.fiap.fintechgrandfinale.controllers;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.application.models.CarteiraDigitalModel;
import br.com.fiap.fintechgrandfinale.application.services.ControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;
import br.com.fiap.fintechgrandfinale.domain.utils.IntegerUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/carteira-servlet")
public class CarteiraServlet extends HttpServlet {
    private IControleFinanceiroService controleFinanceiroService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.controleFinanceiroService = new ControleFinanceiroService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usuario = (Usuario)req.getSession().getAttribute("usuarioLogado");

        var filtroGrupo = req.getParameter("filtro_grupo") != null ? Integer.parseInt(req.getParameter("filtro_grupo")) : 0;
        var filtroInstituicao = req.getParameter("filtro_instituicao") != null ? Integer.parseInt(req.getParameter("filtro_instituicao")) : 0;
        var filtroNome = req.getParameter("filtro_nome");
        var filtroEmail = req.getParameter("filtro_email");

        var model = new CarteiraDigitalModel();
        var instituicoes = this.controleFinanceiroService.getAllInstituicoes();
        var grupos = this.controleFinanceiroService.getAllControleFinanceiro(usuario.getCodigo(), null);
        var carteiras = this.controleFinanceiroService.getAllCarteiraDigital(usuario.getCodigo(), filtroGrupo, filtroInstituicao, filtroNome, filtroEmail);

        model.setGrupos(grupos);
        model.setCarteiras(carteiras);
        model.setInstituicoes(instituicoes);

        req.setAttribute("model", model);
        req.getRequestDispatcher("/controleFinanceiro-carteira-view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var usuario = (Usuario)req.getSession().getAttribute("usuarioLogado");

            var codigo = req.getParameter("codigo");
            var codigoControleFinanceiro = req.getParameter("codigoControleFinanceiro");
            var codigoInstituicao = req.getParameter("codigoInstituicao");
            var email = req.getParameter("email");
            var ativo = req.getParameter("ativo");

            var participante = new Participante();
            var user = new Usuario();
            user.setEmail(email);
            participante.setUsuario(user);
            participante.setCodigoControleFinanceiro(IntegerUtils.tryParseInt(codigoControleFinanceiro));

            var carteira = new CarteiraDigital();
            carteira.setInstituicaoFinanceira(EnumUtils.fromValue(InstituicaoFinanceira.class, IntegerUtils.tryParseInt(codigoInstituicao)));
            carteira.setAtivo(Boolean.parseBoolean(ativo));
            carteira.setParticipante(participante);

            if(codigo != null && !codigo.isEmpty() && IntegerUtils.tryParseInt(codigo) > 0) {
                carteira.setCodigo(Integer.parseInt(codigo));
                this.controleFinanceiroService.updateCarteira(usuario.getCodigo(), carteira);
            }
            else
                this.controleFinanceiroService.insertCarteira(usuario.getCodigo(), carteira);

            req.getSession().setAttribute("status", true);
        } catch (RuntimeException | SQLException e) {
            req.getSession().setAttribute("status", false);
        }

        resp.sendRedirect(req.getContextPath() + "/carteira-servlet");
    }
}
