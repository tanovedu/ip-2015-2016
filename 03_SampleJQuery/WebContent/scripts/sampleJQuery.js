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
	function removeByFind(parent) {
		// remove list items children of the parent 
		parent.find("li").remove();
	}

	// call function:
	// removeByClassName();
	// removeById();
	// removeByType();
	// removeByDescendant();
	removeByFind($("ol"));
	// for more selectors - see https://api.jquery.com/category/selectors/
	
	// add new row to the table
	// very basic example
	$("table").append("<tr><td>3</td><td>Georgi</td></tr>");
	
	// listen for event of type click on selected element
	$("#removeMountain").on("click", function() {
		alert("removing mountain");
	});
});
