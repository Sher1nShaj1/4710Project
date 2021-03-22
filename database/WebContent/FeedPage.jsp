<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {margin:0;}
body {background-color: #ffd394;}

.icon-bar {
  width: 100%;
  background-color: #ffb347;
  overflow: auto;
}

.icon-bar a {
  float: center;
  width: 20%;
  text-align: center;
  
  transition: all 0.3s ease;
  color: white;
  font-size: 36px;
}

.icon-bar a:hover {
  background-color: #000;
}

.active {
  background-color: #4CAF50;
}

</style>
<body>


<!-- The bar to navigate to community page -->
<div class="icon-bar"> 
<a href="CommunityPage.html"><i class="fa fa-search"></i></a> 
</div>


<!--Form to post picture -->
<div id="postImageForm">
<div class="w3-row-padding">
<div class="w3-col m12">
<div class="w3-card w3-round w3-white">
<div class="w3-container w3-padding">
    
    <h2 >
	 		 <a style="margin-right: 20px;" href="/database-master_database/home">Home</a>
	                
	         <a style="margin-right: 20px;" href="/database-master_database/userProfile">User Profile</a>
	         
	          <a  style="margin-right: 20px;" href="/database-master_database/community">Community</a>
	          
	           <a style="margin-right: 20px;"  href="/database-master_database/feed">Feed </a>
 	</h2>
 	<legend  align="center"><h2>Feed Page</h2></legend> 
    
</div>
</div>
</div>
</div>
</div>

     
<!--Post-->
	<div align="center">
		<c:forEach var="image" items="${feedImages}">
			
			<div  style="width:600px" class="w3-container w3-card w3-white w3-round w3-margin"><br>
					<h4>${image.postUser.email}</h4><br>
					<div class="w3-row-padding" style="margin:0 -16px">
			        	<img src="${image.url}"style="width:100%" alt="sunset image" class="w3-margin-bottom">  
					</div>
					<span class="w3-right w3-opacity"><time>${image.postTime}</time></span>
					
					
					<div align="left">
						<span>
							<a href="#" title="Love it" class="btn btn-counter" data-count="0"><span>&#x2764;</span>${image.likesCount}</a>
						</span>
						
				        <p>${image.description}</p>
				        <p>${image.tags}</p>
				        <hr class="w3-clear">	
					</div>
					  				
			</div>
		</c:forEach>
	
	
	</div>
	
	
</body>
</html>