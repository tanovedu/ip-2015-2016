package org.elsysbg.ip.todo.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.subject.Subject;
import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.services.AuthenticationService;
import org.secnod.shiro.jaxrs.Auth;

@Path("/authentication")
public class AuthenticationRest {

	private final AuthenticationService authenticationService;

	@Inject
	public AuthenticationRest(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Member login(@Auth Subject subject, Member member) {
		authenticationService.login(subject,
				member.getUsername(), member.getPassword());
		return authenticationService.getCurrentlyLoggedInMember();
	}
}
