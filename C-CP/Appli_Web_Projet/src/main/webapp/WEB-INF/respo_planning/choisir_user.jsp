<%-- 
    Document   : choisir_user
    Created on : 23 avr. 2015, 12:57:12
    Author     : jeanke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sélection d'un permament </h1>
        <table class="table table-striped">
            <thead><tr><th>Nom</th><th>Prénom</th></thead>
            <tbody>
                <c:forEach items="${consommateur}" var="consommateur">
                    <tr>
                        <td>${consommateur.nom}</td>
                        <td>${consommateur.prenom}</td>
                        <td>
                            <form action = "responsable" methode = "GET">
                                <input type="submit" class="btn btn-default" id="num_permanent" value="Choisir">
                                <input type="hidden" name="permanent_choisi"  value="${consommateur.getId()}">
                                <input type="hidden" name="semaine_id"  value="${semaine.getId()}">
                                <input type="hidden" name="num_perm"  value="${num_perm}">  
                                <input type="hidden" name="action" id="action2" value="update_permanence">
                            </form></td></tr>
                        </c:forEach>
            </tbody>
        </table>

    </body>
</html>
