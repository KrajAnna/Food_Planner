<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 11/03/2024
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/static_jsp_elements/head_contents.jsp" %>
</head>
<body>
<%@include file="WEB-INF/static_jsp_elements/header.jsp"%>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form class="padding-small text-center" action="/login" method="post">
                    <h1 class="text-color-darker">Logowanie</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" name="email" placeholder="podaj adres email">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="password" name="password" placeholder="podaj hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                </form>
            </div>
        </div>
    </div>
</section>

<%@include file="WEB-INF/static_jsp_elements/footer.jsp"%>
<%@include file="WEB-INF/static_jsp_elements/scripts.jsp"%>
</body>
</html>
