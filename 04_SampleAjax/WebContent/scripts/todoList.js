$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasks";
	function taskEndpoint(taskId) {
		return ENDPOINT + "/" + taskId;
	}

	function showPanel(panelName) {
		var ALL_PANELS = ["emptyPanel", "readPanel", "updatePanel", "createPanel"];
		_.forEach(ALL_PANELS, function(nextValue) {
			$("#"+nextValue).hide();
		});
		$("#"+panelName).show();
		$("#tasksList li.active").removeClass("active");
	}

	function listTasks() {
		return $.ajax(ENDPOINT, {
			method: "GET",
			dataType: "json"
		});
	}
	function readTask(taskId) {
		return $.ajax(taskEndpoint(taskId), {
			method: "GET",
			dataType: "json"
		});
	}
	function deleteTask(taskId) {
		return $.ajax(taskEndpoint(taskId), {
			method: "DELETE",
			dataType: "json"
		});
	}

	function createTask(task) {
		return $.ajax(ENDPOINT, {
			method: "POST",
			dataType: "json",
			data: JSON.stringify(task),
			contentType: "application/json; charset=utf-8"
		});
	}
	function updateTask(task) {
		return $.ajax(taskEndpoint(task.id), {
			method: "PUT",
			dataType: "json",
			data: JSON.stringify(task),
			contentType: "application/json; charset=utf-8"
		});
	}
	function highlightTaskInTaskList(task) {
		$("#tasksList li[data-task-id='"+task.id+"']").addClass("active");
	}
	function showTaskView(task) {
		$("#readPanel .task-title").text(task.title);
		$("#readPanel .task-description").text(task.description);
		$("#readPanel .task-action-remove").attr("data-task-id", task.id);
		$("#readPanel .task-action-ok").attr("data-task-id", task.id);
		showPanel("readPanel");
		highlightTaskInTaskList(task);
	}
	function showTaskUpdate(task) {
		$("#updatePanel [name='title']").val(task.title);
		$("#updatePanel [name='description']").val(task.description);

		$("#updatePanel .task-action-ok").attr("data-task-id", task.id);
		showPanel("updatePanel");
		highlightTaskInTaskList(task);
	}

	function reloadTasks() {
		return listTasks().then(function(response) {
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
	}
	function attachHandlers() {
		$(document).on("click", "#tasksList [data-task-id]", function() {
			var taskId = $(this).attr("data-task-id");
			readTask(taskId).then(showTaskView);
		});
		$(".task-action-cancel").click(function() {
			showPanel("emptyPanel");
		});
		$("#addTaskButton").click(function() {
			$("#createPanel [name='title']").val("");
			$("#createPanel [name='description']").val("");
			showPanel("createPanel");
		});
		$("#createPanel .task-action-ok").click(function() {
			var task = {
				title: $("#createPanel [name='title']").val(),
				description: $("#createPanel [name='description']").val()
			};
			createTask(task).then(function(response) {
				reloadTasks().then(function() {
					showTaskView(response);
				});
			});
		});
		$(".task-action-remove").click(function() {
			var taskId = $(this).attr("data-task-id");
			deleteTask(taskId).then(function() {
				reloadTasks();
				showPanel("emptyPanel");
			});
		});
		$("#readPanel .task-action-ok").click(function() {
			var taskId = $(this).attr("data-task-id");
			readTask(taskId).then(showTaskUpdate);
		});
		$("#updatePanel .task-action-ok").click(function() {
			var task = {
				title: $("#updatePanel [name='title']").val(),
				description: $("#updatePanel [name='description']").val(),
				id: $(this).attr("data-task-id")
			};
			updateTask(task).then(function(response) {
				reloadTasks().then(function() {
					showTaskView(response);
				});
			});
		});
	}
	attachHandlers();
	reloadTasks();
});
