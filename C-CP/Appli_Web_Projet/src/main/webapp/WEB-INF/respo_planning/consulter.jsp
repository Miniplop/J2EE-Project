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
              <input type="hidden" name="action" id="action" value="demarrer_mois">
              <button type="submit" class="btn btn-default btn-lg">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajouter mois
              </button>
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
