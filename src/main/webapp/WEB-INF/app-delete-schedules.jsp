<%--
  Created by IntelliJ IDEA.
  User: rivaldo
  Date: 14.03.2024
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="static_jsp_elements/head_contents.jsp" %>
    <style>
        .confirmation-container {
            text-align: center;
            margin-top: 50px;
        }
        .confirmation-actions {
            margin: 20px;
        }
        .confirmation-actions input[type="submit"],
        .confirmation-actions a {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px;
            border: none;
            background-color: #f44336;
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        .confirmation-actions a {
            background-color: #555555;
        }
    </style>
</head>
<body>
<%@include file="static_jsp_elements/header.jsp"%>
<div class="confirmation-container">
    <h2>Czy na pewno chcesz usunąć przepis z planu?</h2>
    <div class="confirmation-actions">
        <form action="/app/plan/delete" method="post">
            <input type="hidden" name="planId" value="${planId}" />

            <input type="submit" value="OK" />
        </form>
        <a href="/app/plan/list">Anuluj</a>
    </div>
</div>
<%@include file="static_jsp_elements/footer.jsp"%>
<%@include file="static_jsp_elements/scripts.jsp"%>
</body>
</html>
