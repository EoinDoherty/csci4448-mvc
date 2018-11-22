<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>
  <h2>Login</h2>
  <form name="f" action="login" method="POST">
  	<table>
  	<tr>
  		<td>User:</td>
  		<td><input type="text" name="u" value=""></td>
  	</tr>
  	<tr>
  		<td>Password:</td>
  		<td><input type="password" name="p" value=""></td>
  	</tr>
  	<tr>
  		<td><input type="submit" name="submit" value="submit"></td>
  	</tr>
  	</table>
  </form>
  
  <h2>Sign Up</h2>
  <form name="f" action="signup" method="POST">
    <table>
      <tr>
    	<td>User:</td>
    	<td><input type="text" name="u" value=""></td>
      </tr>
      <tr>
    	<td>Password:</td>
    	<td><input type="password" name="p" value=""></td>
      </tr>
      <tr>
  		<td><input type="submit" name="submit" value="submit"></td>
  	  </tr>
    </table>
  </form>
</body>
</html>
