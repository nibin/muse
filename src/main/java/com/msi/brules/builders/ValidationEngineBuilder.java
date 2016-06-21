package com.msi.brules.builders;

import com.msi.brules.domain.Notification;
import com.msi.brules.domain.Person;
import com.msi.brules.engine.SimpleValidationEngine;
import com.msi.brules.parser.WithParser;

public abstract class ValidationEngineBuilder {

	private SimpleValidationEngine engine;

	protected ValidationEngineBuilder() {
		super();
		engine = new SimpleValidationEngine();
		build();
	}

	protected abstract void build();

	protected WithParser validate(String description) {
		return new ValidationRuleBuilder(description, engine);
	}

	public Notification run(Person person) {

		return engine.run(person);
	}

}
