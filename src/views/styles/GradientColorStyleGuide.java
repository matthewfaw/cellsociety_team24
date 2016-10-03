package views.styles;

public class GradientColorStyleGuide extends CellStyleGuide {
	
	public GradientColorStyleGuide()
	{
		super();
	}

	@Override
	public String getColor(int aStateIndex) {
		boolean indexIsPositive = aStateIndex >= 0;
		int sign = indexIsPositive ? 1 : -1;
		int magnitude = Math.abs(aStateIndex);
		
		String baseColor = super.getColors().get(sign);
		String magnitudeColor = String.format("%02X", magnitude);
		
		return appendColors(baseColor, magnitudeColor);
	}
	/**
	 * 
	 * @param aBaseColor is the default cell color specified in XML
	 * @param aMagnitudeColor is the color Blue value to update
	 * @return
	 */
	private String appendColors(String aBaseColor, String aMagnitudeColor)
	{
		int endIndex = aBaseColor.length() - 1;
		aBaseColor = aBaseColor.substring(0, endIndex - 1);
		return aBaseColor + aMagnitudeColor;
	}
//	public static void main(String[] args)
//	{
//		String hex = String.format("0x%02X", 15); 
//		System.out.println(hex);
//	}
//
}
