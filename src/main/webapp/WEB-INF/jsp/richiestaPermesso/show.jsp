<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Tipo Permesso:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.tipoPermesso}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nota:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.nota}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Inizio:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_richiestaPermesso_attr.dataInizio}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Fine:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_richiestaPermesso_attr.dataFine}" /></dd>
			    	</dl>
			    	
			    	<div id="approvato">
			    	<dl class="row" >
					  <dt class="col-sm-3 text-right">Approvato?:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.approvato}</dd>
			    	</dl>
			    	</div>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Dipendente id:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.dipendente.id}</dd>
			    	</dl>
			    	
					</div>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div id="approvazione">
			    <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/richiestaPermesso/approva/${show_richiestaPermesso_attr.id }">Approva</a>
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/richiestaPermesso/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
		<script>
  						$(document).ready(function(){
	  						if($("#approvato").val() == true){
	  							 $("#approvazione").hide();
	  						}
	  						else {
	  							$("#approvazione").show();
	  						}
  						});
  						
  						</script>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>