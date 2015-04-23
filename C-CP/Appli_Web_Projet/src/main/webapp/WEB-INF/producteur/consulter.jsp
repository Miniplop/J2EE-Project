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
        <!-- Nom du site + connection -->
        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div id ="nom_user">${self.nom} </div>
            <div id ="prenom_user">${self.prenom} </div>
            <div class="btn-group" role="group" aria-label="...">
                <form action="producteur" method="GET">
                    <input type="hidden" name="action" id="action" value="logout">
                    <button type="submit" class="btn btn-default">déconnexion</button>
                </form>
            </div>
        </header>
        <!-- Liste des produits -->
        <section class="col-lg-6 col-lg-offset-3">
            <div id="list_produit">
                <c:forEach items="${self.getProduits()}" var="produit">
                <div>
                    <div>
                        <h2>${produit.nom}</h2>
                        <p>durée du contrat : <span>${produit.duree} semaines</span></p>
                    </div>
                    <c:if test="${contrats.get(produit.getId()) != null}">
                    <div>
                        <c:forEach items="${contrats.get(produit.getId())}" var="contrat">
                        <div>
                            <h4> Contrat passé par : ${contrat.getConsommateur().getNom()} ${contrat.getConsommateur().getPrenom()} </h4>
                            <span>${contrat.getConsommateur().getAdresse()}</span>
                            <span>${contrat.getConsommateur().getEmail()}</span>
                            <p>
                                Quantité commandée : <span>${contrat.getQuantite()}</span>
                            </p>
                            <form action="producteur" method="get">
                                <option>
                                    <c:forEach items="${semaines}" var="semaine">
                                    <select>semaine ${semaine.getNumero()} ${semaine.getMois().getNom()} ${semaine.getMois().getAnnee()}</select>
                                    </c:forEach>
                                </option>
                                <input type="hidden" name="action" value="valider_contrat">
                                <button type="submit" class="btn btn-success">Valider le contrat</button>
                            </form>
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
