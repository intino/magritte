package siani.goros.definitions;

import siani.tara.core.Definition;
import siani.tara.core.Metamodel;
import siani.tara.core.Id;


public abstract class EntityDefinition extends Definition implements Metamodel.HasCode {

    public static final Type TYPE = new Type(Definition.TYPE);

    public EntityDefinition(Id id) {
        super(id);

    }

	public DescriptionDefinition getDescription() {
		return (DescriptionDefinition) getChild(DescriptionDefinition.TYPE);
	}

	public HelpDefinition getHelp() {
		return (HelpDefinition) getChild(HelpDefinition.TYPE);
	}

	public OperationDefinition getOperation() {
		return (OperationDefinition) getChild(OperationDefinition.TYPE);
	}

	public ViewDefinition getView() {
		return (ViewDefinition) getChild(ViewDefinition.TYPE);
	}

	@Override
    public Type getType() {
        return TYPE;
    }
	
	public static class DescriptionDefinition extends Definition implements Metamodel.Optional {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public DescriptionDefinition() {
	        super();
	        add(new Attribute("description", Definition.Attribute.Primitives.));

	    }

		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
	public static class HelpDefinition extends Definition implements Metamodel.Optional {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public HelpDefinition() {
	        super();
	        add(new Attribute("resource", Definition.Attribute.Primitives.));

	    }

		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
	public static class OperationDefinition extends Definition {
	
	    public static final Type TYPE = new Type(Definition.TYPE);

	    public OperationDefinition() {
	        super();
	        add(new Attribute("label", Definition.Attribute.Primitives.));

	    }

		@Override
	    public Type getType() {
	        return TYPE;
	    }
		
	}
	
	public static abstract class ViewDefinition extends Definition {
	
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


