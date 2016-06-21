package com.msi.brules.engine;

import com.msi.brules.builders.ValidationEngineBuilder;
import com.msi.brules.domain.Person;

public class BasicValidationEngine extends ValidationEngineBuilder {

	@Override
	protected void build() {
		
		validate("Name is present")
			.with((Person p) -> p.getName() != null);

	}
	
	

}
