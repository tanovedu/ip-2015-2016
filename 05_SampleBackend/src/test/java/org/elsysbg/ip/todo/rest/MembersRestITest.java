package org.elsysbg.ip.todo.rest;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotAuthorizedException;

import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.entities.SecurityRole;
import org.elsysbg.ip.todo.test.clients.AuthenticationClient;
import org.elsysbg.ip.todo.test.clients.MembersClient;
import org.glassfish.jersey.test.BaseIntegrationTest;
import org.testng.annotations.Test;

public class MembersRestITest extends BaseIntegrationTest {

	private static final String USERNAME_DEFAULT = "username1";
	private static final String PASSWORD_DEFAULT = "password1";

	private static final String USERNAME_UNKNOWN = "usernameUnknown";
	private static final String PASSWORD_UNKNOWN = "passwordUnknown";

	@Test
	public void testCreateMemberBasic() {
		final MembersClient membersClient = new MembersClient(this);
		final Member member = membersClient.
			createMember(USERNAME_DEFAULT, PASSWORD_DEFAULT);
		assertEquals(member.getUsername(), USERNAME_DEFAULT);
		assertNull(member.getPassword());
		assertEquals(member.getRole(), SecurityRole.USER);
		assertNotEquals(member.getId(), 0);
	}
	
	@Test(expectedExceptions=ClientErrorException.class, expectedExceptionsMessageRegExp="HTTP 409 Conflict")
	public void testCreateMemberAlreadyExists() {
		final MembersClient membersClient = new MembersClient(this);
		membersClient.createMember(USERNAME_DEFAULT, PASSWORD_DEFAULT);
		membersClient.createMember(USERNAME_DEFAULT, PASSWORD_DEFAULT);
	}

	@Test(expectedExceptions=NotAuthorizedException.class, expectedExceptionsMessageRegExp="HTTP 401 Unauthorized")
	public void testCreateMemberWhileLoggedIn() {
		final MembersClient membersClient = new MembersClient(this);
		final AuthenticationClient authenticationClient = new AuthenticationClient(this);
		membersClient.createMember(USERNAME_DEFAULT, PASSWORD_DEFAULT);
		
		authenticationClient.login(USERNAME_DEFAULT, PASSWORD_DEFAULT);
		
		membersClient.createMember(USERNAME_UNKNOWN, PASSWORD_UNKNOWN);
	}
	
	// TODO Add test covering case when creating member with ADMINISTRATOR role set

}
