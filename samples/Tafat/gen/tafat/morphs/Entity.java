package tafat.morphs;

import magritte.wraps.Morph;

public class Entity extends Morph {

	public int[] coordinates() {
		return _get("coordinates", int[].class);
	}

}

