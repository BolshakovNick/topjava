<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Meal</title>
</head>
<body>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <label>
            <input type="datetime-local" name="DateTime" value="${meal.dateTime}">
        </label>
    </dl>
    <dl>
        <label>
            <input type="text" name="Description" size=40 value="${meal.description}">
        </label>
    </dl>
    <dl>
        <label>
            <input type="number" name="Calories" size=4 value="${meal.calories}">
        </label>
    </dl>
    <button type="submit">Сохранить</button>
    <button type="reset" onclick="window.history.back()">Отменить</button>
</form>
</body>
</html>