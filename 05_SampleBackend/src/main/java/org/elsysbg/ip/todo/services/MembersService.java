package org.elsysbg.ip.todo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.elsysbg.ip.todo.entities.Member;

@Singleton
public class MembersService {
	private final EntityManagerService entityManagerService;
	private final AuthenticationService authenticationService;
	
	@Inject
	public MembersService(EntityManagerService entityManagerService,
		AuthenticationService authenticationService) {
		this.entityManagerService = entityManagerService;
		this.authenticationService = authenticationService;
	}

	public Member createMember(Member member) {
		member.setPassword(authenticationService.encryptPassword(member.getPassword()));
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(member);
			em.getTransaction().commit();
			
			return member;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	public List<Member> getMembers() {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<Member> query =
				em.createNamedQuery(Member.QUERY_ALL, Member.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Member getMember(long memberId) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final Member result = em.find(Member.class, memberId);
			if (result == null) {
				throw new IllegalArgumentException(
						"No member with id: " + memberId);
			}
			return result;
		} finally {
			em.close();
		}
	}

	public Member getMemberByUsername(String username) {
		final EntityManager em = entityManagerService.createEntityManager();
		try {
			final TypedQuery<Member> query =
					em.createNamedQuery(Member.QUERY_BY_USERNAME, Member.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
	
}
