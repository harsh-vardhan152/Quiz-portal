package com.qizz.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private String authority;
//we will pass the authority as normal or admin
	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
