<%--
  Created by IntelliJ IDEA.
  User: ia.vorobev
  Date: 10.09.2016
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Meal List</title>
</head>
<body>

<table border="1">
    <tr>
        <th>Описание</th>
        <th>Время</th>
        <th>Калории</th>
        <th>Превышение</th>
    </tr>
    <c:forEach var="meal" items="${mealList}">
        <c:choose>
            <c:when test="${meal.isExceed()}">
                <c:set value="#FF0000" var="red"/>
            </c:when>
            <c:otherwise>
                <c:set value="#000000" var="red"/>
            </c:otherwise>
        </c:choose>
        <tr style="color:${red}">
            <td>
                <c:out value="${meal.getDescription()}"/>
            </td>
            <td>
                <c:out value="${fn:replace(meal.getDateTime(), 'T', ' ')}"/>
            </td>
            <td>
                <c:out value="${meal.getCalories()}"/>
            </td>
            <td>
                <c:out value="${meal.isExceed()}"/>
            </td>
            <td>
                <a href="meals?action=edit&id=${meal.getId()}">Update</a>
            </td>
            <td>
                <a href="meals?action=delete&id=${meal.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=insert">Add Meal</a></p>
</body>
</html>
