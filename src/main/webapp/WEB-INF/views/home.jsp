<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>
  <p>Hi, ${name}</p>
  <form:form action="test" method="POST" modelAttribute="user">
    <table>
      <tr>
        <td><form:label path = "username">Name</form:label></td>
        <td><form:input path = "username" /></td>
      </tr>
    </table>
  </form:form>
</body>
</html>
