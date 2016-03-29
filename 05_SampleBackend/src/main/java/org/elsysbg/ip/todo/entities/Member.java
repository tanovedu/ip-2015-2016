package org.elsysbg.ip.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name=Member.QUERY_ALL,
		query = "SELECT m from Member m"),
	@NamedQuery(name=Member.QUERY_BY_USERNAME,
		query = "SELECT m from Member m WHERE m.username=:username")

})
public class Member {
	public static final String QUERY_ALL = "membersAll";
	public static final String QUERY_BY_USERNAME = "membersByUsername";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@Column(nullable = false, unique = true)
	private String username;

	// TODO password should be hashed/salted in real world projects
	@Column(nullable = false)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
