package views.styles;

public class FixedColorStyleGuide extends CellStyleGuide{
	public FixedColorStyleGuide()
	{
		super();
	}

	@Override
	public String getColor(int aStateIndex)
	{
		return super.getColors().get(aStateIndex);
	}
}
