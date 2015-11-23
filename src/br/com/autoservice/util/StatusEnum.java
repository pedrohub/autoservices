package br.com.autoservice.util;

public enum StatusEnum {

	ACTIVATE(true), INACTIVE(false);
	private final boolean value;

	StatusEnum(boolean value) {
		this.value = value;
	}

	boolean getValue() {
		return this.value;
	}
	
	public boolean isActive() {
		  return true;
		}
	
	public boolean isInactive() {
		  return false;
		}

}
