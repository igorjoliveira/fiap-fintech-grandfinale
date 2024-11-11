
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
  <link href="./resources/css/custom/header.css" rel="stylesheet">
</head>

<header class="header">
  <div class="topbar container-fluid">
    <div class="d-flex align-items-center gap-lg-2 gap-1">
    </div>

    <ul class="topbar-menu d-flex align-items-center gap-3">
      <li class="dropdown profile">
        <a class="nav-link dropdown-toggle arrow-none nav-user px-2" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
          <span class="account-user-avatar">
              <img src="./resources/images/fintech-user.png" alt="user-image" width="32" class="rounded-circle">
          </span>
          <span class="d-lg-flex flex-column gap-1 d-none">
            <h5 class="my-0 text-uppercase">${sessionScope.usuarioLogado.nome}</h5>
            <h6 class="my-0 fw-normal">Dono</h6>
          </span>
        </a>
        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated profile-dropdown">
          <!-- item-->
          <div class=" dropdown-header noti-title">
            <h6 class="text-overflow m-0">Bem vindo!</h6>
          </div>

          <!-- item-->
          <a href="usuario-servlet" class="dropdown-item">
            <i class="mdi mdi-account-circle me-1"></i>
            <span>Minha conta</span>
          </a>

          <!-- item-->
          <a href="logout-servlet" class="dropdown-item">
            <i class="mdi mdi-logout me-1"></i>
            <span>Sair</span>
          </a>
        </div>
      </li>
    </ul>
  </div>
</header>