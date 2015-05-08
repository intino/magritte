import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import siani.tara.Checker;
import siani.tara.dsls.Proteo;
import siani.tara.semantic.model.Node;
import siani.tara.semantic.Definition;
import siani.tara.semantic.SemanticException;

import static siani.tara.semantic.MessageProvider.message;
import static siani.tara.semantic.Script.*;

public class AcceptedGlobalConstrains {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void should_reject_concept_containing_two_duplicated_concepts() throws Exception {
		thrown.expectMessage(message("reject.duplicate.entries", "Term", "Thesaurus"));
		taraCheck(root().include(
			define().type("Concept").name("Thesaurus")
				.include(define().type("Concept").name("Term").node())
				.include(define().type("Concept").name("Term").node())
				.node()));
	}

	@Test
	public void should_accept_facet_targets() throws Exception {
		taraCheck(root().include(
			define().type("Concept").name("Electrical").flags("facet")
				.include(define().type("Concept").name("Term")
					.include(define().type("Concept").name("Term").node()).node())
				.facetTarget(targetDefine().on("Entity").target())
				.node()));
	}

	@Test
	public void should_reject_facet_without_targets() throws Exception {
		thrown.expectMessage(message("no.targets.in.facet", "Electrical"));
		taraCheck(root().include(
			define().type("Concept").name("Electrical").flags("facet")
				.include(define().type("Concept").name("Term")
					.include(define().type("Concept").name("Term").node()).node())
				.node()));
	}

	@Test
	public void should_reject_target_without_facet_annotation() throws Exception {
		thrown.expectMessage(message("reject.target.without.facet"));
		taraCheck(root().include(
			define().type("Concept").name("Electrical")
				.include(define().type("Concept").name("Term")
					.include(define().type("Concept").name("Term").node()).node())
				.facetTarget(targetDefine().on("Entity").target())
				.node()));
	}

	@Test
	public void should_accept_node_reference_to_facetNode_facet_targets() throws Exception {//COMO PROBARLO??? TEST MAL PLANTEADO, NECESIDAD DE UN REFERENCE_NODE
		Node content = define().type("Concept").name("Content").flags("facet").facetTarget(targetDefine().on("Form").target()).node();
		taraCheck(root().include(
			content, define().type("Concept").name("Container").include(content).node()));
	}

	@Test
	public void should_accept_concept_extends_concept() throws Exception {
		Node aspect = define().type("Concept").name("Aspect").node();
		taraCheck(root().include(
			define().type("Concept").name("Electrical").extendsTo(aspect).node(),
			aspect));
	}


	@Test
	public void should_accept_concept_with_address() throws Exception {
		taraCheck(root().include(
			define().type("Concept").name("Entity").address(1l).node()));
	}

	@Test
	public void should_reject_concept_with_two_has_of_the_same_component_type() throws Exception {
		thrown.expectMessage(message("reject.duplicate.entries", "Feature", "Entity"));
		Node feature = define().type("Concept").name("Feature").address(1l).node();
		taraCheck(root().include(
				define().type("Concept").name("Entity").has(feature).has(feature).node()));
	}

	private void taraCheck(Definition definition) throws SemanticException {
		new Checker(new Proteo()).check(definition.node());
	}

}
