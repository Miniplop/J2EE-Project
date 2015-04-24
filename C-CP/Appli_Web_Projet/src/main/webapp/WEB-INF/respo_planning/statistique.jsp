<%-- 
    Document   : statistique
    Created on : 23 avr. 2015, 15:50:11
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
        <h1>Statistiques</h1>
        <table class="table table-striped">
            <thead><tr><th>Consomateur</th><th>Nombre de permanence affectées/réalisées</th><th>Nombre de contrat signés</th></thead>
            <tbody>
                <c:forEach items="${consommateurs}" var="consommateur">
                    <tr>
                        <td>${consommateur.nom} ${consommateur.prenom}</td>
                         <c:forEach items="${stat_count[consommateur]}" var="stat">
                            <td>${stat}</td>
                        </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
