package br.com.fiap.fintechgrandfinale.api;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.application.models.FormLoginModel;
import br.com.fiap.fintechgrandfinale.application.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

    private IUsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var credencial = new FormLoginModel();
        credencial.setEmail(request.getParameter("email"));
        credencial.setSenha(request.getParameter("senha"));

        var usuario = usuarioService.login(credencial);
        if(usuario != null && usuario.getCodigo() > 0) {
            var session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);

            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}