package bazar;

import siani.tara.magritte.Graph;
import siani.tara.magritte.Set;
import siani.tara.magritte.wraps.Model;


public class Bazar extends Model {

	public Bazar(Graph graph) {
		super(graph);
	}

	public People people() {
		return _get("", People.class).get(0);
	}

	public Catalog catalog() {
		return _get("", Catalog.class).get(0);
	}

	public Set<Provider> providerSet() {
		return _get("", Provider.class);
	}

	public Set<Customer> customerSet() {
		return _get("", Customer.class);
	}

	public Set<Product> productSet() {
		return _get("", Product.class);
	}

	public Product _createProduct() {
		return super._create(Product.class);
	}

	public Customer _createCustomer() {
		return super._create(Customer.class);
	}

	public Provider _createProvider() {
		return super._create(Provider.class);
	}

	public void removeProduct(Product product) {
		super._remove(product);
	}

	public void removeCustomer(Customer customer) {
		super._remove(customer);
	}

	public void removeProvider(Provider provider) {
		super._remove(provider);
	}
}
