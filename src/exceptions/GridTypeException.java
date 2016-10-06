package exceptions;

/**
 * The purpose of this class is to provide a simple way to allow an exception
 * to be thrown when the specified grid type is invalid.
 * 
 * This class may be used in the following way:
 * String aGridType = "circular";
 * throw new GridTypeException("Invalid grid type %s", aGridType);
 * 
 * This class will throw an IllegalFormatException if the format string and the values
 * are incompatible
 * @author matthewfaw
 *
 */
public class GridTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GridTypeException(String message, Object ... values)
	{
		super(String.format(message, values));
	}
}
