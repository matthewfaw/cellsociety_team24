package exceptions;

public class ConfigurationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigurationException(String message, Object ... values)
	{
		super(String.format(message, values));
	}
}
