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
//confirm delete popup
function confirmDelete(delForm, delUrl) { // <--- changed here
    if (confirm("Are you sure ?")) {
        delForm.action = delUrl;          // <--- changed here
        return true;                      // <--- changed here
    }
    return false;                         // <--- changed here
}
function loadEdit(id,content) {
           
            document.getElementById("id").value = id;
            document.getElementById("content").value = content;
        }