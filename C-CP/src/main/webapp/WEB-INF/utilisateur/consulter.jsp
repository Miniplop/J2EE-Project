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
        <title>JSP Page</title>
    </head>
    <body>
            
        <!-- Nom du site + connection -->
        <header>
            <h1>Cooperative L.J.P.D.</h1>
            <div>
                <form class="form-inline" id="form-connexion">
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
                <p id="erreur"></p>
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

        <script src="<c:url value="js/jquery.js"/>" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#form-connexion").submit(function(e) {
                    e.preventDefault();
                    return false;
                });
                $("#connexion").click(function(e) {
                    e.preventDefault();
                    var email= $("#email").val();
                    var nom = $("#nom").val();
                    var type= $("#user-type").val();
                    console.log(email);
                    console.log(nom);
                    console.log(type);
                    $.get('utilisateur',{nom:nom, email:email, type:type, action:"login"},function(responseText) {
                        if(responseText === "erreur")
                            $('#erreur').text("Utilisateur inconnu");
                        else if(responseText === "consommateur")
                            document.location.replace("${pageContext.request.contextPath}/consommateur");
                        else if(responseText === "producteur")
                            document.location.replace("${pageContext.request.contextPath}/producteur");
                        else
                            document.write(responseText);
                    });
                    return false;
                });
            });
        </script>
    </body>
</html>
