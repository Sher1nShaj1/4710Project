<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
</head>
<body>
 	<div style = "background-color: lightblue;" >

 		<h2>
	 		 <a style="margin-right: 20px;" href="/database-master_database/home">Home</a>
	                
	         <a href="/database-master_database/userProfile">User Profile</a>
 		</h2>
     	
     
     	<div align="center">
     	<h1>User: ${sessionScope.currentUser.email}</h1>
	 	<p>Welcome  ${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}</p>
	 	
	 	
	 	
	     <form  style="margin-bottom:20px;"  action="/database-master_database/userProfile/createPost">
	    	<input type="submit" value="Create New Post" />
		 </form> 
		 <hr style="margin-bottom:30px;" >
     	 </div>
 	 	 
 	</div>
 	      
 	      
 	       
                                 
    <div align="center">
   	  <c:forEach var="image" items="${postedImages}">
    	  <table style="margin-bottom:30px;" border="3" cellpadding="5">
        	<tr>
                <td >
                   User: ${image.postUser.email}
                   
                    <form style="float: right;" action="/database-master_database/userProfile/editPost">
                    
	                    <input type="hidden" name="imgID" value="${image.imgID}"/>
				    	<input type="submit" value="Edit" />
				    	
					</form> 
					<form style="float: right;" action="/database-master_database/userProfile/deletePost">
					
					   <input type="hidden" name="imgID" value="${image.imgID}"/>
				    	<input type="submit" value="Delete" />
				    	
					</form>   
            </tr> 
            <tr>
                <td colspan="3">
                   <img src="${image.url}" alt="sunset" width="400" height="400">    
           
                </td>
            </tr>
            <tr>
            	<td colspan="3">
            	  	<button type="button" onclick="alert('Hello world!')">Like</button>
                </td>
               
            </tr>
            <tr>
                <td  colspan="3">
                    Description: ${image.description}
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    Tags: ${image.tags}
                </td>
            </tr>

            <tr>
	              <td colspan="3">
	                 Time: ${image.postTime}
	              </td>
            </tr>
          
        	</table>
        	
         
       </c:forEach> 
    </div>   
    
    
</body>
</html>