package bazar;

import siani.tara.magritte.Set;
import siani.tara.magritte.wraps.Operation;

public class People extends Person {

	public Set<Person> forms() {
		return _getMultiple("forms").as(Person.class);
	}

	public Person forms_(int index) {
		return forms().get(index);
	}

	public void forms(Operation operation, Person... forms) {
		_edit(operation).set("forms", forms);
	}


}
