<%-- 
    Document   : ajouterMois
    Created on : 22 avr. 2015, 17:57:11
    Author     : jeanke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form class="form-inline" id="form-connexion">
                    <div class="form-group">
                        <label class="sr-only" for="email">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        Mois <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Janvier</a></li>
                                        <li><a href="#">Février</a></li>
                                        <li><a href="#">Mars</a></li>
                                        <li><a href="#">Avril</a></li>
                                        <li><a href="#">Mai</a></li>
                                        <li><a href="#">Juin</a></li>
                                        <li><a href="#">Juillet</a></li>
                                        <li><a href="#">Aout</a></li>
                                        <li><a href="#">Septembre</a></li>
                                        <li><a href="#">Octobre</a></li>
                                        <li><a href="#">Novembre</a></li>
                                        <li><a href="#">Décembre</a></li>
                                     </ul>
                                </div>
                        </label>
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
    </body>
</html>
