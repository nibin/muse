package com.msi.brules.builders;

import com.msi.brules.engine.EligibilityRule;
import com.msi.brules.engine.EligibilityRuleBase;
import com.msi.brules.engine.ProductionRuleEngineFC;
import com.msi.brules.engine.ProductionRuleEngineParallelFC;
import com.msi.brules.parser.WhenParser;

public abstract class ProductionRuleBuilder {

	private EligibilityRuleBase ruleBase;
	private boolean isParallel = false;

	protected ProductionRuleBuilder(boolean isParallel) {

		super();
		this.ruleBase = new EligibilityRuleBase();
		this.isParallel = isParallel;
		build();
	}

	protected ProductionRuleBuilder() {
		this(false);
	}

	protected abstract void build();

	public WhenParser rule(String description) {

		return new EligibilityRule(ruleBase, description);
	}

	public void run(Object object) {

		if (this.isParallel) {
			ProductionRuleEngineParallelFC ruleEngine = new ProductionRuleEngineParallelFC(
					ruleBase, object);
			ruleEngine.run();

		} else {
			ProductionRuleEngineFC ruleEngine = new ProductionRuleEngineFC(
					ruleBase, object);
			ruleEngine.run();
		}

	}

}
