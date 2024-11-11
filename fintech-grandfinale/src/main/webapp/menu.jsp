
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
    <link href="./resources/css/custom/menu.css" rel="stylesheet">
</head>

<div class="leftside-menu" data-simplebar>

    <a href="index.jsp" class="logo">
        <span class="logo-lg">
            <h1>e<span class="logo">Financeiro</span></h1>
        </span>
    </a>

    <ul class="side-nav">

        <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarDashboards" aria-expanded="true" aria-controls="sidebarDashboards" class="side-nav-link">
                <i class="uil uil-signal-alt-3"></i>
                <span> Dashboards </span>
            </a>
            <div class="collapse show" id="sidebarDashboards">
                <ul class="side-nav-second-level">
                    <li>
                        <a href="#">Saúde Financeira</a>
                    </li>
                    <li>
                        <a href="#">Receitas</a>
                    </li>
                    <li>
                        <a href="#">Despesas</a>
                    </li>
                </ul>
            </div>
        </li>

        <li class="side-nav-title">Gerenciamento</li>

        <li class="side-nav-item">
            <a href="controleFinanceiro.jsp" class="side-nav-link">
                <i class="uil-user-circle"></i>
                <span> Controle Financeiro </span>
                <span class="badge text-bg-secondary float-end">ON</span>
            </a>
        </li>

        <li class="side-nav-item">
            <a data-bs-toggle="collapse" href="#sidebarFinanceiro" aria-expanded="true" aria-controls="sidebarFinanceiro" class="side-nav-link">
                <i class="uil-money-withdraw"></i>
                <span> Fluxo de caixa</span>
                <span class="menu-arrow"></span>
            </a>
            <div class="collapse show" id="sidebarFinanceiro">
                <ul class="side-nav-second-level">
                    <li>
                        <a href="receita.jsp">Receita</a>
                    </li>
                    <li>
                        <a href="despesa.jsp">Despesa</a>
                    </li>
                </ul>
            </div>
        </li>
    </ul>
    <div class="clearfix"></div>
</div>