package com.msi.brules.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A forward chained production rule engine
 * 
 * @author nibin
 *
 */
public class ProductionRuleEngineFC {

	private EligibilityRuleBase eligibilityRuleBase;
	private Object businessObject;

	private List<EligibilityRule> availableRules = new ArrayList<>();
	private List<EligibilityRule> agendaRules = new ArrayList<>();
	private List<EligibilityRule> firedLog = new ArrayList<>();

	public ProductionRuleEngineFC(EligibilityRuleBase eligibilityRuleBase,
			Object businessObject) {

		super();
		this.eligibilityRuleBase = eligibilityRuleBase;
		this.businessObject = businessObject;
		this.availableRules.addAll(eligibilityRuleBase.getRules());
	}

	public void run() {

		activateRules();
		while (agendaRules.size() > 0) {
			fireRulesOnAgenda();
			activateRules();
		}
	}

	private void fireRulesOnAgenda() {

		Iterator<EligibilityRule> it = agendaRules.iterator();
		while (it.hasNext()) {
			fire(it.next());
		}
		agendaRules.clear();
	}

	private void fire(EligibilityRule rule) {
		rule.fire(businessObject);
		firedLog.add(rule);
	}

	private void activateRules() {

		for (EligibilityRule rule : availableRules) {
			if (rule.canActivate(businessObject)) {
				agendaRules.add(rule);
			}
		}

		for (EligibilityRule rule : agendaRules) {
			availableRules.remove(rule);
		}

	}

}
