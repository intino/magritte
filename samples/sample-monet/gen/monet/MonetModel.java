package monet;

import tara.magritte.Graph;
import tara.magritte.Set;
import tara.magritte.handlers.Casting;
import tara.magritte.wraps.Model;
import tara.magritte.wraps.Morph;
import tara.magritte.wraps.Type;

public class MonetModel extends Model {

	public MonetModel(Morph morph) {
		super(morph);
	}

	public MonetModel(Graph graph) {
		super(graph);
	}

	public Set<Entity> entitySet() {
		return _get("",Entity.class);
	}

	public Entity entity(int index) {
		return entitySet().get(index);
	}

	public Set<Collection> collectionSet() {
		return _get("",Collection.class);
	}

	public Collection collection(int index) {
		return collectionSet().get(index);
	}

	public Set<Thesaurus> thesaurusSet() {
		return _get("",Thesaurus.class);
	}

	public Thesaurus thesaurus(int index) {
		return thesaurusSet().get(index);
	}

	public Set<Form> formSet() {
		return _get("",Form.class);
	}

	public Form form(int index) {
		return formSet().get(index);
	}

	public Set<Type> mainTypeSet() {
		return super._aggregables();
	}

	public Type mainType(int index) {
		return mainTypeSet().get(index);
	}

	public Casting.NodeCasting create(Type type) {
		return super._create(type);
	}

	public void free(Morph morph) {
		super._remove(morph);
	}
}
