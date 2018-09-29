<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../cabecalho.jsp" %>
<main class="main">
    <!-- Breadcrumb-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item">Vagas</li>
        <li class="breadcrumb-item">
            <a href="VagasWS">Listar</a>
        </li>

    </ol>
    <div class="container-fluid">
        <div class="row">
           <div class="row">
              <div class="col-lg-auto">
                <div class="card">
                  <div class="card-header">
                    Altera��o
                  </div>
                  <div class="card-body">
                      <form action="VagasWS" method="post">
                          <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">Id</span>
                          </div>
                          <input type="text" value="${obj.id}" name="txtId" readonly="readonly" class="form-control">
                         
                        </div>
                      </div>
              
                      </div>
          
                      <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">Disciplina</span>
                          </div>
                          <input type="text" value="${obj.nomev}" name="txtNomeV" class="form-control">
                         
                        </div>
                      </div>
                          <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">Aberta?</span>
                          </div>
                          <input type="checkbox" value="${obj.confirmacao}" name="txtConfirmacao" class="form-control">
                          
                          
                           </div>
                      </div>
                          
                      <div class="form-group form-actions">
                        <button type="submit" class="btn btn-sm btn-primary">Enviar</button>
                      </div>
                          
                          
                    </form>
                  </div>
                </div>
              </div>
              

            </div>

        <!--/.row-->
    </div>
</main>



<%@include file="../rodape.jsp" %>
