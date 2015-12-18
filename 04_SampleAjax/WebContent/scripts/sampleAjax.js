$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasks";
	function taskEndpoint(taskId) {
		return ENDPOINT + "/" + taskId;
	}

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
	var task = {
		title: "hello",
		description: "some text"
	};
	$.ajax(ENDPOINT, {
		method: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(task),
		dataType: "json"
	}).then(function(response) {
		console.log(response);
	});

	// delete task
	// update task
});
