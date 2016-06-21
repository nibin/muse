package com.msi.brules.dsl

import com.msi.brules.builders.ProductionRuleBuilder
import com.msi.brules.engine.EligibilityRule;
import com.msi.brules.engine.EligibilityRuleBase;

abstract class RuleDslBuilder extends Script {

	String sayHelloNow() {
		return "hello from common func"
	}

	def rule(String description) {		
		return new EligibilityRule(this.binding.ruleBase, description)
	}
}
