package com.msi.brules.actors

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

import java.util.concurrent.CountDownLatch

import com.msi.brules.engine.EligibilityRule

class RuleActivatorActor extends DefaultActor {

	int size
	private CountDownLatch latch
	Object businessObject
	List<Actor> conditionCheckActors = []
	List<EligibilityRule> availableRules = []
	List<EligibilityRule> agendaRules = []

	public RuleActivatorActor(int size, List<EligibilityRule> availableRules, Object businessObject) {
		super()
		this.size = size
		this.availableRules = availableRules
		this.businessObject = businessObject
		this.latch = new CountDownLatch(availableRules.size())
		createActors()
	}

	void act() {
		
		sendConditionChecks()

		loop {
			react { msg ->
				if(msg instanceof Map) {
					if(msg["status"]) {
						agendaRules << msg["rule"]						
					}
					latch.countDown()
				}
			}
		}
	}

	private void createActors() {

		conditionCheckActors = (1..size).collect {
			new ConditionCheckActor().start()
		}
	}

	def sendConditionChecks() {

		int idx = 0
		for(EligibilityRule rule: availableRules) {
			conditionCheckActors[idx % size] << ["rule":rule, "businessObject": businessObject]
			idx++
		}
	}

	public void waitUntilDone() {
		latch.await()
		//println "All done"
	}
}
