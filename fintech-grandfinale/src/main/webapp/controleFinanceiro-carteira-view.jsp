
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div id="content-carteira">
  <form id="carteiraFiltroForm" class="d-inline">
    <div class="row mb-3">
      <div class="col-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-md-3">
                <label for="filtro_grupo" class="form-label">Grupo</label>
                <select class="form-control" id="filtro_grupo" name="filtro_grupo">
                  <option value="0">Selecione</option>
                  <c:forEach items="${model.grupos}" var="item">
                    <option value="${item.codigo}">${item.descricao}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-md-3">
                <label for="filtro_nome" class="form-label">Nome</label>
                <input type="text" class="form-control" id="filtro_nome">
              </div>
              <div class="col-md-4">
                <label for="filtro_email" class="form-label">Email</label>
                <input type="text" class="form-control" id="filtro_email">
              </div>
              <div class="col-md-2">
                <label for="filtro_instituicao" class="form-label">Instituição</label>
                <select class="form-control" id="filtro_instituicao" name="filtro_instituicao">
                  <option value="0">Selecione</option>
                  <c:forEach items="${model.instituicoes}" var="item">
                    <option value="${item.value}">${item.name()}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12 d-flex flex-row-reverse">
        <button type="button" class="btn btn-sm btn-primary ml-2" data-bs-toggle="modal" data-bs-target="#carteiraModal" onclick="clearForm()">Incluir</button>
        <button type="submit" class="btn btn-sm btn-success">Buscar</button>
      </div>
    </div>
  </form>
  <div class="row">
    <div class="col-12">
      <div class="table-responsive-sm">
        <table class="table table-striped table-centered mb-0">
          <thead>
          <tr>
            <th>Código</th>
            <th>Grupo</th>
            <th>Usuário</th>
            <th>Instituição</th>
            <th>Ativo</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <c:if test="${empty model.carteiras}">
            <tr>
              <td colspan="6">Nenhum registro encontrado</td>
            </tr>
          </c:if>
          <c:forEach items="${model.carteiras}" var="item">
            <tr>
              <td>${item.codigo}</td>
              <td>${item.participante.controleFinanceiro.descricao}</td>
              <td>${item.participante.usuario.nome} (${item.participante.usuario.email})</td>
              <td>${item.instituicaoFinanceira}</td>
              <td>${item.ativo}</td>
              <td class="table-action">
                <a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-pencil"></i></a>
                <a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div class="modal fade" id="carteiraModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="modalLabel">Editar Descrição</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="carteiraForm" action="descricaoController" method="GET">
            <div class="mb-3">
              <label for="descricao" class="form-label">Descrição</label>
              <input type="text" class="form-control" id="descricao" name="descricao" required>
            </div>
            <div class="mb-3">
              <label for="dataCadastro" class="form-label">Data de Cadastro</label>
              <input type="text" class="form-control" id="dataCadastro" name="dataCadastro" readonly>
            </div>
            <div class="mb-3">
              <label for="ativo" class="form-label">Ativo</label>
              <select class="form-control" id="ativo" name="ativo">
                <option value="true">Sim</option>
                <option value="false">Não</option>
              </select>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <script>
      function clearForm() {
          document.getElementById('carteiraForm').reset();
      }

      $(document).ready(function() {
          $('#carteiraFiltroForm').submit(function(event) {
              event.preventDefault();

              var grupo = $('#carteiraFiltroForm #filtro_grupo').val();
              var nome = $('#carteiraFiltroForm #filtro_nome').val();
              var email = $('#carteiraFiltroForm #filtro_email').val();
              var instituicao = $('#carteiraFiltroForm #filtro_instituicao').val();

              $.get('carteira-servlet', {
                  filtro_grupo: grupo,
                  filtro_nome: nome,
                  filtro_email: email,
                  filtro_instituicao: instituicao
              }, function(response) {
                  $('#content-carteira').html(response);

                  $('#carteiraFiltroForm #filtro_grupo').val(grupo);
                  $('#carteiraFiltroForm #filtro_nome').val(nome);
                  $('#carteiraFiltroForm #filtro_email').val(email);
                  $('#carteiraFiltroForm #filtro_instituicao').val(instituicao);
              });
          });
      });
  </script>
</div>




