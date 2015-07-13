package magritte.ontology.millener;

import siani.tara.magritte.Graph;
import siani.tara.magritte.editors.GraphEditor;
import siani.tara.magritte.schema.Box;

import static siani.tara.magritte.Tag.Abstract;
import static smartgrid.natives.SmartGridMain.*;

public class Main extends Box.Ontology {
	public static final Box box = new Main();

	@Override
	public Box[] dependencies() {
		return new Box[]{
			magritte.dsl.Tafat.box
		};
	}

	@Override
	protected GraphEditor modelEditor(Graph graph) {
		return new GraphEditor(graph) {
			@Override
			public void write() {
				def(1).name("Device").type("Entity").set(Abstract);
				def(2).name("Appliance").type("Entity").parent("Device").set(Abstract);
				def(3).name("Radiator").type("Entity").parent("Appliance");
				def(4).name("Electrical").type("Behavior").set("*Energy", 0);
				def(5).name("ElectricalDevice").type("Behavior").parent("Electrical").hasComponents(6);
				def(6).type("Behavior.Action").set(Native).set(N101.class);
				def(7).name("ElectricalAppliance").type("Behavior").parent("Electrical").hasComponents(8);
				def(8).type("Behavior.Action").set(Native).set(N102.class);
				def(9).name("ElectricalRadiator").type("Behavior").parent("Electrical").hasComponents(10);
				def(10).type("Behavior.Action").set(Native).set(N103.class);
				def(11).name("Thermal").type("Behavior").set(">temperature", 20);
				def(12).name("ThermalRadiator").type("Behavior").parent("Thermal").set(">threshold", 18).hasComponents(13);
				def(13).type("Behavior.Action").set(Native).set(N104.class);
			}
		};
	}

}
