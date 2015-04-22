<%-- 
    Document   : consulter
    Created on : 20 avr. 2015, 16:11:51
    Author     : loiseln
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <title>Consommateur</title>
    </head>
    <body>
        <!-- Nom du site + connection -->
        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div id ="nom_user">${self.nom} </div>
            <div id ="prenom_user">${self.prenom} </div>
            <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default">déconnexion</button>
            </div>
        </header>
        <!-- Liste des produits -->
        <section class="col-lg-6 col-lg-offset-3">
            <div class="panel-group" id="produits" role="tablist" aria-multiselectable="true">
                <c:forEach items="${produits}" var="produit">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading_${produit.id}">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#produits" href="#collapse_${produit.id}" aria-expanded="true" aria-controls="collapse_${produit.id}">
                                    <h2>${produit.nom}</h2>
                                    <p>durée du contrat : ${produit.duree} semaines</p>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading_${produit.id}">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <!-- Contact -->
        <footer>
            
        </footer>
    </body>
</html>
