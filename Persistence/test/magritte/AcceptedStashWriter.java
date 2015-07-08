package magritte;

import magritte.schema.Stash;
import org.junit.Test;


import static magritte.io.StashWriter.stashOf;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedStashWriter {

    @Test
    public void should_create_stash_from_node() throws Exception {
        Graph source = Shop.create().m0();
        Stash people = stashOf(source.get("pleope"));


        Stash customer = stashOf(source.get("belyus"));
        Stash product = stashOf(source.get("coffee"));

        assertThat(people.toString(), is("stash[pleope:People]"));
        assertThat(passes(people.root.variables[0].value).length, is(5));
        assertThat(passes(people.root.variables[0].value)[0].toString(), is("pass[//jerlos/belyus:Customer]"));
        assertThat(customer.toString(), is("stash[belyus:Customer]"));
        assertThat(customer.root.types.length, is(1));
        assertThat(customer.root.variables, is(nullValue()));
        assertThat(customer.root.components.length, is(2));
        assertThat(customer.root.components[0].variables.length, is(1));
        assertThat(customer.root.components[0].variables[0].name, is("value"));
        assertThat(customer.root.components[0].variables[0].value, is("Mario"));
        assertThat(customer.root.components[1].variables[0].name, is("entities"));
        assertThat(passes(customer.root.components[1].variables[0].value).length, is(2));
        assertThat(passes(customer.root.components[1].variables[0].value)[0].toString(), is("pass[//secre/coffee:Product]"));
        assertThat(passes(customer.root.components[1].variables[0].value)[1].toString(), is("pass[//secre/sugar:Product]"));
        assertThat(customer.root.fanIn.length, is(1));
        assertThat(customer.root.fanIn[0].toString(), is("pass[//miyun/pleope:People]"));
        assertThat(customer.root.fanOut.length, is(2));
        assertThat(customer.root.fanOut[0].type, is("Product"));
        assertThat(customer.root.fanOut[0].uid, is("//secre/coffee:Product"));
        assertThat(customer.root.fanOut[1].type, is("Product"));
        assertThat(customer.root.fanOut[1].uid, is("//secre/sugar:Product"));

        assertThat(product.toString(), is("stash[coffee:Product]"));
        assertThat(product.root.types.length, is(1));
        assertThat(product.root.variables.length, is(1));
        assertThat(product.root.components, is(nullValue()));
        assertThat(product.root.fanIn.length, is(3));
        assertThat(product.root.fanIn[0].toString(), is("pass[//zisvoros/tacalon:Catalog]"));
        assertThat(product.root.fanIn[1].toString(), is("pass[//jerlos/belyus:Customer]"));
        assertThat(product.root.fanIn[2].toString(), is("pass[//zutlu/frelon:Provider]"));
    }

    private Stash.Pass[] passes(Object value) {
        return (Stash.Pass[]) value;
    }

    @Test
    public void should_create_stash_with_resources() throws Exception {
        Graph source = Shop.create().m0();
        Stash customer = stashOf(source.get("vibur"));
        Stash product = stashOf(source.get("sugar"));

        assertThat(customer.toString(), is("stash[vibur:Customer]"));
        assertThat(customer.resources.length, is(2));
        assertThat(customer.resources[0].format(), is("png"));
        assertThat(customer.resources[0].data().length, is(33186));
        assertThat(customer.resources[1].format(), is("png"));
        assertThat(customer.resources[1].data().length, is(1399));
        assertThat(customer.root.components[2].variables[0].name, is("image"));
        assertThat(customer.root.components[2].variables[0].value.getClass().toString(), is("class magritte.schema.Stash$Blob"));
        assertThat(customer.root.components[2].variables[0].value.toString(), is("blob[//jerlos/vibur#1.png]"));
        assertThat(customer.root.components[3].variables[0].name, is("image"));
        assertThat(customer.root.components[3].variables[0].value.getClass().toString(), is("class magritte.schema.Stash$Blob"));
        assertThat(customer.root.components[3].variables[0].value.toString(), is("blob[//jerlos/vibur#2.png]"));

        assertThat(product.toString(), is("stash[sugar:Product]"));
        assertThat(product.root.variables[0].value.toString(), is("blob[//secre/sugar#1.jpg]"));
        assertThat(product.resources.length, is(1));
        assertThat(product.resources[0].format(), is("jpg"));
        assertThat(product.resources[0].size(), is(3245));
    }


}
