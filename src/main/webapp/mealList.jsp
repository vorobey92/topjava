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
            <c:if test="${meal.isExceed()}" >
                <c:set value="#FF0000" var="red"/>
            </c:if>
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
            </tr>
        </c:forEach>
    </table>
</body>
</html>
