package com.msi.brules.engine;

import java.util.ArrayList;
import java.util.List;

public class EligibilityRuleBase {

	private List<EligibilityRule> initialRules = new ArrayList<>();

	public EligibilityRuleBase() {

		super();
	}

	public void addRule(EligibilityRule rule) {
		initialRules.add(rule);
	}

	public List<EligibilityRule> getRules() {

		return this.initialRules;
	}

}
