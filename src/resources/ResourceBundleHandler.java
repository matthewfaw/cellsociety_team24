package resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import exceptions.ResourceBundleException;

/**
 * The purpose of this class is to manage accessing the resource bundle.  It
 * restricts the use of ResourceBundle to only getString. Additionally, it throws
 * customized error messages whenever an invalid resource is requested.
 * 
 * This class depends on the resources/ErrorMessages.properties file existing,
 * and on the ResourceBundle class.
 * 
 * One may request a resourece in the following manner:
 * ResourceBundleHandler rbHander = new ResourceBundleHandler("resources/...");
 * rbHandler.getResource("...");
 * 
 * @author matthewfaw
 *
 */
public class ResourceBundleHandler {
	private static final String ERROR_PATH = "resources/ErrorMessages";
	private final ResourceBundle fErrorMessageRB = ResourceBundle.getBundle(ERROR_PATH);

	private String fResourcePath;
	private ResourceBundle fResourceBundle;
	
	public ResourceBundleHandler(String aResourcePath)
	{
		fResourcePath = aResourcePath;
		fResourceBundle = ResourceBundle.getBundle(fResourcePath);
	}
	
	/**
	 * Gets the string from the resource file
	 * This method will error if the requested resource is invalid
	 * @param aResourceToRetrieve
	 * @return
	 */
	public String getResource(String aResourceToRetrieve)
	{
		try {
			return fResourceBundle.getString(aResourceToRetrieve);
		} catch (MissingResourceException e) {
			throw new ResourceBundleException(e, fErrorMessageRB.getString("NoResource"), fResourcePath);
		} catch (NullPointerException e) {
			throw new ResourceBundleException(e, fErrorMessageRB.getString("NullPtr"));
		}
	}

}
