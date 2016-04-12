package org.elsysbg.ip.todo.test.clients;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.test.dto.CredentialsDto;
import org.glassfish.jersey.test.BaseIntegrationTest;

public class AuthenticationClient extends BaseClient {

	public AuthenticationClient(BaseIntegrationTest jerseyTest) {
		super(jerseyTest, "authentication");
	}
	
	public Member login(String username, String password) {
		final CredentialsDto credentials = new CredentialsDto();
		credentials.setUsername(username);
		credentials.setPassword(password);
		return createTarget().request().post(Entity.entity(credentials, MediaType.APPLICATION_JSON), Member.class);
	}
}
