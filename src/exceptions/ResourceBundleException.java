package exceptions;

public class ResourceBundleException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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

//	public ResourceBundleException(Throwable exception)
//	{
//		super(exception);
//	}
//	
//	public ResourceBundleException(String aCause, String aFileUsed)
//	{
//		
//	}
}
