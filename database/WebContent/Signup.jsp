<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <div align="center">
            <form action="createUser" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                        Sign up
                </h2>
                
                 <c:if test="${passwordError != null}">
            		<p> ${passwordError} </p>
            	</c:if>
            	 <c:if test="${usernameInvalidError != null}">
            		<p> ${usernameInvalidError} </p>
            	</c:if>
            	
            </caption>        
            <tr>
                <th>Username: </th>
                <td>
                    <input type="email" name="username" size="45"
                            value="<c:out value='${username}' />"
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
                <th>Confirm Password: </th>
                <td>
                    <input type="password" name="confirmPassword" size="45"
                            value="<c:out value='${confirmPassword}' />"
                    />
                </td>
            </tr>
         
             <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstName" size="45"
                            value="<c:out value='${firstName}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastName" size="45"
                            value="<c:out value='${lastName}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Gender: </th>
                <td>
                	<label  for="male">Male</label>
		             <input type="radio" name="gender" size="1"
                            value="M" id="male"
                    />
                    <label  for="female">Female</label>
                    <input type="radio" name="gender" size="1"
                            value="F" id="female" 
                    />
                </td>
            </tr>
             <tr>
                <th>Birthday (YYYY-MM-DD): </th>
                <td>
                    <input type="text" name="birthday" 
                            value="<c:out value='${birthday}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Sign up" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
