package bazar;

import magritte.wraps.Operation;
import magritte.Set;
import magritte.wraps.Morph;

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
