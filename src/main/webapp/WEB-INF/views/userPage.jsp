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
  
  <c:if test="${userPageClass.isOwner()}">
  <h4>Hi ${userPageClass.getPageUserName()}, here are your notes:</h4>
  </c:if>
  
  <c:if test="${!userPageClass.isOwner()}">
  <h4>${userPageClass.getPageUserName()}'s notes:</h4>
  </c:if>
  
  <ul>
  <c:forEach items="${userPageClass.getNotes()}" var="item">
  	<c:set var="id" value="${contextPath}/user/redirectNote?id=${item.get(\"_id\")}"/>
  	<li><a href="${id}">${item.get("title")}</a></li>
  </c:forEach>
  </ul>
  
  <c:if test="${userPageClass.isOwner()}">
  <a href="${contextPath}/user/newNote"><button>Create new</button></a>
  </c:if>
  
</body>
</html>
