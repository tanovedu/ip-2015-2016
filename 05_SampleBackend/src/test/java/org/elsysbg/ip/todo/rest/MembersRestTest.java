package org.elsysbg.ip.todo.rest;

import static org.testng.Assert.assertEquals;

import org.elsysbg.ip.todo.entities.Member;
import org.elsysbg.ip.todo.entities.SecurityRole;
import org.elsysbg.ip.todo.services.MembersService;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MembersRestTest {

	private MembersService membersService;
	private MembersRest membersRest;

	@BeforeMethod
	public void createService() {
		membersService = Mockito.mock(MembersService.class);
		// TODO create other services when needed:
		membersRest = new MembersRest(membersService, null, null);
	}

	@Test
	public void testCreateMemberSetRole() {
		final Member member = new Member();
		membersRest.createMember(member);
		Mockito.verify(membersService, Mockito.times(1)).
			createMember(member);
		assertEquals(member.getRole(), SecurityRole.USER);
	}

}
