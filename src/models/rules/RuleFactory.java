package models.rules;

import java.util.Map;
import java.util.ResourceBundle;

public class RuleFactory {
	private static final String RESOURCE_PATH = "resources/Simulation";

	private ResourceBundle fSimulationPropertiesRB;

	public RuleFactory()
	{
		fSimulationPropertiesRB = ResourceBundle.getBundle(RESOURCE_PATH);
	}
	
	//XXX: Check if this type of switch statement occurs in other places, and if it does, refactor
	public Rule createRule(RuleType aRuleType, Map<String, Double> aPropertyMap)
	{
		Rule rule;
		switch (aRuleType) {
		case Fire:
			double pCatchFire = aPropertyMap.get(getResource("ProbCatchingFire"));
			rule = new RuleFire(pCatchFire);
			break;
		case Fish:
			int fishReproductionTime = (int)((double) aPropertyMap.get(getResource("FishReproductionTime")));
			int sharkReproductionTime = (int)((double)aPropertyMap.get(getResource("SharkReproductionTime")));
			int fishEnergy = (int)((double)aPropertyMap.get(getResource("EnergyOfEatingAFish")));
			rule = new RuleFish(fishReproductionTime, sharkReproductionTime, fishEnergy);
			break;
		case Life:
			rule = new RuleLife();
			break;
		case Segregation:
			double pSegregation = aPropertyMap.get(getResource("SegregationPercentage"));
			rule = new RuleSegregation(pSegregation);
			break;
		default:
			rule = null;
			break;
		}
		return rule;
	}
	
	private String getResource(String aResourceToObtain)
	{
		return fSimulationPropertiesRB.getString(aResourceToObtain);
	}
}
