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

import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.entities.Task;
import org.elsysbg.ip.todo.services.MembersService;
import org.elsysbg.ip.todo.services.TasksService;

@Path("/members")
public class MembersRest {
	private final MembersService membersService;
	private final TasksService tasksService;

	@Inject
	public MembersRest(MembersService membersService,
		TasksService tasksService) {
		this.membersService = membersService;
		this.tasksService = tasksService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Member createMember(Member member) {
		return membersService.createMember(member);
	}
	
	@GET
	@Path("/{memberId}/tasks")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> getMemberTasks(
		@PathParam("memberId") long memberId) {
		final Member author = membersService.getMember(memberId);
		return tasksService.getTasksByAuthor(author);
	}
}
