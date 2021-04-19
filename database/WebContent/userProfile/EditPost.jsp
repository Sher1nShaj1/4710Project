<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Post</title>
	<style>
	   input  {
	    width: 600px;
		}
	</style>

</head>
<body>
  <div align="center">
            <form action="../editPost" method="post">
            
             <input type="hidden" name="imgID"
	                            value="${image.imgID}"
	                    />
        <table border="1" cellpadding="40">
            <caption>
                <h1>
                        Edit Post
                </h1>
            </caption>     
            <tr>
                <th>Image URL: </th>
                
                <td>
                    <input type="text" name="url" size= 100
                            value="<c:out value='${image.url}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Tags: </th>
                <td>
                    <input type="text" name="tags"
                            value="<c:out value='${image.tags}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="description" 
                            value="<c:out value='${image.description}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save Changes" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>