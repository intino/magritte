package magritte.model;

import magritte.schema.Model;
import Tafat.engine.core.Entity;
import Tafat.engine.core.Simulation;

import static magritte.schema.Annotation.*;

public class TafatEngineCoreBox extends Box {

	public static final String BEHAVIOR = "Behavior";
	public static final String SIMULATION = "Simulation";
	public static final String ASPECT = "Aspect";
	public static final String ENTITY = "Entity";

    public static void load(Model model) {
        new TafatEngineCoreBox(model);
    }

    private TafatEngineCore(Model model) {
        super(model);
    }

    @Override
    protected void loadDependencies() {
        
    }

    @Override
    protected void loadNodes() {
		def(ASPECT);as(INTENTION); 
            cast(Aspect.class);
        end();
		def(BEHAVIOR);as(INTENTION); 
            cast(Behavior.class);
        end();
		def(SIMULATION);as(CONCEPT); is(REQUIRED);is(SINGLE);is(TERMINAL);is(ROOT);
            cast(Simulation.class);
        end();
		def(ENTITY);as(CONCEPT); is(ROOT);
            cast(Entity.class);
        end();

    }

}

