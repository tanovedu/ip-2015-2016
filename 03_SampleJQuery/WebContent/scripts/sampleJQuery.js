$(document).ready(function() {
	"use strict"
	// define function:
	function removeByClassName() {
		$(".name-column").remove();
	}
	
	// call function:
	removeByClassName();
	
	// add new row to the table
	// very basic example
	$("table").append("<tr><td>3</td><td>Georgi</td></tr>");
	
	
});
