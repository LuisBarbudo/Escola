<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../cabecalho.jsp" %>
<main class="main">
    <!-- Breadcrumb-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item">Vagas</li>
        <li class="breadcrumb-item">
            <a href="#">Listar</a>
        </li>

    </ol>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">

                        <form action="VagasWS" method="get" class="form-horizontal">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <span class="input-group-prepend">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fa fa-search"></i> &nbsp;</button>
                                        </span>
                                        <input type="text" name="txtFiltro" id="input1-group2" class="form-control" placeholder="digite a pesquisa">
                                        <!--Indica pro servlet que a a��o � um filtro -->
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
                                    <th>Disciplina</th>
                                    <th>Esta Aberta</th>
                                    

                                    <th>A��es</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${lista}" var="obj">
                                <tr>
                                    <td>${obj.id}</td>
                                    <td>${obj.nomev}</td>
                                    <td>${obj.confirmacao}</td>
                                    <td>
                                        <a href="VagasWS?acao=upd&id=${obj.id}" class="btn btn-pill btn-success btn-sm" title="alterar"> 
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a href="VagasWS?acao=del&id=${obj.id}" class="btn btn-pill btn-danger btn-sm" title="excluir"> 
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
