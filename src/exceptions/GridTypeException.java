package exceptions;

public class GridTypeException extends RuntimeException {
	public GridTypeException(String message, Object ... values)
	{
		super(String.format(message, values));
	}
}
