package models.rules;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * 
 * @author Matthew
 *
 */
public class RuleFactory {
	private static final String RESOURCE_PATH = "resources/State";

	private ResourceBundle fSimulationPropertiesRB;

	public RuleFactory()
	{
		fSimulationPropertiesRB = ResourceBundle.getBundle(RESOURCE_PATH);
	}
	
	//XXX: Check if this type of switch statement occurs in other places, and if it does, refactor
	public Rule createRule(RuleType aRuleType, Map<String, Double> aDefaultsMap, Map<String, Integer> aStateIdsMap)
	{
		Rule rule;
		switch (aRuleType) {
		case Fire:
			double pCatchFire = aDefaultsMap.get(getResource("ProbCatchingFire"));
			rule = new RuleFire(pCatchFire, aStateIdsMap);
			break;
		case Fish:
			int fishReproductionTime = (int)((double) aDefaultsMap.get(getResource("FishReproductionTime")));
			int sharkReproductionTime = (int)((double)aDefaultsMap.get(getResource("SharkReproductionTime")));
			int fishEnergy = (int)((double)aDefaultsMap.get(getResource("EnergyOfEatingAFish")));
			rule = new RuleFish(fishReproductionTime, sharkReproductionTime, fishEnergy, aStateIdsMap);
			break;
		case Life:
			rule = new RuleLife(aStateIdsMap);
			break;
		case Segregation:
			double pSegregation = aDefaultsMap.get(getResource("SegregationPercentage"));
			rule = new RuleSegregation(pSegregation, aStateIdsMap);
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
