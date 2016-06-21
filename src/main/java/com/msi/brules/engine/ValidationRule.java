package com.msi.brules.engine;

import com.msi.brules.domain.Notification;
import com.msi.brules.domain.Person;

public interface ValidationRule {

	public void check(Notification notification, Person p);

}
