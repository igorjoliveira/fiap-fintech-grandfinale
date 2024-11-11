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
                  <li class="breadcrumb-item active">Renda</li>
                </ol>
              </div>
              <h4 class="page-title">Renda </h4>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- App js -->
<script src="resources/js/bootstrap.bundle.min.js"></script>
</body>

</html>