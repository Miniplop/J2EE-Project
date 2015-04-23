<%-- 
    Document   : consulter.jsp
    Created on : 22 avr. 2015, 10:52:06
    Author     : loiseln
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <title>Producteur</title>
    </head>
    <body>
        
        <header>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
              <!-- Brand and toggle get grouped for better mobile display -->
              <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Coopérative L.J.P.D.</a>
              </div>
                <ul class="nav navbar-nav navbar-right">
                    <div class="navbar-text" id ="nom_user">${self.nom} </div>
                    <div class="navbar-text" id ="prenom_user">${self.prenom} </div>
                    <div class="btn-group" role="group" aria-label="...">
                        <form class="navbar-form navbar-right" action="producteur" method="GET">
                            <input type="hidden" name="action" id="action" value="logout">
                            <button type="submit" class="btn btn-default">déconnexion</button>
                        </form>
                    </div>
                </ul>
              </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
         </nav>
         </header>
        <!-- Nom du site + connection -->
        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div id ="nom_user">${self.nom} </div>
            <div id ="prenom_user">${self.prenom} </div>
            <jsp:include page="../deconnection.jsp">
                <jsp:param name="action" value="producteur"/>
            </jsp:include>
        </header>
        <!-- Liste des produits -->
        <section class="col-lg-6 col-lg-offset-3">
            <div id="list_produit">
                <c:forEach items="${self.getProduits()}" var="produit">
                <div>
                    <div>
                        <h2>${produit.nom}</h2>
                        <p>durée du contrat : <span>${produit.duree} semaines</span></p>
                        <p>Quantite : <span>${produit.quantite} ${produit.unite}</span></p>
                    </div>
                    <c:if test="${contrats.get(produit.getId()) != null}">
                    <div>
                        <c:forEach items="${contrats.get(produit.getId())}" var="contrat">
                        <div>
                            <h4> Contrat passé par : ${contrat.getConsommateur().getNom()} ${contrat.getConsommateur().getPrenom()} </h4>
                            <span>${contrat.getConsommateur().getAdresse()}</span>
                            <span>${contrat.getConsommateur().getEmail()}</span>
                            <p>
                                Quantité commandée : <span>${contrat.getQuantite()} ${contrat.getProduit().getUnite()}</span>
                            </p>
                            <c:choose>
                                <c:when test="${contrat.getValide() == 0}"> <!-- contrat refusé -->
                                    <div class="text-warning">
                                        <h5>Contrat refusé</h5>
                                    </div>
                                </c:when>
                                <c:when test="${contrat.getValide() == 1}"> <!-- contrat validé -->
                                    <div class="text-success">
                                        <h5>Contrat passé</h5>
                                    </div>
                                </c:when>
                                <c:when test="${contrat.getValide() == 2}">
                                    <form class="form-horizontal text-center" action="producteur" method="get">
                                        <select name="semaine_id">
                                            <c:forEach items="${mois.getSemaines()}" var="semaine">
                                            <option value="${semaine.getId()}">semaine ${semaine.getNumero()} ${semaine.getMois().getNom()} ${semaine.getMois().getAnnee()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="action" value="valider_contrat">
                                        <input type="hidden" name="contrat_id" value="${contrat.getId()}">
                                        <button type="submit" class="btn btn-success" name="accept">Valider le contrat</button>
                                        <button type="submit" class="btn btn-success" name="refus">Refuser le contrat</button>
                                    </form>
                                </c:when>
                                <c:otherwise> <jsp:forward page="erreur/controleurErreur.jsp"/></c:otherwise>
                            </c:choose>
                        </div>
                        </c:forEach>
                    </div>
                    </c:if>
                </div>
                </c:forEach>
            </div>
            
            <div>
                <h2>Ajouter un produit</h2>
                <form class="form-horizontal text-center" id="nouveau_produit">
                    <div class="form-group">
                        <label for="nouveau_nom" class="col-lg-3 control-label">Nom du produit</label>
                        <div class="col-lg-3">
                            <input type="text" class="form-control" id="nouveau_nom" placeholder="Nom" name="nom" required="true">
                        </div>
                        <label for="nouveau_unite" class="col-lg-3 control-label">Unité de mesure</label>
                        <div class="col-lg-3">
                            <input type="text" class="form-control" id="nouveau_unite" placeholder="Unité" name="unite" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nouveau_quantite" class="col-lg-3 control-label">Quantité</label>
                        <div class="col-lg-3">
                            <input type="number" class="form-control" id="nouveau_quantite" placeholder="Quantité" name="quantite" required="true">
                        </div>
                        <label for="nouveau_duree" class="col-lg-3 control-label">Durée des contrats <p class="small">(en semaine)</p></label>
                        <div class="col-lg-3">
                            <input type="number" class="form-control" id="nouveau_duree" placeholder="durée" name="duree" required="true">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg" id="ajouter_produit">Ajouter</button>
                </form>
            </div>
        </section>   
        
        
        <script src="<c:url value="js/jquery.js"/>" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#nouveau_produit").submit(function(e) {
                    e.preventDefault();
                    var nom= $("#nouveau_nom").val();
                    var unite = $("#nouveau_unite").val();
                    var quantite= $("#nouveau_quantite").val();
                    var duree= $("#nouveau_duree").val();
                    console.log(nom);
                    console.log(unite);
                    console.log(quantite);
                    console.log(duree);
                    $.get('producteur',{nom:nom, unite:unite, quantite:quantite, duree:duree, action:"renseigner_produit"},function(responseText) {
                        if(!isNaN(responseText))
                            $('#list_produit').append(
                                '<div>\
                                    <div>\
                                        <h4>'+nom+'</h4>\
                                        <p>durée du contrat : <span>'+duree+' semaines</span></p>\
                                    </div>\
                                 </div>');
                        else {
                            document.write(responseText);
                        }
                            
                    });
                    return false;
                });
            });
        </script>
    </body>
</html>
