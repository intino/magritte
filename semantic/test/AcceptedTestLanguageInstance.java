import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import siani.tara.Checker;
import siani.tara.semantic.model.Node;
import siani.tara.semantic.model.Tara;
import siani.tara.semantic.Definition;
import siani.tara.semantic.SemanticException;

import java.util.Locale;

import static siani.tara.semantic.MessageProvider.message;
import static siani.tara.semantic.constraints.RuleFactory.*;
import static siani.tara.semantic.Script.define;
import static siani.tara.semantic.Script.root;

public class AcceptedTestLanguageInstance {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void should_reject_store_extending_thesaurus() throws Exception {
		thrown.expectMessage(message("reject.parent.different.type", "Thesaurus", "Store"));
		Node thesaurus = define().type("Thesaurus").name("T").node();
		testCheck(
			root().include(
				define().type("Store").name("S").extendsTo(thesaurus).node(), thesaurus));
	}

	@Test
	public void should_reject_facet_instance() throws Exception {
		thrown.expectMessage(message("reject.facet.as.primary"));
		testCheck(
			root().include(
				define().type("Electrical").name("B").node()));
	}

	@Test
	public void should_reject_facet_instance_inside_element() throws Exception {
		thrown.expectMessage(message("reject.facet.as.primary"));
		testCheck(
			root().include(define().type("Store").name("S").include(
				define().type("Electrical").name("E").node()
			).node()));
	}

	@Test
	public void should_accept_Store_containing_StoreElement() throws Exception {
		testCheck(root().include(
			define().type("Store").name("S").include(
				define().type("StoreElement").name("SE").node()
			).node()));
	}

	public class TestLanguage extends Tara {

		private static final String CONCEPT = "Concept";

		public TestLanguage() {
			in(Root).def(context(Root).allow(multiple("Store"), multiple("Thesaurus")));
			in("Store").def(context("Concept").allow(name(), multiple("Store.StoreElement"), multiple("Electrical")));
			in("Store.StoreElement").def(context("Concept").allow(name()));
			in("Thesaurus").def(context("Concept").require(_name()));
			in("Electrical").def(context("Concept").require(_name()).assume(isFacetInstance()));
			in("Behavior").def(context("Concept").require(_name()).assume(isFacet()));
		}

		@Override
		public String languageName() {
			return "Test";
		}

		@Override
		public Locale locale() {
			return Locale.ENGLISH;
		}

		@Override
		public boolean isTerminalLanguage() {
			return false;
		}
	}

	private void testCheck(Definition definition) throws SemanticException {
		new Checker(new TestLanguage()).check(definition.node());
	}

}
