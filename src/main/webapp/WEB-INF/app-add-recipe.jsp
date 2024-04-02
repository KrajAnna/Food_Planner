<%--
  Created by IntelliJ IDEA.
  User: rivaldo
  Date: 13.03.2024
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="static_jsp_elements/head_contents.jsp" %>
</head>
<body>
<%@include file="static_jsp_elements/header.jsp"%>
<form action="/app/recipe/add" method="post">
  <div class="mt-4 ml-4 mr-4">
    <div class="row border-bottom border-3">
      <div class="col"><h3 class="color-header text-uppercase">Nowy przepis</h3></div>
      <div class="col d-flex justify-content-end mb-2">
        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
      </div>
    </div>

    <table class="table borderless">
      <tbody>
      <tr class="d-flex">
        <th scope="row" class="col-2">Nazwa Przepisu</th>
        <td class="col-7">

          <input name="recipeName" class="w-100 p-1" value="">
        </td>
      </tr>
      <tr class="d-flex">
        <th scope="row" class="col-2">Opis przepisu</th>
        <td class="col-7">< <textarea name="recipeDescription" class="w-100 p-1" rows="5"></textarea></td>
      </tr>
      <tr class="d-flex">
        <th scope="row" class="col-2">Przygotowanie (minuty)</th>
        <td class="col-3">
          <input name="preparationTime" class="p-1" type="number" value="">
        </td>
      </tr>
      </tbody>
    </table>

    <div class="row d-flex">
      <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób
        przygotowania</h3></div>
      <div class="col-2"></div>
      <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
    </div>
    <div class="row d-flex">
      <div class="col-5 p-4">
        <textarea name="preparation" class="w-100 p-1" rows="10"></textarea>
      </div>
      <div class="col-2"></div>
      <div class="col-5 p-4">
        <textarea name="ingredients" class="w-100 p-1" rows="10"></textarea>
      </div>
    </div>
  </div>
</form>
<%@include file="static_jsp_elements/footer.jsp"%>
<%@include file="static_jsp_elements/scripts.jsp"%>
</body>
</html>