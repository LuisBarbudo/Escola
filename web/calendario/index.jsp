<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../cabecalho.jsp" %>
<main class="main">
    <!-- Breadcrumb-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item">Calendario</li>
        <li class="breadcrumb-item">
            <a href="#">Listar</a>
        </li>

    </ol>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">

                        <form action="CalendarioWS" method="get" class="form-horizontal">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <span class="input-group-prepend">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fa fa-search"></i> &nbsp;</button>
                                        </span>
                                        <input type="text" name="txtFiltro" id="input1-group2" class="form-control" placeholder="digite a pesquisa">
                                        <!--Indica pro servlet que a ação é um filtro -->
                                        <input type="hidden" name="acao" value="filter"/>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-body">
                        <table class="table table-responsive-sm">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Dia</th>
                                    <th>Mes</th>
                                    <th>Ano</th>
                                    <th>Descriçao</th>

                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${lista}" var="obj">
                                <tr>
                                    <td>${obj.id}}</td>
                                    <td>${obj.dia}</td>
                                    <td>${obj.mes}</td>
                                    <td>${obj.ano}</td>
                                    <td>${obj.descricao}</td>
                                    <td>
                                        <a href="CalendarioWS?acao=upd&id=${obj.id}" class="btn btn-pill btn-success btn-sm" title="alterar"> 
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a href="CalendarioWS?acao=del&id=${obj.id}" class="btn btn-pill btn-danger btn-sm" title="excluir"> 
                                            <i class="fa fa-remove"></i>
                                        </a>

                                    </td>
                                </tr>
                                   </c:forEach>
                            </tbody>
                            
                        </table>
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#">Prev</a>
                            </li>
                            
                        <div class="row col-lg-12">
                            <a href="add.jsp" class="btn btn-pill btn-primary"> 
                                <i class="fa fa-plus"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!--/.col-->


        </div>

        <!--/.row-->
    </div>
    
</main>



<%@include file="../rodape.jsp" %>
