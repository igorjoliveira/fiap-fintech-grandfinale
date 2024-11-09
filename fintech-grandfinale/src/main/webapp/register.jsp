<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eFinanceiro</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link href="./resources/css/custom/register.css" rel="stylesheet" type="text/css" id="app-style">
    <link href="./resources/css/icons.css" rel="stylesheet" type="text/css" />
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
                    <form action="register-servlet" method="POST">
                        <div class="form-group mt-5 mb-2">
                            <label for="nome">Primeiro nome</label>
                            <input type="text" class="form-control" id="nome" name="nome" required>
                        </div>
                        <div class="form-group mb-2">
                            <label for="sobreNome">Sobre nome</label>
                            <input type="text" class="form-control" id="sobreNome" name="sobreNome" required>
                        </div>
                        <div class="form-group mb-2">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group mb-2">
                            <label for="senha">Senha</label>
                            <input type="password" class="form-control" id="senha" name="senha" required>
                        </div>
                        <div class="form-group mb-2">
                            <label for="sexo">Sexo</label>
                            <select class="form-control" id="sexo" name="sexo" required>
                                <option value="1">Feminino</option>
                                <option value="2">Masculino</option>
                            </select>
                        </div>
                        <div class="form-group mb-2">
                            <label for="dataNascimento">Data de nascimento</label>
                            <input type="date" class="form-control" id="dataNascimento" name="dataNascimento" required>
                        </div>

                        <div class="d-grid mb-0 text-center">
                            <button class="btn btn-primary" type="submit"><i class="mdi mdi-login"></i> Registar </button>
                        </div>
                    </form>
                </div>

                <footer class="footer footer-alt">
                    <p class="text-muted">Você já possui cadastro? <a href="login.jsp" class="text-muted ms-1"><b>Faça login aqui</b></a></p>
                </footer>
            </div>
        </div>
    </div>
</body>
</html>