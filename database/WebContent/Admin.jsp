<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<h2 >      	
    		 <a style="margin-right: 20px;"  href="/database-master_database/feed">Feed </a>
    		 
	         <a style="margin-right: 20px;" href="/database-master_database/userProfile">User Profile</a>
	         
	          <a  style="margin-right: 20px;" href="/database-master_database/community">Community</a>
	          
	             <c:if test="${sessionScope.currentUser.email.equals('root')}">
           
		    	 <a  style="margin-right: 20px;" href="/database-master_database/admin">Administration</a>
		    	 
		    </c:if>                        
 	</h2>
 	
	<h1>Admin Page</h1>
	
	<div align="center">
			 <a  href="/database-master_database/admin/coolImages">Cool Images </a><br>
			 
			  <a  href="/database-master_database/admin/newImages">New Images </a><br>
			  
			    <a  href="/database-master_database/admin/viralImages">Viral Images </a><br>
			  
			   <a  href="/database-master_database/admin/topUsers">Top Users </a><br>
			   
			    <a  href="/database-master_database/admin/popularUsers">Popular Users </a><br>
			    
		       <a  href="/database-master_database/admin/commonUsers">Common Users </a><br>
		       
	          <a  href="/database-master_database/admin/topTags">Top Tags </a><br>
	          
	         <a  href="/database-master_database/admin/positiveUsers">Positive Users </a><br>
	          
             <a  href="/database-master_database/admin/poorImages">Poor Images </a><br>
	             
             <a  href="/database-master_database/admin/inactiveUsers">Inactive Users </a><br>
	</div>
	
	

    
</body>
</html>