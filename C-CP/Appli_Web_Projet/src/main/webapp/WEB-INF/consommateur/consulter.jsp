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
        <link href="<c:url value="css/consommateur.css"/>" rel="stylesheet" type="text/css">
        <title>Consommateur</title>
    </head>
    <body>
        <!-- Nom du site + connection -->
        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div>${self.nom} ${self.prenom}</div>
            <jsp:include page="../deconnection.jsp">
                <jsp:param name="action" value="consommateur"/>
            </jsp:include>
        </header>
        <!-- Liste des produits -->
        <section class="col-lg-12">
                <c:forEach items="${produits}" var="produit">
                    <div class="col-lg-3">
                        <div class="bloc_produit">
                            <h2>${produit.nom}</h2>
                            <p>Quantité : <span>${produit.quantite} ${produit.unite}</span></p>
                            <p><span >Durée du contrat : ${produit.duree} semaines</span></p>
                            <p><span >Producteur : ${produit.getProducteur().getNom()} ${produit.getProducteur().getPrenom()}</span></p>
                            <div class="bloc-contrat row">
                                <c:if test="${self.getContrats().get(produit.getId()) != null}">
                                <c:forEach items="${self.getContrats().get(produit.getId())}" var="contrat">
                                    <div>
                                        <h3>${contrat.getQuantite()} ${contrat.getProduit().getUnite()}</h3>
                                        <c:choose>
                                            <c:when test="${contrat.getValide() == 2}">
                                                <span>Contrat en attente de validation</span>
                                            </c:when>
                                            <c:when test="${contrat.getValide() == 1}">
                                                <span>Contrat validé</span>
                                                <c:if test="${contrat.getDebutSemaine() != null}">
                                                    <p>Début : 
                                                        <span>${contrat.getDebutSemaine().getNumero()} ${contrat.getDebutSemaine().getMois().getNom()} ${contrat.getDebutSemaine().getMois().getAnnee()}</span>
                                                    </p>
                                                </c:if>
                                                <c:if test="${disponibilites.get(contrat.getId()) == null}">
                                                    <form class="row form-horizontal form_disponibilite" action="consommateur" method="get">
                                                        <h4>Disponibilités</h4>
                                                        <c:forEach begin="1" end="${produit.getDuree()}" var="val">
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input type="checkbox" name="semaine_${val}" id="semaine_${val}"> Semaine ${val}
                                                                </label>
                                                            </div>
                                                        </c:forEach>
                                                        <input type="hidden" name="contrat_id" value="${contrat.getId()}">
                                                        <input type="hidden" name="action" value="renseigner_disponibilites">
                                                        <button type="submit" class="btn btn-info col-lg-6 col-lg-offset-3"> Enregistrer</button>
                                                    </form>
                                                </c:if>
                                                <form class="row form-inline form_renouveler" action="consommateur" method="get">
                                                    <input type="hidden" name="contrat_id" value="${contrat.getId()}">
                                                    <input type="hidden" name="action" value="renouveler_contrat">
                                                    <button type="submit" class="btn btn-success btn-lg btn-block"> Renouveler le contrat</button>
                                                </form>
                                            </c:when>
                                            <c:when test="${contrat.getValide() == 0}">
                                                <span>Contrat refusé</span>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </c:forEach>
                                </c:if>
                            </div>
                            <c:if test="${self.getContrats().get(produit.getId()) == null}">
                                <jsp:include page="signer_contrat.jsp">
                                    <jsp:param name="produit" value="${produit.getId()}"/>
                                </jsp:include>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
        </section>
        <!-- Contact -->
        <footer>
            
        </footer>
    </body>
</html>
