<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>



<style>td {width: 100px; text-align: center; } input, button {width: 90px; }</style>

<p>${message}</p>

<form class="new" method="post" action="manageCustomer">
    <input name="name" placeholder="name">
    <input name="location" placeholder="location">
    <input type="hidden" name="goal" value="add">
    <input type="submit" value="Add">
</form>

<table>
<tr>
<th>Customer ID</th>
<th>Name</th>
<th>Location</th>
</tr>
  <c:forEach var="c" items="${customers}">
  <tr id='sr${c.getId()}'>
      <td>${c.getId()}</td>
      <td>${c.getName()}</td>
      <td>${c.getLocation()} </td>
      <td>
        <button onclick='editRecord(${c.getId()}, "${c.getName()}", "${c.getLocation()}")'>edit</button>
      </td>
      <td>
      <form method="post" action="manageCustomer">
      <input type="hidden" name="goal" value="remove">
      <input type="hidden" name="id" value="${c.getId()}">
      <input type="submit" value="Remove">
      </form>
      </td>
      <td></td>
  </tr>
  </c:forEach>
</table>



<script>

function editRecord(id, name, location) {
    var viewRow = document.getElementById('sr' + id);
    var backupHTML = viewRow.innerHTML;
    
    var newHTML = `
	    <td colspan="5">
	    <form method="post" action="manageCustomer">
        <input type="hidden" name="id">
	    <input disabled name="id_">
	    <input name="name"  placeholder="name">
	    <input name="location" placeholder="location">
	    <input type="submit" value="Done">
	    <input type="hidden" name="goal" value="edit">
	    <button onclick="cancelEditRecord()">Cancel</button>
	    </form>
	    </td>
	`;
    
    viewRow.innerHTML = newHTML;

    viewRow.firstElementChild.firstElementChild.id.value= id;
    viewRow.firstElementChild.firstElementChild.id_.value= id;
    viewRow.firstElementChild.firstElementChild.name.value= name;
    viewRow.firstElementChild.firstElementChild.location.value= location;
    
    cancelEditRecord = () => {
        viewRow.innerHTML = backupHTML;
    }

}


</script>