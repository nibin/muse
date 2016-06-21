package com.msi.brules.parser;

import java.util.function.Predicate;

import com.msi.brules.domain.Person;

public interface WithParser {

	void with(Predicate<Person> condition);
}
