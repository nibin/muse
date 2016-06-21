package com.msi.brules;

import com.msi.brules.builders.ValidationEngineBuilder;
import com.msi.brules.domain.Notification;
import com.msi.brules.domain.Person;

public class BasicRuleExecutor {

	public static void main(String[] args) {

		ValidationEngineBuilder engine = new ValidationEngineBuilder() {
			@Override
			protected void build() {
				validate("Name is present")
				.with((Person p) -> p.getName() != null);
				
				validate("Nationality is present")
				.with((Person p) -> p.getNationality() != null);
			}
		};

		Person person = new Person();
		person.setName("ABC");
		// person.setNationality("Indian");

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
