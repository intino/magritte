package smartgrid.natives;

import smartgrid.morphs.devices.Appliance;
import smartgrid.morphs.electricals.ElectricalDevice;
import smartgrid.morphs.electricals.ElectricalRadiator;
import tafat.intentions.Behavior;

public class SmartGridMain {

	public static class N101 extends ElectricalDevice implements NativeContainer<Behavior.Action> {
		@Override
		public Behavior.Action object() {
			return () -> {
				setPower(0);
			};
		}
	}

	public static class N102 extends Appliance implements NativeContainer<Behavior.Action> {
		@Override
		public Behavior.Action object() {
			return () -> {

			};
		}
	}

	public static class N103 extends ElectricalRadiator implements NativeContainer<Behavior.Action> {
		@Override
		public Behavior.Action object() {
			return () -> {

			};
		}
	}

	public static class N104 extends ElectricalRadiator implements NativeContainer<Behavior.Action> {
		@Override
		public Behavior.Action object() {
			return () -> {

			};
		}
	}
}
