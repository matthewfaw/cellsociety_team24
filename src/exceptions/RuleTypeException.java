package exceptions;

public class RuleTypeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuleTypeException(String message, Object ... values)
	{
		super(String.format(message, values));
	}
}
