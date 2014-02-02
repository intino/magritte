package monet.::projectName::.definitions;

import monet.tara.core.Definition;
import monet.tara.core.Id;
import monet.tara.core.Metamodel.HasId;
import monet.tara.core.Metamodel.Root;

::root::

@definition
::doc::
public ::modifier:: class ::DefinitionName::Definition extends Definition ::implements::{

    public static final Type TYPE = new Type(Definition.TYPE);
	::words::

    public ::DefinitionName::Definition(::id|Id id::) {
        super(::id|id::);
        ::attributes::
        ::references::
    }

	::childrenGetters::

	\@Override
    public Type getType() {
        return TYPE;
    }
	::childrenDeclaration::
}
@word
public enum ::name:: {
	::types::
}
@submodel

@attribute
add(new Attribute("::name::", Definition.Attribute.Primitives.::type::));
@reference
add(new Reference("::name::", ::type::Definition.TYPE));
@childGetter
	public ::childType:: get::childType::::plural|List::() {
		return getChild::plural|ren::(::childType::.TYPE).toArray(new ::childType::[0]);
	}

