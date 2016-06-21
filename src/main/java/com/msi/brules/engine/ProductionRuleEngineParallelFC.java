package com.msi.brules.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.msi.brules.actors.RuleActivatorActor;

public class ProductionRuleEngineParallelFC {

	private EligibilityRuleBase eligibilityRuleBase;
	private Object businessObject;

	private List<EligibilityRule> availableRules = new ArrayList<>();
	private List<EligibilityRule> agendaRules = new ArrayList<>();
	private List<EligibilityRule> firedLog = new ArrayList<>();

	public ProductionRuleEngineParallelFC(
			EligibilityRuleBase eligibilityRuleBase, Object businessObject) {

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

		RuleActivatorActor masterActor = new RuleActivatorActor(5,
				this.availableRules, this.businessObject);
		masterActor.start();
		masterActor.waitUntilDone();
		masterActor.stop();

		agendaRules.addAll(masterActor.getAgendaRules());

		for (EligibilityRule rule : agendaRules) {
			availableRules.remove(rule);
		}

	}

}
