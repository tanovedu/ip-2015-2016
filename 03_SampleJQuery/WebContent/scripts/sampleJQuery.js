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
	
	// listen for event of type mousemove on selected element
	$("#removeMountain").on("mousemove", function() {
		console.log("mouse moved", arguments);
	});
	// listen for event of type click on selected element
	$("#removeMountain").click(function() {
		$("ul li:first-child").remove();
	});
	
	var nextFreeMountainId = 1;
	$("#addMountain").click(function() {
		var newMountainElement = $("<li />");
		var newMountainName = $("#mountainNameInput").val();
		// in jQuery it is common to use func() for getter
		// and func(value) for setter, e.g. val() and text()
		$("#mountainNameInput").val("");
		newMountainElement.text(newMountainName);
		newMountainElement.attr("id",
			"mountain"+(nextFreeMountainId++));
		$("ul").append(newMountainElement);
	});
	// this works only for already created elements
	$("ul li").click(function() {
		alert("clicked");
	});
	
	// this works for already created elements and future added elements
	$(document).on("click", "ul li", function() {
		alert($(this).attr("id"));
	});

	$("#hiking").click(function() {
		var allMountains = $("ul li");
		_.forEach(allMountains, function(value) {
			var next = $(value);
			alert(next.text());
		});
	});

});
