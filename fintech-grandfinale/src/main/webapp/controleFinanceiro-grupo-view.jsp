
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page import="br.com.fiap.fintechgrandfinale.domain.utils.DateUtils" %>

<div id="content-grupo">
  <form id="grupoFiltroForm" class="d-inline">
    <div class="row mb-3">
      <div class="col-12">
        <div class="card">
          <div class="card-body">
            <div class="row">
              <div class="col-md-12">
                <label for="filtro_descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control" id="filtro_descricao" name="filtro_descricao">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12 d-flex flex-row-reverse">
        <button type="button" class="btn btn-sm btn-primary ml-2" data-bs-toggle="modal" data-bs-target="#grupoModal" onclick="clearForm()">Incluir</button>
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
                <a href="javascript: void(0);" class="action-icon" data-item='${item.toJson()}'> <i class="mdi mdi-pencil"></i></a>
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
          <form id="grupoForm">
            <input type="hidden" id="codigo" name="codigo">

            <div class="row mb-3">
              <div class="col-md-10">
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" class="form-control" id="descricao" name="descricao" required>
              </div>
              <div class="col-md-1">
                <label for="ativo" class="form-label">Ativo</label>
                <input type="checkbox" class="form-check-input" id="ativo" name="ativo">
              </div>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <c:if test="${not empty sessionScope.status}">
    <div class="mt-5 alert alert-dismissible fade show alert-footer
    <c:choose>
      <c:when test="${sessionScope.status}">alert-success</c:when>
      <c:otherwise>alert-danger</c:otherwise>
    </c:choose>" role="alert">
      <c:choose>
        <c:when test="${sessionScope.status}"><strong>Parabéns!</strong> Operação realizada com sucesso</c:when>
        <c:otherwise><strong>Atenção!</strong> Ocorreu um erro ao realizar operação</c:otherwise>
      </c:choose>

      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <c:remove var="status" scope="session" />
  </c:if>

  <script>
      function clearForm() {
          document.getElementById('grupoForm').reset();
          document.getElementById('descricao').value = "";
          document.getElementById('codigo').value = "";
          document.getElementById('ativo').checked = true;
          document.getElementById('modalLabel').textContent = "Incluir Grupo";
      }

      function setEditForm(item) {
          document.getElementById('descricao').value = item.descricao;
          document.getElementById('codigo').value = item.codigo;
          document.getElementById('ativo').checked = item.ativo;
          document.getElementById('modalLabel').textContent = "Atualizar Grupo";
      }

      $(document).ready(function() {
          setTimeout(function() {
              $('.alert').alert('close');
          }, 5000);

          $('#grupoFiltroForm').submit(function(event) {
              event.preventDefault();

              var filter = $('input[name="filtro_descricao"]').val();

              $.get('controlefinanceiro-servlet', { filtro_descricao: filter }, function(response) {
                  $('#content-grupo').html(response);
                  $('#filtro_descricao').val(filter);
              });
          });

          $('#grupoForm').submit(function(event) {
              event.preventDefault();

              var descricao = $('input[name="descricao"]').val();
              var codigo = $('input[name="codigo"]').val();
              var ativo = $('#ativo').prop('checked');

              $('#grupoModal').modal('hide');

              $.post('controlefinanceiro-servlet',  { descricao: descricao, codigo: codigo, ativo: ativo }, function(response) {

                  $('#content-grupo').html(response);
                  clearForm();
              });
          });

          $('[data-bs-toggle="modal"][data-bs-target="#grupoModal"]').on('click', function() {
              clearForm();
          });

          $('.action-icon').on('click', function() {
              var item = $(this).data('item');
              setEditForm(item);
              $('#grupoModal').modal('show');
          });
      });
  </script>
</div>

