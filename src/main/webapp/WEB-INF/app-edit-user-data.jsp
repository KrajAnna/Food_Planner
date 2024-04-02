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

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="m-4 border-dashed view-height">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form>
                    <div class="mt-4 ml-4 mr-4">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Edytuj dane</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                                </button>
                            </div>
                        </div>

                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>Imię</h4></th>
                                <td class="col-7">
                                    <input class="w-100 p-1" value="Marek">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>Nazwisko</h4></th>
                                <td class="col-7">
                                    <input class="w-100 p-1" value="Markowski">
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>Email</h4></th>
                                <td class="col-3">
                                    <input class="p-1 w-100" type="text" value="marek@mark.pl">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<%@ include file="static_jsp_elements/app-footer.jsp"%>


</body>
</html>