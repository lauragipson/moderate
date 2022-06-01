<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="ISO-8859-1">
    <title>Moderate :: Register</title>
  </head>
  <body>
    <h1>/ˈmädəˌrāt/</h1>
    <div align="center">
      <form action="insert" method="post">
      <table border="1" cellpadding="5">
        <caption>
        <h2>
            Registration
          </h2>
        </caption>
         <tr>
          <th>Preferred name: </th>
          <td>
            <input type="text" name="prefName" size="45"
              value="<c:out value='${user.prefName}' />"
            />
          </td>
        </tr>
        <tr>
          <th>Email address: </th>
          <td>
            <input type="text" name="email" size="45"
              value="<c:out value='${user.email}' />"
            />
          </td>
        </tr>
        <tr>
          <th>Password: </th>
          <td>
            <input type="text" name="password" size="45"
              value="<c:out value='${user.password}' />"
            />
          </td>
        </tr>
       <tr>
          <th>Confirm password: </th>
          <td>
            <input type="text" name="confirmpw" size="45"/>
            <!--value="<c:out value='${user.password}' />"-->
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="submit" value="Sign Up" />
          </td>
        </tr>
      </table>
      </form>
    </div>  
  </body>
</html>