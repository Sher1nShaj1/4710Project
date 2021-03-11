<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>

 	<h1>User Profile</h1>
 	<button type="button" onclick="alert('Hello world!')">Create New Post</button>
                  
                                 
    <div align="center">
       
        <table border="1" cellpadding="5">
        	<tr>
                <td >
                   User Name goes here.
                   <button style="float: right;" type="button" onclick="alert('Hello world!')">Edit</button>
                   <button style="float: right;" type="button" onclick="alert('Hello world!')">Delete</button>   
            </tr> 
            <tr>
                <td colspan="3">
                   <img src="https://cdn3.dpmag.com/2019/10/shutterstock_1239834655.jpg" alt="sunset" width="400" height="400">    
           
                </td>
            </tr>
            <tr>
            	<td colspan="3">
            	  	<button type="button" onclick="alert('Hello world!')">Like</button>
                </td>
               
            </tr>
            <tr>
                <td colspan="3">
                    Description will go here.
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    Tags will go here.
                </td>
            </tr>

            <tr>
	              <td colspan="3">
	                    Time posted will go here.
	              </td>
            </tr>
          
        </table>
    </div>   
    
</head>
<body>

</body>
</html>