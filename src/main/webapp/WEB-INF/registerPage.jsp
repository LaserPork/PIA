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
		<div class="row">
		<div class="container col-md-4 offset-md-4">
				<a class="logo-1">
					<span>Kiv</span><span class ="logo-2">book</span>
				</a>
			
			<form class="form-horizontal" method="POST" enctype="multipart/form-data">
			  <fieldset>
			    <div id="legend">
			      <h1>Register</h1>
			    </div>
			    <div class="control-group">
			      <label class="control-label" for="username">Username</label>
			      <div class="controls">
			        <input type="text" id="username" name="username" value="${username}" class="input-xlarge form-control col-12">
			        
			      </div>
			    </div>
			 
			    <div class="control-group">
			      <label class="control-label" for="email">E-mail</label>
			      <div class="controls">
			        <input type="text" id="email" name="email" value="${email}" class="input-xlarge form-control col-12">
			        
			      </div>
			    </div>
			    
			     <div class="control-group">
			      <label class="control-label" for="date">Date of Birth (not required)</label>
			      <div class="controls">
			        <input type="date" id="date" name="date" value="${date}" class="form-control col-12">
			        
			      </div>
			    </div>
			    
			    <div class="control-group">
			      <div class="controls">
			      <c:choose>
			      	<c:when test="${gender eq 'male'}">
			      	  <label>Male
			        	<input type="radio"  name="gender" checked value="male" class="d-inline input-xlarge form-control">
			        </label>
			      	<label >Female
			      		<input type="radio"  name="gender"  value="female" class="d-inline input-xlarge form-control">
			      	</label>
			      	</c:when>
			      	<c:when test="${gender eq 'female'}">
			      	  <label  >Male
			        	<input type="radio"  name="gender"  value="male" class="d-inline input-xlarge form-control">
			        </label>
			      	<label >Female
			      		<input type="radio"  name="gender" checked  value="female" class="d-inline input-xlarge form-control">
			      	</label>
			      	</c:when>
			      	<c:otherwise>
			      	  <label  >Male
			        	<input type="radio"  name="gender" value="male" class="d-inline input-xlarge form-control">
			        </label>
			      	<label>Female
			      		<input type="radio" name="gender" value="female" class="d-inline input-xlarge form-control">
			      	</label>
			      	</c:otherwise>
			      </c:choose>
			      
			     	
			   
			       
			      </div>
			    </div>
			 
			    <div class="control-group">
			      <label class="control-label" for="password">Password</label>
			      <div class="controls">
			        <input type="password" id="password" name="password" class="input-xlarge form-control col-12">
			        
			      </div>
			    </div>
			 
			    <div class="control-group">
			      <label class="control-label" for="password_confirm">Password (Confirm)</label>
			      <div class="controls">
			        <input type="password" id="password_confirm"  name="password_confirm" class="input-xlarge form-control col-12">
			      
			      </div>
			    </div>
			    
			
			    <div class="control-group">
			      <label class="control-label" for="pic">Upload your profile picture (not required)</label>
			      <div class="controls">
					<input type="file" id="selectedFile" class="d-none" name="pic" accept="file_extension|image/*">
					<input class="m-0 mt-1 btn post-button col-12" id="pic" type="button" value="Choose picture" onclick="document.getElementById('selectedFile').click();" />
				  </div>
			    </div>
			    
			    
			    <div class="control-group">
			      <label class="control-label" for="test">What is square root of 16?</label>
			      <div class="controls">
			        <input type="text" id="test" name="test" value="${test}" class="input-xlarge form-control col-12">
			      
			      </div>
			    </div>
			    <br>
			 	<c:if test="${not empty requestScope.err}">
					    <p>${requestScope.err} </p>	    
				</c:if>
			    <div class="control-group">
			      <div class="controls">
			        <input class="btn post-button m-0 col-12" type="submit" value="Register" >
			      </div>
			    </div>
			  </fieldset>
			</form>
		    </div>
		</div>
	</body>
</html>