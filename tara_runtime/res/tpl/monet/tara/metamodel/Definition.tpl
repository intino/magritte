package monet.::projectName::.definitions;

import monet.tara.core.Definition;
import monet.tara.core.Metamodel;
::id|import monet.tara.core.Id;::

::root::

@definition
::doc::
public ::modifier|*::class ::DefinitionName::Definition extends Definition ::implements::{

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
	public ::childType::Definition get::childType::::listSuffix|List::() {
		return (::childType::Definition) ::childGetter::(::childType::Definition.TYPE)::listCast|*::;
	}

@multipleCastBlock
.toArray(new ::childType::Definition[0])

