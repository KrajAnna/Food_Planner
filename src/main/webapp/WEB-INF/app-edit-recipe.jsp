<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="static_jsp_elements/head_contents.jsp" %>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 10px;
            font-size: 16px;
            width: 100%;
        }
        .button {
            padding: 10px 20px;
            background-color: #5cb85c;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
            margin-top: 20px;
        }
        .button:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<%@include file="static_jsp_elements/header.jsp"%>

<div class="container">
    <h2>Edycja Przepisu</h2>
    <form action="/app/recipe/edit" method="POST">
        <input type="hidden" name="id" value="${recipe.id}" />
        <label for="name">Nazwa:</label>
        <input type="text" id="name" name="name" value="${recipe.name}" required>
        <label for="description">Opis:</label>
        <textarea id="description" name="description" required>${recipe.description}</textarea>
        <label for="ingredients">Składniki:</label>
        <textarea id="ingredients" name="ingredients" required>${recipe.ingredients}</textarea>
        <label for="preparation">Przygotowanie:</label>
        <textarea id="preparation" name="preparation" required>${recipe.preparation}</textarea>
        <label for="preparationTime">Czas przygotowania (minuty):</label>
        <input type="number" id="preparationTime" name="preparationTime" value="${recipe.preparationTime}" required>
        <input type="submit" class="button" value="Aktualizuj">
    </form>
</div>

<%@include file="static_jsp_elements/footer.jsp"%>
<%@include file="static_jsp_elements/scripts.jsp"%>
</body>
</html>
