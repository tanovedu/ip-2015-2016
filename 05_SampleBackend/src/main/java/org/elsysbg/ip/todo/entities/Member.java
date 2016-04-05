package org.elsysbg.ip.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

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

	@Column(nullable = false)
	private String password;
	
	// store enum value as string (value name), not as integer (position in the enum)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SecurityRole role;

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

	// password is not returned in REST
	// but can be set
	@XmlTransient
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	// password is not returned in REST
	// but can be set
	@XmlElement
	@JsonInclude
	@JsonSetter
	public void setPassword(String password) {
		// for security reasons user should not be able to update the password
		// without providing the old one in real world projects
		this.password = password;
	}

	public SecurityRole getRole() {
		return role;
	}

	public void setRole(SecurityRole role) {
		this.role = role;
	}
}
