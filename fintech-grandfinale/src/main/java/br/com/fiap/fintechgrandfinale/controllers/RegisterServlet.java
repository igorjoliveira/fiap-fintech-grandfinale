package br.com.fiap.fintechgrandfinale.controllers;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.application.services.UsuarioService;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.enums.Autenticador;
import br.com.fiap.fintechgrandfinale.domain.enums.Sexo;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {

    private IUsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String nome = request.getParameter("nome");
            String sobreNome = request.getParameter("sobreNome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String dataNascimentoStr = request.getParameter("dataNascimento");

            var usuario = new Usuario(nome,
            sobreNome,
            EnumUtils.fromValue(Sexo.class, Integer.parseInt(request.getParameter("sexo"))),
            LocalDate.parse(dataNascimentoStr),
            email,
            Autenticador.Interno,
            senha);

            this.usuarioService.register(usuario);

            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

