<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 14/03/2024
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="static_jsp_elements/app-head_contents.jsp" %>
    <script src="${pageContext.request.contextPath}/js/recipeplanadd.js"></script>
</head>
<body>
<%@ include file="static_jsp_elements/app-header.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="static_jsp_elements/app-menu-left.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="#" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <form action="/app/recipe/plan/add" method="post">
                        <div class="form-group row">
                            <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                Wybierz plan
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="choosePlan" name="planId">
                                    <c:forEach items="${plans}" var="plan">
                                        <option value="<c:out value="${plan.id}"/>"><c:out value="${plan.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="mealType" class="col-sm-2 label-size col-form-label">
                                Nazwa posiłku
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="mealType" name="mealType">
                                    <option value="Śniadanie">Śniadanie</option>
                                    <option value="Obiad">Obiad</option>
                                    <option value="Kolacja">Kolacja</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="number" class="col-sm-2 label-size col-form-label">
                                Numer posiłku
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="" id="number"
                                       placeholder="Numer posiłku" name="mealOrder">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="recipie" class="col-sm-2 label-size col-form-label">
                                Przepis
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="recipie" name="recipeId">
                                    <c:forEach items="${recipes}" var="recipe">
                                        <option value="<c:out value="${recipe.id}"/>"><c:out value="${recipe.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="day" class="col-sm-2 label-size col-form-label">
                                Dzień
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="day" name="dayId">
                                    <c:forEach items="${days}" var="day">
                                        <option value="<c:out value="${day.id}"/>"><c:out value="${day.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="static_jsp_elements/scripts.jsp" %>
</body>
</html>
