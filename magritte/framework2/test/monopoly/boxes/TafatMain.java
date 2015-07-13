package monopoly.boxes;

import monopoly.tafat.*;
import tara.magritte.Box;
import tara.magritte.MorphFactory;
import tara.magritte.RootMorph;

import java.util.Collections;
import java.util.List;


public class TafatMain extends Box {
    public static final Box box = new TafatMain();

    @Override
    public List<Box> dependencies() {
        return Collections.emptyList();
    }

    @Override
    public void write() {
        registerTypes();
    }

    private void registerTypes() {
        MorphFactory.register("root", RootMorph.class);
        MorphFactory.register("facet", Facet.class);
        MorphFactory.register("entity", Entity.class);
        MorphFactory.register("behavior$start", Behavior.Start.class);
        MorphFactory.register("action", Action.class);
        MorphFactory.register("behavior$knol", Behavior.Knol.class);
        MorphFactory.register("simulation", Simulation.class);
    }

}