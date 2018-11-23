<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>
  
  <h3>Title: ${noteModel.getTitle()}</h3>
  <h4>By: <a href="${contextPath}/user?usr=${noteModel.getUsername()}">${noteModel.getUsername()}</a></h4>
  <p>Text: ${noteModel.getText()}</p>
  <c:if test="${userClass.getUsername().equals(noteModel.getUsername())}">
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
  <a href="${contextPath}/deleteNote?noteId=${noteId}"><button>Delete</button></a>
  </c:if>
  <a href="${contextPath}/user"><button>Back to home page</button></a>
  
</body>
</html>
