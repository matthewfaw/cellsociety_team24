package resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import exceptions.ResourceBundleException;

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
