<%-- 
    Document   : consulter
    Created on : 20 avr. 2015, 16:11:51
    Author     : loiseln
--%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <title>Cooperative L.J.P.D.</title>
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
                    <p class="navbar-text" id="erreur"></p>
                    <form class="navbar-form navbar-left" id="form-connexion">
                        <div class="form-group">
                            <label class="sr-only" for="email">email:</label>
                            <input type="text" class="form-control" id="email" placeholder="Entrer l'email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="nom">Nom:</label>
                            <input type="text" class="form-control" id="nom" placeholder="Nom" name="nom" required>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="type" id="type">Vous êtes :</label>
                            <select name="type" id="user-type" class="form-control">
                                <option>producteur</option>
                                <option>consommateur</option>
                            </select>
                        </div>
                        <input type="submit" class="btn btn-default" id="connexion" value="Connexion">
                        <input type="hidden" id="action" name="action" value="login"/><BR>
                    </form>
                </ul>
              </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
         </nav>
         </header>
            
        <!-- Nom du site + connection -->
<!--        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div>
                <form class="form-inline" id="form-connexion" action="utilisateur" method="get">
                    <div class="form-group">
                        <label class="sr-only" for="email">Email:</label>
                        <input type="text" class="form-control" id="email" placeholder="Entrer l'email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="nom">Nom:</label>
                        <input type="text" class="form-control" id="nom" placeholder="Nom" name="nom" required>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="type" id="type">Vous êtes :</label>
                        <select name="type" id="user-type" class="form-control">
                            <option>producteur</option>
                            <option>consommateur</option>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-default" id="connexion" value="Connexion">
                    <input type="hidden" id="action" name="action" value="login"/><BR>
                </form>
                <p id="erreur"></p>
            </div>
        </header>-->
        
        <!-- Liste des produits -->
        <section class="col-lg-6 col-lg-offset-3">
            <div class="panel-group" id="produits" role="tablist" aria-multiselectable="true">
                <c:forEach items="${produits}" var="produit">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading_${produit.id}">
                            <h4 class="panel-title">
                                <div aria-expanded="true">
                                    <h2>${produit.nom}</h2>
                                    <p>Quantite : <span>${produit.quantite} ${produit.unite}</span></p>
                                    <p>durée du contrat : ${produit.duree} semaines</p>
                                    <p>producteur : ${produit.getProducteur().getNom()} ${produit.getProducteur().getPrenom()}</p>
                                </div>
                            </h4>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <!-- Contact -->
        <footer>
            
        </footer>

        <script src="<c:url value="js/jquery.js"/>" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#form-connexion").submit(function(e) {
                    e.preventDefault();
                    var email= $("#email").val();
                    var nom = $("#nom").val();
                    var type= $("#user-type").val();
                    $.get('utilisateur',{nom:nom, email:email, type:type, action:"login"},function(responseText) {
                        if(responseText === "erreur")
                            $('#erreur').text("Utilisateur inconnu");
                        else if(responseText === "consommateur" || responseText === "producteur" || responseText === "responsable")
                            document.location.replace("${pageContext.request.contextPath}/"+responseText);
                        else
                            document.write(responseText);
                    });
                    return false;
                });
            });
        </script>
    </body>
</html>
