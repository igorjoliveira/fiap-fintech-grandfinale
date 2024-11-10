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
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                  <li class="breadcrumb-item active">Controle Financeiro</li>
                </ol>
              </div>
              <h4 class="page-title">Controle Financeiro </h4>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-body">
                <ul class="nav nav-tabs mb-3">
                  <li class="nav-item">
                    <a href="javascript:void(0);" data-bs-toggle="tab" aria-expanded="true" class="nav-link active" id="tab-grupo" onclick="loadTabContent('grupo', 'controlefinanceiro-servlet')">
                      <i class="mdi mdi-home-variant d-md-none d-block"></i>
                      <span class="d-none d-md-block">Grupo</span>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a href="javascript:void(0);" data-bs-toggle="tab" aria-expanded="false" class="nav-link" id="tab-participante" onclick="loadTabContent('participante', 'participante-servlet')">
                      <i class="mdi mdi-account-circle d-md-none d-block"></i>
                      <span class="d-none d-md-block">Participante</span>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a href="javascript:void(0);" data-bs-toggle="tab" aria-expanded="false" class="nav-link" id="tab-carteira" onclick="loadTabContent('carteira', 'carteira-servlet')">
                      <i class="mdi mdi-settings-outline d-md-none d-block"></i>
                      <span class="d-none d-md-block">Carteira Digital</span>
                    </a>
                  </li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane show active" id="grupo">
                    <%@include file="controleFinanceiro-grupo-view.jsp"%>
                  </div>
                  <div class="tab-pane" id="participante">
                    <%@include file="controleFinanceiro-participante-view.jsp"%>
                  </div>
                  <div class="tab-pane" id="carteira">
                    <%@include file="controleFinanceiro-carteira-view.jsp"%>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- App js -->
<script src="resources/js/bootstrap.bundle.min.js"></script>

<script>
    function loadTabContent(tabId, servletUrl) {
        $('.tab-pane').removeClass('active show');

        // Set active tab and content
        $('#tabMenu .nav-link').removeClass('active');
        $('#' + 'tab-' + tabId).addClass('active');

        // Make AJAX request to the servlet
        $.ajax({
            url: servletUrl,
            type: 'GET',
            success: function(response) {
                // Update the content of the current tab
                $('#' + tabId).html(response).addClass('active show');
            },
            error: function(xhr, status, error) {
                alert('Error: ' + error);
            }
        });
    }

    // Initially load the first tab (Grupo)
    $(document).ready(function() {
        loadTabContent('grupo', 'controlefinanceiro-servlet');
    });
</script>

</body>

</html>