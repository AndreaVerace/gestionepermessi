<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca elementi</h5> 
			    </div>
			    <div class='card-body'>
	
						<form:form modelAttribute="search_richiestaPermesso_attr" method="post" action="${pageContext.request.contextPath}/richiestaPermesso/list" class="row g-3">
						
							
							<div class="col-md-6">
								<label for="nota" class="form-label">Nota</label>
								<input type="text" name="nota" id="nota" class="form-control" placeholder="Inserire la nota" >
							</div>
							
							<div class="col-md-6">
								<label for="codiceCertificato" class="form-label">Codice Certificato</label>
								<input type="text" name="codiceCertificato" id="codiceCertificato" class="form-control" placeholder="Inserire codiceCertificato" >
							</div>
							
							<div class="col-md-6">
								<label for="dataInizio" class="form-label">Data di Inizio</label>
                        		<input class="form-control" id="dataInizio" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataInizio" >
							</div>
							<div class="col-md-6">
								<label for="dataFine" class="form-label">Data di Fine</label>
                        		<input class="form-control" id="dataFine" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataFine" >
							</div>
							<div class="col-md-3">
								<label for="tipoPermesso" class="form-label">Tipo Permesso</label>
								    <select class="form-select " id="tipoPermesso" name="tipoPermesso" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="FERIE" >FERIE</option>
								    	<option value="MALATTIA">MALATTIA</option>
							    	</select>
							</div>
							
							
							<div class="col-md-3">
								<label for="dipendente" class="form-label">Dipendente</label>
								    <select class="form-select " id="dipendente" name="dipendente" >
								   	<option value="" selected> - Selezionare - </option>
								    <c:forEach items="${ search_dipendenti_list}" var="dipendenteItem">
								      	<option value="${dipendenteItem.id}" > ${dipendenteItem.nome} ${dipendenteItem.cognome}</option>
									</c:forEach>
							    	</select>
							</div>
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								
							</div>
	
							
						</form:form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>