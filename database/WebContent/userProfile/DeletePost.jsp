<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Post</title>
</head>
<body>
 <div align="center">
    
    		<h1>Are you sure you want to delete this post?</h1>
         
	        <table style="margin-bottom:30px;" border="3" cellpadding="5">
        	<tr>
                <td >
                   User: ${image.postUser.email} 
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
        	
        	
        	<form action="../deletePost">
        	 	<input type="hidden" name="imgID" value="${image.imgID}"/>
		    	<input type="submit" value="Delete Post" />
			</form> 
			
			 
			<form action="/database-master_database/userProfile">
		    	<input type="submit" value="Cancel" />
			</form>  
    
       
    </div>   
</body>
</html>