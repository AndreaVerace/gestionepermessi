<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_richiestaPermesso_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Tipo Permesso:</dt>
							  <dd class="col-sm-9">${delete_richiestaPermesso_attr.tipoPermesso}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Eventuale Codice Certificato:</dt>
							  <dd class="col-sm-9">${delete_richiestaPermesso_attr.codiceCertificato}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nota:</dt>
							  <dd class="col-sm-9">${delete_richiestaPermesso_attr.nota}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Approvato?:</dt>
							  <dd class="col-sm-9">${delete_richiestaPermesso_attr.approvato}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Inizio:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type="date" value = "${delete_richiestaPermesso_attr.dataInizio}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Fine:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type="date" value = "${delete_richiestaPermesso_attr.dataFine}" /></dd>
							  <form:errors  path="dataRientro" cssClass="error_field" />
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
							  <dd class="col-sm-9">${dipendente_info.nome}</dd>
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${dipendente_info.cognome}</dd>
							  <dt class="col-sm-3 text-right">Email:</dt>
							  <dd class="col-sm-9">${dipendente_info.email}</dd>
							  <dt class="col-sm-3 text-right">Sesso:</dt>
							  <dd class="col-sm-9">${dipendente_info.sesso}</dd>
					    	</dl>
					    </div>
					
					</div>
					    	
					    	
						    	 <a href="${pageContext.request.contextPath}/richiestaPermesso/remove/${delete_richiestaPermesso_attr.id}" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Conferma </a>
							
					    	
					    </div>
					    <!-- end card body -->
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/satellite" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>