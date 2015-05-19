package com.fms.core.entity;

import com.fms.base.entity.BaseEntity;

public class UserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private AclUser user;
	private Role role;

	public AclUser getUser() {
		return user;
	}

	public void setUser(AclUser user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
