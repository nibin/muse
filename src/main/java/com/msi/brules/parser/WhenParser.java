package com.msi.brules.parser;

import java.util.function.Predicate;

public interface WhenParser {
	
	public ThenParser when(Predicate condition);

}
