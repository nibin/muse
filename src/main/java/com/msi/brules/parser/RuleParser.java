package com.msi.brules.parser;

import java.util.function.Predicate;

public interface RuleParser {

	public WhenParser rule(Predicate condition);

}
