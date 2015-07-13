package tafat.morphs;

import tara.magritte.wraps.Morph;

public class Entity extends Morph {

	public int[] coordinates() {
		return _get("coordinates", int[].class);
	}

}

