package br.com.fiap.fintechgrandfinale.controllers;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.application.models.ParticipanteModel;
import br.com.fiap.fintechgrandfinale.application.services.ControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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

        var grupos = this.controleFinanceiroService.getAllControleFinanceiro(usuario.getCodigo(), null);
        var participantes = this.controleFinanceiroService.getAllParticipante(usuario.getCodigo(), 0, "", "");

        var model = new ParticipanteModel();
        model.setGrupos(grupos);
        model.setParticipantes(participantes);

        req.setAttribute("model", model);
        req.getRequestDispatcher("/controleFinanceiro-participante-view.jsp").forward(req, resp);
    }
}
