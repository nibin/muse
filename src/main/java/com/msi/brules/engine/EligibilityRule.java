package com.msi.brules.engine;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.msi.brules.parser.ThenParser;
import com.msi.brules.parser.WhenParser;

public class EligibilityRule implements WhenParser, ThenParser {

	private EligibilityRuleBase ruleBase;
	private String description;
	private Predicate condition;
	private Consumer action;

	public EligibilityRule(EligibilityRuleBase ruleBase, String description) {

		super();
		this.ruleBase = ruleBase;
		this.description = description;
	}

	public boolean canActivate(Object businessObject) {

		return condition.test(businessObject);
	}

	public void fire(Object businessObject) {
		
		action.accept(businessObject);
	}

	@Override
	public ThenParser when(Predicate condition) {
		this.condition = condition;
		return this;
	}

	@Override
	public void then(Consumer action) {
		this.action = action;
		ruleBase.addRule(this);
		
	}

}
