<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://topjava/functions" prefix="f"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meal List</title>
</head>
<body>
<a href="meals?action=save">Add Meal</a>
<table table class="table">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${mealsTo}" var="mealTo">
        <c:if test="${mealTo.isExcess() == true}">
            <tr>
                <td><font color="red">${f:formatLocalDateTime(mealTo.getDateTime(), pattern)}</font> </td>
                <td><font color="red">${mealTo.getDescription()}</font></td>
                <td><font color="red">${mealTo.getCalories()}</font></td>
                <td><a href="meals?action=update&id=${mealTo.getId()}">Update</a></td>
                <td><a href="meals?action=delete&id=${mealTo.getId()}">Delete</a></td>
            </tr>
        </c:if>
        <c:if test="${mealTo.isExcess() == false}">
            <tr>
                <td><font color="green">${f:formatLocalDateTime(mealTo.getDateTime(), pattern)}</font></td>
                <td><font color="green">${mealTo.getDescription()}</font></td>
                <td><font color="green">${mealTo.getCalories()}</font></td>
                <td><a href="meals?action=update&id=${mealTo.getId()}">Update</a></td>
                <td><a href="meals?action=delete&id=${mealTo.getId()}">Delete</a></td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
