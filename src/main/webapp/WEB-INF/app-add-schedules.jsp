<%--
  Created by IntelliJ IDEA.
  User: rivaldo
  Date: 14.03.2024
  Time: 01:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="static_jsp_elements/head_contents.jsp" %>
</head>
<body>
<%@include file="static_jsp_elements/header.jsp"%>
<form action="/app/plan/add" method="post">
    <div class="row border-bottom border-3 p-1 m-1">
        <div class="col noPadding">
            <h3 class="color-header text-uppercase">NOWY PLAN</h3>
        </div>
        <div class="col d-flex justify-content-end mb-2 noPadding">
            <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
        </div>
    </div>

    <div class="schedules-content">

        <div class="form-group row">
            <label for="planName" class="col-sm-2 label-size col-form-label">
                Nazwa planu
            </label>
            <div class="col-sm-10">
                <input name=planName class="form-control" id="planName" placeholder="Nazwa planu">
            </div>
        </div>
        <div class="form-group row">
            <label for="planDescription" class="col-sm-2 label-size col-form-label">
                Opis planu
            </label>
            <div class="col-sm-10">
                                <textarea name="planDescription" class="form-control" rows="5" id="planDescription"
                                          placeholder="Opis plany"></textarea>
            </div>
        </div>

    </div>
</form>
<%@include file="static_jsp_elements/footer.jsp"%>
<%@include file="static_jsp_elements/scripts.jsp"%>
</body>
</html>
