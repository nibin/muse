package com.msi.brules.engine;

import java.util.ArrayList;
import java.util.List;

import com.msi.brules.domain.Notification;
import com.msi.brules.domain.Person;

public class SimpleValidationEngine {

	List<ValidationRule> rules = new ArrayList<>();

	public Notification run(Person person) {

		Notification notification = new Notification();
		for (ValidationRule rule : rules) {
			rule.check(notification, person);
		}

		return notification;
	}

	public void addRule(ValidationRule rule) {

		rules.add(rule);
	}

	public List<ValidationRule> getRules() {

		return rules;
	}

}
