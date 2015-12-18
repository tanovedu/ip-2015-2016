$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasks";

	$.ajax(ENDPOINT, {
		method: "GET",
		dataType: "json"
	}).then(function(response) {
		function addTaskToList(task) {
			var newItem = $("<li />");
			newItem.text(task.title);
			newItem.addClass("list-group-item");
			newItem.attr("data-task-id", task.id);

			$("#tasksList").append(newItem);
		}
		$("#tasksList").html("");
		_.forEach(response, addTaskToList);
	});
});
