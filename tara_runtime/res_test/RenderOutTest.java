package monet.goros.definitions;

import monet.tara.core.Definition;
import monet.tara.core.Id;
import monet.tara.core.Metamodel.HasId;
import monet.tara.core.Metamodel.Root;


public abstract class EntityDefinition extends Definition implements HasCode {

    public static final Type TYPE = new Type(Definition.TYPE);

    public EntityDefinition(Id id) {
        super(id);

    }

	public Description getDescription() {
		return getChild(Description.TYPE).toArray(new Description[0]);
	}

	public Help getHelp() {
		return getChild(Help.TYPE).toArray(new Help[0]);
	}

	public Operation getOperation() {
		return getChild(Operation.TYPE).toArray(new Operation[0]);
	}

	public View getView() {
		return getChild(View.TYPE).toArray(new View[0]);
	}


	@Override
    public Type getType() {
        return TYPE;
    }
	
	public class DescriptionDefinition extends Definition implements Optional {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public DescriptionDefinition() {
	        super();
	        add(new Attribute("description", Definition.Attribute.Primitives.String));

	    }

	
		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
	public class HelpDefinition extends Definition implements Optional {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public HelpDefinition() {
	        super();
	        add(new Attribute("resource", Definition.Attribute.Primitives.String));

	    }

	
		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
	public class OperationDefinition extends Definition {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public OperationDefinition() {
	        super();
	        add(new Attribute("label", Definition.Attribute.Primitives.String));

	    }

	
		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
	public abstract class ViewDefinition extends Definition {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public ViewDefinition() {
	        super();

	    }

	
		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
}


