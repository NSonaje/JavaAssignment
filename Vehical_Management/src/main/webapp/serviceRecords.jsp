<style>td {width: 100px; text-align: center; } input, button {width: 90px; }</style>

<p>${message}</p>
<form class="new" action="manageService" method="post">
	<input name="regNo" placeholder="reg no">
    <input name="dueDate" placeholder="due date">
	<input type="hidden" name="goal" value="add">
	<input type="submit" value="Add">
</form>

<table>
<tr>
<th>Record ID</th>
<th>Reg., No</th>
<th>Brand</th>
<th>Model</th>
<th>Customer Name</th>
<th>Due Date</th>
<th>Service Status</th>
</tr>
<c:forEach var="serviceRecord" items="${serviceRecords}">
  <tr id='sr${serviceRecord.getId()}'>
      <td>${serviceRecord.getId()}</td>
      <td>${serviceRecord.getSoldVehicle().getRegNo()} </td>
      <td>${serviceRecord.getSoldVehicle().getVehicle().getBrand()}</td>
      <td>${serviceRecord.getSoldVehicle().getVehicle().getModel()}</td>
      <td>${serviceRecord.getSoldVehicle().getCustomer().getName()}</td>
      <td>${serviceRecord.getDueDate()}</td>
      <td>${serviceRecord.getServiceStatus()}</td>
      <td>
        <button onclick='editRecord(${serviceRecord.getId()}, "${serviceRecord.getSoldVehicle().getRegNo()}", "${serviceRecord.getSoldVehicle().getVehicle().getBrand()}" ,  "${serviceRecord.getSoldVehicle().getVehicle().getModel()}", "${serviceRecord.getSoldVehicle().getCustomer().getName()}",  "${serviceRecord.getDueDate()}", "${serviceRecord.getServiceStatus()}")'>edit</button>
      </td>
      <td><form action="manageService" method="post">
      <input type="hidden" name="id" value="${serviceRecord.getId()}">
      <input type="hidden" name="goal" value="remove" >
      <input type="submit" value="Remove">
      </form></td>
      <td>
      <c:if test='${serviceRecord.getServiceStatus().equals("serviced")}'>
      <form action="generateBill" method="post">
      <input type="hidden" name="id" value="${serviceRecord.getId()}">
      <input type="submit" value="Get Receipt">
      </form>
      </c:if>
      </td>
  </tr>
</c:forEach>
</table>

<script>

function editRecord(id, regNo, brand, model, customerName, dueDate, serviceStatus) {
    var viewRow = document.getElementById('sr' + id);
    var backupHTML = viewRow.innerHTML;
    
    
    var newHTML = `
        <td colspan="100">
        <form method="post" action="manageService">
        <input disabled name="id_">
        <input type="hidden" name="id">
        <input name="regNo" placeholder="reg no">
        <input name="brand" disabled>
        <input name="model" disabled>
        <input name="customerName" disabled>
        <input name="dueDate" placeholder="due date">
        <input disabled name="serviceStatus">
        <input type="submit" value="Done">
        <input type="hidden" name="goal" value="edit">
        <button onclick="cancelEditRecord()">Cancel</button>
        </form>
        </td>
    `;
    
    viewRow.innerHTML = newHTML;

    viewRow.firstElementChild.firstElementChild.id.value= id;
    viewRow.firstElementChild.firstElementChild.id_.value= id;
    viewRow.firstElementChild.firstElementChild.regNo.value= regNo;
    viewRow.firstElementChild.firstElementChild.brand.value= brand;
    viewRow.firstElementChild.firstElementChild.model.value= model;
    viewRow.firstElementChild.firstElementChild.customerName.value= customerName;
    viewRow.firstElementChild.firstElementChild.serviceStatus.value= serviceStatus;
    viewRow.firstElementChild.firstElementChild.dueDate.value= dueDate;
    
    cancelEditRecord = () => {
        viewRow.innerHTML = backupHTML;
    }

}


</script>