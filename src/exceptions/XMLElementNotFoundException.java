package exceptions;

import resources.ResourceBundleHandler;

public class XMLElementNotFoundException extends RuntimeException {
	private static final String ERROR_PATH = "resources/ErrorMessages";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public XMLElementNotFoundException(String message, Object ... values)
	{
		super(String.format(message, values));
	}
}
