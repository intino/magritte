package magritte.model;

import magritte.schema.Model;
::imports::
import static magritte.schema.Annotation.*;

public class ::BoxName::Box extends Box {

::identifiers::
    public static void load(Model model) {
        new ::BoxName::Box(model);
    }

    private ::BoxName::(Model model) {
        super(model);
    }

    \@Override
    protected void loadDependencies() {
        ::implicatedParentBoxes::
    }

    \@Override
    protected void loadNodes() {
::nodeLoads::
    }

}

@imports
import ::qn::;
@identifiers
    public static final String ::identifierUpper:: = "::identifier::";
@nodeLoads
def(::nameUpper::);::as|as(*);:: ::annotations::
            cast(::nameProper::.class);::indef|\\n\\t\\t\\t*::::has|\\n\\t\\t\\t*::::set|\\n\\t\\t\\t*::
        end();
@annotations
is(::annotation::);
@implicatedParentBoxes
::box::.load(model());node.
@indef
indef(::nameUpper::); ::as|as(*);:: ::annotations::
	cast(::nameProper::.class);::indef|\\n\\t\\t\\t\\t*::::has|\\n\\t\\t\\t\\t*::::set|\\n\\t\\t\\t*::
end();
@has
has(::nameUpper::);
@set
set(::nodeName::.::var::, ::defaultValue::);