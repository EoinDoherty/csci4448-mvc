<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@page import="models.User" %>
<html>
<body>
  <form name="f" action="search" method="POST">
    <table>
      <tr>
        <td><input type="text" name="query" value=""></td>
        <td><input type="submit" name="submit" value="search"></td>
      </tr>
    </table>
  </form>
  
  <h4>Hi ${userClass.getUsername()}, here are your notes:</h4>
  
  <ul>
  <c:forEach items="${userNotes}" var="item">
  	<c:set var="id" value="${contextPath}/user/redirectNote?id=${item.get(\"_id\")}"/>
  	<li><a href="${id}">${item.get("title")}</a></li>
  </c:forEach>
  </ul>
  <a href="${contextPath}/user/newNote"><button>Create new</button></a>
</body>
</html>
