<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<style>td {width: 100px; text-align: center; } input, button {width: 90px; }</style>

<p>${message}</p>

<form class="new" method="post" action="manageVehicle">
    <input name="brand" placeholder="brand">
    <input name="model" placeholder="model">
    <input name="price" placeholder="price">
    <input type="hidden" name="goal" value="add">
    <input type="submit" value="Add">
</form>

<table>
  <tr>
  <th>Vehicle ID</th>
  <th>Brand</th>
  <th>Model</th>
  <th>Price</th>
  </tr>
  <c:forEach var="v" items="${vehicles}">
  <tr id='v${v.getId()}'>
      <td>${v.getId()}</td>
      <td>${v.getBrand()}</td>
      <td>${v.getModel()} </td>
      <td>${v.getPrice()} </td>
      <td>
        <button onclick='editRecord(${v.getId()}, "${v.getBrand()}", "${v.getModel()}", "${v.getPrice()}")'>edit</button>
      </td>
      <td>
      <form method="post" action="manageVehicle">
      <input type="hidden" name="goal" value="remove">
      <input type="hidden" name="id" value="${v.getId()}">
      <input type="submit" value="Remove">
      </form>
      </td>
      <td></td>
  </tr>
  </c:forEach>
</table>



<script>

function editRecord(id, brand, model, price) {
    var viewRow = document.getElementById('v' + id);
    var backupHTML = viewRow.innerHTML;
    
    var newHTML = `
        <td colspan="500">
        <form method="post" action="manageVehicle">
        <input disabled name="id_">
        <input type="hidden" name="id">
        <input name="brand"  placeholder="brand">
        <input name="model" placeholder="model">
        <input name="price" placeholder="price">
        <input type="submit" value="Done">
        <input type="hidden" name="goal" value="edit">
        <button onclick="cancelEditRecord()">Cancel</button>
        </form>
        </td>
    `;
    
    viewRow.innerHTML = newHTML;

    viewRow.firstElementChild.firstElementChild.id.value= id;
    viewRow.firstElementChild.firstElementChild.id_.value= id;
    viewRow.firstElementChild.firstElementChild.brand.value= brand;
    viewRow.firstElementChild.firstElementChild.model.value= model;
    viewRow.firstElementChild.firstElementChild.price.value= price;
    
    cancelEditRecord = () => {
        viewRow.innerHTML = backupHTML;
    }

}


</script>