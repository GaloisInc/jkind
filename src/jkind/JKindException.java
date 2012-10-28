package jkind;

public class JKindException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JKindException(String message) {
		super(message);
	}
	
	public JKindException(String message, Exception e) {
		super(message, e);
	}
}