<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Post</title>

    <div align="center">
            <form action="loginUser" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h1>
                        Create Post
                </h1>
            </caption>     
            <tr>
                <th>Image URL: </th>
                <td>
                    <input type="text" name="username" size="45"
                            value="<c:out value='${url}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Tags: </th>
                <td>
                    <input type="text" name="tags" size="45"
                            value="<c:out value='${tags}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="description" size="45"
                            value="<c:out value='${description}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Post" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
    
</head>
<body>

</body>
</html>