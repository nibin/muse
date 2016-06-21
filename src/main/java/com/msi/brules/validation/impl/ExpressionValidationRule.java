package com.msi.brules.validation.impl;

import java.util.function.Predicate;

import com.msi.brules.domain.Notification;
import com.msi.brules.domain.Person;
import com.msi.brules.engine.ValidationRule;

public class ExpressionValidationRule implements ValidationRule {

	private final Predicate<Person> condition;
	private final String description;

	public ExpressionValidationRule(Predicate<Person> condition,
			String description) {
		super();
		this.condition = condition;
		this.description = description;
	}

	@Override
	public void check(Notification notification, Person p) {
		
		if(!condition.test(p)) {
			notification.addError(String.format("Validation '%s' failed", description));
		}
		

	}

}
