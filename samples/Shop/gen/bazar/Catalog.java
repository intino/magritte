package bazar;

import magritte.Set;
import magritte.wraps.Morph;
import magritte.wraps.Operation;

public class Catalog extends Morph {

	public Set<Product> forms() {
		return _getMultiple("forms").as(Product.class);
	}

	public Product forms_(int index) {
		return forms().get(index);
	}

	public void forms(Operation operation, Product... forms) {
		_edit(operation).set("forms", forms);
	}


}
