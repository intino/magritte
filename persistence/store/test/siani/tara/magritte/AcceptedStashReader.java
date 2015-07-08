package siani.tara.magritte;

import org.junit.Test;
import siani.tara.magritte.schema.Capsule;
import siani.tara.magritte.schema.Stash;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static siani.tara.magritte.AcceptedStash.createRadiatorStash;
import static siani.tara.magritte.helpers.Check.check;
import static siani.tara.magritte.io.StashReader.read;


public class AcceptedStashReader {

	@Test
	public void should_read_a_polymorphic_stash_into_repository() throws Exception {
		Stash stash = createRadiatorStash();
		Node node = read(stash).into(new Capsule(null));
		assertThat(node.types().size(), is(3));
		assertThat(node.toString(), is("Case[Instance:Radiator]"));
		assertThat(node.is(Tag.Case), is(true));
		assertThat("Node is instance of Thermal", check(node, "Thermal"));
		assertThat("Node is instance of Electrical", check(node, "Electrical"));
		assertThat("Node is instance of Radiator", check(node, "Radiator"));
		assertThat("Node is instance of Appliance", check(node, "Appliance"));
		assertThat("Node is instance of Device", check(node, "Device"));
	}
}
