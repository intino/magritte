package monet;

import siani.tara.magritte.wraps.Morph;
import monet.natives.OnChange;

public class Field extends Morph {

	public String label() {
		return _definition()._get("label").asString();
	}

	public void onChange() {
		OnChange onChange = _definition()._get("onChange").asNative();
		onChange.execute();
	}

}
