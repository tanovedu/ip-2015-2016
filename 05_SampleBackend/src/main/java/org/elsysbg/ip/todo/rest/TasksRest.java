package org.elsysbg.ip.todo.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.Task;

@Path("/tasks")
public class TasksRest {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getTasks() {
		final Task task = new Task();
		task.setDescription("description");
		task.setTitle("title");
		task.setId(1);
		
		return Collections.singletonList(task);
	}
}
