package br.com.autoservice.util;

public class MessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5758745602547706130L;
	private String msg;

	public MessageException(String msg) {
		super(msg);
		this.msg = msg;	
	}

	public String getMessage() {
		return msg;
	}
}
