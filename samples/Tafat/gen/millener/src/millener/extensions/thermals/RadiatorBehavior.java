package millener.src.millener.extensions.thermals;

import millener.thermals.Radiator;
import tafat.intentions.BehaviorIntention;

public class RadiatorBehavior extends Radiator implements BehaviorIntention {
	@Override
	public void tick() {
		setTemperature(temperature() + 0.1);
	}
}
