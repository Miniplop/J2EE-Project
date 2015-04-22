<%-- 
    Document   : consulter
    Created on : 20 avr. 2015, 16:11:51
    Author     : loiseln
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Nom du site + connection -->
        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div>
                <form class="form-inline">
                    <div class="form-group">
                        <label class="sr-only" for="email">email:</label>
                        <input type="email" class="form-control" id="email" placeholder="Entrer l'email">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="nom">Nom:</label>
                        <input type="nom" class="form-control" id="password" placeholder="Nom:">
                    </div>
                     <span class="erreur"> 
                         <%
                                 out.println(request.getParameter("Erreur"));
                         %> 
                     </span>
                    <div class="checkbox">
                        <label>
                          <input type="checkbox"> Producteur 
                        </label>
                    </div>                    
                    <div class="checkbox">
                         <label>
                          <input type="checkbox"> Consomateur
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default">Connexion</button>
                    <input type="hidden" id="action" name="action" value="login"/><BR>
                </form>
            </div>
        </header>
        <!-- Liste des produits -->
        <section>
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
