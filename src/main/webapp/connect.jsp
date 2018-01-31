<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Class.forName("com.mysql.jdbc.Driver"); 
    Connection conn = DriverManager.getConnection("com.mysql.//localhost/major_db", "root", "12345678");
%>
