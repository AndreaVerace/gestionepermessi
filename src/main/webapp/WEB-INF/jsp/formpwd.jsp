<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
	<head>
	  <meta charset="utf-8">
		<title>Accedi</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="assets/css/signin.css" rel="stylesheet">
	</head>
	
	<body class="text-center">
		<main class="form-signin">
			<form  class="form-signin"  action="resetPassword" method='POST' novalidate="novalidate">
				
				
			  	<img class="mb-4" src="./assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Change password</h1>
		    	
			    <div class="form-floating">
			      <input type="password" name="vecchiaPassword" class="form-control" id="inputVecchiaPassword" placeholder="Vecchia Password">
			      <label for="inputVecchiaPassword">Vecchia Password</label>
			    </div>
			
				 <div class="form-floating">
			      <input type="password" name="nuovaPassword" class="form-control" id="inputNuovaPassword" placeholder="Nuova Password">
			      <label for="inputNuovaPassword">Nuova Password</label>
			    </div>
			    
			     <div class="form-floating">
			      <input type="password" name="confermaPassword" class="form-control" id="inputConfermaPassword" placeholder="Conferma Password">
			      <label for="inputConfermaPassword">Conferma Password</label>
			    </div>
			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember me
			      </label>
			    </div>
			    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
			    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
			  
			  	<input type="hidden" value="userInfo.username" name="userInfo">
			  
			</form>
		</main>
	</body>
</html>