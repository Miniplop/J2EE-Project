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
            <div>${self.nom} ${self.prenom}</div>
            <jsp:include page="deconnection.jspf">
                <jsp:param name="action" value="consommateur"/>
            </jsp:include>
        </header>
        <!-- Liste des produits -->
        <section class="col-lg-12">
                <c:forEach items="${produits}" var="produit">
                    <div class="col-lg-3">
                        <h2 >${produit.nom}</h2>
                        <span >Quantité : ${produit.quantite} ${produit.unite}</span>
                        <span >Durée du contrat : ${produit.duree} semaines</span>
                        <span >Producteur : ${produit.getProducteur().getNom()} ${produit.getProducteur().getPrenom()}</span>
                        <c:if test="${self.getContrats().get(produit.getId()) != null}">
                        <div>
                            <c:forEach items="${self.getContrats().get(produit.getId())}" var="contrat">
                                <div>
                                    <h3>${contrat.getQuantite()}</h3>
                                    <c:choose>
                                        <c:when test="${contrat.getValide() == 2}">
                                            <span>Contrat en attente de validation</span>
                                        </c:when>
                                        <c:when test="${contrat.getValide() == 1}">
                                            <span>Contrat validé</span>
                                            <c:if test="${disponibilites.get(contrat.getId()) == null}">
                                                <jsp:include page="consommateur/renseigner_disponibilite.jspf">
                                                    <jsp:param name="produit" value="${produit}"/>
                                                    <jsp:param name="contrat" value="${contrat}"/>
                                                </jsp:include>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${contrat.getValide() == 0}">
                                            <span>Contrat refusé</span>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${contrat.getDebutSemaine() != null}">
                                        <span>semaine ${contrat.getDebutSemaine().getNumero()} ${contrat.getDebutSemaine().getMois().getNom()} ${contrat.getDebutSemaine().getMois().getAnnee()}</span>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                        </c:if>
                        <jsp:include page="consommateur/signer_contrat.jspf">
                            <jsp:param name="produit" value="${produit}"/>
                        </jsp:include>
                    </div>
                </c:forEach>
        </section>
        <!-- Contact -->
        <footer>
            
        </footer>
    </body>
</html>
