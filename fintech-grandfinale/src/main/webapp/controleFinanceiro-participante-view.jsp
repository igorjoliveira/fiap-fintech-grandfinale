
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div id="content-participante">
  <form id="participanteFiltroForm" class="d-inline">
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
              <div class="col-md-4">
                <label for="filtro_nome" class="form-label">Nome</label>
                <input type="text" class="form-control" id="filtro_nome">
              </div>
              <div class="col-md-5">
                <label for="filtro_email" class="form-label">Email</label>
                <input type="text" class="form-control" id="filtro_email">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-12 d-flex flex-row-reverse">
        <button type="button" class="btn btn-sm btn-primary ml-2" data-bs-toggle="modal" data-bs-target="#participanteModal" onclick="clearForm()">Incluir</button>
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
            <th>Código Usuário</th>
            <th>Grupo</th>
            <th>Usuário</th>
            <th>Proprietário</th>
            <th>Ativo</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <c:if test="${empty model.participantes}">
            <tr>
              <td colspan="7">Nenhum registro encontrado</td>
            </tr>
          </c:if>
          <c:forEach items="${model.participantes}" var="item">
            <tr>
              <td>${item.codigo}</td>
              <td>${item.codigoUsuario}</td>
              <td>${item.controleFinanceiro.descricao}</td>
              <td>${item.usuario.nome} (${item.usuario.email})</td>
              <td>${item.proprietario}</td>
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
  <div class="modal fade" id="participanteModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="modalLabel">Participante</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="participanteForm">
            <input type="hidden" id="codigo" name="codigo">

            <div class="row mb-3">
              <div class="col-md-12">
                <label for="codigoControleFinanceiro" class="form-label">Grupo</label>
                <select class="form-control" id="codigoControleFinanceiro" name="codigoControleFinanceiro" required>
                  <option value="">Selecione</option>
                  <c:forEach items="${model.grupos}" var="item">
                    <option value="${item.codigo}">${item.descricao}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-10">
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control" id="email" name="email" required>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-2">
                <label for="proprietario" class="form-label">Propritário</label>
                <input type="checkbox" class="form-check-input" id="proprietario" name="proprietario">
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
      $('#participanteForm')[0].reset();
      $('#participanteForm #codigo').val(0);
      $('#participanteForm #codigoControleFinanceiro').val(0);
      $('#participanteForm #email').val('');
      $('#participanteForm #ativo').prop('checked', true);
      $('#participanteForm #proprietario').prop('checked', false);
      $('#participanteModal #modalLabel').text('Incluir Participante');
    }

    function setEditForm(item) {
      $('#participanteForm')[0].reset();
      $('#participanteForm #codigo').val(item.codigo);
      $('#participanteForm #codigoControleFinanceiro').val(item.codigo_controle_financeiro);
      $('#participanteForm #email').val(item.usuario.email);
      $('#participanteForm #ativo').prop('checked', item.ativo);
      $('#participanteForm #proprietario').prop('checked', item.proprietario);
      $('#participanteModal #modalLabel').text('Atualizar Participante');
    }

    $(document).ready(function() {
      setTimeout(function() {
        $('.alert').alert('close');
      }, 5000);

      $('#participanteFiltroForm').submit(function(event) {
        event.preventDefault();

        var grupo = $('#filtro_grupo').val();
        var nome = $('#filtro_nome').val();
        var email = $('#filtro_email').val();

        $.get('participante-servlet', {
          filtro_grupo: grupo,
          filtro_nome: nome,
          filtro_email: email
          }, function(response) {
            $('#content-participante').html(response);

            $('#filtro_grupo').val(grupo);
            $('#filtro_nome').val(nome);
            $('#filtro_email').val(email);
          });
        });

      $('#participanteForm').submit(function(event) {
        event.preventDefault();

        var codigo = $('#participanteForm #codigo').val();
        var codigoControleFinanceiro = $('#participanteForm #codigoControleFinanceiro').val();
        var email = $('#participanteForm #email').val();
        var ativo = $('#participanteForm #ativo').prop('checked');
        var proprietario = $('#participanteForm #proprietario').prop('checked');

        $('#participanteModal').modal('hide');

        $.post('participante-servlet',  {
          codigo: codigo,
          codigoControleFinanceiro: codigoControleFinanceiro,
          email: email,
          ativo: ativo,
          proprietario: proprietario
          }, function(response) {

            $('#content-participante').html(response);
            clearForm();
          });
        });

      $('[data-bs-toggle="modal"][data-bs-target="#participanteModal"]').on('click', function() {
        clearForm();
      });

      $('.action-icon').on('click', function() {
        var item = $(this).data('item');
        setEditForm(item);
        $('#participanteModal').modal('show');
      });
    });
  </script>
</div>

