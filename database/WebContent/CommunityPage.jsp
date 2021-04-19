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

 
   
 	
<body>
 	 <h2 >      
    		<a style="margin-right: 20px;"  href="/database-master_database/feed">Feed </a>
    		 
	       	<a style="margin-right: 20px;" href="/database-master_database/userProfile">User Profile</a>
	         
	        <a  style="margin-right: 20px;" href="/database-master_database/community">Community</a>
	          
            <c:if test="${sessionScope.currentUser.email.equals('root')}">
           
		    	 <a  style="margin-right: 20px;" href="/database-master_database/admin">Administration</a>
		    	 
		    </c:if>                    
 	</h2>
 	
	<form  action="search" >
	  
	   <input type="text"  name="searchInput"  placeholder="Search.." value="<c:out value='${searchInput}' />"/>
	  <input type="submit" value="Search" />
	</form>
	
	<div align="center">
		


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
	</div>


</body>
</html>