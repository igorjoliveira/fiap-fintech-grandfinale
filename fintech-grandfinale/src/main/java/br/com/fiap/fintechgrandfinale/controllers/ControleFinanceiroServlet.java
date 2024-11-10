package br.com.fiap.fintechgrandfinale.controllers;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.application.models.ControleFinanceiroModel;
import br.com.fiap.fintechgrandfinale.application.services.ControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controlefinanceiro-servlet")
public class ControleFinanceiroServlet extends HttpServlet {
    private IControleFinanceiroService controleFinanceiroService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.controleFinanceiroService = new ControleFinanceiroService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usuario = (Usuario)req.getSession().getAttribute("usuarioLogado");
        var descricao = req.getParameter("filtro_descricao");
        var grupos = this.controleFinanceiroService.getAllControleFinanceiro(usuario.getCodigo());

        var model = new ControleFinanceiroModel();
        model.setGrupos(grupos);
        model.setAbaAtiva(1);

        req.setAttribute("model", model);
        req.getRequestDispatcher("/controleFinanceiro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var usuario = (Usuario)req.getSession().getAttribute("usuarioLogado");
            var controleFinanceiro = new ControleFinanceiro(req.getParameter("descricao"));

            this.controleFinanceiroService.insertControleFinanceiro(usuario.getCodigo(), controleFinanceiro);

            req.getRequestDispatcher("/controleFinanceiro.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
