package org.elsysbg.ip.todo.test.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is used to pass password from MembersClient to the server
 * because Member.password is XmlTransient and JsonIgnore
 */
@XmlRootElement
public class CredentialsDto {
	private String username;
	private String password;

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
