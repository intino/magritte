import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import siani.tara.Checker;
import siani.tara.dsls.Monet;
import siani.tara.semantic.Definition;
import siani.tara.semantic.SemanticException;

import static siani.tara.semantic.MessageProvider.message;
import static siani.tara.semantic.Script.*;

public class AcceptedMonetLanguageInstance {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void should_allow_facet_application() throws Exception {
		Definition animal = define().type("Container").plate("220696291L").name("Animal").facet(facetDefine().as("Collectable").
			include(
				define().type("Display").parameter(0, "fields", "FichaAnimal.Archivo").node()).facet());

		monetChecks(
			root().include(
				define().type("Collection").name("RegistroAnimales").annotations("single").plate("220696291L").include(animal.node()).node(),
				animal.node()));
	}

	@Test
	public void should_reject_unknown_facet_application() throws Exception {
		thrown.expectMessage(message("reject.unknown.facet.in.context", "Element"));
		Definition animal = define().type("Container").plate("220696291L").name("Animal").facet(facetDefine().as("Element").
			include(
				define().type("Display").parameter(0, "fields", "FichaAnimal.Archivo").node()).facet());

		monetChecks(
			root().include(
				animal.node()));
	}

	@Test
	public void should_allow_Role_withplate() throws Exception {
		monetChecks(root().include(
			define().type("Role").name("Gestor").plate("613602067L").node()));
	}

	@Test
	public void should_reject_Role_withOutplate() throws Exception {
		thrown.expectMessage(message("required.plate", "Role", "Gestor"));
		monetChecks(root().include(
			define().type("Role").name("Gestor").node()));
	}

	private void monetChecks(Definition definition) throws SemanticException {
		new Checker(new Monet()).check(definition.node());
	}

}
