<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <div class="d-flex mx-3 m-2 m-lg-0 flex-row-reverse">
       		<c:choose>
			    <c:when test="${not empty sessionScope.user}">
			       	 <form method="POST">
				   		<input type="hidden" name="logout">
				    	<input class="btn logout-button my-2 my-sm-0" type="submit" value="Fuck this" >
			    	</form>
			       	<c:if test="${sessionScope.user ne profile.username}">
          				<a class="nav-link my-2 my-sm-0" href="${pageContext.request.contextPath}/profile">Profile</a>
          			</c:if>
          			<a class="nav-link my-2 my-sm-0" href="${pageContext.request.contextPath}/requests">Requests</a>
          			<a class="nav-link my-2 my-sm-0 " href="${pageContext.request.contextPath}/friends">Friends</a>
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
	<c:if test="${empty requestScope.err}">
	<!-- PROFILE BEGIN -->
	<div class="col-lg-3"> 
	<div class="post">
		<div class="post-header float-none">
			<div class="zoomable-img post-pic">
				<figure>
					<a href="${pageContext.request.contextPath}/profile/${profile.id}">
						<c:choose>
						    <c:when test="${empty profile.profileImage}">
						        <img alt="Profile" src="${pageContext.request.contextPath}/img/profile/profile.png">
						    </c:when>    
						    <c:otherwise>
						       <img alt="Profile" src="${pageContext.request.contextPath}/img/profile/${profile.profileImage}">
						    </c:otherwise>
						</c:choose>
					</a>
				</figure>
			</div>
				<span class="post-header-text-name clear-left"> <c:out value="${profile.username}"/></span><br/>
				<span class="post-header-text-time clear-left">	<c:if test="${not empty age}">Age: ${age}</c:if></span>	
				<span class="post-header-text-name clear-left"><c:choose>
																<c:when test="${profile.male eq true}">
																	Male
																</c:when>
																<c:otherwise>
																	Female
																</c:otherwise>
																</c:choose></span><br/>
				<span class="post-header-text-time clear-left">This is some of my bio. I like swimming, fishing and other water activities</span>			
				<c:if test="${sessionScope.user eq profile.username}">
				<a class="m-0 mb-2 mt-2 btn post-button col-12" href="${pageContext.request.contextPath}/friends" role="button">Friends</a>
    			<form class="p-0 mb-0 col-12" id="fileUpload" name="fileUpload" method="POST" enctype="multipart/form-data" >
					<input type="file" onchange="document.getElementById('fileUpload').submit()" id="selectedFile" class="d-none" name="pic" accept="file_extension|image/*">
					<input class="m-0 mt-1 btn post-button col-12" type="button" value="Change picture" onclick="document.getElementById('selectedFile').click();" />
				</form>
				</c:if>
		</div>
		
	</div>
	</div> 
	 <!-- PROFILE END -->
	 </c:if>
	<div class="col-lg-7">
	<!-- POST BEGIN  -->
	<c:if test="${not empty requestScope.err}">
			<div class="post"> 
				<div class="clear-left">
			       <h2>${requestScope.err} </h2>	
				</div>
			</div>  
	</c:if>
	<c:if test="${not empty requestScope.posts}">
	<c:forEach items="${posts}" var="post">
	<div class="post"> 
	<div class="post-header">
		<div class="zoomable-img profile-pic">
			<figure>
				<a href="${pageContext.request.contextPath}/profile/${post.user.id}">
					<c:choose>
					    <c:when test="${empty post.user.profileImage}">
					        <img alt="Profile" src="${pageContext.request.contextPath}/img/profile/profile.png">
					    </c:when>    
					    <c:otherwise>
					       <img alt="Profile" src="${pageContext.request.contextPath}/img/profile/${post.user.profileImage}">
					    </c:otherwise>
					</c:choose>
				</a>
			</figure>
		</div>
		<div class="post-header-text">
			<span class="post-header-text-name"><c:out value="${post.user.username}"/></span><br/>
			<span class="post-header-text-time clear-left"><c:out value="${post.timestamp}"/></span>		
		</div>
	</div>
		<div class="clear-left">
	        <p class="lead"> <c:out value="${post.content}"/></p>
	       	<form method="POST" class="m-0 p-0 clear-left">
	       		<input type="hidden" name="likedPost" value="${post.id}">
				<div class="mb-0 btn-group d-none d-sm-block">
			        <input class="btn post-button" type="submit" name="1" value="${fn:length(post.first)} Fuck yeah!" >
			        <input class="btn post-button" type="submit" name="2" value="${fn:length(post.second)} Fuck no" >
			        <input class="btn post-button" type="submit" name="3" value="${fn:length(post.third)} Fuck off" >
			       </div>
			</form >	
			
			<form method="POST" class="m-0 p-0">
				<input type="hidden" name="likedPost" value="${post.id}">
				<div class="mb-0 d-block d-sm-none col-12">
					<input class="btn post-button col-12" type="submit" name="1" value="${fn:length(post.first)} Fuck yeah!" >
			        <input class="btn post-button col-12" type="submit" name="2" value="${fn:length(post.second)} Fuck no" >
			        <input class="btn post-button col-12" type="submit" name="3" value="${fn:length(post.third)} Fuck off" >
				</div>
			</form>	
			
		</div>
	</div> 
	<div class="col-lg-2"></div>  
	
	</c:forEach>
	
	
		<nav class="col-12">
		<form id="pagination" class="d-inline-flex" method="POST">
				<select onchange="document.getElementById('pagination').submit()" class="form-control btn post-btn" name="pagination">
					<c:choose>
						<c:when test="${sessionScope.pagination eq 50}">
							<option>10</option>
							<option>25</option>
							<option selected>50</option>
						</c:when>
						<c:when test="${sessionScope.pagination eq 25}">
							<option>10</option>
							<option selected>25</option>
							<option>50</option>
						</c:when>
						<c:otherwise>
							<option selected>10</option>
							<option>25</option>
							<option>50</option>
						</c:otherwise>
					</c:choose>
				</select>	
			
		  <ul class="pagination mb-0 justify-content-center">
		  
		   <!--   <li class="page-item disabled">
		      <a class="btn post-button" href="${pageContext.request.contextPath}/home?page=${requestScope.page}" tabindex="-1">Previous</a>
		    </li>-->
		    <c:forEach var="pageValue" items="${pageCount}" varStatus="loop">
		    <c:choose>
		    	<c:when test="${pageValue eq requestScope.page}">
		    	 <li class="page-item"><a class="btn logout-button" href="<c:url value ="${pageContext.request.pathInfo}?page=${pageValue}"/>">${pageValue}</a></li>
		    	</c:when>
		    	<c:otherwise>
		    	 <li class="page-item"><a class="btn post-button" href="<c:url value ="${pageContext.request.pathInfo}?page=${pageValue}"/>">${pageValue}</a></li>	
		    	</c:otherwise>
		    </c:choose>
			</c:forEach>
			
		  	<!--   <li class="page-item">
		      <a class="btn post-button" href="${pageContext.request.contextPath}/home?page=${requestScope.page}">Next</a>
		    </li>-->
		  </ul>
		  	</form>
		</nav>
		
	</c:if>
	
	</div>
</div>
</body>
</html>
