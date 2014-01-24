package monet.::projectName::.definitions;

import monet.tara.core.Definition;
import monet.tara.core.Id;
import monet.tara.core.Metamodel.HasId;
import monet.tara.core.Metamodel.Root;

public ::abstract:: class ::DefinitionName::Definition extends Definition ::implements:: {

    public static final Type TYPE = new Type(Definition.TYPE);

    public ::DefinitionName::Definition(Id id) {
        super(id);
    }

    public View[] getViews() {
        return getChildren(View.TYPE).toArray(new View[0]);
    }

    @Override
    public Type getType() {
        return TYPE;
    }


::childrenDeclaration::


@childDeclaration
    public static abstract class ::childName:: extends Definition {

        public static final Type TYPE = new Type(Definition.TYPE);

        @Override
        public Type getType() {
            return TYPE;
        }
    }
    ::childrenDeclaration::

}
