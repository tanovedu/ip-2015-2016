$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasksaa";

	// global error handler:
	$(document).ajaxError(function() {
		console.log("error: ", arguments);
		alert("Error!");
	});
	// list all tasks
	$.ajax(ENDPOINT, {
		method: "GET",
		// to add parameters to URL:
		data: {
			title: "hello"
		},
		dataType: "json"
	}).then(function(response) {
		console.log(response);
	});
	// read single task
	// add task
	// delete task
	// update task
});
