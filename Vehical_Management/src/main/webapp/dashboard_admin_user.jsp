<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<style>
a { color: green; margin: 30px; display: inline-block; } form.new { text-align: center; } 
</style>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dasboard | Admin</title>

</head>
<body>
    <header>
    <h1>Admin Home</h1>
    <p>welcome ${user.getUsername()}<p>
    <nav>
    <a href="dashboard?section=serviceRecords">Service Records</a>
    <a href="dashboard?section=customers">Customers</a>
    <a href="dashboard?section=vehicles">List Vehicles</a>
    <a href="dashboard?section=soldVehicles">Sold Vehicles</a>
    <a href="login">switch user</a>
    </nav>
    </header>
    
    <c:if test='${section.equals("serviceRecords") == true}'>
    <%@ include file="serviceRecords.jsp" %>
    </c:if>
    
    <c:if test='${section.equals("customers")}'>
    <%@ include file="customers.jsp" %>
    </c:if>
    
    <c:if test='${section.equals("vehicles")}'>
    <%@ include file="vehicles.jsp" %>
    </c:if>
    
    <c:if test='${section.equals("soldVehicles")}'>
    <%@ include file="soldVehicles.jsp" %>
    </c:if>
    

</body>
</html>