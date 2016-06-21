package com.msi.brules.builders;

import java.util.function.Predicate;

import com.msi.brules.domain.Person;
import com.msi.brules.engine.SimpleValidationEngine;
import com.msi.brules.parser.WithParser;
import com.msi.brules.validation.impl.ExpressionValidationRule;

public class ValidationRuleBuilder implements WithParser {

	private String description;
	private SimpleValidationEngine engine;

	public ValidationRuleBuilder(String description, SimpleValidationEngine engine) {

		super();
		this.engine = engine;
		this.description = description;
	}

	@Override
	public void with(Predicate<Person> condition) {
		engine.addRule(new ExpressionValidationRule(condition, description));
		
	}

}
