package smartgrid.morphs;


import magritte.wraps.Morph;

public class Electrical extends Morph {

	public double power() {
		return _get("power", double.class);
	}

	public double energy() {
		return _get("energy", double.class);
	}

	public void setEnergy(double value) {
		set("energy", value);
	}

	public void setPower(double value) {
		set("power", value);
	}


}
