$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasks";
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
	// list all tasks
	// read single task
	// add task
	// delete task
	// update task
});
