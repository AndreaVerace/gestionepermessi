<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
		
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a href="${pageContext.request.contextPath}/richiestaPermesso/search" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Nota</th>
			                        <th>Codice Certificato</th>
			                        <th>Tipo Permesso</th>
			                        <th>Approvato?</th>
			                        <th> Attachment</th>
			                        <th> Dipendente</th>
			                        <th> Data inizio </th>
			                        <th> data Fine </th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${richiestaPermesso_list_attribute }" var="richiestaItem">
									<tr>
										<td>${richiestaItem.nota }</td>
										<td>${richiestaItem.codiceCertificato }</td>
										<td>${richiestaItem.tipoPermesso }</td>
										<td>${richiestaItem.approvato }</td>
										<td>${richiestaItem.attachment.id }</td>
										<td>${richiestaItem.dipendente.id }</td>
										<td>${richiestaItem.dataInizio }</td>
										<td>${richiestaItem.dataFine }</td>
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/richiestaPermesso/show/${ richiestaItem.id }">Visualizza</a>
											<sec:authorize access="hasRole('DIPENDENTE_USER')">
											<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/richiestaPermesso/delete/${richiestaItem.id }">Delete</a>
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/richiestaPermesso/edit/${richiestaItem.id }">Edit</a>
											</sec:authorize>
											
										</td>
									</tr>
								</c:forEach>
			                </tbody>
			            </table>
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>