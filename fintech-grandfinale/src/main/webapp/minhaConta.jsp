<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page session="true" %>

<%
  var user = session.getAttribute("usuarioLogado");
  if (user == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>eFinanceiro</title>
  <link rel="stylesheet" href="resources/css/bootstrap.css">
  <link href="resources/css/custom/userdetail.css" rel="stylesheet" type="text/css" id="app-style">
  <link href="resources/css/icons.css" rel="stylesheet" type="text/css" />

  <script>
    function sendPost() {
      var form = document.getElementById('userForm');
      form.method = 'POST';
      form.action = 'usuario-servlet';
      form.submit();
    }
  </script>
</head>

<body>
<div class="wrapper">
  <%@include file="header.jsp"%>
  <%@include file="menu.jsp"%>

  <div class="content-page">
    <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="page-title-box">
              <div class="page-title-right">
                <ol class="breadcrumb m-0">
                  <li class="breadcrumb-item"><a href="index.jsp">Início</a></li>
                  <li class="breadcrumb-item active">Minha conta</li>
                </ol>
              </div>
              <h4 class="page-title">Minha conta </h4>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3"></div>
          <div class="col-md-6">
            <form id="userForm" action="usuario-servlet" method="POST">
              <div class="card">
                <div class="card-body">
                  <div class="row mb-2">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${sessionScope.usuarioLogado.nome}" required>
                      </div>
                    </div>
                  </div>
                  <div class="row mb-2">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="sobreNome">Sobrenome</label>
                        <input type="text" class="form-control" id="sobreNome" name="sobreNome" value="${sessionScope.usuarioLogado.sobreNome}" required>
                      </div>
                    </div>
                  </div>
                  <div class="row mb-2">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${sessionScope.usuarioLogado.email}" required>
                      </div>
                    </div>
                  </div>
                  <div class="row mb-2">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="sexo">Sexo</label>
                        <select class="form-control" id="sexo" name="sexo" required>
                          <option value="1" <c:if test="${sessionScope.usuarioLogado.getSexo().getValue() == 1}">selected</c:if>>Masculino</option>
                          <option value="2" <c:if test="${sessionScope.usuarioLogado.getSexo().getValue() == 2}">selected</c:if>>Feminino</option>
                        </select>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="dataNascimento">Data de nascimento</label>
                        <input type="date" class="form-control" id="dataNascimento" name="dataNascimento"
                               value="${sessionScope.usuarioLogado.dataNascimento}" required>
                      </div>
                    </div>
                  </div>
                  <div class="row mb-2">
                    <div class="col-md-12 d-flex flex-row-reverse">
                      <a type="button" class="btn btn-sm btn-primary ml-2" href="javascript:void(0);" onclick="sendPost()">Salvar</a>
                      <a type="button" class="btn btn-sm btn-secondary ml-2" href="minhaConta.jsp">Cancelar</a>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-3"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- App js -->
<script src="resources/js/bootstrap.bundle.min.js"></script>
</body>

</html>