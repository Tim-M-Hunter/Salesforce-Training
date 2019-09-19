$(document).ready(function() {
	// W3Schools Code: https://www.w3schools.com/bootstrap/bootstrap_filters.asp
	$("#filterTableInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#tableFields tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	// W3Schools Code: https://www.w3schools.com/bootstrap/bootstrap_popover.asp
	$('[data-toggle="popover"]').popover({html:true});
	
	// W3Schools Code: https://www.w3schools.com/bootstrap/bootstrap_tooltip.asp
	$('[data-toggle="tooltip"]').tooltip();
});