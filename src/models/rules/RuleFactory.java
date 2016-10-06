package models.rules;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * The purpose of this class is to construct a Rule object for the Model layer without
 * exposing which subclass of Rule is being constructed.
 * 
 * This class will fail if there is no file at resources/State.properties from which to read,
 * or if one of the requested string resources is missing, or if the defaults map or state id
 * map has been incorrectly constructed.
 * 
 * This class's primary dependencies are on the ResourceBundle class for pulling the proper ids from
 * the defaults map, and on the RuleType enum, which specifies the possible rules from which to choose.
 * 
 * Assuming we have a valid RuleType aRule, a valid DefaultsMap aDefaults, and a valid StateIds map stateIds,
 * the class may be used as follows:
 * RuleFactory rf = new RuleFactory();
 * Rule rule = rf.createRule(aRule, aDefaults, stateIds);
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
	
	/**
	 * A method to create a rule based on the requested rule type and additional configuration information
	 * The method returns null if the requested rule type is not listed in the switch case
	 * @param aRuleType
	 * @param aDefaultsMap
	 * @param aStateIdsMap
	 * @return the Rule subclass corresponding to aRuleType
	 */
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
		case SlimeMold:
			double evapRate = aDefaultsMap.get(getResource("evapRate"));
			double diffusionRate = aDefaultsMap.get(getResource("diffusionRate"));
			double sniffAngle = aDefaultsMap.get(getResource("sniffAngle"));
			double wiggleAngle = aDefaultsMap.get(getResource("wiggleAngle"));
			double sniffThreshold = aDefaultsMap.get(getResource("sniffThreshold"));
			double wiggleBias = aDefaultsMap.get(getResource("wiggleBias"));
			double dropRate = aDefaultsMap.get(getResource("dropRate"));
			double maxChem = aDefaultsMap.get(getResource("maxChem"));
			
//			String[] cellAttribs = aDefaultsMap.keySet().toArray(new String[2]);

			rule = new RuleSlime(evapRate,
						diffusionRate,
						sniffAngle,
						wiggleAngle,
						sniffThreshold,
						wiggleBias,
						dropRate,
						maxChem,
//						cellAttribs,
						aStateIdsMap);
			break;
		default:
			rule = null;
			break;
		}
		return rule;
	}
	
	/**
	 * A method to make accessing the resource file slightly easier
	 * This method fails when the resource does not exist
	 * @param aResourceToObtain
	 * @return string associated with aResourceToObtain
	 */
	@Deprecated
	private String getResource(String aResourceToObtain)
	{
		return fSimulationPropertiesRB.getString(aResourceToObtain);
	}
}
