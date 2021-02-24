<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
	<h1>Home</h1>
    <div align="center">
     <c:if test="${username.equals('root')}">
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
