package org.elsysbg.ip.todo.test.clients;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.test.dto.CredentialsDto;
import org.glassfish.jersey.test.BaseIntegrationTest;

public class MembersClient extends BaseClient {

	public MembersClient(BaseIntegrationTest jerseyTest) {
		super(jerseyTest, "members");
	}
	
	public Member createMember(String username, String password) {
		final CredentialsDto credentials = new CredentialsDto();
		credentials.setUsername(username);
		credentials.setPassword(password);
		return createTarget().request().post(Entity.entity(credentials, MediaType.APPLICATION_JSON), Member.class);
	}
	

}
