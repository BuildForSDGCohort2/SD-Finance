<%-- 
    Document   : logout
    Created on : Aug 28, 2020, 2:41:41 PM
    Author     : Seumx Plus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!--Sign Out All Accounts-->
    <%
    
    Cookie[] cks=request.getCookies();
    for(Cookie ch:cks) {
        ch.setMaxAge(0); response.addCookie(ch);
    }
    
    /*Let Us Go Back To Login Page*/
    response.sendRedirect("sign.html");
    
    %>
