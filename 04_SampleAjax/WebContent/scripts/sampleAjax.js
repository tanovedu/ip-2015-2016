$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasks";
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
	$.ajax(ENDPOINT + "/" + 1, {
		method: "GET",
		dataType: "json"
	}).then(function(response) {
		console.log(response);
	});

	// add task
	// delete task
	// update task
});
