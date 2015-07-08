package shop;

import siani.tara.magritte.Set;
import siani.tara.magritte.wraps.Operation;


public class Collection extends Entity {

	public Set<Form> forms() {
		return _getMultiple("forms").as(Form.class);
	}

	public Form forms_(int index) {
		return forms().get(index);
	}

	public void forms(Operation operation, Form... forms) {
		_edit(operation).set("forms", forms);
	}


}
