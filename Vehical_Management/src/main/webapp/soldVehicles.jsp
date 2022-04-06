<style>td {width: 100px; text-align: center; } input, button {width: 90px; }</style>
<p>${message}</p>
<form class="new" action="manageSoldVehicle" method="post">
    <input name="regNo" placeholder="reg no">
    <input name="customerId" placeholder="customer id">
    <input name="vehicleId" placeholder="vehicle id">
    <input type="hidden" name="goal" value="add">
    <input type="submit" value="Add">
</form>

<table>
<tr>
<th>Reg., No</th>
<th>Customer ID</th>
<th>Name</th>
<th>Location</th>
<th>Vehicle ID</th>
<th>Brand</th>
<th>Model</th>
<th>Price</th>
</tr>
  <c:forEach var="sv" items="${soldVehicles}">
  <tr id='sv${sv.getRegNo()}'>
      <td>${sv.getRegNo()}</td>
      <td>${sv.getCustomer().getId()}</td>
      <td>${sv.getCustomer().getName()}</td>
      <td>${sv.getCustomer().getLocation()}</td>
      <td>${sv.getVehicle().getId()}</td>
      <td>${sv.getVehicle().getBrand()}</td>
      <td>${sv.getVehicle().getModel()}</td>
      <td>${sv.getVehicle().getPrice()}</td>

      <td>
        <button onclick='editRecord(${sv.getRegNo()}, ${sv.getCustomer().getId()}, "${sv.getCustomer().getName()}", "${sv.getCustomer().getLocation()}", ${sv.getVehicle().getId()}, "${sv.getVehicle().getBrand()}",  "${sv.getVehicle().getModel()}", ${sv.getVehicle().getPrice()})'>edit</button>
      </td>
      <td>
      <form method="post" action="manageSoldVehicle">
      <input type="hidden" name="goal" value="remove">
      <input type="hidden" name="regNo" value="${sv.getRegNo()}">
      <input type="submit" value="Remove">
      </form>
      </td>
  </tr>
  </c:forEach>
</table>


<script>

function editRecord(regNo, customerId, customerName, location, vehicleId, brand, model, price) {
    var viewRow = document.getElementById('sv' + regNo);
    var backupHTML = viewRow.innerHTML;
    
    var newHTML = `
        <td colspan="10">
        <form method="post" action="manageSoldVehicle">
        <input name="regNo">
        <input name="customerId"  placeholder="customer ID">
        <input name="customerName" disabled>
        <input name="location" disabled>
        <input name="vehicleId" placeholder="vehicle ID">
        <input name="brand" disabled>
        <input name="model" disabled>
        <input name="price" disabled>
        <input type="submit" value="Done">
        <input type="hidden" name="goal" value="edit">
        <button onclick="cancelEditRecord()">Cancel</button>
        </form>
        </td>
    `;
    
    viewRow.innerHTML = newHTML;

    viewRow.firstElementChild.firstElementChild.regNo.value = regNo;
    viewRow.firstElementChild.firstElementChild.customerId.value = customerId;
    viewRow.firstElementChild.firstElementChild.customerName.value = customerName;
    viewRow.firstElementChild.firstElementChild.location.value = location;
    viewRow.firstElementChild.firstElementChild.vehicleId.value = vehicleId;
    viewRow.firstElementChild.firstElementChild.brand.value = brand;
    viewRow.firstElementChild.firstElementChild.model.value = model;
    viewRow.firstElementChild.firstElementChild.price.value = price;
    
    cancelEditRecord = () => {
        viewRow.innerHTML = backupHTML;
    }

}


</script>