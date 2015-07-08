package millener;

import magritte.Graph;
import magritte.castings.NodeSetWrapper;
import magritte.wraps.Morph;
import millener.devices.Appliance;
import millener.devices.appliances.Radiator;

public class Millener {
    private final Graph graph;

    public Millener(Graph graph) {
        this.graph = graph;
    }

    public Device[] devices() {
        return select(Device.class);
    }
    public Appliance[] appliances() {
        return select(Appliance.class);
    }
    public Radiator[] radiators() {
        return select(Radiator.class);
    }
    public Thermal[] thermals() {
        return select(Thermal.class);
    }
    public Electrical[] electricals() {
        return select(Electrical.class);
    }


    private <T extends Morph> T[] select(Class<T> morphClass) {
        return NodeSetWrapper.wrap(graph.get(morphClass)).with(morphClass);
    }

}
