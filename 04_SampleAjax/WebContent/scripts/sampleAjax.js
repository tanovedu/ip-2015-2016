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
	$.ajax(taskEndpoint(1), {
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
	var createPromise = $.ajax(ENDPOINT, {
		method: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(task),
		dataType: "json"
	}).then(function(response) {
		console.log(response);
		return response;
	});
	// update task
	$.ajax(taskEndpoint(2), {
		method: "PUT",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({
			title: "updated",
			description: "new description"
		}),
		dataType: "json"
	}).then(function(response) {
		console.log(response);
	});

	// delete task
	createPromise.then(function(response) {
		$.ajax(taskEndpoint(response.id), {
			method: "DELETE"
		});
	});
});
