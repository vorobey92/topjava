<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ia.vorobev
  Date: 12.09.2016
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<form method="POST" action='meals' name="frmAddMeal">
    Meal ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${meal.id}" />"/> <br/>
    Description : <input type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    DateTime : <input type="text" name="dateTime"
        value="<c:out value="${fn:replace(meal.getDateTime(), 'T', ' ')}" />"/> <br/>
    Calories : <input type="text" name="calories"
                      value="<c:out value="${meal.calories}" />"/> <br/> <input
        type="submit" value="Submit"/>
</form>
</body>
</html>
