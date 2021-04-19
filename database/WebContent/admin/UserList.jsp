<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<title>${userListTitle }</title></head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

 
   
 	
<body>
 	<div style = "background-color: lightblue;" >

	 	<h2 >      
	    		<a style="margin-right: 20px;"  href="/database-master_database/feed">Feed </a>
	    		 
		       	<a style="margin-right: 20px;" href="/database-master_database/userProfile">User Profile</a>
		         
		        <a  style="margin-right: 20px;" href="/database-master_database/community">Community</a>
		          
	            <c:if test="${sessionScope.currentUser.email.equals('root')}">
	           
			    	 <a  style="margin-right: 20px;" href="/database-master_database/admin">Administration</a>
			    	 
			    </c:if>                    
	 	</h2>
     	
     
     	<div align="center" >
     		<h1>${userListTitle}</h1><br>
     	 </div>
 	 	 
 	</div>
	
	<div align="center">
	
		<c:forEach var="user" items="${userList}" varStatus="loop">
				<h3>${user.firstName}  ${user.lastName} </h3><br>
		</c:forEach>
	</div>


</body>
</html>