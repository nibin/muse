package com.msi.brules;

import com.msi.brules.domain.Notification;
import com.msi.brules.domain.Person;
import com.msi.brules.engine.SimpleValidationEngine;
import com.msi.brules.validation.impl.ExpressionValidationRule;

public class SimpleRuleExecutor {

	public static void main(String[] args) {

		SimpleValidationEngine engine = new SimpleValidationEngine();
		engine.addRule(new ExpressionValidationRule((Person p) -> p
				.getNationality() != null, "Nationality check"));

		Person person = new Person();
		person.setName("ABC");
		//person.setNationality("Indian");

		Notification notification = engine.run(person);

		if (notification.getErrors().size() > 0) {
			System.out.println("Printing errors");
			for (String error : notification.getErrors()) {
				System.out.println("Error: " + error);
			}
		} else {
			System.out.println("No errors");
		}

	}
}
