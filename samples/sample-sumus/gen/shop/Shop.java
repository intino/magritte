package shop;

import tara.magritte.Graph;
import tara.magritte.Set;
import tara.magritte.handlers.Casting.NodeCasting;
import tara.magritte.wraps.Model;
import tara.magritte.wraps.Type;

public class Shop extends Model {
	public Shop(Graph graph) {
		super(graph);
	}

	public Set<Collection> collectionSet() {
		return _get("", Collection.class);
	}

	public Collection collection(int index) {
		return collectionSet().get(index);
	}

	public Set<Form> formSet() {
		return _get("", Form.class);
	}

	public Form form(int index) {
		return formSet().get(index);
	}

	@Override
	public Set<Type> _aggregables() {
		return super._aggregables();
	}

	@Override
	public Type _aggregable(int index) {
		return super._aggregable(index);
	}

	@Override
	public NodeCasting _create(Type type) {
		return super._create(type);
	}

}
