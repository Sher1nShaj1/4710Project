<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<div style = "background-color: lightblue;" >

 		<h2 >
	 		 <a style="margin-right: 20px;" href="/database-master_database/home">Home</a>
	                
	         <a style="margin-right: 20px;" href="/database-master_database/userProfile">User Profile</a>
	         
	          <a  style="margin-right: 20px;" href="/database-master_database/community">Community</a>
	          
	           <a style="margin-right: 20px;"  href="/database-master_database/feed">Feed </a>
 		</h2>
     	
     
     	<div align="center">
     		<h1>Home</h1>
	     	<h1>User: ${sessionScope.currentUser.email}</h1>
		 	<p>Welcome  ${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}!</p>
		 	<hr style="margin-bottom:30px;" >
     	</div>
 	 	 
 	</div>



     
    <div align="center">
     <c:if test="${sessionScope.currentUser.email.equals('root')}">
    	<form action="initializeDatabase" method="post">
        <table border="1" cellpadding="5">
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Initialize Database" />
                </td>
            </tr>
        </table>
        
        </form>
     </c:if>
    </div>  
    
     
</body>
</html>
