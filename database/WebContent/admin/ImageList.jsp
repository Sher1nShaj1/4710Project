<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>${imageListTitle}</title>
</head>
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
     	
     
     	<div align="center">
     		<h1>${imageListTitle}</h1>
     	 </div>
 	 	 
 	</div>
 	      
 	      
 	       
                                 
    <div align="center">
   	  <c:forEach var="image" items="${imageList}">
    	  <table style="margin-bottom:30px;" border="3" cellpadding="5">
        	<tr>
                <td >
                    ${image.postUser.email}
                   
            </tr> 
            <tr>
                <td colspan="3">
                   <img src="${image.url}" alt="sunset" width="400" height="400">    
           
                </td>
            </tr>
            
            <tr>
                <td  colspan="3">
                    Description: ${image.description}
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