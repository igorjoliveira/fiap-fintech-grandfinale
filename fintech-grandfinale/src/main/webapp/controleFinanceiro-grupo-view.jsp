
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="br.com.fiap.fintechgrandfinale.domain.utils.DateUtils" %>

<div id="content-grupo">
  <div class="row">
    <div class="col-12">
      <div class="card">
        <div class="card-body">
          <form id="grupoFiltroForm" class="d-inline">
            <div class="row">
              <div class="col-md-12">
                <label for="filtro_descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control" id="filtro_descricao" name="filtro_descricao">
              </div>
            </div>

            <div class="row mt-2">
              <div class="col-12 d-flex flex-row-reverse">
                <button type="button" class="btn btn-sm btn-primary ml-2" data-bs-toggle="modal" data-bs-target="#grupoModal" onclick="clearForm()">Incluir</button>
                <button type="submit" class="btn btn-sm btn-success">Buscar</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="row mt-2">
    <div class="col-12">
      <div class="table-responsive-sm">
        <table class="table table-striped table-centered mb-0">
          <thead>
          <tr>
            <th>Código</th>
            <th>Descrição</th>
            <th>Data de Cadastro</th>
            <th>Ativo</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <c:if test="${empty model.grupos}">
            <tr>
              <td colspan="5">Nenhum registro encontrado</td>
            </tr>
          </c:if>
          <c:forEach items="${model.grupos}" var="item">
            <tr>
              <td class="table-user">${item.codigo}</td>
              <td>${item.descricao}</td>
              <td>${DateUtils.formatLocalDateTime(item.dataHoraCadastro)}</td>
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
  <div class="modal fade" id="grupoModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="modalLabel">Controle Financeiro</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="grupoForm" action="controlefinanceiro-servlet" method="POST">
            <div class="row mb-3">
              <div class="col-md-12">
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control" id="descricao" name="descricao" required>
              </div>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script>
      function clearForm() {
          document.getElementById('grupoForm').reset();
          document.getElementById('descricao').value = "";
      }

      $('#grupoFiltroForm').submit(function(event) {
          event.preventDefault();

          var filter = $('input[name="filtro_descricao"]').val();

          $.get('controlefinanceiro-servlet', { filtro_descricao: filter }, function(response) {
              $('#content-grupo').html(response);
              $('#filtro_descricao').val(filter);
          });
      });
  </script>
</div>

