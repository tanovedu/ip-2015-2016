package org.elsysbg.ip.todo.rest;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.entities.SecurityRole;
import org.elsysbg.ip.todo.test.clients.MembersClient;
import org.glassfish.jersey.test.BaseIntegrationTest;
import org.testng.annotations.Test;

public class MembersRestITest extends BaseIntegrationTest {

	private static final String USERNAME_DEFAULT = "username1";
	private static final String PASSWORD_DEFAULT = "password1";

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

	@Test(expectedExceptions=javax.ws.rs.ClientErrorException.class, expectedExceptionsMessageRegExp="HTTP 409 Conflict")
	public void testCreateMemberAlreadyExists() {
		final MembersClient membersClient = new MembersClient(this);
		membersClient.createMember(USERNAME_DEFAULT, PASSWORD_DEFAULT);
		membersClient.createMember(USERNAME_DEFAULT, PASSWORD_DEFAULT);
	}
}
