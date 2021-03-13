<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

    <div align="center">
            <form action="loginUser" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h1>
                        Login
                </h1>
            </caption>
            <c:if test="${loginError != null}">
            		<p> ${loginError} </p>
            </c:if>       
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${email}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45"
                            value="<c:out value='${password}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Login" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
    
</head>
<body>

</body>
</html>