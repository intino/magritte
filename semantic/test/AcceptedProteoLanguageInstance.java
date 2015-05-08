import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import siani.tara.Checker;
import siani.tara.dsls.Proteo;
import siani.tara.semantic.Definition;
import siani.tara.semantic.SemanticException;

import static siani.tara.semantic.MessageProvider.message;
import static siani.tara.semantic.Script.define;
import static siani.tara.semantic.Script.root;

public class AcceptedProteoLanguageInstance {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void should_allow_simple_concept() throws Exception {
		proteoCheck(root().include(
				define().type("Concept").name("Thesaurus").node())
		);
	}

	@Test
	public void should_reject_unnamed_concept() throws SemanticException {
		thrown.expectMessage(message("required.name"));
		proteoCheck(root().include(
			define().type("Concept").node()));
	}

	@Test
	public void should_reject_no_concept_type() throws Exception {
		thrown.expectMessage(message("reject.type.not.exists", "Definition"));
		proteoCheck(root().include(
			define().type("Definition").name("Thesaurus")
				.node()));
	}

	@Test
	public void should_reject_concept_including_one_unnamed() throws Exception {
		thrown.expectMessage(message("required.name"));
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus")
				.include(define().type("Concept").node())
				.node()));
	}


	@Test
	public void should_allow_concept_containing_another_concept() throws Exception {
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus")
				.include(define().type("Concept").name("Term").node())
				.node()));
	}

	@Test
	public void should_allow_concept_containing_two_concepts() throws Exception {
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus")
				.include(define().type("Concept").name("Term").node())
				.include(define().type("Concept").name("Fact").node())
				.node()));
	}


	@Test
	public void should_reject_concept_containing_no_concept_type() throws Exception {
		thrown.expectMessage(message("reject.type.not.exists", "Definition"));
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus")
				.include(define().type("Definition").name("Term").node())
				.node()));
	}

	@Test
	public void should_allow_concept_containing_a_recursive_one() throws Exception {
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus")
				.include(define().type("Concept").name("Term")
					.include(define().type("Concept").name("Term").node()).node())
				.node()));
	}

	@Test
	public void should_reject_concept_containing_a_recursive_anonymous_one() throws Exception {
		thrown.expectMessage(message("required.name"));
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus")
					.include(define().type("Concept").name("Term")
							.include(define().type("Concept").node()).node())
					.node()));
	}

	@Test
	public void should_reject_concept_with_parameters() throws Exception {
		thrown.expectMessage("Parameter name not allowed");
		proteoCheck(root().include(
			define().type("Concept").name("Thesaurus").parameter(0, "name", "T1")
				.include(define().type("Concept").name("Term")
					.include(define().type("Concept").name("Term").node()).node())
				.node()));
	}

	private void proteoCheck(Definition definition) throws SemanticException {
		new Checker(new Proteo()).check(definition.node());
	}

}
