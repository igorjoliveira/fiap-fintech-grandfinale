package br.com.fiap.fintechgrandfinale.controllers;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.application.models.ParticipanteModel;
import br.com.fiap.fintechgrandfinale.application.services.ControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.utils.IntegerUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/participante-servlet")
public class ParticipanteServlet extends HttpServlet {
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
        var filtroNome = req.getParameter("filtro_nome");
        var filtroEmail = req.getParameter("filtro_email");

        var grupos = this.controleFinanceiroService.getAllControleFinanceiro(usuario.getCodigo(), null);
        var participantes = this.controleFinanceiroService.getAllParticipante(usuario.getCodigo(), filtroGrupo, filtroNome, filtroEmail);

        var model = new ParticipanteModel();
        model.setGrupos(grupos);
        model.setParticipantes(participantes);

        req.setAttribute("model", model);
        req.getRequestDispatcher("/controleFinanceiro-participante-view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var usuario = (Usuario)req.getSession().getAttribute("usuarioLogado");

            var codigo = req.getParameter("codigo");
            var codigoControleFinanceiro = req.getParameter("codigoControleFinanceiro");
            var email = req.getParameter("email");
            var ativo = req.getParameter("ativo");
            var proprietario = req.getParameter("proprietario");

            var user = new Usuario();
            user.setEmail(email);

            var participante = new Participante();
            participante.setCodigoControleFinanceiro(Integer.parseInt(codigoControleFinanceiro));
            participante.setProprietario(Boolean.parseBoolean(proprietario));
            participante.setAtivo(Boolean.parseBoolean(ativo));
            participante.setUsuario(user);


            if(codigo != null && !codigo.isEmpty() && IntegerUtils.tryParseInt(codigo) > 0) {
                participante.setCodigo(Integer.parseInt(codigo));
                this.controleFinanceiroService.updateParticipante(usuario.getCodigo(), participante);
            }
            else
                this.controleFinanceiroService.insertParticipante(usuario.getCodigo(), participante);

            req.getSession().setAttribute("status", true);
        } catch (RuntimeException | SQLException e) {
            req.getSession().setAttribute("status", false);
        }

        resp.sendRedirect(req.getContextPath() + "/participante-servlet");
    }
}
