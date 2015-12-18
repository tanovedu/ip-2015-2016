$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasksaa";
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
	}, function() {
		console.log("error: ", arguments);
		alert("Error!");
	});
	// read single task
	// add task
	// delete task
	// update task
});
