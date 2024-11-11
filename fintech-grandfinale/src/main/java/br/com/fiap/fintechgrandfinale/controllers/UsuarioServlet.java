package br.com.fiap.fintechgrandfinale.controllers;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.application.services.UsuarioService;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.enums.Sexo;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/usuario-servlet")
public class UsuarioServlet extends HttpServlet {

    private IUsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioService();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usuarioLogado = (Usuario)req.getSession().getAttribute("usuarioLogado");

        String nome = req.getParameter("nome");
        String sobreNome = req.getParameter("sobreNome");
        String email = req.getParameter("email");
        String dataNascimentoStr = req.getParameter("dataNascimento");

        var usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSobreNome(sobreNome);
        usuario.setEmail(email);
        usuario.setSexo(EnumUtils.fromValue(Sexo.class, Integer.parseInt(req.getParameter("sexo"))));
        usuario.setDataNascimento(LocalDate.parse(dataNascimentoStr));
        usuario.setAtivo(true);

        this.usuarioService.update(usuarioLogado.getCodigo(), usuario);
        resp.sendRedirect(req.getContextPath() + "/usuario-servlet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usuarioLogado = (Usuario)req.getSession().getAttribute("usuarioLogado");
        var userModel = this.usuarioService.getUser(usuarioLogado.getCodigo());

        req.getSession().setAttribute("usuarioLogado", userModel);
        req.getRequestDispatcher("/minhaConta.jsp").forward(req, resp);
    }
}
