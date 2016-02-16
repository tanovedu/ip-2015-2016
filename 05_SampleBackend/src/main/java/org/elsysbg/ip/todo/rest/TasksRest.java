package org.elsysbg.ip.todo.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.Task;
import org.elsysbg.ip.todo.services.TasksService;

@Path("/tasks")
public class TasksRest {
	private final TasksService tasksService;

	@Inject
	public TasksRest(TasksService tasksService) {
		this.tasksService = tasksService;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getTasks() {
		return tasksService.getTasks();
	}
	
	@GET
	@Path("/{taskId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Task getTask(@PathParam("taskId") long taskId) {
		return tasksService.getTask(taskId);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Task createTask(Task task) {
		return tasksService.createTask(task);
	}
}
