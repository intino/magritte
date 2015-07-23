package magritte.ontology.m2;

import tafat.*;
import tara.magritte.Box;

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
        def("Simulation", Simulation.class).type("Concept").end();
        def("Entity", Entity.class).type("Concept").allowsMultiple("Entity", "Entity$Feature").end();
        def("Entity$Feature", Entity.Feature.class).type("Concept").end();
        abstractDef("Facet", Facet.class).type("Concept").end();
        def("Behavior", Behavior.class).type("Concept").type("Facet").allowsSingle("Behavior$Start", "Action", "TableFunction", "PointSet", "Job", "Map").allowsMultiple("Behavior.Knol", "EquationSystem", "StateChart", "Task").end();
        def("Behavior$Start", Behavior.Start.class).type("Concept").end();
        def("Behavior$Knol", Behavior.Knol.class).type("Concept").end();
        def("Action", Action.class).type("Concept").end();
    }

}