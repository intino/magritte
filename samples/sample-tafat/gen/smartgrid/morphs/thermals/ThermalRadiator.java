package smartgrid.morphs.thermals;

import smartgrid.morphs.Thermal;

public class ThermalRadiator extends Thermal {

	public double threshold() {
		return get("threshold");
	}
}

