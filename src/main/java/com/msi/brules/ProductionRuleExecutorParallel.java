package com.msi.brules;

import com.msi.brules.builders.ProductionRuleBuilder;
import com.msi.brules.domain.Person;

public class ProductionRuleExecutorParallel {
	
	public static void main(String[] args) {

		ProductionRuleBuilder ruleEngine = new ProductionRuleBuilder(true) {

			@Override
			protected void build() {

				rule("If name is not null then shout")
					.when(p -> ((Person) p).getName()!= null)
					.then(p -> ((Person) p).shout());
				
				rule("If shouted, then stay silent")
					.when(p -> ((Person) p).isShouted())
					.then(p -> ((Person) p).shutUp());

				
			}
		};
		
		Person person = new Person();
		person.setName("Jack");
		
		ruleEngine.run(person);

	}

}
