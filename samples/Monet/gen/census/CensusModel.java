package census;

import siani.tara.magritte.Graph;
import siani.tara.magritte.Set;
import siani.tara.magritte.wraps.Model;
import siani.tara.magritte.wraps.Morph;
import siani.tara.magritte.wraps.Type;

public class CensusModel extends Model {

	public CensusModel(Graph graph) {
		super(graph);
	}

	public CensusModel(Morph morph) {
		super(morph);
	}

	public Censo censo() {
		return _get("", Censo.class).get(0);
	}

	public Set<Type> mainTypeSet() {
		return super._aggregables();
	}

	public Type mainTypeSet(int index) {
		return mainTypeSet().get(index);
	}

	public Perro _createPerro() {
		return super._create(Perro.class);
	}

	public Gato _createGato() {
		return super._create(Gato.class);
	}

	public void _removePerro(Perro perro) {
		super._remove(perro);
	}

	public void _removeGato(Gato gato) {
		super._remove(gato);
	}


}
