

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- This jsp will act like a normal DBConnection like thay Huong did. -->
<sql:setDataSource
    var="conn"
    driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
    url="jdbc:sqlserver://MSI\MSSQLSERVER02:1433;databaseName=FlappyBird;encrypt=true;trustServerCertificate=true;"
    user="sa"
    password="An0354768872"
    />

<c:set var="DBC" value="${conn}" scope="application" />
