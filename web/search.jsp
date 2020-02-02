<%-- 
    Document   : search
    Created on : 7-nov-2019, 15.30.51
    Author     : gigan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <form id="userByID" action="UserListController" method="post">
            <div>
                UserByID
            </div>
            <div>
                <input type="text" id ="userID" placeholder="ID utente" name="userID">
            </div>
            <div>
                <input type="submit" name="Search">
                <button type="button" id="findUserButton" onclick="findUser()">CERCA</button>
            </div>
            <div>Lista utenti:</div>
            <span class="error">${error}</span>
            <div id="userSearchList"></div>
        </form>
            
            
    </body>
</html>
