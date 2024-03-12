package hgc.demojwt.User.Responses;

import hgc.demojwt.User.Entitys.Role;

public class UserRoleResponse {

	Role rol = Role.ROLE_USER;

	public UserRoleResponse() {
		super();
	}

	public UserRoleResponse(Role rol) {
		super();
		this.rol = rol;
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}
	
	
	
}
