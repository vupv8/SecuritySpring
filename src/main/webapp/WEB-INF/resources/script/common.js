/**
 * 
 */
 function sayHello()  {    
  alert("Hello from JavaScript");
}

//confirm delete popup
function confirmDelete(delForm, delUrl) { // <--- changed here
    if (confirm("Are you sure ?")) {
        delForm.action = delUrl;          // <--- changed here
        return true;                      // <--- changed here
    }
    return false;                         // <--- changed here
}
function confirmDelete_bt()
{
  if (confirm("Are you sure you want to delete?"))
      return true;
  else
    return false;
}
