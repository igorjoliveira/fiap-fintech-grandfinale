<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>eFinanceiro</title>
  <link rel="stylesheet" href="resources/css/bootstrap.css">
  <link href="resources/css/custom/login.css" rel="stylesheet" type="text/css" id="app-style">
  <link href="resources/css/icons.css" rel="stylesheet" type="text/css" />
</head>

<body class="authentication-bg pb-0">
  <div class="auth-fluid">
    <div class="auth-fluid-form-box">
      <div class="card-body d-flex flex-column h-100 gap-3">

        <div class="auth-brand text-center text-lg-start">
          <a href="#" class="logo">
            <h1>e<span>Financeiro</span></h1>
          </a>
        </div>

        <div class="my-auto">
          <h4 class="mt-0">Entrar</h4>
          <p class="text-muted mb-4">Entre com o seu endereço de e-mail ou senha para acessar sua conta.</p>

          <form action="login-servlet" method="POST">
            <div class="mb-3">
              <label for="email" class="form-label">E-mail</label>
              <input class="form-control" type="email" id="email" name="email" required="" placeholder="Digite seu e-mail">
            </div>
            <div class="mb-3">
              <label for="senha" class="form-label">Senha</label>
              <input class="form-control" type="password" required="" id="senha" name="senha" placeholder="Digite a sua senha">
            </div>
            <div class="d-grid mb-0 text-center">
              <button class="btn btn-primary" type="submit"><i class="mdi mdi-login"></i> Entrar </button>
            </div>

            <div class="text-center mt-4">
              <p class="text-muted font-16">Entra com</p>
              <ul class="social-list list-inline mt-3">
                <li class="list-inline-item">
                  <a href="#" class="social-list-item border-primary text-primary"><i class="mdi mdi-facebook"></i></a>
                </li>
                <li class="list-inline-item">
                  <a href="#" class="social-list-item border-danger text-danger"><i class="mdi mdi-google"></i></a>
                </li>
              </ul>
            </div>
          </form>
        </div>

        <footer class="footer footer-alt">
          <p class="text-muted">Você não possui cadastro? <a href="cadastro.jsp" class="text-muted ms-1"><b>Cadastre-se</b></a></p>
        </footer>

      </div>
    </div>

    <div class="auth-fluid-right text-center">
      <div class="auth-user-testimonial">
        <p class="lead"><i class="mdi mdi-format-quote-open"></i> Challenger FINTECH <i class="mdi mdi-format-quote-close"></i>
        </p>
        <p>
          - br.com.fiap
        </p>
      </div>
    </div>
  </div>
</body>
</html>