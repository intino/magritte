package bazar;

import siani.tara.magritte.Set;
import siani.tara.magritte.wraps.Morph;
import siani.tara.magritte.wraps.Operation;

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
