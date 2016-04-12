package org.elsysbg.ip.todo.services;

import static org.testng.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.elsysbg.ip.todo.entities.Member;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MembersServiceTest {
	private static final String PASSWORD_PLAIN_TEXT = "plaintextpassword";
	private static final String PASSWORD_HASHED = "hashedpassword";
	
	private MembersService membersService;
	private EntityManagerService entityManagerService;
	private AuthenticationService authenticationService;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	@BeforeMethod
	public void createService() {
		entityManagerService = Mockito.mock(EntityManagerService.class);
		entityManager = Mockito.mock(EntityManager.class);
		Mockito.when(entityManagerService.createEntityManager()).
			thenReturn(entityManager);
		
		transaction = Mockito.mock(EntityTransaction.class);
		
		Mockito.when(entityManager.getTransaction()).
			thenReturn(transaction);
	
		authenticationService = Mockito.mock(AuthenticationService.class);

		membersService = new MembersService(entityManagerService, authenticationService);
	}

	@Test
	public void testCreateMemberEncryptPassword() {
		final Member member = new Member();
		member.setPassword(PASSWORD_PLAIN_TEXT);
		
		Mockito.when(authenticationService.encryptPassword(
			PASSWORD_PLAIN_TEXT)).thenReturn(PASSWORD_HASHED);
		
		membersService.createMember(member);
		
		Mockito.verify(authenticationService, Mockito.times(1)).
			encryptPassword(PASSWORD_PLAIN_TEXT);
		
		assertEquals(member.getPassword(), PASSWORD_HASHED);

		Mockito.verify(entityManager, Mockito.times(1)).
			persist(member);
	}
}
