package com.coderscampus.servicetally.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_type")
public class UsersType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userTypeId;

	private String typeName;

	@OneToMany(targetEntity = Users.class, mappedBy = "userTypeId", cascade = CascadeType.ALL)
	private List<Users> users;

	public UsersType() {
	}

	public UsersType(int userTypeId, String typeName, List<Users> users) {
		this.userTypeId = userTypeId;
		this.typeName = typeName;
		this.users = users;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UsersType [userTypeId=" + userTypeId + ", typeName=" + typeName + "]";
	}

	
	
	
}
