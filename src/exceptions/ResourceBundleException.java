package exceptions;

import java.util.ResourceBundle;

/**
 * The purpose of this class is to provide a variety of ways to throw
 * an exception when a resource bundle has been improperly encountered.
 * It provides a readable way to write error cases in code when accessing a ResourceBundle.
 * 
 * This class may fail if the format string it is passed is not compatible with
 * the parameters with which it is provided
 * 
 * This class extends Runtime Exception
 * 
 * One may throw an error in the following way:
 * ResourceBundle rb = ResourceBundle.getBundle(...)
 * try {
 * 	rb.getString(...);
 * } catch (Exception e) {
 * 	throw new ResourceBundleException(e, "Invalid Resource Bundle Request because %s",aReason);
 * }
 * @author matthewfaw
 *
 */
public class ResourceBundleException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ResourceBundle rb = ResourceBundle.getBundle("");
	
	public ResourceBundleException(String message, Object ... values)
	{
		super(String.format(message, values));
	}

	public ResourceBundleException(Throwable cause, String message, Object ... values)
	{
		super(String.format(message, values), cause);
	}
	
	public ResourceBundleException(Throwable cause, String message)
	{
		super(message, cause);
	}

}
