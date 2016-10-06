package exceptions;

/**
 * The purpose of this class is to provide a means of explicity specifying
 * an invalid rule type has been specified.
 * 
 * This class will fail if the format string is incompatible with the values to be inserted.
 * 
 * A sample use case:
 * ruleType = "aNonexistentRule";
 * throw new RuleTypeException("The rule %s is invalid!", ruleType);
 * @author matthewfaw
 *
 */
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
