<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<title>Community Page</title></head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--Search Bar-->
<form class="example" action="action_page.php">
  <input type="text" placeholder="Search.." name="search">
  <button type="submit"><i class="fa fa-search"></i></button>
</form>


<!--Follow/Unfollow-->
<body>

	<c:forEach var="user" items="${allUsers}" varStatus="loop">
	
			
			<h3>${user.firstName}  ${user.lastName}  
			
			
				<c:if test="${followList.get(loop.index).isFollowedByCurrentUser == true}">
					<form  action="/database-master_database/unfollow">
		            		<input type="hidden" name="email" value="${user.email}"/>
			    			<input type="submit" value="Unfollow" />
	    			</form>
		        </c:if>  
				
					   	
			    <c:if test="${followList.get(loop.index).isFollowedByCurrentUser == false}">
			    	<form  action="/database-master_database/follow">
	            		<input type="hidden" name="email" value="${user.email}"/>
		    			<input type="submit" value="Follow" />
	    			</form> 
	             </c:if> 
			</h3>
	</c:forEach>


</body>
</html>