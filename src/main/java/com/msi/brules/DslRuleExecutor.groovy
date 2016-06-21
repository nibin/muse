package com.msi.brules

import com.msi.brules.domain.Person
import com.msi.brules.dsl.ConsoleService

class DslRuleExecutor {
	
	public static void main(String[] args) {
		
		def console = new ConsoleService()
		
		def person = new Person(name: "John", nationality: "Indian")
		
		console.eval """
			
			rule "shout" \
				when { it.name != null } \
				then { it.shout() }

			rule "silence" \
				when { p -> p.isShouted() == true } \
				then { p -> p.shutUp() }

			
		""", ["businessObject": person]
	}

}
