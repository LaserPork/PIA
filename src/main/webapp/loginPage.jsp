<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="cs" class="login-body">
	<head>
		<link href="https://getbootstrap.com/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<meta charset="UTF-8">
	 	<link href="${pageContext.request.contextPath}/css/gallery.css" type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1">

	 	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
		<title>Kivbook</title>
	</head>

	<body class="login-body">
	<form id="logo" method="post"><input type="hidden" name="redirect" value="home" /></form>
		<div class="row">
				<a class="col-12 logo-login-1" href="${pageContext.request.contextPath}/home">
					<span>Kiv</span><span class ="logo-login-2">book</span>
				</a>
			
			<div class="container col-md-4 offset-md-4">
		      	<form class="form-signin" method="post">
		        	<h2 class="form-signin-heading">Please sign in</h2>
		        	<div class="m-1">

				        <input type="text" name="inputName" class="form-control col-12" placeholder="Name" >
		       		</div>
			       	<div class="m-1">
				       
				        <input type="password" name="inputPassword" class="form-control col-12" placeholder="Password" >
			        </div>
			    <!--   <div class="checkbox">
				          <label>
				          	<input type="checkbox" value="remember-me"> Remember me
				          </label>
			        </div> -->  
					<c:if test="${not empty requestScope.err}">
					    <p>Error: ${requestScope.err} </p>	    
					</c:if>
					<input class="btn post-button col-12" type="submit" value="Sign in">
		      	</form>
		      	
		      	<a class="btn logout-button col-12" href="${pageContext.request.contextPath}/register" role="button">Register</a>
		    </div>
		</div>
	</body>
</html>