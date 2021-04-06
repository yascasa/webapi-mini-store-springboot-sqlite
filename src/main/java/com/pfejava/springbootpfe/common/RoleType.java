package com.pfejava.springbootpfe.common;

public enum RoleType {
	STUDENT(Names.ADMIN), TEACHER(Names.USER);

	public class Names {
		public static final String ADMIN = "ADMIN";
		public static final String USER = "USER";
	}

	private final String label;

	private RoleType(String label) {
		this.label = label;
	}

	public String toString() {
		return this.label;
	}
}
