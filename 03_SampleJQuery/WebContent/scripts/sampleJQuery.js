$(document).ready(function() {
	"use strict"
	// define function:
	function removeByClassName() {
		$(".name-column").remove();
	}
	function removeById() {
		$("#studentsTable").remove();
	}
	function removeByType() {
		$("ol").remove();
	}
	function removeByDescendant() {
		// remove list items children of UL, but not of OL 
		$("ul li").remove();
	}

	// call function:
	// removeByClassName();
	// removeById();
	// removeByType();
	removeByDescendant()
	
	// add new row to the table
	// very basic example
	$("table").append("<tr><td>3</td><td>Georgi</td></tr>");
	
	
});
