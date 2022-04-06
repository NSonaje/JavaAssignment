<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>dashboard</title>


</head>
<body>

<style>
a { color: green; margin: 30px; display: inline-block; } form.new { text-align: center; } 
</style>



    <header>
    <h1>Service User Home</h1>
    <p>welcome ${user.getUsername()}<p>
    <nav>
    <a href="login">switch user</a>
    </nav>
    </header>

    <table border="1px">
    <tr>
    <th>Service Id</th>
    <th>Vehicle Reg No</th>
    <th>Vehicle Brand</th>
    <th>Vehicle Model</th>
    <th>Customer Name</th>
    <th>Service Due Date</th>
    <th>Service Status</th>
    </tr>
	<c:forEach var="serviceRecord" items="${serviceRecords}">
		<tr>
			<td>${serviceRecord.getId()}</td>
			<td>${serviceRecord.getSoldVehicle().getRegNo()} </td>
            <td>${serviceRecord.getSoldVehicle().getVehicle().getBrand()}</td>
            <td>${serviceRecord.getSoldVehicle().getVehicle().getModel()}</td>
            <td>${serviceRecord.getSoldVehicle().getCustomer().getName()}</td>
            <td>${serviceRecord.getDueDate()}</td>
            <td>${serviceRecord.getServiceStatus()}</td>
            <td>
            <c:if test='${serviceRecord.getServiceStatus().equals("unserviced")}'>
            <form method="post" action="/manageService">
            <input type="hidden" name="serviceId" value="${serviceRecord.getId()}">
            <input type="hidden" name="goal" value="toServicing">
            <input type="submit" value="mark servicing">
            </form>
            </c:if>
            <c:if test='${serviceRecord.getServiceStatus().equals("servicing")}'>
            <button onclick="markDone(${serviceRecord.getId()});">Mark Done</button>
            </c:if>
            </td>
		</tr>

	</c:forEach>
	</table>
	
	<div id="dialog" style="display: none;">
		<p>enter the bill of materials</p>
		<form method="post" action="/manageService" id="endServiceForm">
			<div id="mats_container">
			   <div>
				   <input name="1_partName" placeholder="Part Name">
				   <input name="1_partQuantity" placeholder="quantity" type="number">
				   <input name="1_partPrice" placeholder="price">
				</div>
			</div>
			<input type="hidden" name="goal" value="toServiced">
		   <input type="button" value="Add More" onclick="addServicePart();">
		   <input type="submit" value="Mark Done">
		</form>
	</div>
	
    <script>
    
    function markDone(serviceId) {
    	var endServiceForm = document.getElementById("endServiceForm");
        var serviceIdField = document.createElement("input");
        serviceIdField.name = "serviceId";
        serviceIdField.value = serviceId;
        serviceIdField.type = "hidden";
        endServiceForm.appendChild(serviceIdField);
        var dialog = document.getElementById("dialog");
        dialog.style.display = 'block';
    }
    
    
    function addServicePart() {
        var container = document.getElementById("mats_container");
        var n = container.children.length + 1;
        var entry = document.createElement("div");
        var part_name = document.createElement("input");
        part_name.name = n + "_partName";
        part_name.placeholder = "Part Name";
        var quantity = document.createElement("input")
        quantity.name = n + "_partQuantity";
        quantity.type = "number";
        var price = document.createElement("input");
        price.name = n + "_partPrice";
        price.placeholder = "Price";
        entry.appendChild(part_name);
        entry.appendChild(quantity);
        entry.appendChild(price);
        container.appendChild(entry);
    }
    
    
    </script>

</body>
</html>