<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 3/12/24
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="static_jsp_elements/app-head_contents.jsp" %>
</head>

<body>
<%@ include file="static_jsp_elements/app-header.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="static_jsp_elements/app-menu-left.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/plan/list">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: <c:out value="${qtyRecipes}"/></span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: <c:out value="${qtyPlans}"/></span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span>
                </h2>
                <c:forEach var="day" items="${lastPlan}">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${day.key}</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>

                    <tbody>
                    <c:forEach var="recipe" items="${day.value}">
                        <tr class="d-flex">
                            <td class="col-2">${recipe.mealName}</td>
                            <td class="col-8">${recipe.recipeName}</td>
                            <td class="col-2">
                                <button type="button" class="btn btn-primary rounded-0">Szczegóły</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<%@ include file="static_jsp_elements/app-footer.jsp" %>


</body>
</html>