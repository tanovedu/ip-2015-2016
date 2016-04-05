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

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.entities.SecurityRole;
import org.elsysbg.ip.todo.entities.Task;
import org.elsysbg.ip.todo.services.AuthenticationService;
import org.elsysbg.ip.todo.services.MembersService;
import org.elsysbg.ip.todo.services.TasksService;
import org.secnod.shiro.jaxrs.Auth;

@Path("/members")
public class MembersRest {
	private final MembersService membersService;
	private final TasksService tasksService;
	private final AuthenticationService authenticationService;

	@Inject
	public MembersRest(MembersService membersService,
		TasksService tasksService, AuthenticationService authenticationService) {
		this.membersService = membersService;
		this.tasksService = tasksService;
		this.authenticationService = authenticationService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequiresGuest
	public Member createMember(Member member) {
		member.setRole(SecurityRole.USER);
		return membersService.createMember(member);
	}
	
	@GET
	@Path("/{memberId}/tasks")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequiresAuthentication
	public List<Task> getMemberTasks(
		@Auth Subject subject,
		@PathParam("memberId") long memberId) {
		final Member author = membersService.getMember(memberId);
		// TODO see how to create annotations to handle this case without writing code here:
		// org.secnod.shiro.jersey.AuthorizationFilter in shiro-jersey-0.2.0
		final Member currentlyLoggedInMember =
			authenticationService.getCurrentlyLoggedInMember(subject);
		if (memberId != currentlyLoggedInMember.getId()) {
			throw new UnauthorizedException("User have permissions to view "
				+ "tasks for member " + currentlyLoggedInMember.getUsername() + " only");
		}
		return tasksService.getTasksByAuthor(author);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RequiresPermissions("members:list")
	// or @RequiresRoles("ADMINISTRATOR")
	public List<Member> getMembers() {
		return membersService.getMembers();
	}
}
