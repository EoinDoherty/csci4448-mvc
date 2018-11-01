<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>
  <h1>Login</h1>
  <form name="f" action="login" method="POST">
  	<table>
  	<tr>
  		<td>User:</td>
  		<td><input type="text" name="username" value=""></td>
  	</tr>
  	<tr>
  		<td>Password:</td>
  		<td><input type="password" name="password" value=""></td>
  	</tr>
  	<tr>
  		<td><input type="submit" name="submit" value="submit"></td>
  	</tr>
  	</table>
  </form>
</body>
</html>
