<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
					  <dt class="col-sm-3 text-right">Nome Attachment:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.attachment.nomeFile}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Content Type Attachment:</dt>
					  <dd class="col-sm-9">${show_richiestaPermesso_attr.attachment.contentType}</dd>
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
			    	
			    		<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseDipendente" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Dipendente
					  </a>
					</p>
					<div class="collapse" id="collapseDipendente">
						<div class="card card-body">
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome:</dt>
							  <dd class="col-sm-9">${show_richiestaPermesso_attr.dipendente.nome}</dd>
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${show_richiestaPermesso_attr.dipendente.cognome}</dd>
							  <dt class="col-sm-3 text-right">Email:</dt>
							  <dd class="col-sm-9">${show_richiestaPermesso_attr.dipendente.email}</dd>
							  <dt class="col-sm-3 text-right">Sesso:</dt>
							  <dd class="col-sm-9">${show_richiestaPermesso_attr.dipendente.sesso}</dd>
					    	</dl>
					    </div>
					
					</div>
			    	
					</div>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <sec:authorize access="hasRole('BO_USER')">
			    <div>
			    <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/richiestaPermesso/approva/${show_richiestaPermesso_attr.id }">Approva</a>
			    </div>
			    </sec:authorize>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/richiestaPermesso/" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>