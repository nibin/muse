package com.msi.brules.domain;

import java.util.ArrayList;
import java.util.List;

public class Notification {

	private List<String> errors = new ArrayList<>();

	public void addError(String error) {

		errors.add(error);
	}

	public List<String> getErrors() {

		return errors;
	}

}
