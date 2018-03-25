<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="cs">
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

	<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top navbar-black bg-dark">
      <a class="navbar-brand logo-1" href="${pageContext.request.contextPath}/">
			
			
			  <span>Kiv</span>
			  <span class ="logo-2">book</span>
			

		</a>
		
		
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

     
      <div class="collapse navbar-collapse flex-row-reverse" id="navbarsExampleDefault">
        <div class="d-flex my-2 my-lg-0 flex-row-reverse">
       		<c:choose>
			    <c:when test="${not empty sessionScope.user}">
			    <form method="POST">
			   		<input type="hidden" name="logout">
			    	<input class="btn logout-button my-2 my-sm-0" type="submit" value="Fuck this" >
			    </form>
          			<a class="nav-link my-2 my-sm-0 " href="${pageContext.request.contextPath}/profile">Profile</a>
          			<a class="nav-link my-2 my-sm-0 " href="${pageContext.request.contextPath}/requests">Requests</a>
			    </c:when>    
			    <c:otherwise>
			       <a class="btn post-button my-2 my-sm-0"  href="${pageContext.request.contextPath}/" role="button">Sign in</a>
			    </c:otherwise>
			</c:choose>
          
        </div>
         <c:if test="${not empty sessionScope.user}">
        <form class="my-2 my-lg-0 form-inline" action="${pageContext.request.contextPath}/search" method="POST">
        <div class="form-group p-sm-0">
       		<input type="text" id="search" name="search" placeholder="Search for user" class="input-xlarge form-control col-12">
        </div>
        <div class="form-group p-sm-0">
        	<input class="btn post-button" type="submit" value="Search" >
        </div>
		</form>
		</c:if>
      </div>
    </nav>
<div class="row">	
<c:if test="${not empty requestScope.err}">
		
			<div class="col-12 post"> 
				<div class="clear-left">
			       <h2>${requestScope.err} </h2>	
				</div>
			</div>  
		
	</c:if>
<c:choose>
<c:when test="${not empty requestScope.friends}">
	<c:forEach items="${friends}" var="friend">
	<div class="col-lg-3"> 
	<div class="post">
		<div class="post-header post-pic">
			<div class="zoomable-img post-pic">
				<figure>
					<a href="${pageContext.request.contextPath}/profile/${friend.id}">
					<c:choose>
					    <c:when test="${empty friend.profileImage}">
					        <img alt="Profile" src="${pageContext.request.contextPath}/img/profile/profile.png">
					    </c:when>    
					    <c:otherwise>
					       <img alt="Profile" src="${pageContext.request.contextPath}/img/profile/${friend.profileImage}">
					    </c:otherwise>
					</c:choose>
				</a>
				</figure>
			</div>
				<span class="post-header-text-name clear-left">${friend.username}</span><br/>
			</div>
		
	</div>
	</div> 
	</c:forEach>
	</c:when>  
	<c:otherwise>
		<div class="col-md-2"></div> 
			<div class="col-md-8 post"> 
			       <h1 class="text-center float-none"> Get some friends! </h1>	
			</div>  
		<div class="col-md-2"></div> 
	</c:otherwise>
</c:choose>
	

	</div>
</body>
</html>