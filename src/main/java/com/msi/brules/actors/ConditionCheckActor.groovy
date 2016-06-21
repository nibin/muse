package com.msi.brules.actors

import com.msi.brules.engine.EligibilityRule;

import groovyx.gpars.actor.DefaultActor;

class ConditionCheckActor extends DefaultActor{

	void act() {

		loop {
			react { msg ->
				if(msg instanceof Map) {
					reply checkEligibilityRule(msg["rule"], msg["businessObject"])
				} else {
					println ("Unknown message received")
				}
			}
		}
	}

	def checkEligibilityRule(EligibilityRule rule, Object businessObject) {
		boolean canActivate = rule.canActivate(businessObject)
		return ["rule" : rule, "status": canActivate]
	}
}
