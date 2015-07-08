package smartgrid.morphs;

import magritte.wraps.Morph;

public class Thermal extends Morph {

	public double temperature() {
		return _get("temperature", double.class);
	}

	public void setTemperature(double value) {
		set("temperature", value);
	}

}
