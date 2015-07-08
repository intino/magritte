package magritte;

import magritte.primitives.Date;
import magritte.schema.Stash;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static magritte.io.StashDeserializer.stashFrom;
import static magritte.io.StashSerializer.serialize;
import static magritte.io.StashWriter.stashOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedSerializer {

    @Test
    public void should_serialize_stash() throws Exception {
        byte[] bytes = serialize(createRadiatorStash().root);
        assertThat(bytes.length, is(172));
    }

    @Test
    public void should_serialize_node() throws Exception {
        Graph graph = Shop.create().m0();
        byte[] bytes = serialize(stashOf(graph.roots().get(2)).root);
        assertThat(bytes.length, is(529));
    }

    @Test
    public void should_serialize_and_deserialize_node() throws Exception {
        Graph graph = Shop.create().m0();
        Stash pleope = stashFrom((serialize(stashOf(graph.get("pleope")).root)));
        Stash vibur = stashFrom((serialize(stashOf(graph.get("vibur")).root)));
        assertThat(pleope.root.variables.length, is(1));
        assertThat(passes(pleope.root.variables[0].value)[0], is("pass[//jerlos/belyus:Customer]"));
        assertThat(passes(pleope.root.variables[0].value)[1], is("pass[//jerlos/brorde:Customer]"));
        assertThat(passes(pleope.root.variables[0].value)[2], is("pass[//jerlos/vibur:Customer]"));
        assertThat(passes(pleope.root.variables[0].value)[3], is("pass[//zutlu/frelon:Provider]"));
        assertThat(passes(pleope.root.variables[0].value)[4], is("pass[//zutlu/jarnat:Provider]"));
        assertThat(vibur.root.components.length, is(5));
        assertThat(vibur.root.components[0].toString(), is("case[?:Person.FullName]"));
        assertThat(vibur.root.components[1].toString(), is("case[?:Person.Product]"));
        assertThat(vibur.root.components[2], is("case[?:Person.Photo]"));
        assertThat(vibur.root.components[3].toString(), is("case[?:Person.Logo]"));
        assertThat(vibur.root.components[4].toString(), is("case[?:Person.Date]"));
        assertThat(format((Date) vibur.root.components[4].variables[0].value), is("2010-10-05"));
    }

    private String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


    private Stash.Pass[] passes(Object value) {
        return (Stash.Pass[]) value;
    }

}
