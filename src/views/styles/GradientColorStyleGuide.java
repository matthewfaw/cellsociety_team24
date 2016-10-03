package views.styles;

public class GradientColorStyleGuide extends CellStyleGuide {
	
	public GradientColorStyleGuide()
	{
		super();
	}

	@Override
	public String getColor(int aStateIndex) {
		boolean indexIsPositive = aStateIndex >= 0;
		int magnitude = indexIsPositive ? 1 : -1;
		
		String aBaseColor = super.getColors().get(magnitude);

		//XXX: change this
		throw new RuntimeException("You should change this");
//		return aBaseColor;
	}

}
