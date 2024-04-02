<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 3/12/24
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="static_jsp_elements/app-head_contents.jsp"%>
</head>

<body>
<%@ include file="static_jsp_elements/app-header.jsp"%>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="static_jsp_elements/app-menu-left.jsp"%>

        <div class="schedules-content">
            <table class="table">
                <thead>
                <tr class="d-flex">
                    <th class="col-1">ID</th>
                    <th class="col-3">IMIĘ</th>
                    <th class="col-6">NAZWISKO</th>
                    <th class="col-2 center">AKCJE</th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">
                <tr class="d-flex">
                    <td class="col-1">1</td>
                    <td class="col-3">Marek</td>
                    <td class="col-6">Marecki</td>
                    <td class="col-2 center">
                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                    </td>
                </tr>
                <tr class="d-flex">
                    <td class="col-1">2</td>
                    <td class="col-3">Marek</td>
                    <td class="col-6">Marecki</td>
                    <td class="col-2 center">
                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                    </td>
                </tr>
                <tr class="d-flex">
                    <td class="col-1">3</td>
                    <td class="col-3">Marek</td>
                    <td class="col-6">Marecki</td>
                    <td class="col-2 center">
                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
</section>
<%@ include file="static_jsp_elements/app-footer.jsp"%>


</body>
</html>