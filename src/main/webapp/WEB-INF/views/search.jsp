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
  
  <h3>Results:</h3>
  
  <c:set var="notes" value="${searchClass.getSearchResults()}"/>
  <ul>
  	<c:forEach items="${notes}" var="item">
      <c:set var="id" value="${contextPath}/user/redirectNote?id=${item.get(\"_id\")}"/>
      <li><a href="${id}">${item.get("title")}</a></li>
  	</c:forEach>
  </ul>
  
  <c:if test="${notes.size() == 0}">
    <p>Nothing found</p>
  </c:if>
  
  <a href="${contextPath}/user"><button>Return to user page</button></a>
</body>
</html>
