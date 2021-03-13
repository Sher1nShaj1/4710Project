<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>

 	<h1>User Profile</h1>
 	<p>User: ${sessionScope.currentUser.email}</p>
 	
 	<button type="button" onclick="alert('Hello world!')">Create New Post</button>
                  
                                 
    <div align="center">
   	  <c:forEach var="image" items="${postedImages}">
    	  <table style="margin-bottom:30px;" border="3" cellpadding="5">
        	<tr>
                <td >
                   User: ${image.postUser.email}
                   <button style="float: right;" type="button" onclick="alert('Hello world!')">Edit</button>
                   <button style="float: right;" type="button" onclick="alert('Hello world!')">Delete</button>   
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
    
</head>
<body>

</body>
</html>