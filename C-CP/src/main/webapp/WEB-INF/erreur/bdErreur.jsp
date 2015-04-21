<%-- 
    Document   : bdErreur.jsp
    Created on : 24 mars 2015, 11:13:56
    Author     : loiseln
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erreur BD</title>
    </head>
    <body>
        <h1>Erreur!</h1>
        <p>Une erreur est survenue.</p>
        <p>
            ${erreurMessage}
        </p>
    </body>
</html>
