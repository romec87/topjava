<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 05.07.2016
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>

</head>
<body>
<table border="1" cellpadding="15px" cellspacing="0px">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    <c:forEach items="${meallist}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"></jsp:useBean>
        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>

    </c:forEach>
</table>


</body>
</html>
