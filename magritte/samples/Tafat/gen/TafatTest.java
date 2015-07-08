import magritte.Graph;
import magritte.primitives.Date;
import magritte.schema.MemoryGraph;
import millener.Millener;
import millener.src.millener.extensions.thermals.RadiatorBehavior;
import millener.thermals.Radiator;
import org.junit.Test;
import tafat.Engine;
import tafat.Entity;
import tafat.Simulation;
import tafat.morphs.RichBehavior;

import static org.junit.Assert.*;


public class TafatTest {

    @Test
    public void engine() throws Exception {
        Graph graph = Script.start(new MemoryGraph()).scenario().model();
        Engine engine = new Engine(graph);

        Simulation simulation = engine.simulation();
        Assert.assertNotNull(simulation);
        assertEquals(Date.date(2014, 1, 1), simulation.from());
        assertEquals(Date.date(2014, 12, 31), simulation.to());

        assertEquals(2, engine.entities().length);
        assertEquals(2, engine.behaviors().length);

        Entity entity = engine.entities()[0];

        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.definition());
        assertEquals("Radiator", entity.definition().name());
        assertEquals(21.0, entity.as(Radiator.class).temperature(), 0.0001);

        entity.as(RadiatorBehavior.class).tick();
        assertEquals(21.1, entity.as(Radiator.class).temperature(), 0.0001);

        engine.behaviors()[0].as(RichBehavior.class).tick();
        assertEquals(21.2, entity.as(Radiator.class).temperature(), 0.0001);

        engine.tick();
        engine.tick();
        assertEquals(21.4, entity.as(Radiator.class).temperature(), 0.0001);
    }

    @Test
    public void scenario() throws Exception {
        Graph graph = Script.start(new MemoryGraph()).scenario().model();
        Millener millener = new Millener(graph);

        assertEquals(2, millener.devices().length);
        assertEquals(2, millener.appliances().length);
        assertEquals(2, millener.radiators().length);
        assertEquals(2, millener.thermals().length);
        Assert.assertTrue(millener.thermals()[0].is(Radiator.class));
        assertEquals(21.0, millener.thermals()[0].temperature(), 0.001);
        assertEquals(18.0, millener.thermals()[0].as(Radiator.class).threshold(), 0.0001);
        Assert.assertTrue(millener.thermals()[1].is(Radiator.class));
        assertEquals(21.0, millener.thermals()[1].temperature(), 0.001);
        assertEquals(20.0, millener.thermals()[1].as(Radiator.class).threshold(), 0.0001);
        assertEquals(2, millener.electricals().length);
        assertEquals(1200.0, millener.electricals()[0].power(), 0.001);
        assertEquals(0, millener.electricals()[0].energy(), 0.001);
    }

    public static class Script extends bazar.scripts.Script {
        public Script(Graph graph) {
            super(graph);
        }

        public static Script start(Graph graph) {
            return new Script(graph);
        }

        public Script scenario() {
            magritte.ontology.millener.scenario.Main.box.load(model);
            return this;
        }

    }
}
