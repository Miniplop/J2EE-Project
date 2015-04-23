<%-- 
    Document   : consulter.jsp
    Created on : 22 avr. 2015, 10:57:35
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
        <form action="responsable" method="GET">
            <input type="hidden" name="action" id="action" value="logout">
            <button type="submit" class="btn btn-default">déconnexion</button>
        </form>

        <form action="responsable" method="GET">
            <h1>Ajouter un mois</h1>
            <div class="btn-group">
                <div class="form-group">
                    <label class="sr-only" for="nom_mois" id="mois">Mois :</label>
                    <select name="nom_mois" id="nom_mois" class="form-control">
                        <option>Janvier</option>
                        <option>Février</option>
                        <option>Mars</option>
                        <option>Avril</option>
                        <option>Mai</option>
                        <option>Juin</option>
                        <option>Juillet</option>
                        <option>Aout</option>
                        <option>Septembre</option>
                        <option>Octobre</option>
                        <option>Novembre</option>
                        <option>Décembre</option>
                    </select> 
                    <div class="btn-group">
                        <label class="sr-only" for="annee" id="annee">Année :</label>
                        <select name="annee" id="annee" class="form-control">
                            <option>2015</option>
                            <option>2016</option>
                            <option>2017</option>
                        </select>
                    </div>
                    <input type="hidden" name="action" id="action" value="demarrer_mois">
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajouter mois
                    </button>

                </div>
        </form>


        <section>
            <div class="panel-group" id="produits" role="tablist" aria-multiselectable="true">
                <div class="container">
                    <h2>Planning</h2>
                    <div class="panel-group" id="accordion">
                        <c:forEach items="${Mois}" var="mois">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${mois.nom}${mois.annee}">${mois.nom} ${mois.annee} </a>
                                    </h4>
                                </div>
                                <div id="collapse${mois.nom}${mois.annee}" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach items="${mois.semaines}" var="semaine">
                                                <li class="list-group-item">
                                                    Semaine n°${semaine.numero}
                                                    <c:choose>
                                                        <c:when test="${semaine.permanent1 != null}"><a>${semaine.consommateur_1_id}</a></c:when>
                                                        <c:otherwise><a>Permanent Non attribué</a>
                                                            <input type="hidden" name="action" id="action" value="affecter_permanences">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${semaine.consommateur_2_id != null}"><a>${semaine.consommateur_2_id}</a></c:when>
                                                        <c:otherwise><a>Pemanent Non attribué</a>
                                                            <input type="hidden" name="action" id="action" value="affecter_permanences">
                                                        </c:otherwise>
                                                    </c:choose>
                                                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                                </li>                              
                                            </c:forEach>
                                        </ul>
                                    </div>                   
                                </div>
                            </div>
                        </c:forEach>
                    </div> 
                </div>
            </div>
        </section>
    </body>
</html>
