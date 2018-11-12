<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>
  <p>${user.getUsername() }</p>
  <h3>Title: ${noteModel.getTitle()}</h3>
  <p>Text: ${noteModel.getText()}</p>
  <form:form action="update" method="POST" modelAttribute="noteModel">
  	<table>
	  	<tr>
	  	  <td><form:label path="title">Title</form:label></td>
	  	  <td><form:input path="title"/></td>
	  	</tr>
	  	<tr>
	  	  <td><form:label path="text">Text</form:label></td>
	  	  <td><form:textarea path="text"/>
	  	</tr>
	  	<tr>
	  	  <td><input type="submit" value="Save"/></td>
	  	</tr>
  	</table>
  </form:form>
  <a href="">Back</a>
</body>
</html>
