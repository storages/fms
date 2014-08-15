
$(function(){
$("a[edit-emp]").click(function(){
	var id= $(this).attr("edit-emp");
	toMain(Global+"/empl_editEmployee.action?emid="+id);
});
});