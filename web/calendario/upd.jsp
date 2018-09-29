<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../cabecalho.jsp" %>
<main class="main">
    <!-- Breadcrumb-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item">Calendario</li>
        <li class="breadcrumb-item">
            <a href="CalendarioWS">Listar</a>
        </li>

    </ol>
    <div class="container-fluid">
        <div class="row">
           <div class="row">
              <div class="col-lg-auto">
                <div class="card">
                  <div class="card-header">
                    Alteração
                  </div>
                  <div class="card-body">
                      <form action="CalendarioWS" method="post">
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
                            <span class="input-group-text">Dia</span>
                          </div>
                          <input type="number" value="${obj.dia}" name="txtDia" class="form-control">
                         
                        </div>
                      </div>
                          <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">Mes</span>
                          </div>
                          <input type="number" value="${obj.mes}" name="txtMes" class="form-control">
                          
                          
                           </div>
                      </div>
                          <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">Ano</span>
                          </div>
                          <input type="number" value="${obj.ano}" name="txtAno" class="form-control">
                          
                          
                           </div>
                      </div>
                          <div class="form-group">
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">Descrição</span>
                          </div>
                          <input type="text" value="${obj.descricao}" name="txtDescricao" class="form-control">
                         
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
