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
</head>
<body>
<%@ include file="static_jsp_elements/app-header.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="static_jsp_elements/app-menu-left.jsp" %>
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="mt-4 ml-4 mr-4">
                    <div class="row border-bottom border-3">
                        <div class="col"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                        <div class="col d-flex justify-content-end mb-2"><a href="/app/recipe/list"
                                                                            class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                        </div>
                    </div>

                    <table class="table borderless">
                        <tbody>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Nazwa przepisu</th>
                            <td class="col-7">
                                <c:out value="${recipe.name}"/>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Opis przepisu</th>
                            <td class="col-7"><c:out value="${recipe.description}"/>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                            <td class="col-7">
                                <c:out value="${recipe.preparationTime}"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3>
                        </div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <p><c:out value="${recipe.preparation}"/></p>
                        </div>
                        <div class="col-2"></div>
                        <ul class="col-5 p-4 list-unstyled">
                            <c:forEach items="${recipe.ingredients}" var="ingredient">
                                <li><c:out value="${ingredient}" /></li>
                            </c:forEach>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="static_jsp_elements/scripts.jsp" %>
</body>
</html>
