<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<h2>Customer</h2>
<table border="1px" cellspacing="0">
<tr><td>name</td><td>${sr.getSoldVehicle().getCustomer().getName()}</td></tr>
<tr><td>location</td><td>${sr.getSoldVehicle().getCustomer().getLocation()}</td></tr>
</table>
<h2>Vehicle</h2>
<table border="1px" cellspacing="0">
<tr><td>reg no</td><td>${sr.getSoldVehicle().getRegNo()}</td></tr>
<tr><td>brand</td><td>${sr.getSoldVehicle().getVehicle().getBrand()}</td></tr>
<tr><td>model</td><td>${sr.getSoldVehicle().getVehicle().getModel()}</td></tr>
</table>
<h2>Bill</h2>
<table border="1px" cellspacing="0">
  <tr>
  <th>Part Name</th>
  <th>Quantity</th>
  <th>Price</th>
  <th>Total</th>
  <c:forEach var="bom" items="${boms}">
  <tr>
  <td>${bom.getName()}</td>
  <td>${bom.getQuantity()}</td>
  <td>${bom.getPrice()}</td>
  <td>${bom.getQuantity() * bom.getPrice()}<td>
  <tr>
  </c:forEach>
  </table>